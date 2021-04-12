package com.example.roomdao

import android.app.Application
import com.example.roomdao.hospitalDatabase.HospitalDatabase
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class ControlApp: Application() {
    override fun onCreate() {
        super.onCreate()
        HospitalDatabase.init(this)
        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}