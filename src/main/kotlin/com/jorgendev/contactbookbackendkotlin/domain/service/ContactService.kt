package com.jorgendev.contactbookbackendkotlin.domain.service

import com.jorgendev.contactbookbackendkotlin.domain.model.Contact
import com.jorgendev.contactbookbackendkotlin.domain.repository.ContactRepository
import com.jorgendev.contactbookbackendkotlin.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*


@Service
class ContactService(private val contactRepository: ContactRepository) {

    fun findContacts(): List<Contact> = contactRepository.findAllByOrderByFullNameAsc()

    fun createContact(contact: Contact): Contact = this.contactRepository.save(contact)

    fun findById(id: Long): Contact = contactRepository.findById(id)
        .orElseThrow { CustomException("Contact is not found!", HttpStatus.NOT_FOUND) }

    //fun updateContact(contact: Contact): Contact = contactRepository.save(contact)

    /*fun updateContact(id: Long, contact: Contact): Contact{

        return if (contactRepository.existsById(id)){
            contactRepository.save(
                Contact(
                    id = contact.id,
                    fullName = contact.fullName,
                    company = contact.company,
                    phoneNumber = contact.phoneNumber,
                    email = contact.email,
                )
            )
        }else throw CustomException("",HttpStatus.NOT_FOUND)
    }*/


    fun deleteContactById(contactId: Long) {
        return if (contactRepository.existsById(contactId)) {
            contactRepository.deleteById(contactId)
            println("Contact with id $contactId deleted successfully!")
        } else throw CustomException("Contact with id $contactId is not found!", HttpStatus.NOT_FOUND)
    }

   /*  fun searchContact(contactName: String): List<Contact> {
 
         if(contactName != null) {
 
            contactRepository.findByFullNameContains(contactName)
         }
 
         return contactRepository.findAll()
     }*/


}