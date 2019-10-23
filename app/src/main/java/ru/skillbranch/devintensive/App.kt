package ru.skillbranch.devintensive

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import ru.skillbranch.devintensive.repositories.PreferenceRepository

/** Created by qq_3000 on 22.10.2019. */
class App : Application() {
    companion object {
        private var instance: App? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        PreferenceRepository.getAppTheme().also {
            AppCompatDelegate.setDefaultNightMode(it)
        }
    }
}