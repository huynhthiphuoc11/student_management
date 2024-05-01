package com.doancoso3.management.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import com.fasterxml.jackson.annotation.JsonIgnore


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var name: String,

    var email: String,

    var address: String? = null,

    @Column(name = "contact_no")
    var contactNo: String? = null,

    var role: Int,

    @Column(name = "class_name")
    var userClass: String? = null,

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    @JsonIgnore // Ngăn Spring Boot Serialize trường này thành JSON
    var userClassEntity: Classes? = null,

    @Column(name = "password_hash")
    var passwordHash: String? = null
)