package com.example.roomdao.hospitalDatabase.recordings

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.doctors.DoctorsContract
import com.example.roomdao.hospitalDatabase.patients.Patient
import com.example.mylab.database.hospitalDatabase.patients.PatientContract
import com.example.mylab.database.hospitalDatabase.recordings.RecordingsContract

@Entity(
    tableName = RecordingsContract.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Doctor::class,
        parentColumns = [DoctorsContract.Columns.ID],
        childColumns = [RecordingsContract.Columns.DOCTOR_ID]
    ), ForeignKey(
        entity = Patient::class,
        parentColumns = [PatientContract.Columns.ID],
        childColumns = [RecordingsContract.Columns.PATIENT_ID]
    )]
)
data class Recording(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = RecordingsContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = RecordingsContract.Columns.PATIENT_ID)
    val patient_id: Long,
    @ColumnInfo(name = RecordingsContract.Columns.DOCTOR_ID)
    val doctor_id: Long,
    @ColumnInfo(name = RecordingsContract.Columns.RECORD_TIME)
    val record_time: String
)