package com.doancoso3.management.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

// Lớp Class
@Entity
@Table(name = "classes")
data class Classes(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "class_name")
    var className: String,

    @OneToMany(mappedBy = "userClassEntity", cascade = [CascadeType.ALL], orphanRemoval = true)
    var users: List<User> = mutableListOf() // Mỗi lớp có thể có nhiều người dùng
)