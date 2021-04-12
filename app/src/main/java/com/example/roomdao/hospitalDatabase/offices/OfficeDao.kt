package com.example.roomdao.hospitalDatabase.offices

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OfficeDao {
    @Insert
    suspend fun addNewOffices(offices: List<Office>)

    @Query("select * from ${OfficesContract.TABLE_NAME}")
    suspend fun getAllOffices(): List<Office>

    @Query("delete from ${OfficesContract.TABLE_NAME}")
    suspend fun deleteAllOffices()
}