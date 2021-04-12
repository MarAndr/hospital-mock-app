package com.example.roomdao.hospitalDatabase

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.roomdao.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment: Fragment(R.layout.fragment_main) {

    private val viewModel by viewModels<HospitalViewModel>()
    private var addingItemsAmount = 10

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_mainFragment_getAllDoctors.setOnClickListener {
            findNavController().navigate(R.id.action_hospitalDatabaseFragment2_to_doctorListFragment)
        }

        btn_testDatabase_deleteAllDoctors.setOnClickListener {
            viewModel.deleteAllDoctors()
        }

        btn_mainFragment_getAllPatients.setOnClickListener {
            findNavController().navigate(R.id.action_hospitalDatabaseFragment2_to_patientListFragment)
        }

        btn_hospitalDatabaseFragment_deleteAllPatients.setOnClickListener {
            viewModel.deleteAllPatients()
        }

        btn_mainFragment_getAllMedFac.setOnClickListener {
            findNavController().navigate(R.id.action_hospitalDatabaseFragment2_to_medFacilityListFragment)
        }

        btn_mainFragment_deleteAllMedFac.setOnClickListener {
            viewModel.deleteAllMedFac()
        }

        btn_mainFragment_addRandomDoctors.setOnClickListener {
            viewModel.addRandomDoctors(addingItemsAmount)
        }

        btn_mainFragment_addRandomPatients.setOnClickListener {
            viewModel.addRandomPatients(addingItemsAmount)
        }

        btn_mainFragment_addRandomMedFac.setOnClickListener {
            viewModel.addRandomMedFac(addingItemsAmount)
        }

        btn_mainFragment_getAllRecordings.setOnClickListener {
            findNavController().navigate(R.id.action_hospitalDatabaseFragment2_to_recordingListFragment)
        }

        btn_mainFragment_deleteAllRecordings.setOnClickListener {
            viewModel.deleteAllRecordings()
        }

        btn_mainFragment_getAllMedFacDoctor.setOnClickListener {
            findNavController().navigate(R.id.action_hospitalDatabaseFragment2_to_medFacDocListFragment)
        }

        btn_mainFragment_deleteAllMedFacDoctor.setOnClickListener {
            viewModel.deleteAllMedFacDoc()
        }

        observeLiveData()

    }

    private fun observeLiveData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.addingEvent.collect {
                when(it){
                    is AddingItemsState.DOCTOR_AD -> makeSnackbar(it.message)
                    is AddingItemsState.PATIENT_AD -> makeSnackbar(it.message)
                    is AddingItemsState.OFFICE_AD -> makeSnackbar(it.message)
                    is AddingItemsState.FACILITY_AD -> makeSnackbar(it.message)
                    is AddingItemsState.DOCTORS_DEL_ALL -> makeSnackbar(it.message)
                    is AddingItemsState.PATIENTS_DEL_ALL -> makeSnackbar(it.message)
                    is AddingItemsState.MEDFAC_DEL_ALL -> makeSnackbar(it.message)
                    is AddingItemsState.OFFICES_DEL_ALL -> makeSnackbar(it.message)
                    is AddingItemsState.RECORDINGS_DEL_ALL -> makeSnackbar(it.message)
                    is AddingItemsState.ERROR -> makeSnackbar(it.message)
                    else -> Unit
                }
            }
        }

    }

    private fun makeSnackbar(message: String){
        Snackbar.make(container, message, Snackbar.LENGTH_SHORT).show()
    }
}