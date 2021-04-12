package com.example.roomdao.hospitalDatabase

sealed class AddingItemsState{
    class DOCTOR_AD(val message: String): AddingItemsState()
    class PATIENT_AD(val message: String): AddingItemsState()
    class FACILITY_AD(val message: String): AddingItemsState()
    class OFFICE_AD(val message: String): AddingItemsState()
    class RECORDING_AD(val message: String): AddingItemsState()
    class DOCTORS_DEL_ALL(val message: String): AddingItemsState()
    class PATIENTS_DEL_ALL(val message: String): AddingItemsState()
    class MEDFAC_DEL_ALL(val message: String): AddingItemsState()
    class OFFICES_DEL_ALL(val message: String): AddingItemsState()
    class RECORDINGS_DEL_ALL(val message: String): AddingItemsState()
    class ERROR(val message: String): AddingItemsState()
    object EMPTY: AddingItemsState()
}
