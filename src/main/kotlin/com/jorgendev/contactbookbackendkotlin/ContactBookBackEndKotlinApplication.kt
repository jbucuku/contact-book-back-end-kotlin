package com.jorgendev.contactbookbackendkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ContactBookBackEndKotlinApplication

fun main(args: Array<String>) {
    runApplication<ContactBookBackEndKotlinApplication>(*args)
}
