package com.example.roomdao.hospitalDatabase

import com.example.roomdao.hospitalDatabase.doctors.Doctor
import com.example.roomdao.hospitalDatabase.doctors.DoctorSpecialization
import com.example.roomdao.hospitalDatabase.doctors.ReceptionHour
import com.example.roomdao.hospitalDatabase.medFac.MedFacility
import com.example.roomdao.hospitalDatabase.offices.Office
import com.example.roomdao.hospitalDatabase.patients.Patient
import com.example.roomdao.hospitalDatabase.patients.PatientDiagnosis
import kotlin.random.Random

object DataGenerator {
    private val doctorNames = listOf("Mike", "John", "Tom", "Sam",
            "Julia", "Garret", "Daile", "Bob", "Harry", "Mark")
    private val doctorLastNames = listOf("Bale", "McCartney", "Stegen", "Dest",
            "Pique", "Roberto", "Johns", "Lenglet", "Umtiti", "Fraser")

    private val hospitalsTitles = listOf("Ichilov", "Assuta",
        "Johns Hopkins", "Great Ormond", "Harvard Medical School",
        "Cedars-Sinai Medical Complex", "Shiba Medical Center", "Munich Clinic",
        "Charite Clinic", "Gachon Medical University")

    private val hospitalAddresses = listOf("Shaftesbury Avenue", "King’s Road",
        "Abby Road", "Carnaby Street", "Baker Street", "Portobello Road", "Oxford Street",
        "Piccadilly", "Downing Street", "The Strand")

    private val doctorsPhotos = listOf(
            "https://cdn.pixabay.com/photo/2014/12/10/21/01/doctor-563429__340.jpg",
            "https://cdn.pixabay.com/photo/2020/05/25/03/37/doctor-5216835__340.png",
            "https://cdn.pixabay.com/photo/2020/04/09/19/19/cartoon-doctor-5022797__340.jpg",
            "https://cdn.pixabay.com/photo/2017/01/29/21/16/nurse-2019420__340.jpg",
            "https://cdn.pixabay.com/photo/2019/07/30/15/57/dentist-4373290__340.jpg",
            "https://cdn.pixabay.com/photo/2020/06/20/15/30/woman-doctor-5321351__340.jpg",
            "https://cdn.pixabay.com/photo/2020/03/31/07/38/face-mask-4986543__340.jpg",
            "https://cdn.pixabay.com/photo/2016/02/08/23/36/isolated-1188036__340.png",
            "https://cdn.pixabay.com/photo/2017/01/31/22/32/boy-2027768__340.png",
            "https://cdn.pixabay.com/photo/2017/03/22/19/07/ambulance-2166079__340.jpg"
    )

    private val patientsPhotos = listOf(
            "https://cdn.pixabay.com/photo/2016/09/28/22/32/analogue-1701651__340.jpg",
            "https://cdn.pixabay.com/photo/2015/07/11/19/18/patient-841165__340.jpg",
            "https://cdn.pixabay.com/photo/2019/08/08/06/54/disease-4392164__340.jpg",
            "https://cdn.pixabay.com/photo/2013/07/13/09/49/influenza-156098__340.png",
            "https://cdn.pixabay.com/photo/2017/01/25/06/30/the-common-cold-2007171__340.jpg",
            "https://cdn.pixabay.com/photo/2019/08/08/06/53/disease-4392163__340.jpg",
            "https://cdn.pixabay.com/photo/2017/01/31/17/25/comic-characters-2025727__340.png",
            "https://cdn.pixabay.com/photo/2020/06/17/10/32/back-pain-5308969__340.jpg",
            "https://cdn.pixabay.com/photo/2012/04/11/16/35/wheelchair-28812__340.png",
            "https://cdn.pixabay.com/photo/2017/09/15/06/31/african-american-2751286__340.jpg"
    )

    private val patientNames = listOf("Samuel", "Jack", "Joseph", "Jacob",
            "Oscar", "Archie", "Henry", "Stanley", "Owen", "Theodore")
    private val patientsLastNames = listOf("Abramson", "Berrington", "Bishop", "Campbell",
            "Cook", "Davidson", "Durham", "Enderson", "Fitzgerald", "Gimson")

    fun generateRandomDoctor(): List<Doctor>{
        return listOf(Doctor(id = 0, name = doctorNames.random(), lastName = doctorLastNames.random(),
                workDays = Week.values().toList(), workHours = ReceptionHour.values().random(),  age = Random.nextInt(18, 80), officeNumber = Random.nextInt(1, 10), specialization = DoctorSpecialization.values().random(), photo = doctorsPhotos.random()))
    }

    fun generateRandomPatient(): List<Patient>{
        return listOf(Patient(id = 0, name = patientNames.random(), lastName = patientsLastNames.random(),
                age = Random.nextInt(18, 80), photo = patientsPhotos.random(), diagnosis = PatientDiagnosis.values().random()))
    }

    fun generateRandomMedFacility(): List<MedFacility>{
        return listOf(MedFacility(id = 0, title = hospitalsTitles.random(), address = hospitalAddresses.random(),
            workDays = Week.values().toList(), photo = ""))
    }

    fun generateRandomOffices(): List<Office>{
        return listOf(Office())
    }
}