package com.example.roomdao.hospitalDatabase.medFac.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.medFac.dialog.AddMedFacDialog
import com.example.roomdao.hospitalDatabase.medFac.dialog.UpdateMedFacDialog
import kotlinx.android.synthetic.main.fragment_medfac_list.*
import kotlinx.coroutines.launch
import timber.log.Timber

class MedFacilityListFragment : Fragment(R.layout.fragment_medfac_list) {


    private var medFacilityAdapter: MedFacilityListAdapter? = null
    private val viewModel: HospitalViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getAllMedFacilitiesLiveData()
        observeLiveData()
        fab_medFacListFragment_addMedFacility.setOnClickListener {
            AddMedFacDialog(viewModel).show(childFragmentManager, "addMedFacDialog")
        }
        initList()
    }

    private fun initList() {
        medFacilityAdapter = MedFacilityListAdapter({
            viewModel.deleteMedFac(it)
        },{
            UpdateMedFacDialog(viewModel, it).show(childFragmentManager, "addMedFacDialog")
        })
        with(rv_medFacListFragment_medFacilitiesList) {
            adapter = medFacilityAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observeLiveData() {
        Timber.d("observe")
        lifecycleScope.launch {
            viewModel.medFacilities.observe(viewLifecycleOwner){
                medFacilityAdapter?.updateMedFacilityList(it)
            }

        }

        }



}