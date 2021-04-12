package com.example.mylab.database.hospitalDatabase.patients

object PatientContract {
    const val TABLE_NAME = "patients_table"

    object Columns{
        const val ID = "id"
        const val NAME = "name"
        const val LAST_NAME = "last_name"
        const val DIAGNOSIS = "diagnosis"
        const val AGE = "age"
        const val PHOTO = "photo"
        const val MED_FACILITIES_ID = "medFacilities_id"
    }
}