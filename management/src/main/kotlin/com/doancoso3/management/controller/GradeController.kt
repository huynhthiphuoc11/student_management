package com.doancoso3.management.controller

import com.doancoso3.management.entity.Grade
import com.doancoso3.management.service.GradeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/grades")
class GradeController(@Autowired private val gradeService: GradeService) {

    @GetMapping
    fun getAllGrades(): ResponseEntity<List<Grade>> {
        val grades = gradeService.getAllGrades()
        return ResponseEntity(grades, HttpStatus.OK)
    }

    @PostMapping
    fun createGrade(@RequestBody gradeInfo: Grade): ResponseEntity<Grade> {
        val createdGrade = gradeService.createGrade(gradeInfo)
        return ResponseEntity(createdGrade, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getGradeById(@PathVariable id: Long): ResponseEntity<Grade> {
        val gradeInfo = gradeService.getGradeById(id)
        return ResponseEntity(gradeInfo, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateGrade(@PathVariable id: Long, @RequestBody updatedGrade: Grade): ResponseEntity<Grade> {
        val updatedInfo = gradeService.updateGrade(id, updatedGrade)
        return ResponseEntity(updatedInfo, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteGrade(@PathVariable id: Long): ResponseEntity<Void> {
        gradeService.deleteGrade(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
