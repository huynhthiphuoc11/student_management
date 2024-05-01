package com.doancoso3.management.controller

import com.doancoso3.management.entity.Classes
import com.doancoso3.management.service.ClassService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/classes")
class ClassController(@Autowired private val classService: ClassService) {

    @GetMapping
    fun getAllClasses(): ResponseEntity<List<Classes>> {
        val classes = classService.getAllClasses()
        return ResponseEntity(classes, HttpStatus.OK)
    }

    @PostMapping
    fun createClass(@RequestBody classInfo: Classes): ResponseEntity<Classes> {
        val createdClass = classService.createClass(classInfo)
        return ResponseEntity(createdClass, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getClassById(@PathVariable id: Long): ResponseEntity<Classes> {
        val classInfo = classService.getClassById(id)
        return ResponseEntity(classInfo, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateClass(@PathVariable id: Long, @RequestBody updatedClass: Classes): ResponseEntity<Classes> {
        val updatedInfo = classService.updateClass(id, updatedClass)
        return ResponseEntity(updatedInfo, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteClass(@PathVariable id: Long): ResponseEntity<Void> {
        classService.deleteClass(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
