package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.IntentionToOperate
import ar.edu.unq.desapp.grupof.backenddesappapi.model.IntentionToOperateDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.services.IntentionToOperateService
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration
class IntentionToOperateController {
    @Autowired
    private val cryptoQuoteService : IntentionToOperateService? = null
    @Autowired
    private val userService : UserService = UserService()

    @GetMapping("/api/transactions")
    fun getAll(): ResponseEntity<*> {
        val list = cryptoQuoteService!!.findAll()
        return ResponseEntity.ok().body(list)
    }

    @GetMapping("/api/transaction")
    fun get(@RequestParam(required = true) id : Int): ResponseEntity<IntentionToOperate> {
        val list = cryptoQuoteService!!.findByID(id)
        return ResponseEntity.ok().body(list)
    }

    @PostMapping("/api/transaction/create")
    fun createIntention(@RequestBody cryptoTransactionDTO : IntentionToOperateDTO): ResponseEntity<*> {
        val cryptoTransaction : IntentionToOperate
        val userId : Int
        try {
            userId = cryptoTransactionDTO.walletAddress
            userService.findByID(userId)
            cryptoTransaction = IntentionToOperate(cryptoTransactionDTO.cryptoActive, cryptoTransactionDTO.amount, userId, cryptoTransactionDTO.operation)
        } catch(e:Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
        val list = cryptoQuoteService!!.create(cryptoTransaction)
        return ResponseEntity.ok().body(list)
    }

    fun deleteByID(id: Int) {
        cryptoQuoteService!!.deleteById(id)
    }
}