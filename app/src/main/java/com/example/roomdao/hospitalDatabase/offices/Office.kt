package com.example.roomdao.hospitalDatabase.offices

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = OfficesContract.TABLE_NAME)
data class Office(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = OfficesContract.Columns.NUMBER)
        val number: Int = 0
)