package com.jorgendev.contactbookbackendkotlin.domain.repository


import com.jorgendev.contactbookbackendkotlin.domain.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.awt.print.Book


@Repository
interface ContactRepository : JpaRepository<Contact, Long>  {

    // sorting by fullName
    fun findAllByOrderByFullNameAsc(): List<Contact>

    //search by name
    fun findByFullNameContainsOrCompanyContains(contactName: String, company: String)
    fun findByFullNameContains(contactName: String)

    fun findContactsByFullNameOrCompanyContains(contactName: String, company: String)

    @Query("SELECT c FROM Contact c WHERE c.fullName LIKE %?1%")
    fun searchContact(keyword: String): List<Contact>

}