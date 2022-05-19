package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoAssetQuote
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import net.bytebuddy.asm.Advice.Return
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
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
        val list : CryptoAssetQuote
          try {
              list = cryptoAssetQuoteService!!.findByCryptoName(cryptoName, LocalDateTime.now())
          }  catch (e:IllegalArgumentException) {
              return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message);
          }
        return ResponseEntity.ok().body(list)
    }

    @GetMapping("/api/10cryptoQuote")
    fun getCryptoAssetsQuote(): ResponseEntity<*> {
        val list : MutableList<CryptoAssetQuote>
        try {
            list = cryptoAssetQuoteService!!.getTenCryptoAssets(LocalDateTime.now())
        }  catch (e:IllegalArgumentException) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message);
        }
        return ResponseEntity.ok().body(list)
    }
}