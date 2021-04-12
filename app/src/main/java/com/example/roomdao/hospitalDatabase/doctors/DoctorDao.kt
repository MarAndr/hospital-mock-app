package com.example.roomdao.hospitalDatabase.doctors

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DoctorDao {
    @Insert
    suspend fun addNewDoctors(doctors: List<Doctor>)

    @Query("select * from ${DoctorsContract.TABLE_NAME}")
    fun getAllDoctorsLiveData(): LiveData<List<Doctor>>

    @Query("select * from ${DoctorsContract.TABLE_NAME}")
    suspend fun getAllDoctors(): List<Doctor>

    @Query("delete from ${DoctorsContract.TABLE_NAME} where ${DoctorsContract.Columns.ID} = :doctorId")
    suspend fun deleteDoctor(doctorId: Long)

    @Query("update ${DoctorsContract.TABLE_NAME} set ${DoctorsContract.Columns.NAME} = :name,  ${DoctorsContract.Columns.LAST_NAME} = :lastName, ${DoctorsContract.Columns.AGE} = :age, ${DoctorsContract.Columns.SPECIALIZATION} = :specialization, ${DoctorsContract.Columns.WORK_HOURS} = :workHours, ${DoctorsContract.Columns.OFFICE_NUMBER} = :office where ${DoctorsContract.Columns.ID} = :doctorId")
    suspend fun updateDoctor(doctorId: Long, name: String, lastName: String, age: Int, specialization: DoctorSpecialization, workHours: ReceptionHour, office: Int)

    @Query("select * from ${DoctorsContract.TABLE_NAME} where ${DoctorsContract.Columns.NAME} = :doctorName")
    suspend fun searchDoctorByName(doctorName: String): List<Doctor>

    @Query("delete from ${DoctorsContract.TABLE_NAME}")
    suspend fun deleteAllDoctors()

    @Query("select ${DoctorsContract.Columns.LAST_NAME} from ${DoctorsContract.TABLE_NAME} where ${DoctorsContract.Columns.ID} = :doctorId")
    suspend fun getDoctorNameById(doctorId: Long): String

    @Query("SELECT * FROM ${DoctorsContract.TABLE_NAME} ORDER BY ${DoctorsContract.Columns.AGE} DESC")
    fun getDoctorsSortByDescAge(): LiveData<List<Doctor>>

    @Query("SELECT * FROM ${DoctorsContract.TABLE_NAME} ORDER BY ${DoctorsContract.Columns.NAME} DESC")
    fun getDoctorsSortByDescName(): LiveData<List<Doctor>>

    @Query("SELECT * FROM ${DoctorsContract.TABLE_NAME} ORDER BY ${DoctorsContract.Columns.LAST_NAME} DESC")
    fun getDoctorsSortByDescLastName(): LiveData<List<Doctor>>

    @Query("select * from ${DoctorsContract.TABLE_NAME} where ${DoctorsContract.Columns.AGE} BETWEEN :ageFrom AND :ageTo")
    suspend fun getDoctorsByAgeRage(ageFrom: Int, ageTo: Int): List<Doctor>

    @Query("select * from ${DoctorsContract.TABLE_NAME} where ${DoctorsContract.Columns.LAST_NAME} = :doctorsLastName")
    suspend fun getDoctorsByName(doctorsLastName: String): List<Doctor>
}