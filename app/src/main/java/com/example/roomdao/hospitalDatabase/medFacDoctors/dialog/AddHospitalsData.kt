package com.example.roomdao.hospitalDatabase.medFacDoctors.dialog

import androidx.lifecycle.LiveData
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.medFac.MedFacility
import com.example.roomdao.hospitalDatabase.offices.Office
import com.example.roomdao.hospitalDatabase.patients.Patient
import kotlinx.coroutines.flow.Flow

data class AddHospitalsData(val medFacilities: List<MedFacility>)
