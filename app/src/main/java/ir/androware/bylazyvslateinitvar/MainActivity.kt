package ir.androware.bylazyvslateinitvar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ir.androware.bylazyvslateinitvar.di.AppComponent
import ir.androware.bylazyvslateinitvar.di.AppModule
import ir.androware.bylazyvslateinitvar.di.DaggerAppComponent
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var injectObject : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MyApplication).component.inject(this)
        init()
    }

    private fun init() {
        btnSimpleByLazy.clicks()
            .subscribeBy { simpleByLazy() }
        btnSimpleLateinitVar.clicks()
            .subscribeBy { simpleLateinitVar() }
        btnDependancyInjection.clicks()
            .subscribeBy { dependancyInjection() }
    }

    private fun simpleByLazy() {
        log("simpleByLazy")
        val simpleObject: String by lazy { "SIMPLE BY LAZY" }
        log("simpleByLazy , object : " + simpleObject)
        //
        val lazyX = lazy { "Fun Lazy" }
        val anotherSimpleObject: String by lazyX
        log("anotherSimpleObject , before lazyX : " + lazyX.isInitialized())
        log("anotherSimpleObject , object : " + anotherSimpleObject)
        log("anotherSimpleObject , after  lazyX : " + lazyX.isInitialized())
    }

    private fun simpleLateinitVar() {
        log("simpleLateinitVar")
        lateinit var simpleLateinitvarObject: String
        try {
            log("simpleLateinitVar , object : " + simpleLateinitvarObject)
        } catch (e: Exception) {
            log("simpleLateinitVar error : " + e.message)
        }
        simpleLateinitvarObject = "First Data"
        log("simpleLateinitVar , object : " + simpleLateinitvarObject)
        simpleLateinitvarObject = "Second Data"
        log("simpleLateinitVar , object : " + simpleLateinitvarObject)
    }

    private fun dependancyInjection() {
        log("dependancyInjectiony")
        log("dependancyInjection injectObject : "+injectObject)
    }

    private fun log(str: String) = Log.e("MainActivity", str)
}
