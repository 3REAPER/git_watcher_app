package ru.pervukhin.gitwatcher

import android.app.Application
import ru.pervukhin.gitwatcher.di.AppComponent
import ru.pervukhin.gitwatcher.di.DaggerAppComponent

class App: Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}