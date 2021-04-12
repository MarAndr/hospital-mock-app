package com.example.roomdao.hospitalDatabase.medFac.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CheckBox
import androidx.fragment.app.DialogFragment
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.hospitalDatabase.Week
import com.example.roomdao.hospitalDatabase.medFac.MedFacility
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_add_medfac.view.*

class AddMedFacDialog(val viewModel: HospitalViewModel) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_medfac, null)
        val checkboxDayList = getCheckboxDaysList(requireContext())
        checkboxDayList.forEach {
            mView.container_medFac.addView(it)
        }
        val dialog = MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogCustom)
                .setView(mView)
                .setPositiveButton("create facility") { _, _ ->
                    val workDaysChecked = mutableListOf<String>()
                    checkboxDayList.map {
                        if (it.isChecked) {
                            workDaysChecked.add(it.text.toString())
                        }
                    }
                    val workDays = workDaysChecked.map { Week.valueOf(it) }
                    val title = mView.et_dialogAddMedFac_title.text.toString()
                    val address = mView.et_dialogAddMedFac_address.text.toString()

                    val newMedFac = listOf<MedFacility>(MedFacility(
                            id = 0,
                            title = title,
                            address = address,
                            workDays = workDays,
                            photo = ""
                    ))
                    viewModel.insertMedFacilities(newMedFac)

                }
                .setNegativeButton("cancel") { _, _ -> }
                .create()

        return dialog
    }

    private fun createCheckbox(context: Context, checkboxText: String) = CheckBox(context).apply {
        text = checkboxText
    }

    private fun getCheckboxDaysList(context: Context): List<CheckBox> {
        return Week.values().map {
            createCheckbox(context, it.toString())
        }
    }
}