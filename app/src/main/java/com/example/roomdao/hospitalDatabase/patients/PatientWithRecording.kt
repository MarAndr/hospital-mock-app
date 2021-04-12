package com.example.roomdao.hospitalDatabase.patients

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mylab.database.hospitalDatabase.patients.PatientContract
import com.example.roomdao.hospitalDatabase.recordings.Recording
import com.example.mylab.database.hospitalDatabase.recordings.RecordingsContract

data class PatientWithRecording(
        @Embedded
    val patient: Patient,
        @Relation(
        parentColumn = PatientContract.Columns.ID,
        entityColumn = RecordingsContract.Columns.PATIENT_ID
    )
    val recordings: List<Recording>
)