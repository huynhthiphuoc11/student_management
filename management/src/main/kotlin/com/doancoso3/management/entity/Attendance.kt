package com.doancoso3.management.entity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "attendance")
data class Attendance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "student_id")
    var studentId: Long,

    @Column(name = "class_id")
    var classId: Long,

    @Column(name = "course_id")
    var courseId: Long,

    @Column(name = "attendance_date")
    var attendanceDate: String,

    var status: String
)
