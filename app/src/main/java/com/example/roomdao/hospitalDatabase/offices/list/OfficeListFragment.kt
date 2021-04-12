package com.example.roomdao.hospitalDatabase.offices.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import kotlinx.android.synthetic.main.fragment_office_list.*

class OfficeListFragment: Fragment(R.layout.fragment_office_list){

    private val viewModel by viewModels<HospitalViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getAllOffices()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.offices.observe(viewLifecycleOwner){
            tv_officeListFragment.text = it.joinToString()
        }
    }


}