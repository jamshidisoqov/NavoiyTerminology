package io.jamshid.navoiyterminology.application

import android.app.Application
import io.jamshid.navoiyterminology.di.dataModule
import io.jamshid.navoiyterminology.di.useCaseModule
import io.jamshid.navoiyterminology.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val modules = listOf(viewModelModule, dataModule, useCaseModule)
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            koin.loadModules(modules)
        }
    }
}