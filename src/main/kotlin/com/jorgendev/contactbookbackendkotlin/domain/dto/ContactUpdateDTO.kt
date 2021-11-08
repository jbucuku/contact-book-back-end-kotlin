package com.jorgendev.contactbookbackendkotlin.domain.dto

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class ContactUpdateDTO(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "full_name", unique = true, nullable = false)
    val fullName: String,
    @Column(name = "company", nullable = false)
    val company: String,
    @Column(name = "phone_number", nullable = true)
    val phoneNumber: String?,
    @Column(name = "email", nullable = false)
    val email: String,
)