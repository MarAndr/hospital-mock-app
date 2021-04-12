package com.example.roomdao.hospitalDatabase.patients

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mylab.database.hospitalDatabase.patients.PatientContract

@Entity(tableName = PatientContract.TABLE_NAME)
@TypeConverters(PatientDiagnosisConverter::class)
data class Patient(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PatientContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = PatientContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = PatientContract.Columns.LAST_NAME)
    val lastName: String,
    @ColumnInfo(name = PatientContract.Columns.AGE)
    val age: Int,
    @ColumnInfo(name = PatientContract.Columns.PHOTO)
    val photo: String,
    @ColumnInfo(name = PatientContract.Columns.DIAGNOSIS)
    val diagnosis: PatientDiagnosis
)
