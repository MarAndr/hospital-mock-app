package com.example.roomdao.hospitalDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mylab.database.hospitalDatabase.doctors.SpecializationConverter
import com.example.roomdao.hospitalDatabase.AppHospitalDatabase.Companion.DB_VERSION
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.doctors.DoctorDao
import com.example.roomdao.hospitalDatabase.patients.Patient
import com.example.roomdao.hospitalDatabase.patients.PatientDao
import com.example.roomdao.hospitalDatabase.recordings.Recording
import com.example.roomdao.hospitalDatabase.recordings.RecordingDao
import com.example.roomdao.hospitalDatabase.doctors.ReceptionHoursConverter
import com.example.roomdao.hospitalDatabase.medFac.MedFacDao
import com.example.roomdao.hospitalDatabase.medFac.MedFacility
import com.example.roomdao.hospitalDatabase.medFacDoctors.MedFacDoctor
import com.example.roomdao.hospitalDatabase.medFacDoctors.MedFacDoctorDao
import com.example.roomdao.hospitalDatabase.offices.Office
import com.example.roomdao.hospitalDatabase.offices.OfficeDao
import com.example.roomdao.hospitalDatabase.patients.PatientDiagnosisConverter
import com.example.roomdao.hospitalDatabase.workSchedules.WorkSchedule
import com.example.roomdao.hospitalDatabase.workSchedules.WorkSchedulesDao

@Database(entities = [Doctor::class, Patient::class, Recording::class, MedFacility::class, WorkSchedule::class, Office::class, MedFacDoctor::class], version = DB_VERSION)
@TypeConverters(WeekConverter::class, ReceptionHoursConverter::class, PatientDiagnosisConverter::class, SpecializationConverter::class)
abstract class AppHospitalDatabase: RoomDatabase() {

    companion object{
        const val DB_VERSION = 2
    }

    abstract fun doctorDao(): DoctorDao
    abstract fun patientDao(): PatientDao
    abstract fun recordingDao(): RecordingDao
    abstract fun medFacilityDao(): MedFacDao
    abstract fun workSchedulesDao(): WorkSchedulesDao
    abstract fun officeDao(): OfficeDao
    abstract fun medFacDocDao(): MedFacDoctorDao
}