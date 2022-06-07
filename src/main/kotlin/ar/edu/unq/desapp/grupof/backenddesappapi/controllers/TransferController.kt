package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Transfer
import ar.edu.unq.desapp.grupof.backenddesappapi.services.TransferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration
class TransferController {
    @Autowired
    private val activityTransactionService : TransferService? = null

    @GetMapping("/api/activities")
    fun getAll(): ResponseEntity<*> {
        val list = activityTransactionService!!.findAll()
        return ResponseEntity.ok().body(list)
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

    @PostMapping("/api/makeTransfer")
    fun confirmTransfer(@RequestBody transferID: Int, userID : Int): ResponseEntity.BodyBuilder {
        activityTransactionService!!.confirmTransfer(transferID, userID)
        return ResponseEntity.ok()
    }

    @PostMapping("/api/makeTransfer")
    fun cancelTransfer(@RequestBody transferID: Int, userID : Int): ResponseEntity.BodyBuilder {
        activityTransactionService!!.cancelTransfer(transferID, userID)
        return ResponseEntity.ok()
    }

    @PostMapping("/api/activity/create")
    fun createTransfer(@RequestBody transfer: Transfer): ResponseEntity<*> {
        val list = activityTransactionService!!.createTransfer(transfer)
        //Queremos que haya un DTO que muestre los datos de la transferencia dada en el enunciado.
        return ResponseEntity.ok().body(list)
    }
    @DeleteMapping("/api/activity/delete")
    fun deleteByID(id: Int) {
        activityTransactionService!!.deleteById(id)
    }
}