package ar.edu.unq.desapp.grupof.backenddesappapi.webservices

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoTransaction
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoTransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@EnableAutoConfiguration
class CryptoTransactionRestService {
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
//    @ResponseBody
    fun createCryptoQuote(@RequestBody cryptoTransaction : CryptoTransaction): ResponseEntity<*> {
        val list = cryptoQuoteService!!.create(cryptoTransaction)
        return ResponseEntity.ok().body(list)
    }

//    @RequestMapping(value = ["/custom"], method = [RequestMethod.POST])
//    fun custom(): String? {
//        return "custom"
//    }
}