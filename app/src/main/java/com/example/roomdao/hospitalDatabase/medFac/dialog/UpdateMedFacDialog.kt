package com.example.roomdao.hospitalDatabase.medFac.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.hospitalDatabase.Week
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_add_medfac.view.*
import timber.log.Timber

class UpdateMedFacDialog(val viewModel: HospitalViewModel, val medFacId: Long): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_medfac, null)
        val dialog = MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogCustom)
                .setView(mView)
                .setPositiveButton("update facility"){_,_->

//                    val weekList = mutableListOf<String>()
//                    (mView as ViewGroup).children
//                            .mapNotNull { it as? CheckBox }
//                            .forEach { checkBox ->
//                                when(checkBox.isChecked){
//                                    true -> {
//                                        weekList.add("test")
//                                        Timber.d("checkBox.isChecked = ${checkBox.isChecked}")
//                                    }
//                                    false -> Unit
//                                }
//                            }
//
//                        Timber.d("weekList = ${weekList.joinToString()}")

                    val newTitle = mView.et_dialogAddMedFac_title.text.toString()
                    val newAddress = mView.et_dialogAddMedFac_address.text.toString()
                    Timber.d("newTitle = $newTitle, newAddress = $newAddress")
                    viewModel.updateMedFac(
                            medFacId = medFacId,
                            title = newTitle,
                            address = newAddress,
                            workDays = listOf(Week.MONDAY, Week.TUESDAY, Week.SUNDAY)
                    )

                }
                .setNegativeButton("cancel"){_,_-> }
                .create()

        return dialog
    }
}