package com.example.roomdao.hospitalDatabase


import androidx.lifecycle.*
import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.patients.Patient
import com.example.roomdao.hospitalDatabase.patients.PatientWithRecording
import com.example.roomdao.hospitalDatabase.doctors.DoctorSpecialization
import com.example.roomdao.hospitalDatabase.doctors.ReceptionHour
import com.example.roomdao.hospitalDatabase.doctors.dialog.AddDoctorData
import com.example.roomdao.hospitalDatabase.medFac.MedFacility
import com.example.roomdao.hospitalDatabase.medFacDoctors.MedFacDoctor
import com.example.roomdao.hospitalDatabase.medFacDoctors.dialog.AddHospitalsData
import com.example.roomdao.hospitalDatabase.offices.Office
import com.example.roomdao.hospitalDatabase.patients.PatientDiagnosis
import com.example.roomdao.hospitalDatabase.recordings.Recording
import com.example.roomdao.hospitalDatabase.recordings.dialog.AddRecordingData
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HospitalViewModel() : ViewModel() {
    private val repo = HospitalRepository()
    val doctors: LiveData<List<Doctor>> = getAllDoctorsLiveData()
    val doctorsSortedByAge: LiveData<List<Doctor>> = getDoctorsSortByDescAge()
    val doctorsSortedByName: LiveData<List<Doctor>> = getDoctorsSortByDescName()
    val doctorsSortedByLastName: LiveData<List<Doctor>> = getDoctorsSortByDescLastName()
    private val _doctorsByAgeRage= MutableLiveData<List<Doctor>>()
    val doctorsByAgeRage: LiveData<List<Doctor>> = _doctorsByAgeRage
    val patients: LiveData<List<Patient>> = getAllPatientsLiveData()
    val patientsSortedByAge: LiveData<List<Patient>> = getPatientsSortByDescAge()
    val patientsSortedByName: LiveData<List<Patient>> = getPatientsSortByDescName()
    val patientsSortedByLastName: LiveData<List<Patient>> = getPatientsSortByDescLastName()
    private val _patientsByAgeRage= MutableLiveData<List<Patient>>()
    val patientsByAgeRage: LiveData<List<Patient>> = _patientsByAgeRage
    private val _patientsWithRecordings = MutableLiveData<List<PatientWithRecording>>()
    val patientsWithRecordings: LiveData<List<PatientWithRecording>> = _patientsWithRecordings
    val medFacilities: LiveData<List<MedFacility>> = getAllMedFacilitiesLiveData()
    private val _offices = MutableLiveData<List<Office>>()
    val offices: LiveData<List<Office>> = _offices
//    private val _recordings = MutableLiveData<List<Recording>>()
    val recordings: LiveData<List<Recording>> = getAllRecordings()
    private val _medFacDocs = MutableLiveData<List<MedFacDoctor>>()
    val medFacDocs: LiveData<List<MedFacDoctor>> = getAllMedFacDoc()
    private val _addingEvent = MutableStateFlow<AddingItemsState>(AddingItemsState.EMPTY)
    val addingEvent: StateFlow<AddingItemsState> = _addingEvent
    private val _addDoctorData = MutableLiveData<AddDoctorData>()
    val addDoctorData: LiveData<AddDoctorData> = _addDoctorData
    private val _addRecordingData = MutableLiveData<AddRecordingData>()
    val addRecordingData: LiveData<AddRecordingData> = _addRecordingData
    private val _hospitalsData = MutableLiveData<AddHospitalsData>()
    val hospitalsData: LiveData<AddHospitalsData> = _hospitalsData
    private val _addCommonData = MutableLiveData<AddCommonData>()
    val addCommonData: LiveData<AddCommonData> = _addCommonData
    private val _medFacDocByMedFacId = MutableLiveData<List<MedFacDoctor>>()
    val medFacDocByMedFacId: LiveData<List<MedFacDoctor>> = _medFacDocByMedFacId
    private val _medFacDocByDoctorId = MutableLiveData<List<MedFacDoctor>>()
    val medFacDocByDoctorId: LiveData<List<MedFacDoctor>> = _medFacDocByDoctorId


    fun createAddDoctorDialog(){
        viewModelScope.launch {
            val result = async {
                repo.getAllOffices()
            }.await()
            _addDoctorData.postValue(AddDoctorData(result))
        }
    }

    fun createHospitalData(){
        viewModelScope.launch {
            val result = async {
                repo.getAllMedFacilities()
            }.await()
            _hospitalsData.postValue(AddHospitalsData(result))
        }
    }

    fun createAddRecordingDialog(){
        viewModelScope.launch {
            val doctorResult = async {
                repo.getAllDoctors()
            }

            val patientResult = async {
                repo.getAllPatients()
            }

            val doctors = doctorResult.await()
            val patients = patientResult.await()

            _addRecordingData.postValue(AddRecordingData(doctors, patients))
        }

    }

    fun createAddMedFacDocDialog(){
        viewModelScope.launch {
            val doctorResult = async {
                repo.getAllDoctors()
            }

            val medFacResult = async {
                repo.getAllMedFacilities()
            }

            val doctors = doctorResult.await()
            val medFacilities = medFacResult.await()

            _addCommonData.postValue(AddCommonData(doctors = doctors, medFac = medFacilities))
        }

    }

    fun addDoctors(doctors: List<Doctor>) {
        viewModelScope.launch {
            repo.insertDoctors(doctors)
        }
    }

    fun searchDoctorByName(doctorName: String){

    }

    fun addRandomDoctors(amount: Int) {
        viewModelScope.launch {
            if (repo.getMedFacilitiesCount() > 0){
                repo.addRandomDoctors(amount)
                _addingEvent.value = AddingItemsState.DOCTOR_AD("Added $amount doctors")
            } else {
                _addingEvent.value = AddingItemsState.ERROR("The medFac list is empty")
            }
        }
    }

    fun getAllDoctorsLiveData() = repo.getAllDoctorsLiveData()

    fun getDoctorsSortByDescAge() = repo.getDoctorsSortByDescAge()

    fun getDoctorsSortByDescName() = repo.getDoctorsSortByDescName()

    fun getDoctorsSortByDescLastName() = repo.getDoctorsSortByDescLastName()

    fun getDoctorsByAgeRage(ageFrom: Int, ageTo: Int){
        viewModelScope.launch {
        _doctorsByAgeRage.value = repo.getDoctorsByAgeRage(ageFrom, ageTo)
        }
    }

    fun getDoctorLastNameById(doctorId: Long, callback: (String) -> Unit){
        viewModelScope.launch {
        val result = repo.getDoctorLastNameById(doctorId)
            callback(result)
        }
    }

    fun getDoctorsByLastName(doctorsLastName: String, callback: (List<Doctor>) -> Unit){
        viewModelScope.launch {
                callback(repo.getDoctorsByName(doctorsLastName))
            }

    }

    fun getPatientsLastNameById(patientId: Long, callback: (String) -> Unit){
        viewModelScope.launch {
            val result = repo.getPatientLastNameById(patientId)
            callback(result)
        }
    }


    fun deleteDoctor(doctorId: Long) {
        viewModelScope.launch {
            repo.deleteDoctor(doctorId)
        }
    }

    fun deleteAllDoctors() {
        viewModelScope.launch {
            repo.deleteAllDoctors()
            _addingEvent.value = AddingItemsState.DOCTORS_DEL_ALL("Deleted all doctors")
        }
    }

    fun updateDoctor(doctorId: Long, name: String, lastName: String, age: Int, specialization: DoctorSpecialization, workHours: ReceptionHour, office: Int) {
        viewModelScope.launch {
            repo.updateDoctor(doctorId, name, lastName, age, specialization, workHours, office)
        }
    }

    fun addPatients(patients: List<Patient>) {
        viewModelScope.launch {
            repo.insertPatients(patients)
        }
    }

    fun addRandomPatients(amount: Int) {
        viewModelScope.launch {
            repo.addRandomPatients(amount)
            _addingEvent.value = AddingItemsState.PATIENT_AD("Added $amount patients")
        }
    }

    fun deletePatient(patientId: Long) {
        viewModelScope.launch {
            repo.deletePatient(patientId)
        }
    }

    fun deleteAllPatients() {
        viewModelScope.launch {
            repo.deleteAllPatients()
            _addingEvent.value = AddingItemsState.PATIENTS_DEL_ALL("Deleted all patients")
        }
    }

    fun updatePatient(patientId: Long, patientName: String, lastName: String, age: Int, diagnosis: PatientDiagnosis) {
        viewModelScope.launch {
            repo.updatePatient(patientId, patientName, lastName, age, diagnosis)
        }
    }

    fun getAllPatientsLiveData() = repo.getAllPatientsLiveData()

    fun getPatientsSortByDescAge() = repo.getPatientsSortByDescAge()

    fun getPatientsSortByDescName() = repo.getPatientsSortByDescName()

    fun getPatientsSortByDescLastName() = repo.getPatientsSortByDescLastName()

    fun getPatientsByAgeRage(ageFrom: Int, ageTo: Int){
        viewModelScope.launch {
            _patientsByAgeRage.value = repo.getPatientsByAgeRage(ageFrom, ageTo)
        }
    }

    fun getAllPatientsWithRecording() {
        viewModelScope.launch {
            _patientsWithRecordings.value = repo.getPatientsWithRecordings()
        }
    }

    fun getAllMedFacilitiesLiveData() = repo.getAllMedFacilitiesLiveData()

    fun getMedFacTitleById(medFacId: Long, callback: (String) -> Unit){
        viewModelScope.launch {
            val result = repo.getMedFacTitleById(medFacId)
            callback(result)
        }
    }

    fun deleteMedFac(medFacId: Long) {
        viewModelScope.launch {
            repo.deleteMedFac(medFacId)
        }
    }

    fun deleteAllMedFac() {
        viewModelScope.launch {
            repo.deleteAllMedFac()
            _addingEvent.value = AddingItemsState.MEDFAC_DEL_ALL("Deleted all medical facilities")
        }
    }

    fun updateMedFac(medFacId: Long, title: String, address: String, workDays: List<Week>) {
        viewModelScope.launch {
            repo.updateMedFac(medFacId, title, address, workDays)
        }
    }

    fun insertMedFacilities(medFac: List<MedFacility>) {
        viewModelScope.launch {
            repo.insertMedFacilities(medFac)
        }
    }

    fun addRandomMedFac(amount: Int) {
        viewModelScope.launch {
            repo.addRandomMedFacilities(amount)
            _addingEvent.value = AddingItemsState.FACILITY_AD("Added $amount hospitals")
        }
    }

    fun getAllOffices() {
        viewModelScope.launch {
            _offices.value = repo.getAllOffices()
        }
    }

    fun deleteAllOffices() {
        viewModelScope.launch {
            repo.deleteAllOffices()
            _addingEvent.value = AddingItemsState.OFFICES_DEL_ALL("Deleted all offices")
        }
    }

    fun insertOffices(offices: List<Office>) {
        viewModelScope.launch {
            repo.insertOffices(offices)
        }
    }

    fun getAllRecordings() = repo.getAllRecordings()



    fun insertRecordings(recordings: List<Recording>) {
        viewModelScope.launch {
            repo.insertRecordings(recordings)
        }
    }

    fun updateRecordings(recordingId: Long, patientId: Long, doctorId: Long, recordTime: String) {
        viewModelScope.launch {
            repo.updateRecordings(recordingId, patientId, doctorId, recordTime)
        }
    }

    fun deleteRecording(recordingId: Long) {
        viewModelScope.launch {
            repo.deleteRecordings(recordingId)
        }
    }

    fun deleteAllRecordings() {
        viewModelScope.launch {
            repo.deleteAllRecordings()
            _addingEvent.value = AddingItemsState.RECORDINGS_DEL_ALL("Deleted all recordings")
        }
    }

    fun addMedFacDoc(medFacDocList: List<MedFacDoctor>) {
        viewModelScope.launch {
            repo.addMedFacDoc(medFacDocList)
        }
    }

    fun getAllMedFacDoc()  = repo.getAllMedFacDoc()



    fun deleteAllMedFacDoc() {
        viewModelScope.launch {
            repo.deleteAllMedFacDoc()
        }
    }

    fun deleteMedFacDoc(medFacDocId: Long) {
        viewModelScope.launch {
            repo.deleteMedFacDoc(medFacDocId)
        }
    }

    fun updateMedFacDoc(medFacDocId: Long, medFacId: Long, doctorId: Long) {
        viewModelScope.launch {
            repo.updateMedFacDoc(medFacDocId, medFacId, doctorId)
        }
    }

    fun getMedFacDocByMedFacId(medFacId: Long){
        viewModelScope.launch {
            _medFacDocByMedFacId.postValue(repo.getMedFacDocByMedFacId(medFacId))
        }
    }

    fun getMedFacDocByDoctorId(doctorId: Long){
        viewModelScope.launch {
            _medFacDocByDoctorId.postValue(repo.getMedFacDocByDoctorId(doctorId))
        }
    }
}