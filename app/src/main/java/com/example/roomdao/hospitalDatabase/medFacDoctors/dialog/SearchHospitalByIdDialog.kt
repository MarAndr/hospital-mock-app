package com.example.roomdao.hospitalDatabase.medFacDoctors.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.medFac.MedFacility
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_search_hospital_by_id.view.*

class SearchHospitalByIdDialog(val medFacilities: List<MedFacility>, val onHospitalButtonClick: (Long) -> Unit): DialogFragment() {

    lateinit var mView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_search_hospital_by_id, null)
        val medFacilities = medFacilities.map { "${it.id} - ${it.title}" }
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, medFacilities)
        mView.autoCompleteText_searchHospitalByIdDialog.setAdapter(adapter)
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogCustom)
                .setView(mView)
                .setPositiveButton("search"){_,_ ->
                    val medFacId = getMedFacIdFromString(mView.autoCompleteText_searchHospitalByIdDialog.text.toString())
                    onHospitalButtonClick(medFacId)
                }
                .setNegativeButton("cancel"){_,_ -> }
                .create()
    }

    private fun getMedFacIdFromString(str: String): Long{
        return str.substringBefore('-').trim().toLong()
    }
}