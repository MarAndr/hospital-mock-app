package com.example.roomdao.hospitalDatabase.doctors.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.mylab.utils.inflate
import com.example.roomdao.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_doctor.*
import kotlinx.android.synthetic.main.item_doctor.view.*

class DoctorsListAdapter(private val onItemDelete: (doctorId: Long) -> Unit, private val onItemUpdate: (doctorId: Long) -> Unit): RecyclerView.Adapter<DoctorsListAdapter.DoctorsViewHolder>() {


    private val differ = AsyncListDiffer(this, DoctorsDiffUtil())


    fun updateDoctorsList(newList: List<Doctor>){
        differ.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsViewHolder {
        return DoctorsViewHolder(parent.inflate(R.layout.item_doctor), differ.currentList, onItemDelete, onItemUpdate)
    }

    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {
        val doctor = differ.currentList[position]
        holder.bind(doctor)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class DoctorsViewHolder(override val containerView: View, doctors: List<Doctor>, onItemDelete: (doctorId: Long) -> Unit, onItemUpdate: (doctorId: Long) -> Unit): RecyclerView.ViewHolder(containerView), LayoutContainer{

        init {
            containerView.btn_itemDoctor_update.setOnClickListener {
                onItemUpdate(doctors[adapterPosition].id)
            }

            containerView.btn_itemDoctor_delete.setOnClickListener {
                onItemDelete(doctors[adapterPosition].id)
            }
        }

        fun bind(doctor: Doctor){
            firstName_itemDoctor.text = doctor.name
            lastName_itemDoctor.text = doctor.lastName
            id_itemDoctor.text = doctor.id.toString()
            specialization_itemDoctor.text = doctor.specialization.toString()
            age_itemDoctor.text = doctor.age.toString()
            workDays_itemDoctor.text = doctor.workDays.joinToString { it.tag.toString() }
            workHours_itemDoctor.text = doctor.workHours.tag
            office_itemDoctor.text = doctor.officeNumber.toString()

            Glide
                .with(itemView)
                .load(doctor.photo)
                .placeholder(R.drawable.avatar_doctor)
                .into(avatar_itemDoctor)
        }
    }

    class DoctorsDiffUtil(): DiffUtil.ItemCallback<Doctor>(){
        override fun areItemsTheSame(oldItem: Doctor, newItem: Doctor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Doctor, newItem: Doctor): Boolean {
            return oldItem == newItem
        }
    }
}