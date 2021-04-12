package com.example.roomdao.hospitalDatabase.medFac.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mylab.utils.inflate
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.medFac.MedFacility
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_doctor.*
import kotlinx.android.synthetic.main.item_medfac.*
import kotlinx.android.synthetic.main.item_medfac.view.*

class MedFacilityListAdapter(private val onItemDelete: (medFacId: Long) -> Unit, private val onItemUpdate: (medFacId: Long) -> Unit): RecyclerView.Adapter<MedFacilityListAdapter.MedFacViewHolder>() {

    private val differ = AsyncListDiffer(this, MedFacDiffutil())

    fun updateMedFacilityList(newList: List<MedFacility>){
        differ.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedFacViewHolder {
        return MedFacViewHolder(parent.inflate(R.layout.item_medfac), onItemDelete, onItemUpdate, differ.currentList)
    }

    override fun onBindViewHolder(holder: MedFacViewHolder, position: Int) {
        val medFac = differ.currentList[position]
        holder.bind(medFac)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class MedFacViewHolder(override val containerView: View, onItemDelete: (medFacId: Long) -> Unit, onItemUpdate: (medFacId: Long) -> Unit, medFac: List<MedFacility>): RecyclerView.ViewHolder(containerView), LayoutContainer{

        init {
            containerView.btn_itemMedFac_delete.setOnClickListener {
            onItemDelete(medFac[adapterPosition].id)
            }
            containerView.btn_itemMedFac_update.setOnClickListener {
            onItemUpdate(medFac[adapterPosition].id)
            }
        }

        fun bind(medFac: MedFacility){
            id_itemMedFac.text = medFac.id.toString()
            title_itemMedFac.text = medFac.title
            address_itemMedFac.text = medFac.address
            workDays_itemMedFac.text = medFac.workDays.toString()

            Glide.with(itemView)
                    .load(medFac.photo)
                    .placeholder(R.drawable.hospital)
                    .into(iv_medFac_photo)
        }
    }

    class MedFacDiffutil: DiffUtil.ItemCallback<MedFacility>(){
        override fun areItemsTheSame(oldItem: MedFacility, newItem: MedFacility): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MedFacility, newItem: MedFacility): Boolean {
            return oldItem == newItem
        }
    }
}