package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@EnableAutoConfiguration
class CryptoAssetQuoteController {
    @Autowired
    private val cryptoAssetQuoteService: CryptoAssetQuoteService? = null

    @GetMapping("/api/cryptoQuote")
    fun getCryptoAssetQuote(@RequestParam(required = true) cryptoName : String): ResponseEntity<*> {
        val list = cryptoAssetQuoteService!!.findByCryptoName(cryptoName, LocalDateTime.now())
        return ResponseEntity.ok().body(list)
    }
}