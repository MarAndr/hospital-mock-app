package com.example.roomdao.hospitalDatabase.patients.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mylab.utils.inflate
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.patients.Patient
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_patient.*
import kotlinx.android.synthetic.main.item_patient.view.*

class PatientListAdapter(private val onItemClickDelete: (id: Long) -> Unit, private val onItemClickUpdate: (id: Long) -> Unit) : RecyclerView.Adapter<PatientListAdapter.PatientViewHolder>() {

    private val differ = AsyncListDiffer(this, PatientsDiffutil())


    fun updatePatientsList(newList: List<Patient>) {
        differ.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        return PatientViewHolder(parent.inflate(R.layout.item_patient), onItemClickDelete, differ.currentList, onItemClickUpdate)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = differ.currentList[position]
        holder.bind(patient)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class PatientViewHolder(override val containerView: View, onItemClickDelete: (id: Long) -> Unit, patients: List<Patient>, onItemClickUpdate: (id: Long) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {

            containerView.btn_itemPatient_delete.setOnClickListener {
                onItemClickDelete(patients[adapterPosition].id)
            }

            containerView.btn_itemPatient_update.setOnClickListener {
                onItemClickUpdate(patients[adapterPosition].id)
            }
        }

        fun bind(patient: Patient) {
            firstName_itemPatient.text = patient.name
            lastName_itemPatient.text = patient.lastName
            id_itemPatient.text = patient.id.toString()
            diagnosis_itemPatient.text = patient.diagnosis.toString()
            age_itemPatient.text = patient.age.toString()

            Glide
                    .with(itemView)
                    .load(patient.photo)
                    .placeholder(R.drawable.patient_avatar)
                    .into(avatar_itemPatient)
        }
    }

    class PatientsDiffutil() : DiffUtil.ItemCallback<Patient>() {
        override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
            return oldItem == newItem
        }
    }
}