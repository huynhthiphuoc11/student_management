package com.doancoso3.management.service

import com.doancoso3.management.entity.Classes
import com.doancoso3.management.repository.ClassRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClassService(@Autowired private val classRepository: ClassRepository) {

    fun getAllClasses(): List<Classes> {
        return classRepository.findAll()
    }

    fun createClass(classInfo: Classes): Classes {
        return classRepository.save(classInfo)
    }

    fun getClassById(id: Long): Classes {
        return classRepository.findById(id)
            .orElseThrow { NoSuchElementException("Class with id $id not found") }
    }

    fun updateClass(id: Long, updatedClass: Classes): Classes {
        val existingClass = getClassById(id)
        existingClass.className = updatedClass.className
        return classRepository.save(existingClass)
    }

    fun deleteClass(id: Long) {
        classRepository.deleteById(id)
    }
}
