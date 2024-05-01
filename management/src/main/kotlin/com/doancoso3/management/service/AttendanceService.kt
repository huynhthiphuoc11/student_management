package com.doancoso3.management.service

import com.doancoso3.management.entity.Attendance
import com.doancoso3.management.repository.AttendanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AttendanceService(@Autowired private val attendanceRepository: AttendanceRepository) {

    fun getAllAttendance(): List<Attendance> {
        return attendanceRepository.findAll()
    }

    fun createAttendance(attendanceInfo: Attendance): Attendance {
        return attendanceRepository.save(attendanceInfo)
    }

    fun getAttendanceById(id: Long): Attendance {
        return attendanceRepository.findById(id)
            .orElseThrow { NoSuchElementException("Attendance with id $id not found") }
    }

    fun updateAttendance(id: Long, updatedAttendance: Attendance): Attendance {
        val existingAttendance = getAttendanceById(id)
        existingAttendance.studentId = updatedAttendance.studentId
        existingAttendance.classId = updatedAttendance.classId
        existingAttendance.courseId = updatedAttendance.courseId
        existingAttendance.attendanceDate = updatedAttendance.attendanceDate
        existingAttendance.status = updatedAttendance.status
        return attendanceRepository.save(existingAttendance)
    }

    fun deleteAttendance(id: Long) {
        attendanceRepository.deleteById(id)
    }
}
