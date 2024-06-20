package com.example.student_management


data class Schedule(
    val dayOfWeek: DayOfWeek,
    val startTime: String,
    val endTime: String,
    val course: String

)


enum class DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}