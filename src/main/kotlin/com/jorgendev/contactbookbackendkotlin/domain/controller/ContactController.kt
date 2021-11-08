package com.jorgendev.contactbookbackendkotlin.domain.controller

import com.jorgendev.contactbookbackendkotlin.domain.model.Contact
import com.jorgendev.contactbookbackendkotlin.domain.repository.ContactRepository
import com.jorgendev.contactbookbackendkotlin.domain.service.ContactService
import com.jorgendev.contactbookbackendkotlin.exception.CustomException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/contacts")
class ContactController(private val contactService: ContactService, private val contactRepository: ContactRepository) {

    val log = LoggerFactory.getLogger(ContactController::class.java);

    @GetMapping
    fun getContacts(): List<Contact> = contactService.findContacts()

    @GetMapping("/find/{id}")
    fun getContactById(@PathVariable("id") id: Long): ResponseEntity<Contact> {
        val contact = contactService.findById(id)
        return ResponseEntity<Contact>(contact, HttpStatus.OK)
    }

    @PostMapping("/create")
    fun createContact(@RequestBody contact: Contact): ResponseEntity<Contact> {
        return ResponseEntity.ok(this.contactService.createContact(contact))
    }

    @PutMapping("/update/{id}")
    fun updateContact(@PathVariable("id") id: Long, @RequestBody contact: Contact): ResponseEntity<Contact> {
        //val contact = contactRepository.findById(id).orElseThrow { CustomException("Contact is not found!", HttpStatus.NOT_FOUND) }
        return contactRepository.findById(id).map { contactDetails ->
            val updatedContact: Contact = contactDetails.copy(
                fullName = contact.fullName,
                company = contact.company,
                phoneNumber = contact.phoneNumber,
                email = contact.email
            )
            ResponseEntity(contactRepository.save(updatedContact), HttpStatus.CREATED)
        }.orElseThrow { CustomException("There was an error serving this content!", HttpStatus.INTERNAL_SERVER_ERROR) }

    }

    @DeleteMapping("/delete/{id}")
    fun deleteContactById(@PathVariable("id") id: Long): Unit =
        contactService.deleteContactById(id)

  /*  @GetMapping("/search")
    fun searchContact(@RequestParam("contactName", defaultValue = "") contactName: String): ResponseEntity<List<Contact>> {
        val contacts: List<Contact> = contactService.searchContact(contactName)
        return ResponseEntity.ok(contacts)
    }*/
  /*@GetMapping("/search")
  fun searchContact(@RequestParam("contactName", defaultValue = "") contactName: String): ResponseEntity<List<Contact>> {

      return ResponseEntity.ok(this.contactRepository.searchContact(contactName)
  }*/


}