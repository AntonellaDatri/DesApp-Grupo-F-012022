package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoTransaction
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoTransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration
class CryptoTransactionController {
    @Autowired
    private val cryptoQuoteService : CryptoTransactionService? = null

    @GetMapping("/api/transactions")
    fun getAll(): ResponseEntity<*> {
        val list = cryptoQuoteService!!.findAll()
        return ResponseEntity.ok().body(list)
    }

    @GetMapping("/api/transaction")
    fun get(@RequestParam(required = true) id : Int): ResponseEntity<CryptoTransaction> {
        val list = cryptoQuoteService!!.findByID(id)
        return ResponseEntity.ok().body(list)
    }


    @PostMapping("/api/transaction/create")
    fun createCryptoQuote(@RequestBody cryptoTransaction : CryptoTransaction): ResponseEntity<*> {
        val list = cryptoQuoteService!!.create(cryptoTransaction)
        return ResponseEntity.ok().body(list)
    }

    fun deleteByID(id: Int) {
        cryptoQuoteService!!.deleteById(id)
    }
}