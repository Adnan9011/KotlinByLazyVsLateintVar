package ir.androware.bylazyvslateinitvar.di

import dagger.Component
import ir.androware.bylazyvslateinitvar.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(target: MainActivity)
}