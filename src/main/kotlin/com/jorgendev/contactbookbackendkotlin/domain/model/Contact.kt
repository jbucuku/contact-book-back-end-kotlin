package com.jorgendev.contactbookbackendkotlin.domain.model


import javax.persistence.*

@Entity
@Table(name = "contacts")
data class Contact(
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
){}
