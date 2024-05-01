package com.doancoso3.management.controller

import com.doancoso3.management.entity.Attendance
import com.doancoso3.management.service.AttendanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/attendance")
class AttendanceController(@Autowired private val attendanceService: AttendanceService) {

    @GetMapping
    fun getAllAttendance(): ResponseEntity<List<Attendance>> {
        val attendance = attendanceService.getAllAttendance()
        return ResponseEntity(attendance, HttpStatus.OK)
    }

    @PostMapping
    fun createAttendance(@RequestBody attendanceInfo: Attendance): ResponseEntity<Attendance> {
        val createdAttendance = attendanceService.createAttendance(attendanceInfo)
        return ResponseEntity(createdAttendance, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getAttendanceById(@PathVariable id: Long): ResponseEntity<Attendance> {
        val attendanceInfo = attendanceService.getAttendanceById(id)
        return ResponseEntity(attendanceInfo, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateAttendance(@PathVariable id: Long, @RequestBody updatedAttendance: Attendance): ResponseEntity<Attendance> {
        val updatedInfo = attendanceService.updateAttendance(id, updatedAttendance)
        return ResponseEntity(updatedInfo, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteAttendance(@PathVariable id: Long): ResponseEntity<Void> {
        attendanceService.deleteAttendance(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
