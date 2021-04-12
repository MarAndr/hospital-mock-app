package com.example.roomdao.hospitalDatabase

import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.patients.Patient
import com.example.roomdao.hospitalDatabase.patients.PatientWithRecording
import com.example.roomdao.hospitalDatabase.doctors.DoctorSpecialization
import com.example.roomdao.hospitalDatabase.doctors.ReceptionHour
import com.example.roomdao.hospitalDatabase.medFac.MedFacility
import com.example.roomdao.hospitalDatabase.medFacDoctors.MedFacDoctor
import com.example.roomdao.hospitalDatabase.offices.Office
import com.example.roomdao.hospitalDatabase.patients.PatientDiagnosis
import com.example.roomdao.hospitalDatabase.recordings.Recording

class HospitalRepository {
    private val doctorDao = HospitalDatabase.instance.doctorDao()
    private val patientDao = HospitalDatabase.instance.patientDao()
    private val recordingDao = HospitalDatabase.instance.recordingDao()
    private val medFacDao = HospitalDatabase.instance.medFacilityDao()
    private val officeDao = HospitalDatabase.instance.officeDao()
    private val medFacDocDao = HospitalDatabase.instance.medFacDocDao()

    suspend fun insertDoctors(doctors: List<Doctor>){
        doctorDao.addNewDoctors(doctors)
    }

    suspend fun addRandomDoctors(amount: Int){
        HospitalDatabase.instance.withTransaction {
            (0..amount).forEach {
                doctorDao.addNewDoctors(DataGenerator.generateRandomDoctor())
            }
        }
    }

    fun getAllDoctorsLiveData(): LiveData<List<Doctor>>{
        return doctorDao.getAllDoctorsLiveData()
    }

    suspend fun getAllDoctors(): List<Doctor>{
        return doctorDao.getAllDoctors()
    }

    fun getDoctorsSortByDescAge(): LiveData<List<Doctor>>{
        return doctorDao.getDoctorsSortByDescAge()
    }

    suspend fun getDoctorsByAgeRage(ageFrom: Int, ageTo: Int): List<Doctor>{
        return doctorDao.getDoctorsByAgeRage(ageFrom, ageTo)
    }

    fun getDoctorsSortByDescName(): LiveData<List<Doctor>>{
        return doctorDao.getDoctorsSortByDescName()
    }

    fun getDoctorsSortByDescLastName(): LiveData<List<Doctor>>{
        return doctorDao.getDoctorsSortByDescLastName()
    }

    suspend fun getDoctorLastNameById(doctorId: Long): String{
        return doctorDao.getDoctorNameById(doctorId)
    }

    suspend fun deleteDoctor(doctorId: Long){
        doctorDao.deleteDoctor(doctorId)
    }

    suspend fun getDoctorsByName(doctorsLastName: String): List<Doctor>{
        return doctorDao.getDoctorsByName(doctorsLastName)
    }

    suspend fun deleteAllDoctors(){
        doctorDao.deleteAllDoctors()
    }

    suspend fun updateDoctor(doctorId: Long, name: String, lastName: String, age: Int, specialization: DoctorSpecialization, workHours: ReceptionHour, office: Int){
        doctorDao.updateDoctor(doctorId, name, lastName, age, specialization, workHours, office)
    }

    suspend fun searchDoctorByName(doctorName: String): List<Doctor>{
        return doctorDao.searchDoctorByName(doctorName)
    }

    suspend fun insertPatients(patients: List<Patient>){
        patientDao.addNewPatients(patients)
    }

    suspend fun addRandomPatients(amount: Int){
        HospitalDatabase.instance.withTransaction {
            (0..amount).forEach {
                patientDao.addNewPatients(DataGenerator.generateRandomPatient())
            }
        }
    }

    suspend fun getPatientLastNameById(patientId: Long): String{
        return patientDao.getPatientsLastNameById(patientId)
    }

    suspend fun deletePatient(patientId: Long){
        patientDao.deletePatient(patientId)
    }

    suspend fun deleteAllPatients(){
        patientDao.deleteAllPatients()
    }

    suspend fun updatePatient(patientId: Long, name: String, lastName: String, age: Int, diagnosis: PatientDiagnosis){
        patientDao.updatePatient(patientId, name, lastName, age, diagnosis)
    }

    fun getAllPatientsLiveData(): LiveData<List<Patient>>{
        return patientDao.getAllPatientsLiveData()
    }

    suspend fun getAllPatients(): List<Patient>{
        return patientDao.getAllPatients()
    }

