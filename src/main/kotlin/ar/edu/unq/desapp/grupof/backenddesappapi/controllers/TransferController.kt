package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.*
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import ar.edu.unq.desapp.grupof.backenddesappapi.services.TransferService
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@EnableAutoConfiguration
class TransferController {
    @Autowired
    private val activityTransactionService : TransferService? = null
    @Autowired
    private val userService : UserService? = null
    @Autowired
    private val cryptoAssetQuoteService : CryptoAssetQuoteService? = null

    @GetMapping("/api/activities")
    fun getAll(): ResponseEntity<*> {
        val list = activityTransactionService!!.findAll()
        return ResponseEntity.ok().body(list)
    }

    @GetMapping("/api/activityBetween")
    fun getActivityBetween(@RequestParam(required = true) date1: String, date2: String, userId: Int): ResponseEntity<*> {
        val list = activityTransactionService!!.findBetween(userId, date1, date2)
        val user = userService!!.findByID(userId)
        val response = VolumeOperationsDTO(user,list)
        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/api/activity")
    fun get(@RequestParam(required = true) id : Int): ResponseEntity<Transfer> {
        val list = activityTransactionService!!.findByID(id)
        return ResponseEntity.ok().body(list)
    }


    @PostMapping("/api/makeTransfer")
    fun makeTransfer(@RequestParam transferID: Int, userID : Int): ResponseEntity<String> {
        activityTransactionService!!.makeTransfer(transferID, userID)
        return ResponseEntity.status( HttpStatus.OK).body("Successful transfer")
    }

    @PostMapping("/api/confirmTransfer")
    fun confirmTransfer(@RequestParam transferID: Int, userID : Int): ResponseEntity<String> {
        activityTransactionService!!.confirmTransfer(transferID, userID)
        return ResponseEntity.status( HttpStatus.OK).body("Confirmed transfer")

    }

    @PostMapping("/api/cancelTransfer")
    fun cancelTransfer(@RequestParam transferID: Int, userID : Int): ResponseEntity.BodyBuilder {
        activityTransactionService!!.cancelTransfer(transferID, userID)
        return ResponseEntity.ok()
    }

    @PostMapping("/api/activity/create")
    fun createTransfer(@RequestBody transferRequestDTO: TransferRequestDTO): ResponseEntity<*> {
        val transfer = activityTransactionService!!.createTransfer(transferRequestDTO)
        val criptsPrice =  cryptoAssetQuoteService!!.findByCryptoName(transfer.order.cryptoactive!! ,LocalDateTime.now())

        return ResponseEntity.ok().body(TransferDTO.fromModel(transfer, criptsPrice.price.toDouble()))
    }
    @DeleteMapping("/api/activity/delete")
    fun deleteByID(id: Int) {
        activityTransactionService!!.deleteById(id)
    }
}