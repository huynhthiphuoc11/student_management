package com.doancoso3.management.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "notifications")
data class Notification(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "teacher_id")
    var teacherId: Long,

    @Column(name = "student_id")
    var studentId: Long,

    var content: String,

    @Column(name = "sent_date")
    var sentDate: String,

    var status: String
)
