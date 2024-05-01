package com.doancoso3.management.repository

import com.doancoso3.management.entity.Attendance
import org.springframework.data.jpa.repository.JpaRepository

interface AttendanceRepository  : JpaRepository<Attendance, Long>