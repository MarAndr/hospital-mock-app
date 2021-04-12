package com.example.roomdao.hospitalDatabase.medFacDoctors.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.hospitalDatabase.medFacDoctors.MedFacDoctor
import com.example.roomdao.hospitalDatabase.medFacDoctors.dialog.AddMedFacDocDialog
import com.example.roomdao.hospitalDatabase.medFacDoctors.dialog.SearchDoctorByNameDialog
import com.example.roomdao.hospitalDatabase.medFacDoctors.dialog.SearchHospitalByIdDialog
import com.example.roomdao.hospitalDatabase.medFacDoctors.dialog.UpdateMedFacDocDialog
import kotlinx.android.synthetic.main.fragment_medfacdoctor_list.*
import timber.log.Timber

class MedFacDocListFragment : Fragment(R.layout.fragment_medfacdoctor_list) {

    private var medFacAdapter: MedFacDocAdapterFragment? = null
    private val viewModel by viewModels<HospitalViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getAllMedFacDoc()
        observeLiveData()
        fab_medFacDoctor_addItem.setOnClickListener {
            viewModel.createAddMedFacDocDialog()
            viewModel.addCommonData.observeForever {
                AddMedFacDocDialog(viewModel, it.medFac!!, it.doctors!!).show(childFragmentManager, "addMedFacDialog")
            }
        }

        bottomAppBar_medFacDocListFragment.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                R.id.searchDoctor_medFacDoc -> {
                    viewModel.createAddMedFacDocDialog()
                    viewModel.addCommonData.observeForever {
                    SearchDoctorByNameDialog(it.doctors?: emptyList()){ doctorLastName ->
                        viewModel.getDoctorsByLastName(doctorLastName){ doctorList ->
                            doctorList.map { doctor ->
                                viewModel.getMedFacDocByDoctorId(doctor.id)
                                viewModel.medFacDocByDoctorId.observe(viewLifecycleOwner){medFacDoctors ->
                                    medFacAdapter?.update(medFacDoctors)
                                    medFacAdapter?.notifyDataSetChanged()
                                }
                            }
                        }
                    }.show(childFragmentManager, "searchDoctorByNameDialog")
                    }
                    true
                }
                R.id.searchHospital_medFacDoc -> {
                    viewModel.createHospitalData()
                    viewModel.hospitalsData.observeForever {
                        SearchHospitalByIdDialog(medFacilities = it.medFacilities){ medFacId ->
                            viewModel.getMedFacDocByMedFacId(medFacId)
                            viewModel.medFacDocByMedFacId.observe(viewLifecycleOwner){medFacDocListByMedFacId ->
                                medFacAdapter?.update(medFacDocListByMedFacId)
                                medFacAdapter?.notifyDataSetChanged()
                            }
                        }.show(childFragmentManager, "searchHospitalByIdDialog")
                    }

                    true
                }
                else -> false
            }
        }
        initList()
    }

    private fun observeLiveData() {
        viewModel.medFacDocs.observe(viewLifecycleOwner) {
            medFacAdapter?.update(it)
            medFacAdapter?.notifyDataSetChanged()
        }
    }

    private fun initList() {
        medFacAdapter = MedFacDocAdapterFragment(
                viewModel = viewModel,
                onDeleteButtonClick = { medFacDocId -> viewModel.deleteMedFacDoc(medFacDocId)},
                onUpdateButtonClick = { medFacDocId ->
                    viewModel.createAddMedFacDocDialog()
                    viewModel.addCommonData.observeForever {
                        UpdateMedFacDocDialog(
                                medFacDocId = medFacDocId,
                                viewModel = viewModel,
                                medFacList = it.medFac!!,
                                doctorList = it.doctors!!
                        ).show(childFragmentManager, "updateMedFacDocDialog")
                    }
                }
)
        with(rv_medFacDocListFragment) {
            adapter = medFacAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        medFacAdapter = null
    }
}