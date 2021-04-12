package com.example.roomdao.hospitalDatabase

import androidx.room.TypeConverter

class WeekConverter {

    @TypeConverter
    fun convertFromWeekToString(week: List<Week>): String{
        return week.joinToString(",") { it.name }
    }

    @TypeConverter
    fun convertFromStringToWeek(weekNames: String): List<Week>{
        val list = weekNames.split(",")
        return if(weekNames.isEmpty()) emptyList() else list.map { Week.valueOf(it) }
    }
}