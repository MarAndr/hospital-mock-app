package com.example.roomdao.hospitalDatabase.recordings.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.patients.Patient
import com.example.roomdao.hospitalDatabase.recordings.Recording
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_add_recording.view.*
import timber.log.Timber

///^[0-9]{2}[:]{1}[0-9]{2}$/
class AddRecordingDialog(
        val viewModel: HospitalViewModel,
        val doctors: List<Doctor>,
        val patients: List<Patient>
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_recording, null)
        val doctorsList = doctors.map { "${it.id}, ${it.lastName}" }
        val patientsList = patients.map { "${it.id}, ${it.lastName}" }

        val patientsAdapter = ArrayAdapter(requireContext(), R.layout.list_item, patientsList)
        mView.autoCompTextView_addRecordingDialog_patientName.setAdapter(patientsAdapter)
        val doctorsAdapter = ArrayAdapter(requireContext(), R.layout.list_item, doctorsList)
        mView.autoCompTextView_addRecordingDialog_doctorName.setAdapter(doctorsAdapter)


        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogCustom)
            .setTitle("Add recording's data")
            .setView(mView)
            .setPositiveButton("add recording") { _, _ ->
                val recordTime = mView.et_dialogAddRecording_recordTime.text.toString()
                val isRecordTimeValid = Regex("/^[0-9]{2}[:]{1}[0-9]{2}\$/").matches(recordTime)
                Timber.d("recordTime = $recordTime, isRecordTimeValid = $isRecordTimeValid")
                val doctorId = mView.autoCompTextView_addRecordingDialog_doctorName.text.toString().substringBefore(',').toLong()
                val patientId = mView.autoCompTextView_addRecordingDialog_patientName.text.toString().substringBefore(',').toLong()
                viewModel.insertRecordings(
                    listOf(
                        Recording(
                            id = 0,
                            patient_id = patientId,
                            doctor_id = doctorId,
                            record_time = if (isRecordTimeValid) recordTime else "00:00"
                        )
                    )
                )
            }
            .setNegativeButton("cancel") { _, _ -> }
            .create()
    }
}