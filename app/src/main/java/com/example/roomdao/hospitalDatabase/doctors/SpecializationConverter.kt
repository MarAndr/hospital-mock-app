package com.example.mylab.database.hospitalDatabase.doctors

import androidx.room.TypeConverter
import com.example.roomdao.hospitalDatabase.doctors.DoctorSpecialization

class SpecializationConverter {

    @TypeConverter
    fun convertFromSpecializationToString(specialization: DoctorSpecialization): String{
        return specialization.name
    }

    @TypeConverter
    fun converterFromStringToSpecialization(specializationString: String): DoctorSpecialization {
        return DoctorSpecialization.valueOf(specializationString)
    }
}