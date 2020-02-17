package ir.androware.bylazyvslateinitvar

import android.app.Application
import ir.androware.bylazyvslateinitvar.di.AppComponent
import ir.androware.bylazyvslateinitvar.di.AppModule
import ir.androware.bylazyvslateinitvar.di.DaggerAppComponent

class MyApplication : Application() {

    lateinit var component : AppComponent

    override fun onCreate() {
        super.onCreate()

        component = initDagger()
    }


    @Suppress("DEPRECATION")
    private fun initDagger(): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
}
