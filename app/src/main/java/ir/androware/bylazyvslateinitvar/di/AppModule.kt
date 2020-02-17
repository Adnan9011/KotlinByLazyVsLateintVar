package ir.androware.bylazyvslateinitvar.di

import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideObject() : String {
        val str : String by lazy { "ObjectStr" }
        return str
    }
}