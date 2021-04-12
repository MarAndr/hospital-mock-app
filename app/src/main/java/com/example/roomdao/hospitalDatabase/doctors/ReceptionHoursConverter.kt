package com.example.roomdao.hospitalDatabase.doctors

import androidx.room.TypeConverter

class ReceptionHoursConverter {
    @TypeConverter
    fun convertFromReceptionHourToString(receptionHour: ReceptionHour): String = receptionHour.tag.orEmpty()

    @TypeConverter
    fun convertFromStringToReceptionHour(receptionHourTag: String): ReceptionHour {
        return ReceptionHour.values().first { it.tag == receptionHourTag }
    }
}