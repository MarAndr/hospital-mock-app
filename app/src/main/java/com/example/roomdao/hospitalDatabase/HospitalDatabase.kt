package com.example.roomdao.hospitalDatabase

import android.content.Context
import androidx.room.Room

object HospitalDatabase {

    private const val DB_NAME = "hospital_database"
    lateinit var instance: AppHospitalDatabase

    fun init(context: Context){
        instance = Room.databaseBuilder(
            context,
            AppHospitalDatabase::class.java,
            DB_NAME
        ).build()
    }


}