package com.example.roomdao.hospitalDatabase.medFacDoctors.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.medFac.MedFacility
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_search_doctor_by_name.view.*
import kotlinx.android.synthetic.main.dialog_search_hospital_by_id.view.*

class SearchDoctorByNameDialog(val doctors: List<Doctor>, val onDoctorSearchButtonClick: (String) -> Unit): DialogFragment() {

    lateinit var mView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_search_doctor_by_name, null)
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogCustom)
                .setView(mView)
                .setPositiveButton("search"){_,_ ->
                    onDoctorSearchButtonClick(mView.et_searchDoctorByNameDialog_doctorLastName.text.toString())
                }
                .setNegativeButton("cancel"){_,_ -> }
                .create()
    }

}