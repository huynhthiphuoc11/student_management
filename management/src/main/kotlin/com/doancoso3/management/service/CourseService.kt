package com.doancoso3.management.service

import com.doancoso3.management.entity.Courses
import com.doancoso3.management.repository.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseService(@Autowired private val courseRepository: CourseRepository) {

    fun getAllCourses(): List<Courses> {
        return courseRepository.findAll()
    }

    fun createCourse(courseInfo: Courses): Courses {
        return courseRepository.save(courseInfo)
    }

    fun getCourseById(id: Long): Courses {
        return courseRepository.findById(id)
            .orElseThrow { NoSuchElementException("Course with id $id not found") }
    }

    fun updateCourse(id: Long, updatedCourse: Courses): Courses {
        val existingCourse = getCourseById(id)
        existingCourse.courseName = updatedCourse.courseName
        existingCourse.teacherId = updatedCourse.teacherId
        return courseRepository.save(existingCourse)
    }

    fun deleteCourse(id: Long) {
        courseRepository.deleteById(id)
    }
}
