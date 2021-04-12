package com.example.roomdao.hospitalDatabase.recordings

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mylab.database.hospitalDatabase.recordings.RecordingsContract

@Dao
interface RecordingDao {
    @Insert
    suspend fun addNewRecordings(recordings: List<Recording>)

    @Query("select * from ${RecordingsContract.TABLE_NAME}")
    fun getAllRecordings(): LiveData<List<Recording>>

    @Query("delete from ${RecordingsContract.TABLE_NAME} where ${RecordingsContract.Columns.ID} = :recordingId")
    suspend fun deleteRecordings(recordingId: Long)

    @Query("update ${RecordingsContract.TABLE_NAME} set ${RecordingsContract.Columns.PATIENT_ID} = :patientId, ${RecordingsContract.Columns.DOCTOR_ID} = :doctorId, ${RecordingsContract.Columns.RECORD_TIME} = :recordTime where ${RecordingsContract.Columns.ID} = :recordingId")
    suspend fun updateRecording(recordingId: Long, patientId: Long, doctorId: Long, recordTime: String)

    @Query("delete from ${RecordingsContract.TABLE_NAME}")
    suspend fun deleteAllRecordings()
}