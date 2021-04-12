package com.example.roomdao.hospitalDatabase.medFac

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdao.hospitalDatabase.Week

@Entity(tableName = MedFacContract.TABLE_NAME)
data class MedFacility(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = MedFacContract.Columns.ID)
        val id: Long,
        @ColumnInfo(name = MedFacContract.Columns.ADDRESS)
        val address: String,
        @ColumnInfo(name = MedFacContract.Columns.TITLE)
        val title: String,
        @ColumnInfo(name = MedFacContract.Columns.WORK_DAYS)
        val workDays: List<Week>,
        @ColumnInfo(name = MedFacContract.Columns.PHOTO)
        val photo: String
)
