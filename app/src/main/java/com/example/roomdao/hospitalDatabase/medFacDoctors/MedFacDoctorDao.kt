package com.example.roomdao.hospitalDatabase.medFacDoctors

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdao.hospitalDatabase.doctors.DoctorsContract

@Dao
interface MedFacDoctorDao {

    @Insert
    suspend fun addMedFacDoctor(medFacDoctors: List<MedFacDoctor>)

    @Query("delete from ${MedFacDoctorsContract.TABLE_NAME}")
    suspend fun deleteAllMedFacDoctor()

    @Query("delete from ${MedFacDoctorsContract.TABLE_NAME} where ${MedFacDoctorsContract.Columns.ID} = :medFacDocId")
    suspend fun deleteMedFacDoctor(medFacDocId: Long)

    @Query("select * from ${MedFacDoctorsContract.TABLE_NAME}")
    fun getAllMedFacDoctor(): LiveData<List<MedFacDoctor>>

    @Query("select ${MedFacDoctorsContract.Columns.DOCTOR_ID} from ${MedFacDoctorsContract.TABLE_NAME} where ${MedFacDoctorsContract.Columns.MEDFAC_ID} = :medFacId")
    suspend fun getDoctorIdByMedFacId(medFacId: Long): Long

    @Query("select ${MedFacDoctorsContract.Columns.MEDFAC_ID} from ${MedFacDoctorsContract.TABLE_NAME} where ${MedFacDoctorsContract.Columns.DOCTOR_ID} = :doctorId")
    suspend fun getMedFacIdByDoctorId(doctorId: Long): Long

    @Query("update ${MedFacDoctorsContract.TABLE_NAME} set ${MedFacDoctorsContract.Columns.MEDFAC_ID} = :medFacId, ${MedFacDoctorsContract.Columns.DOCTOR_ID} = :doctorId where ${MedFacDoctorsContract.Columns.ID} = :medFacDocId")
    suspend fun updateMedFacDoc(medFacDocId: Long, medFacId: Long, doctorId: Long)

    @Query("select * from ${MedFacDoctorsContract.TABLE_NAME} where ${MedFacDoctorsContract.Columns.MEDFAC_ID} = :medFacId")
    suspend fun getMedFacDocByMedFacId(medFacId: Long): List<MedFacDoctor>

    @Query("select * from ${MedFacDoctorsContract.TABLE_NAME} where ${MedFacDoctorsContract.Columns.DOCTOR_ID} = :doctorId")
    suspend fun getMedFacDocByDoctorId(doctorId: Long): List<MedFacDoctor>
}