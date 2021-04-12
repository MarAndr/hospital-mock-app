package com.example.roomdao.hospitalDatabase.doctors

object DoctorsContract {
    const val TABLE_NAME = "doctors_table"
    object Columns{
        const val ID = "id"
        const val NAME = "name"
        const val LAST_NAME = "lastname"
        const val MEDFACILITIES_ID = "medFacilities_id"
        const val WORK_SCHEDULES_ID = "workSchedules_id"
        const val WORK_DAYS = "work_days"
        const val WORK_HOURS = "work_hours"
        const val AGE = "age"
        const val OFFICE_NUMBER = "office_number"
        const val SPECIALIZATION = "specialization"
        const val PHOTO = "photo"
    }
}