package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.*
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import ar.edu.unq.desapp.grupof.backenddesappapi.services.TransferService
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
        activityTransactionService!!.makeTransfer(transferID, userID)
        return ResponseEntity.status( HttpStatus.OK).body("Successful transfer")
    }

    @PostMapping("/api/confirmTransfer")
    fun confirmTransfer(@RequestParam transferID: Int, userID : Int): ResponseEntity<String> {
        activityTransactionService!!.confirmTransfer(transferID, userID)
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