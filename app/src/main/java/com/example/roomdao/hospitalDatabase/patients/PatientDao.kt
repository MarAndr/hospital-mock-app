package com.example.roomdao.hospitalDatabase.patients

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mylab.database.hospitalDatabase.patients.PatientContract

@Dao
interface PatientDao {
    @Insert
    suspend fun addNewPatients(patients: List<Patient>)

    @Query("select * from ${PatientContract.TABLE_NAME}")
    fun getAllPatientsLiveData(): LiveData<List<Patient>>

    @Query("select * from ${PatientContract.TABLE_NAME}")
    suspend fun getAllPatients(): List<Patient>

    @Query("select * from ${PatientContract.TABLE_NAME}")
    suspend fun getPatientsWithRecordings(): List<PatientWithRecording>

    @Query("delete from ${PatientContract.TABLE_NAME} where ${PatientContract.Columns.ID} = :patientId")
    suspend fun deletePatient(patientId: Long)

    @Query("update ${PatientContract.TABLE_NAME} set ${PatientContract.Columns.NAME} = :patientName,  ${PatientContract.Columns.LAST_NAME} = :lastName, ${PatientContract.Columns.AGE} = :age, ${PatientContract.Columns.LAST_NAME} = :lastName, ${PatientContract.Columns.DIAGNOSIS} = :diagnosis where ${PatientContract.Columns.ID} = :patientId")
    suspend fun updatePatient(patientId: Long, patientName: String, lastName: String, age: Int, diagnosis: PatientDiagnosis)

    @Query("delete from ${PatientContract.TABLE_NAME}")
    suspend fun deleteAllPatients()

    @Query("select ${PatientContract.Columns.LAST_NAME} from ${PatientContract.TABLE_NAME} where ${PatientContract.Columns.ID} = :patientId")
    suspend fun getPatientsLastNameById(patientId: Long): String

    @Query("SELECT * FROM ${PatientContract.TABLE_NAME} ORDER BY ${PatientContract.Columns.AGE} DESC")
    fun getPatientsSortByDescAge(): LiveData<List<Patient>>

    @Query("SELECT * FROM ${PatientContract.TABLE_NAME} ORDER BY ${PatientContract.Columns.NAME} DESC")
    fun getPatientsSortByDescName(): LiveData<List<Patient>>

    @Query("SELECT * FROM ${PatientContract.TABLE_NAME} ORDER BY ${PatientContract.Columns.LAST_NAME} DESC")
    fun getPatientsSortByDescLastName(): LiveData<List<Patient>>

    @Query("select * from ${PatientContract.TABLE_NAME} where ${PatientContract.Columns.AGE} BETWEEN :ageFrom AND :ageTo")
    suspend fun getPatientsByAgeRage(ageFrom: Int, ageTo: Int): List<Patient>

}