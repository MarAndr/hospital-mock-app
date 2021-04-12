package com.example.roomdao.hospitalDatabase.doctors

enum class ReceptionHour(val tag: String? = null) {
    MORNING(tag = "8:00 - 12:00"),
    AFTERNOON(tag = "12:00 - 16:00"),
    EVENING(tag = "16:00 - 20:00")
}