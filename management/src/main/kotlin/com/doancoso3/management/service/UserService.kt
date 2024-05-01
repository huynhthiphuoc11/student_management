package com.doancoso3.management.service

import com.doancoso3.management.entity.User
import com.doancoso3.management.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

    fun getAllUsers(): List<User> {
        return userRepository.findAllUsers()
    }

    fun createUser(userInfo: User): User {
        return userRepository.save(userInfo)
    }

    fun getUserById(id: Long): User {
        return userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User with id $id not found") }
    }

    fun updateUser(id: Long, updatedUser: User): User {
        val existingUser = getUserById(id)
        existingUser.name = updatedUser.name
        existingUser.email = updatedUser.email
        existingUser.address = updatedUser.address
        existingUser.contactNo = updatedUser.contactNo
        existingUser.role = updatedUser.role
        return userRepository.save(existingUser)
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}
