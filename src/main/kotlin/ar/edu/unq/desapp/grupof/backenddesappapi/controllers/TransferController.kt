package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.dto.TransferRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.services.TransferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration
class TransferController {
    @Autowired
    private val transferService : TransferService? = null

    @PostMapping("/api/transfer/create")
    fun createTransfer(@RequestBody transferRequestDTO: TransferRequestDTO): ResponseEntity<*> {
        return try {
            //Using a DTO request to only ask the necessary information and not use all the transfer.
            val transferDTO = transferService!!.createTransfer(transferRequestDTO)
            //Return a DTO to show only the necessary things.
            ResponseEntity.ok().body(transferDTO)
        }catch (e:Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }

    }

    @PostMapping("/api/transfer/make")
    fun makeTransfer(@RequestParam transferID: Int, userID : Int): ResponseEntity<String> {
        try {
            transferService!!.makeTransfer(transferID, userID)
        }catch (e:Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
        return ResponseEntity.status( HttpStatus.OK).body("Successful transfer")
    }

    @PostMapping("/api/transfer/confirm")
    fun confirmTransfer(@RequestParam transferID: Int, userID : Int): ResponseEntity<String> {
        try {
            transferService!!.confirmTransfer(transferID, userID)
        }catch (e:Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
        return ResponseEntity.status( HttpStatus.OK).body("Confirmed transfer")

    }

    @PostMapping("/api/transfer/cancel")
    fun cancelTransfer(@RequestParam transferID: Int, userID : Int): ResponseEntity<*> {
        try {
            transferService!!.cancelTransfer(transferID, userID)
        }catch (e:Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
        return ResponseEntity.status( HttpStatus.OK).body("Transfer canceled")
    }

    @GetMapping("/api/transfer/activityBetween")
    fun getActivityBetween(@RequestParam(required = true) date1: String, date2: String, userId: Int): ResponseEntity<*> {
        val volumeOperationsDTO = transferService!!.getVolumeOperation(userId, date1, date2)
        return ResponseEntity.ok().body(volumeOperationsDTO)
    }

    @GetMapping("/api/transfer")
    fun get(@RequestParam(required = true) id : Int): ResponseEntity<*> {
        return try {
            val transfer = transferService!!.findByID(id)
            ResponseEntity.ok().body(transfer)
        }catch (e:Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping("/api/transfer/all")
    fun getAll(): ResponseEntity<*> {
        val transfersDTO = transferService!!.findAll()
        return ResponseEntity.ok().body(transfersDTO)
    }

    @DeleteMapping("/api/transfer/delete")
    fun deleteByID(id: Int) {
        transferService!!.deleteById(id)
    }
}