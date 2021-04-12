package com.example.roomdao.hospitalDatabase.workSchedules

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkSchedulesDao {
    @Insert
    suspend fun addNewWorkSchedule(workSchedule: List<WorkSchedule>)

    @Query("select * from ${WorkSchedulesContract.TABLE_NAME}")
    suspend fun getWorkSchedules(): List<WorkSchedule>
}