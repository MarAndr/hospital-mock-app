package com.example.roomdao.hospitalDatabase.doctors.list

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.doctors.dialog.AddDoctorDialog
import com.example.roomdao.hospitalDatabase.doctors.dialog.FilterDoctorDialog
import com.example.roomdao.hospitalDatabase.doctors.dialog.UpdateDoctorDialog
import kotlinx.android.synthetic.main.fragment_doctor_list.*
import timber.log.Timber

class DoctorListFragment : Fragment(R.layout.fragment_doctor_list) {


    private var doctorsAdapter: DoctorsListAdapter? = null
    private val viewModel: HospitalViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getAllDoctorsLiveData()

        fab_doctorsListFragment_addDoctor.setOnClickListener {
            viewModel.createAddDoctorDialog()
            viewModel.addDoctorData.observeForever {
                AddDoctorDialog(viewModel, it.offices).show(childFragmentManager, "addDoctorDialog")
            }
        }

        bottomAppBar_doctorListFragment.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                R.id.search -> {
                    true
                }
                R.id.filter -> {
                    FilterDoctorDialog ({ sortingVariant ->
                        rv_doctorListFragment_doctorsList.scrollToPosition(0)
                        when (sortingVariant) {
                            "AGE" -> {
                                viewModel.getDoctorsSortByDescAge()
                                viewModel.doctorsSortedByAge.observeForever { doctorSortedListByAge ->
                                    doctorsAdapter?.updateDoctorsList(doctorSortedListByAge)
                                }
                            }
                            "NAME" -> {
                                viewModel.getDoctorsSortByDescName()
                                viewModel.doctorsSortedByName.observeForever { doctorSortedListByName ->
                                    doctorsAdapter?.updateDoctorsList(doctorSortedListByName)
                                }
                            }
                            "LAST_NAME" -> {
                                viewModel.getDoctorsSortByDescLastName()
                                viewModel.doctorsSortedByLastName.observeForever { doctorSortedListByLastName ->
                                    doctorsAdapter?.updateDoctorsList(doctorSortedListByLastName)
                                }
                            }
                        }


                    },{
                        viewModel.getDoctorsByAgeRage(it[0].toInt(), it[1].toInt())
                        viewModel.doctorsByAgeRage.observe(viewLifecycleOwner){
                            doctorsAdapter?.updateDoctorsList(it)
                        }
                    })
                            .show(childFragmentManager, "filterDoctorDialog")
                    true
                }
                else -> false
            }
        }
        initList()
        observeLiveData()
    }

    private fun initList() {
        val divider = DividerItemDecoration(requireContext(), 1)
        doctorsAdapter = DoctorsListAdapter({
            viewModel.deleteDoctor(it)
        }, {
            UpdateDoctorDialog(viewModel, it).show(childFragmentManager, "updateDoctorDialog")
        })
        with(rv_doctorListFragment_doctorsList) {
            adapter = doctorsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(divider)
            setHasFixedSize(true)
        }
    }

    private fun observeLiveData() {
        viewModel.doctors.observe(viewLifecycleOwner) { doctors ->
            doctorsAdapter?.updateDoctorsList(doctors)
        }
    }
}