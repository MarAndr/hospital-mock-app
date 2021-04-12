package com.example.roomdao.hospitalDatabase.doctors.dialog

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.hospitalDatabase.doctors.DoctorSpecialization
import com.example.roomdao.hospitalDatabase.doctors.ReceptionHour
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_add_doctor.view.*

class UpdateDoctorDialog(private val viewModel: HospitalViewModel, private val doctorId: Long) : DialogFragment() {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_doctor, null)
        val specializations = DoctorSpecialization.values().map { it.name }
        val workHours = ReceptionHour.values().map { it.name }

        val specializationAdapter = ArrayAdapter(requireContext(), R.layout.list_item, specializations)
        mView.autoCompTextView_addDoctorDialog_specialization.setText(specializationAdapter.getItem(0).toString(), false)
        mView.autoCompTextView_addDoctorDialog_specialization.setAdapter(specializationAdapter)

        val workHoursAdapter = ArrayAdapter(requireContext(), R.layout.list_item, workHours)
        mView.autoCompTextView_addDoctorDialog_workHours.setText(workHoursAdapter.getItem(0).toString(), false)
        mView.autoCompTextView_addDoctorDialog_workHours.setAdapter(workHoursAdapter)

        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogCustom)
                .setView(mView)
                .setPositiveButton("update") { _, _ ->
                    val newName = mView.et_dialogAddDoctor_name.text.toString()
                    val newLastName = mView.et_dialogAddDoctor_lastName.text.toString()
                    val newWorkHour = mView.autoCompTextView_addDoctorDialog_workHours.text.toString()
                    val newSpecialization = mView.autoCompTextView_addDoctorDialog_specialization.text.toString()
                    val newAge = mView.et_dialogAddDoctor_age.text.toString()
//                    val newOfficeNumber = mView.et_dialogAddDoctor_officeNumber.text.toString()
                    viewModel.updateDoctor(
                            doctorId = doctorId,
                            name = newName,
                            lastName = newLastName,
                            age = newAge.toIntOrNull() ?: 0,
                            specialization = DoctorSpecialization.valueOf(newSpecialization),
                            workHours = ReceptionHour.valueOf(newWorkHour),
//                            office = newOfficeNumber.toIntOrNull()?:0
                            office = 0
                    )
                }
                .setNegativeButton("cancel") { _, _ -> }
                .create()
    }
}