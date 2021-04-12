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
import com.example.roomdao.hospitalDatabase.patients.Patient
import com.example.roomdao.hospitalDatabase.patients.PatientDiagnosis
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_add_patient.view.*

class AddPatientsDialog(private val viewModel: HospitalViewModel) : DialogFragment() {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_patient, null)
        val diagnoses = PatientDiagnosis.values().map { it.toString() }

        val diagnosisAdapter = ArrayAdapter(requireContext(), R.layout.list_item, diagnoses)
        mView.autoCompTextView_addPatientDialog_diagnosis.setText(diagnosisAdapter.getItem(0).toString(), false)
        mView.autoCompTextView_addPatientDialog_diagnosis.setAdapter(diagnosisAdapter)

        val dialog = MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogCustom)
                .setView(mView)
                .setPositiveButton("create patient") { _, _ ->

                    val name = mView.et_dialogAddPatient_name.text.toString()
                    val lastName = mView.et_dialogAddPatient_lastName.text.toString()
                    val diagnosis = mView.autoCompTextView_addPatientDialog_diagnosis.text.toString()
                    val age = mView.et_dialogAddPatient_age.text.toString()

                    val newPatient = listOf(Patient(
                            id = 0,
                            name = name,
                            lastName = lastName,
                            age = age.toIntOrNull() ?: 0,
                            diagnosis = PatientDiagnosis.valueOf(diagnosis),
                            photo = ""
                    ))
                    viewModel.addPatients(newPatient)

                }
                .setNegativeButton("cancel") { _, _ -> }
                .create()

        return dialog
    }
}