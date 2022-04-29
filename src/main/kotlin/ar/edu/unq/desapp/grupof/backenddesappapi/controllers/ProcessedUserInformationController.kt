package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.ProcessedUserInformation
import ar.edu.unq.desapp.grupof.backenddesappapi.services.ProcessedUserInformationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration
class ProcessedUserInformationController {
    @Autowired
    private val processedUserInformationService : ProcessedUserInformationService? = null

    @GetMapping("/api/all-processed-users-information")
    fun getAll(): ResponseEntity<*> {
        val list = processedUserInformationService!!.findAll()
        return ResponseEntity.ok().body(list)
    }

    @GetMapping("/api/processed-users-information")
    fun get(@RequestParam(required = true) id : Int): ResponseEntity<ProcessedUserInformation> {
        val list = processedUserInformationService!!.findByID(id)
        return ResponseEntity.ok().body(list)
    }


    @PostMapping("/api/processed-users-information/create")
    fun createCryptoQuote(@RequestBody processedUserInformation : ProcessedUserInformation): ResponseEntity<*> {
        val list = processedUserInformationService!!.create(processedUserInformation)
        return ResponseEntity.ok().body(list)
    }
    @DeleteMapping("/api/processed-users-information/delete")
    fun deleteByID(id: Int) {
        processedUserInformationService!!.deleteById(id)
    }
}