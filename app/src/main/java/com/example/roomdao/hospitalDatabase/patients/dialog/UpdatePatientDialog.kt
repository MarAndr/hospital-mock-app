package com.example.roomdao.hospitalDatabase.patients.dialog

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.hospitalDatabase.patients.PatientDiagnosis
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_add_patient.view.*

class UpdatePatientDialog(private val viewModel: HospitalViewModel, private val patientId: Long) : DialogFragment() {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_patient, null)
        val diagnoses = PatientDiagnosis.values().map { it.toString() }

        val diagnosisAdapter = ArrayAdapter(requireContext(), R.layout.list_item, diagnoses)
        mView.autoCompTextView_addPatientDialog_diagnosis.setText(diagnosisAdapter.getItem(0).toString(), false)
        mView.autoCompTextView_addPatientDialog_diagnosis.setAdapter(diagnosisAdapter)

        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogCustom)
                .setView(mView)
                .setPositiveButton("update") { _, _ ->
                    val newName = mView.et_dialogAddPatient_name.text.toString()
                    val newLastName = mView.et_dialogAddPatient_lastName.text.toString()
                    val newDiagnosis = mView.autoCompTextView_addPatientDialog_diagnosis.text.toString()
                    val newAge = mView.et_dialogAddPatient_age.text.toString()
                    viewModel.updatePatient(
                            patientId = patientId,
                            patientName = newName,
                            lastName = newLastName,
                            age = newAge.toIntOrNull()?:0,
                            diagnosis = PatientDiagnosis.valueOf(newDiagnosis)
                    )
                }
                .setNegativeButton("cancel") { _, _ -> }
                .create()
    }
}