package com.doancoso3.management.repository

import com.doancoso3.management.entity.Classes
import org.springframework.data.jpa.repository.JpaRepository

interface ClassRepository : JpaRepository<Classes, Long>