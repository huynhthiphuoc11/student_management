package com.doancoso3.management.service

import com.doancoso3.management.entity.Grade
import com.doancoso3.management.repository.GradeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GradeService(@Autowired private val gradeRepository: GradeRepository) {

    fun getAllGrades(): List<Grade> {
        return gradeRepository.findAll()
    }

    fun createGrade(gradeInfo: Grade): Grade {
        return gradeRepository.save(gradeInfo)
    }

    fun getGradeById(id: Long): Grade {
        return gradeRepository.findById(id)
            .orElseThrow { NoSuchElementException("Grade with id $id not found") }
    }

    fun updateGrade(id: Long, updatedGrade: Grade): Grade {
        val existingGrade = getGradeById(id)
        existingGrade.studentId = updatedGrade.studentId
        existingGrade.score = updatedGrade.score
        return gradeRepository.save(existingGrade)
    }

    fun deleteGrade(id: Long) {
        gradeRepository.deleteById(id)
    }
}
