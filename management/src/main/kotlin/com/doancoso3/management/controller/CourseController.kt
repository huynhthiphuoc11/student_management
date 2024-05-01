package com.doancoso3.management.controller

import com.doancoso3.management.entity.Courses
import com.doancoso3.management.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/courses")
class CourseController(@Autowired private val courseService: CourseService) {

    @GetMapping
    fun getAllCourses(): ResponseEntity<List<Courses>> {
        val courses = courseService.getAllCourses()
        return ResponseEntity(courses, HttpStatus.OK)
    }

    @PostMapping
    fun createCourse(@RequestBody courseInfo: Courses): ResponseEntity<Courses> {
        val createdCourse = courseService.createCourse(courseInfo)
        return ResponseEntity(createdCourse, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getCourseById(@PathVariable id: Long): ResponseEntity<Courses> {
        val courseInfo = courseService.getCourseById(id)
        return ResponseEntity(courseInfo, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateCourse(@PathVariable id: Long, @RequestBody updatedCourse: Courses): ResponseEntity<Courses> {
        val updatedInfo = courseService.updateCourse(id, updatedCourse)
        return ResponseEntity(updatedInfo, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteCourse(@PathVariable id: Long): ResponseEntity<Void> {
        courseService.deleteCourse(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
