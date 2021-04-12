package com.example.roomdao.hospitalDatabase.recordings.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.mylab.utils.inflate
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.hospitalDatabase.recordings.Recording
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_doctor.*
import kotlinx.android.synthetic.main.item_doctor.view.*
import kotlinx.android.synthetic.main.item_recording.*
import kotlinx.android.synthetic.main.item_recording.view.*

class RecordingListAdapter(private val onItemDelete: (recordingId: Long) -> Unit, private val onItemUpdate: (recordingId: Long) -> Unit, val viewModel: HospitalViewModel): RecyclerView.Adapter<RecordingListAdapter.RecordingViewHolder>() {


    private val differ = AsyncListDiffer(this, RecordingDiffutil())

    fun updateRecordingList(newList: List<Recording>){
        differ.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordingViewHolder {
        return RecordingViewHolder(viewModel, parent.inflate(R.layout.item_recording), differ.currentList, onItemDelete, onItemUpdate)
    }

    override fun onBindViewHolder(holder: RecordingViewHolder, position: Int) {
        val recording = differ.currentList[position]
        holder.bind(recording)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class RecordingViewHolder(val viewModel: HospitalViewModel, override val containerView: View, recordings: List<Recording>, onItemDelete: (recordingId: Long) -> Unit, onItemUpdate: (recordingId: Long) -> Unit): RecyclerView.ViewHolder(containerView), LayoutContainer{

        init {
            containerView.btn_itemRecording_update.setOnClickListener {
                onItemUpdate(recordings[adapterPosition].id)
            }

            containerView.btn_itemRecording_delete.setOnClickListener {
                onItemDelete(recordings[adapterPosition].id)
            }
        }

        fun bind(recording: Recording){
            viewModel.getPatientsLastNameById(recording.patient_id){
                patientsName_itemRecording.text = it
            }

            viewModel.getDoctorLastNameById(recording.doctor_id){
                doctorsName_itemRecording.text = it
            }
            recordingTime_itemRecording.text = recording.record_time
        }
    }

    class RecordingDiffutil(): DiffUtil.ItemCallback<Recording>(){
        override fun areItemsTheSame(oldItem: Recording, newItem: Recording): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recording, newItem: Recording): Boolean {
            return oldItem == newItem
        }
    }
}