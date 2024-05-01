package com.doancoso3.management.repository

import com.doancoso3.management.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    // Sửa truy vấn SQL ở đây để sử dụng tên cột đúng
    @Query("SELECT u FROM User u")
    fun findAllUsers(): List<User>
}
