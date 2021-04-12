package com.example.roomdao.hospitalDatabase.recordings.list

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.recordings.dialog.AddRecordingDialog
import com.example.roomdao.hospitalDatabase.recordings.dialog.UpdateRecordingDialog
import kotlinx.android.synthetic.main.fragment_recording_list.*
import timber.log.Timber

class RecordingListFragment : Fragment(R.layout.fragment_recording_list) {


    private var recordingAdapter: RecordingListAdapter? = null
    private val viewModel: HospitalViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getAllRecordings()
        observeLiveData()
        fab_recordingListFragment_addRecording.setOnClickListener {
            viewModel.createAddRecordingDialog()
            viewModel.addRecordingData.observeForever{
                AddRecordingDialog(viewModel, it.doctors, it.patients).show(childFragmentManager, "addRecordingDialog")
            }
        }
        initList()

       bottomAppBar_recordingListFragment.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    val searchItem = bottomAppBar_recordingListFragment.menu.findItem(R.id.search)
                    (searchItem.actionView as SearchView).setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            newText?.let {
                            viewModel.searchDoctorByName(newText)
                            }
                            return true
                        }
                    })
                    true
                }
                R.id.filter -> {
                    Timber.d("more")
                    true
                }
                else -> false
            }
        }
    }

    private fun initList() {
        val divider = DividerItemDecoration(requireContext(), 1)
        recordingAdapter = RecordingListAdapter({ recordingId ->
            viewModel.deleteRecording(recordingId)
        },{ recordingId ->
            viewModel.createAddRecordingDialog()
            viewModel.addRecordingData.observeForever{
                UpdateRecordingDialog(viewModel, it.doctors, it.patients, recordingId).show(childFragmentManager, "addRecordingDialog")
            }
        }, viewModel = viewModel)
        with(rv_recordingListFragment_recordingList) {
            adapter = recordingAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(divider)
            setHasFixedSize(true)
        }
    }

    private fun observeLiveData() {
        viewModel.recordings.observe(viewLifecycleOwner) { recordings ->
            recordingAdapter?.updateRecordingList(recordings)
        }
    }
}