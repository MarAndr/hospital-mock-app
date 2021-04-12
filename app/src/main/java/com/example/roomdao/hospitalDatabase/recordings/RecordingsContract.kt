package com.example.mylab.database.hospitalDatabase.recordings

object RecordingsContract {
    const val TABLE_NAME = "recording_table"

    object Columns{
        const val ID = "id"
        const val PATIENT_ID = "patient_id"
        const val DOCTOR_ID = "doctor_id"
        const val RECORD_TIME = "record_time"
    }
}