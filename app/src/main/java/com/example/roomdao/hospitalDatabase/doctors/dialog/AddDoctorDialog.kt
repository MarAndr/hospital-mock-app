package com.example.roomdao.hospitalDatabase.doctors.dialog

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.hospitalDatabase.Week

import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.doctors.DoctorSpecialization
import com.example.roomdao.hospitalDatabase.doctors.ReceptionHour
import com.example.roomdao.hospitalDatabase.offices.Office
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_add_doctor.view.*

class AddDoctorDialog(val viewModel: HospitalViewModel, val offices: List<Office>): DialogFragment() {
    
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel.getAllOffices()
        val mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_doctor, null)
        val specializations = DoctorSpecialization.values().map { it.name }
        val workHours = ReceptionHour.values().map { it.name }
        val officeNumbers = offices.map { it.number }
        val workDaysChecked = mutableListOf<String>()

        val specializationAdapter = ArrayAdapter(requireContext(), R.layout.list_item, specializations)
        mView.autoCompTextView_addDoctorDialog_specialization.setText(specializationAdapter.getItem(0).toString(), false)
        mView.autoCompTextView_addDoctorDialog_specialization.setAdapter(specializationAdapter)

        val officeNumberAdapter = ArrayAdapter(requireContext(), R.layout.list_item, officeNumbers)
        mView.autoCompTextView_addDoctorDialog_officeNumber.setText(officeNumberAdapter.getItem(0).toString(), false)
        mView.autoCompTextView_addDoctorDialog_officeNumber.setAdapter(officeNumberAdapter)

        val workHoursAdapter = ArrayAdapter(requireContext(), R.layout.list_item, workHours)
        mView.autoCompTextView_addDoctorDialog_workHours.setText(workHoursAdapter.getItem(0).toString(), false)
        mView.autoCompTextView_addDoctorDialog_workHours.setAdapter(workHoursAdapter)

        val checkboxListDays = Week.values().map { it.name }.map { day ->
            createCheckbox(requireContext(), day)
        }
        checkboxListDays.forEach {
            mView.container.addView(it)
        }

        val dialog = MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogCustom)
                .setView(mView)
                .setPositiveButton("create doctor"){_,_->
                    checkboxListDays.forEach { checkBox ->
                        if (checkBox.isChecked){
                            workDaysChecked.add(checkBox.text.toString())
                        }
                    }
                    val workDays = workDaysChecked.map {
                        Week.valueOf(it)
                    }
                    val name = mView.et_dialogAddDoctor_name.text.toString()
                    val lastName = mView.et_dialogAddDoctor_lastName.text.toString()
                    val workHour = mView.autoCompTextView_addDoctorDialog_workHours.text.toString()
                    val specialization = mView.autoCompTextView_addDoctorDialog_specialization.text.toString()
                    val age = mView.et_dialogAddDoctor_age.text.toString()
                    val officeNumber: String = mView.autoCompTextView_addDoctorDialog_officeNumber.text.toString()

                    val newDoctor = listOf(Doctor(
                            id = 0,
                            workDays = workDays,
                            workHours = ReceptionHour.valueOf(workHour),
                            name = name,
                            lastName = lastName,
                            age = age.toIntOrNull()?:0,
                            officeNumber = officeNumber.toIntOrNull() ?:0,
                            specialization = DoctorSpecialization.valueOf(specialization),
                            photo = ""
                    ))
                    viewModel.addDoctors(newDoctor)

                }
                .setNegativeButton("cancel"){_,_-> }
                .create()

        return dialog
    }

    private fun createCheckbox(context: Context, checkboxText: String) = CheckBox(context).apply {
        text = checkboxText
    }
}