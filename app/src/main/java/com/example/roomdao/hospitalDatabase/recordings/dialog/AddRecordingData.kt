package com.example.roomdao.hospitalDatabase.recordings.dialog

import androidx.lifecycle.LiveData
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.offices.Office
import com.example.roomdao.hospitalDatabase.patients.Patient
import kotlinx.coroutines.flow.Flow

data class AddRecordingData(val doctors: List<Doctor>, val patients: List<Patient>)
