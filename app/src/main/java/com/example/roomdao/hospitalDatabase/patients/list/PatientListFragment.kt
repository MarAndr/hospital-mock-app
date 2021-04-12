package com.example.roomdao.hospitalDatabase.patients.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.patients.dialog.AddPatientsDialog
import com.example.roomdao.hospitalDatabase.patients.dialog.FilterPatientDialog
import com.example.roomdao.hospitalDatabase.patients.dialog.UpdatePatientDialog
import kotlinx.android.synthetic.main.fragment_patient_list.*
import timber.log.Timber

class PatientListFragment : Fragment(R.layout.fragment_patient_list) {


    private var patientsAdapter: PatientListAdapter? = null
    private val viewModel: HospitalViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getAllPatientsLiveData()
        observeLiveData()
        fab_patientListFragment_addPatient.setOnClickListener {
            makeDialog()
        }
        bottomAppBar_patientListFragment.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                R.id.search -> {
                    true
                }
                R.id.filter -> {
                    FilterPatientDialog ({ sortingVariant ->
                        rv_patientListFragment_patientsList.scrollToPosition(0)
                        when (sortingVariant) {
                            "AGE" -> {
                                viewModel.getPatientsSortByDescAge()
                                viewModel.patientsSortedByAge.observeForever { patientSortedListByAge ->
                                    patientsAdapter?.updatePatientsList(patientSortedListByAge)
                                }
                            }
                            "NAME" -> {
                                viewModel.getPatientsSortByDescName()
                                viewModel.patientsSortedByName.observeForever { patientSortedListByName ->
                                    patientsAdapter?.updatePatientsList(patientSortedListByName)
                                }
                            }
                            "LAST_NAME" -> {
                                viewModel.getPatientsSortByDescLastName()
                                viewModel.patientsSortedByLastName.observeForever { patientSortedListByLastName ->
                                    patientsAdapter?.updatePatientsList(patientSortedListByLastName)
                                }
                            }
                        }


                    },{
                        try {
                            viewModel.getPatientsByAgeRage(it[0].toInt(), it[1].toInt())
                        } catch (e: NumberFormatException) {
                            Timber.d(e)
                        }
                        viewModel.patientsByAgeRage.observe(viewLifecycleOwner){
                            patientsAdapter?.updatePatientsList(it)
                        }
                    })
                            .show(childFragmentManager, "filterPatientDialog")
                    true
                }
                else -> false
            }
        }

        initList()
    }

    private fun initList() {
        val divider = DividerItemDecoration(requireContext(), 1)
        patientsAdapter = PatientListAdapter({
                                             viewModel.deletePatient(it)
        },{
            UpdatePatientDialog(viewModel, it).show(childFragmentManager, "updatePatientDialog")
        })
        with(rv_patientListFragment_patientsList) {
            adapter = patientsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(divider)
            setHasFixedSize(true)
        }
    }

    private fun observeLiveData() {
        viewModel.patients.observe(viewLifecycleOwner) { patients ->
            Timber.d("patients = $patients")
            patientsAdapter?.updatePatientsList(patients)
        }
    }

    private fun makeDialog(){
        AddPatientsDialog(viewModel).show(childFragmentManager, "addPatientDialog")
    }


}