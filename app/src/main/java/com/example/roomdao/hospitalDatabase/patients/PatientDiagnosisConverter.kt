package com.example.roomdao.hospitalDatabase.patients

import androidx.room.TypeConverter

class PatientDiagnosisConverter {

    @TypeConverter
    fun convertStringToDiagnosis(diagnosisString: String): PatientDiagnosis{
        return PatientDiagnosis.valueOf(diagnosisString)
    }

    @TypeConverter
    fun convertDiagnosisToString(diagnosis: PatientDiagnosis): String{
        return diagnosis.name
    }
}