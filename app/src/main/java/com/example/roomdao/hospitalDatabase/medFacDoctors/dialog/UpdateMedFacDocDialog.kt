package com.example.roomdao.hospitalDatabase.medFacDoctors.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.medFac.MedFacility
import com.example.roomdao.hospitalDatabase.medFacDoctors.MedFacDoctor
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_add_medfacdoc.view.*
import timber.log.Timber

class UpdateMedFacDocDialog(val medFacDocId: Long, val viewModel: HospitalViewModel, private val medFacList: List<MedFacility>, private val doctorList: List<Doctor>): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_medfacdoc, null)
        val doctorsList = doctorList.map { "${it.id}: ${it.lastName}" }
        val medFacList = medFacList.map { "${it.id}: ${it.title}" }

        val medFacAdapter = ArrayAdapter(requireContext(), R.layout.list_item, medFacList)
        val doctorAdapter = ArrayAdapter(requireContext(), R.layout.list_item, doctorsList)

        mView.autoCompTextView_dialogAddMedFacDoc_medFacTitle.setAdapter(medFacAdapter)
        mView.autoCompTextView_dialogAddMedFacDoc_doctorLastName.setAdapter(doctorAdapter)


        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogCustom)
                .setTitle("Update medFacDoc")
                .setView(mView)
                .setPositiveButton("add"){_,_ ->
                    val medFacId = mView.autoCompTextView_dialogAddMedFacDoc_medFacTitle.text.toString().substringBefore(':').toLongOrNull()?:0
                    val doctorId = mView.autoCompTextView_dialogAddMedFacDoc_doctorLastName.text.toString().substringBefore(':').toLongOrNull()?:0
                    viewModel.updateMedFacDoc(
                            medFacDocId = medFacDocId,
                            medFacId = medFacId,
                            doctorId = doctorId
                    )
                }
                .setNegativeButton("cancel"){_,_ ->}
                .create()
    }
}