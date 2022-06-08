package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Transfer
import ar.edu.unq.desapp.grupof.backenddesappapi.model.TransferActivesDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.TransferDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.VolumeOperationsDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.UserRepository
import ar.edu.unq.desapp.grupof.backenddesappapi.services.TransferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@EnableAutoConfiguration
class TransferController {
    @Autowired
    private val activityTransactionService : TransferService? = null
    @Autowired
    private val userRepository : UserRepository? = null

    @GetMapping("/api/activities")
    fun getAll(): ResponseEntity<*> {
        val list = activityTransactionService!!.findAll()
        return ResponseEntity.ok().body(list)
    }

    @GetMapping("/api/activityBetween")
    fun getActivityBetween(@RequestBody date1: Date, date2: Date, userId: Int): ResponseEntity<*> {
        val list = activityTransactionService!!.findBetween(userId, date1, date2)
        val listActives = mutableListOf<TransferActivesDTO>()
        list.forEach { listActives.add(TransferActivesDTO.fromModel(it!!)) }
        val user = userRepository!!.findById(userId).get()
        val response = VolumeOperationsDTO(user,listActives)
        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/api/activity")
    fun get(@RequestParam(required = true) id : Int): ResponseEntity<Transfer> {
        val list = activityTransactionService!!.findByID(id)
        return ResponseEntity.ok().body(list)
    }


    @PostMapping("/api/makeTransfer")
    fun makeTransfer(@RequestBody transferID: Int, userID : Int): ResponseEntity.BodyBuilder {
        activityTransactionService!!.makeTransfer(transferID, userID)
        return ResponseEntity.ok()
    }

    @PostMapping("/api/confirmTransfer")
    fun confirmTransfer(@RequestBody transferID: Int, userID : Int): ResponseEntity.BodyBuilder {
        activityTransactionService!!.confirmTransfer(transferID, userID)
        return ResponseEntity.ok()
    }

    @PostMapping("/api/cancelTransfer")
    fun cancelTransfer(@RequestBody transferID: Int, userID : Int): ResponseEntity.BodyBuilder {
        activityTransactionService!!.cancelTransfer(transferID, userID)
        return ResponseEntity.ok()
    }

    @PostMapping("/api/activity/create")
    fun createTransfer(@RequestBody transferRequest: Transfer): ResponseEntity<*> {
        val transfer = activityTransactionService!!.createTransfer(transferRequest)
        return ResponseEntity.ok().body(TransferDTO.fromModel(transfer))
    }
    @DeleteMapping("/api/activity/delete")
    fun deleteByID(id: Int) {
        activityTransactionService!!.deleteById(id)
    }
}