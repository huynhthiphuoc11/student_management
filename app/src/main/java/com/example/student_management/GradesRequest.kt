package com.example.student_management

data class GradesRequest(
    val studentId: Int,
    val scholasticId: Int,
    val courseId: Int,
    val frequentScore1: Double,
    val frequentScore2: Double,
    val frequentScore3: Double,
    val frequentScore4: Double,
    val frequentScore5: Double,
    val midtermScore: Double,
    val finalScore: Double,
    val comments: String?,
    val courseName: String?,
    val nameUser: String?
)
