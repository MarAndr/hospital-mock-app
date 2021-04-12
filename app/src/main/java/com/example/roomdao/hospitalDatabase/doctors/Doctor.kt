package com.example.roomdao.hospitalDatabase.doctors

import androidx.room.*
import com.example.mylab.database.hospitalDatabase.doctors.SpecializationConverter
import com.example.roomdao.hospitalDatabase.Week
import com.example.roomdao.hospitalDatabase.offices.Office
import com.example.roomdao.hospitalDatabase.offices.OfficesContract

@Entity(tableName = DoctorsContract.TABLE_NAME,
        foreignKeys = [ForeignKey(entity = Office::class,
                parentColumns = [OfficesContract.Columns.NUMBER],
                childColumns = [DoctorsContract.Columns.OFFICE_NUMBER])],
        indices = [Index(DoctorsContract.Columns.NAME)]
)
data class Doctor(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = DoctorsContract.Columns.ID)
        val id: Long,
        @ColumnInfo(name = DoctorsContract.Columns.WORK_DAYS)
        val workDays: List<Week>,
        @ColumnInfo(name = DoctorsContract.Columns.WORK_HOURS)
        val workHours: ReceptionHour,
        @ColumnInfo(name = DoctorsContract.Columns.NAME)
        val name: String,
        @ColumnInfo(name = DoctorsContract.Columns.LAST_NAME)
        val lastName: String,
        @ColumnInfo(name = DoctorsContract.Columns.AGE)
        val age: Int,
        @ColumnInfo(name = DoctorsContract.Columns.OFFICE_NUMBER)
        val officeNumber: Int,
        @ColumnInfo(name = DoctorsContract.Columns.SPECIALIZATION)
        val specialization: DoctorSpecialization,
        @ColumnInfo(name = DoctorsContract.Columns.PHOTO)
        val photo: String
)
