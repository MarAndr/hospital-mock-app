package com.example.roomdao.hospitalDatabase.medFacDoctors.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mylab.utils.inflate
import com.example.roomdao.R
import com.example.roomdao.hospitalDatabase.HospitalViewModel
import com.example.roomdao.hospitalDatabase.medFacDoctors.MedFacDoctor
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_medfacdoctor.*
import kotlinx.android.synthetic.main.item_medfacdoctor.view.*
import timber.log.Timber

class MedFacDocAdapterFragment(
        val viewModel: HospitalViewModel,
        val onDeleteButtonClick: (Long) -> Unit,
        val onUpdateButtonClick: (Long) -> Unit
): RecyclerView.Adapter<MedFacDocAdapterFragment.MedFacDocViewHolder>() {

    private val differ = AsyncListDiffer(this, MedFacDocDiffUtil())
    private var medFacDoctors = emptyList<MedFacDoctor>()

//    fun update(newList: List<MedFacDoctor>){
//        Timber.d("update")
//        differ.submitList(newList)
//    }

    fun update(newList: List<MedFacDoctor>){
        medFacDoctors = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedFacDocViewHolder {
        Timber.d("onCreateViewHolder")
        return MedFacDocViewHolder(
                containerView = parent.inflate(R.layout.item_medfacdoctor),
                viewModel = viewModel,
                onDeleteButtonClick = onDeleteButtonClick,
                onUpdateButtonClick = onUpdateButtonClick,
                medFacDoctorList = medFacDoctors)
    }

    override fun onBindViewHolder(holder: MedFacDocViewHolder, position: Int) {
        Timber.d("onBindViewHolder")
        val medFacDoc = medFacDoctors[position]
        holder.bind(medFacDoc)
    }

    override fun getItemCount(): Int {
        return medFacDoctors.size
    }

    class MedFacDocViewHolder(
            override val containerView: View,
            val viewModel: HospitalViewModel,
            onDeleteButtonClick: (Long) -> Unit,
            onUpdateButtonClick: (Long) -> Unit,
            medFacDoctorList: List<MedFacDoctor>
    ): RecyclerView.ViewHolder(containerView), LayoutContainer{

        init {
            containerView.btn_itemMedFacDoc_delete.setOnClickListener {
                onDeleteButtonClick(medFacDoctorList[adapterPosition].id)
            }

            containerView.btn_itemMedFacDoc_update.setOnClickListener {
                onUpdateButtonClick(medFacDoctorList[adapterPosition].id)
            }
        }

        fun bind(medFacDoc: MedFacDoctor){
            viewModel.getMedFacTitleById(medFacDoc.medFacId){medFacTitle ->
            hospitalTitle_medFacItem.text = medFacTitle
            }
            viewModel.getDoctorLastNameById(medFacDoc.doctorId){ doctorsLastName ->
            doctorsLastName_medFacItem.text = doctorsLastName
            }
        }
    }

    class MedFacDocDiffUtil(): DiffUtil.ItemCallback<MedFacDoctor>(){
        override fun areItemsTheSame(oldItem: MedFacDoctor, newItem: MedFacDoctor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MedFacDoctor, newItem: MedFacDoctor): Boolean {
            return oldItem == newItem
        }
    }
}