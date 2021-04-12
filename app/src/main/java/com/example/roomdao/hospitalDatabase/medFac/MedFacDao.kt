package com.example.roomdao.hospitalDatabase.medFac

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdao.hospitalDatabase.Week


@Dao
interface MedFacDao {

    @Insert
    suspend fun insertMedFac(medFac: List<MedFacility>)

    @Query("select * from ${MedFacContract.TABLE_NAME}")
    fun getAllMedFacilitiesLiveData(): LiveData<List<MedFacility>>

    @Query("select * from ${MedFacContract.TABLE_NAME}")
    suspend fun getAllMedFacilities(): List<MedFacility>

    @Query("delete from ${MedFacContract.TABLE_NAME} where ${MedFacContract.Columns.ID} = :medFacId")
    suspend fun deleteMedFac(medFacId: Long)

    @Query("update ${MedFacContract.TABLE_NAME} set ${MedFacContract.Columns.TITLE} = :title,  ${MedFacContract.Columns.ADDRESS} = :address, ${MedFacContract.Columns.WORK_DAYS} = :workDays where ${MedFacContract.Columns.ID} = :medFacId")
    suspend fun updateMedFac(medFacId: Long, title: String, address: String, workDays: List<Week>)

    @Query("delete from ${MedFacContract.TABLE_NAME}")
    suspend fun deleteAllMedFac()

    @Query("SELECT COUNT(*) FROM ${MedFacContract.TABLE_NAME}")
    suspend fun getMedFacCount(): Int

    @Query("SELECT ${MedFacContract.Columns.TITLE} FROM ${MedFacContract.TABLE_NAME} where ${MedFacContract.Columns.ID} = :medFacId")
    suspend fun getMedFacTitleById(medFacId: Long): String
}