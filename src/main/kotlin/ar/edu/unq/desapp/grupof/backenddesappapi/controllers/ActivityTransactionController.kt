package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.ActivityTransaction
import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoTransaction
import ar.edu.unq.desapp.grupof.backenddesappapi.services.ActivityTransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration
class ActivityTransactionController {
    @Autowired
    private val activityTransactionService : ActivityTransactionService? = null

    @GetMapping("/api/activities")
    fun getAll(): ResponseEntity<*> {
        val list = activityTransactionService!!.findAll()
        return ResponseEntity.ok().body(list)
    }

    @GetMapping("/api/activity")
    fun get(@RequestParam(required = true) id : Int): ResponseEntity<CryptoTransaction> {
        val list = activityTransactionService!!.findByID(id)
        return ResponseEntity.ok().body(list)
    }


    @PostMapping("/api/activity/create")
    fun createCryptoQuote(@RequestBody activityTransaction : ActivityTransaction): ResponseEntity<*> {
        val list = activityTransactionService!!.create(activityTransaction)
        return ResponseEntity.ok().body(list)
    }
    @DeleteMapping("/api/activity/delete")
    fun deleteByID(id: Int) {
        activityTransactionService!!.deleteById(id)
    }
}