    fun getPatientsSortByDescAge(): LiveData<List<Patient>>{
        return patientDao.getPatientsSortByDescAge()
    }

    suspend fun getPatientsByAgeRage(ageFrom: Int, ageTo: Int): List<Patient>{
        return patientDao.getPatientsByAgeRage(ageFrom, ageTo)
    }

    fun getPatientsSortByDescName(): LiveData<List<Patient>>{
        return patientDao.getPatientsSortByDescName()
    }

    fun getPatientsSortByDescLastName(): LiveData<List<Patient>>{
        return patientDao.getPatientsSortByDescLastName()
    }

    suspend fun getPatientsWithRecordings(): List<PatientWithRecording>{
        return patientDao.getPatientsWithRecordings()
    }

    fun getAllMedFacilitiesLiveData(): LiveData<List<MedFacility>> {
        return medFacDao.getAllMedFacilitiesLiveData()
    }

    suspend fun getAllMedFacilities(): List<MedFacility> {
        return medFacDao.getAllMedFacilities()
    }

    suspend fun getMedFacTitleById(medFacId: Long): String{
        return medFacDao.getMedFacTitleById(medFacId)
    }

    suspend fun insertMedFacilities(medFac: List<MedFacility>) {
        medFacDao.insertMedFac(medFac)
        officeDao.addNewOffices(DataGenerator.generateRandomOffices())
    }

    suspend fun addRandomMedFacilities(amount: Int){
        HospitalDatabase.instance.withTransaction {
            (0..amount).forEach {
                medFacDao.insertMedFac(DataGenerator.generateRandomMedFacility())
                officeDao.addNewOffices(DataGenerator.generateRandomOffices())
            }
        }
    }

    suspend fun getMedFacilitiesCount(): Int {
       return medFacDao.getMedFacCount()
    }

    suspend fun deleteMedFac(medFacId: Long){
        medFacDao.deleteMedFac(medFacId)
    }

    suspend fun deleteAllMedFac(){
        medFacDao.deleteAllMedFac()
        officeDao.deleteAllOffices()
    }

    suspend fun updateMedFac(medFacId: Long, title: String, address: String, workDays: List<Week>){
        medFacDao.updateMedFac(medFacId, title, address, workDays)
    }

    suspend fun getAllOffices(): List<Office>{
        return officeDao.getAllOffices()
    }

    suspend fun deleteAllOffices(){
        officeDao.deleteAllOffices()
    }

    suspend fun insertOffices(offices: List<Office>) {
        officeDao.addNewOffices(offices)
    }

    suspend fun addRandomOffices(amount: Int){
        HospitalDatabase.instance.withTransaction {
            (0..amount).forEach {
                officeDao.addNewOffices(listOf(Office(0)))
            }
        }
    }

    fun getAllRecordings(): LiveData<List<Recording>>{
        return recordingDao.getAllRecordings()
    }

    suspend fun insertRecordings(recordings: List<Recording>) {
        recordingDao.addNewRecordings(recordings)
    }

    suspend fun updateRecordings(recordingId: Long, patientId: Long, doctorId: Long, recordTime: String) {
        recordingDao.updateRecording(recordingId, patientId, doctorId, recordTime)
    }

    suspend fun deleteRecordings(recordingId: Long) {
        recordingDao.deleteRecordings(recordingId)
    }

    suspend fun deleteAllRecordings(){
        recordingDao.deleteAllRecordings()
    }

    suspend fun addMedFacDoc(medFacDocList: List<MedFacDoctor>){
        medFacDocDao.addMedFacDoctor(medFacDocList)
    }

    fun getAllMedFacDoc(): LiveData<List<MedFacDoctor>>{
        return medFacDocDao.getAllMedFacDoctor()
    }

    suspend fun deleteAllMedFacDoc(){
        return medFacDocDao.deleteAllMedFacDoctor()
    }

    suspend fun deleteMedFacDoc(medFacDocId: Long){
        return medFacDocDao.deleteMedFacDoctor(medFacDocId)
    }

    suspend fun updateMedFacDoc(medFacDocId: Long, medFacId: Long, doctorId: Long){
        medFacDocDao.updateMedFacDoc(medFacDocId, medFacId, doctorId)
    }

    suspend fun getMedFacDocByMedFacId(medFacId: Long): List<MedFacDoctor>{
        return medFacDocDao.getMedFacDocByMedFacId(medFacId)
    }

    suspend fun getMedFacDocByDoctorId(doctorId: Long): List<MedFacDoctor>{
        return medFacDocDao.getMedFacDocByDoctorId(doctorId)
    }
}