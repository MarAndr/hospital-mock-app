package com.example.roomdao.hospitalDatabase.medFacDoctors

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.doctors.DoctorsContract
import com.example.roomdao.hospitalDatabase.medFac.MedFacContract
import com.example.roomdao.hospitalDatabase.medFac.MedFacility

@Entity(tableName = MedFacDoctorsContract.TABLE_NAME, foreignKeys = [ForeignKey(entity = MedFacility::class, parentColumns = [MedFacContract.Columns.ID], childColumns = [MedFacDoctorsContract.Columns.MEDFAC_ID]),
    ForeignKey(entity = Doctor::class, parentColumns = [DoctorsContract.Columns.ID], childColumns = [MedFacDoctorsContract.Columns.DOCTOR_ID])])
data class MedFacDoctor(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = MedFacDoctorsContract.Columns.ID)
        val id: Long,
        @ColumnInfo(name = MedFacDoctorsContract.Columns.MEDFAC_ID)
        val medFacId: Long,
        @ColumnInfo(name = MedFacDoctorsContract.Columns.DOCTOR_ID)
        val doctorId: Long
)