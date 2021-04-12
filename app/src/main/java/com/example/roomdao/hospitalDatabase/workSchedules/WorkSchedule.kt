package com.example.roomdao.hospitalDatabase.workSchedules

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.roomdao.hospitalDatabase.Week
import com.example.roomdao.hospitalDatabase.doctors.ReceptionHour
import com.example.roomdao.hospitalDatabase.doctors.ReceptionHoursConverter

@Entity(tableName = WorkSchedulesContract.TABLE_NAME)
@TypeConverters(ReceptionHoursConverter::class)
data class WorkSchedule(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = WorkSchedulesContract.Columns.ID)
        val id: Long,
        @ColumnInfo(name = WorkSchedulesContract.Columns.WORK_DAYS)
        val workDays: List<Week>,
        @ColumnInfo(name = WorkSchedulesContract.Columns.RECEPTION_HOURS)
        val receptionHours: ReceptionHour
        )


