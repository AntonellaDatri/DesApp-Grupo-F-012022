package ar.edu.unq.desapp.grupof.backenddesappapi.webservices

import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableAutoConfiguration
class CryptoAssetQuoteRestService {
    @Autowired
    private val cryptoAssetQuoteService: CryptoAssetQuoteService? = null

    @GetMapping("/api/cryptoQuote")
    fun getCryptoAssetQuote(@RequestParam(required = true) cryptoName : String): ResponseEntity<*> {
        val list = cryptoAssetQuoteService!!.findByCryptoName(cryptoName)
        return ResponseEntity.ok().body(list)
    }
}