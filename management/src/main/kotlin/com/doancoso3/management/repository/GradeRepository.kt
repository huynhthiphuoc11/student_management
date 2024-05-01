package com.doancoso3.management.repository

import com.doancoso3.management.entity.Grade
import org.springframework.data.jpa.repository.JpaRepository

interface GradeRepository : JpaRepository<Grade, Long>