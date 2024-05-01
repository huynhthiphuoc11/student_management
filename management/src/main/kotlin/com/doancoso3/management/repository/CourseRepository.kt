package com.doancoso3.management.repository

import com.doancoso3.management.entity.Courses
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Courses, Long>