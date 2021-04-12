package com.example.roomdao.hospitalDatabase.doctors.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.doctors.DoctorOption
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import kotlinx.android.synthetic.main.dialog_filter_doctor.view.*
import kotlinx.android.synthetic.main.dialog_filter_patient.view.*
import timber.log.Timber

class FilterDoctorDialog(val onFilterButtonClick: (String) -> Unit, val onFilterButtonClickAgeRange: (List<String>) -> Unit): DialogFragment() {

    lateinit var mView: View
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_filter_doctor, null)
        mView.switch_filterDoctorDialog_ageSelection.setOnCheckedChangeListener { _, isChecked ->
            mView.relativeLayout_filterDoctorDialog_ageSelection?.isVisible = isChecked
        }
        mView.switch_filterDoctorDialog_sorting.setOnCheckedChangeListener { _, isChecked ->
            mView.textInputLayout_filterDoctorDialog_sorting?.isVisible = isChecked
        }
        val DoctorOptions = DoctorOption.values().map { it.toString() }
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.list_item, DoctorOptions)
        mView.autoComplete_filterDoctorDialog_sorting.setAdapter(arrayAdapter)

        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogCustom)
                .setView(mView)
                .setPositiveButton("filter"){_,_ ->
                    if (isAgeFieldsValid()){
                        val minAge = mView.et_filterDoctorDialog_minAge.text.toString()
                        val maxAge = mView.et_filterDoctorDialog_maxAge.text.toString()
                        onFilterButtonClickAgeRange(listOf(minAge, maxAge))
                    } else if (isCheckboxAgeRangeChecked() && !isAgeFieldsValid()){
                        mView.textInputLayout_filterDoctorDialog_maxAge.error = "Fill up the maxAge"
                        mView.textInputLayout_filterDoctorDialog_minAge.error = "Fill up the maxAge"
                    }
                    onFilterButtonClick(mView.autoComplete_filterDoctorDialog_sorting.text.toString())

                }
                .setNegativeButton("Cancel"){_,_ ->}
                .create()
    }

    private fun isAgeFieldsValid() =
            mView.et_filterDoctorDialog_minAge.text.toString().trim().isNotEmpty()
                    && mView.et_filterDoctorDialog_maxAge.text.toString().trim().isNotEmpty()
    private fun isCheckboxAgeRangeChecked() = mView.switch_filterDoctorDialog_ageSelection.isChecked
}