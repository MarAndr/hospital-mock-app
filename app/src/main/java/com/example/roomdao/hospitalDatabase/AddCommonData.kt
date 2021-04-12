package com.example.roomdao.hospitalDatabase

import androidx.lifecycle.LiveData
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.medFac.MedFacility
import com.example.roomdao.hospitalDatabase.offices.Office
import com.example.roomdao.hospitalDatabase.patients.Patient
import kotlinx.coroutines.flow.Flow

data class AddCommonData(val doctors: List<Doctor>? = null, val patients: List<Patient>? = null, val medFac: List<MedFacility>? = null)
