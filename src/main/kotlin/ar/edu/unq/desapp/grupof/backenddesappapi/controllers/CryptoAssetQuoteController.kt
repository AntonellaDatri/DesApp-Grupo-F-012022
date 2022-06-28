package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.aspect.LogExecutionTime
import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoAssetQuote
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CacheService
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@EnableAutoConfiguration
class CryptoAssetQuoteController {
    @Autowired
    private val cryptoAssetQuoteService: CryptoAssetQuoteService? = null
    @Autowired
    private val cacheService : CacheService? = null

    @LogExecutionTime
    @GetMapping("/api/cryptoQuote")
    fun getCryptoAssetQuote(@RequestParam(required = true) cryptoName : String): ResponseEntity<*> {
        return try {
            val cryptoQuote  =
                cryptoAssetQuoteService!!.findByCryptoName(cryptoName, LocalDateTime.now())
            ResponseEntity.ok().body(cryptoQuote)
        }  catch (e:IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @LogExecutionTime
    @GetMapping("/api/cryptoQuote/firstTen")
    fun getCryptoAssetsQuote(): ResponseEntity<*> {
        return try {
            val cryptosQuotes : Collection<CryptoAssetQuote> = getAllTenCryptos()
            ResponseEntity.ok().body(cryptosQuotes)
        }  catch (e:IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @Scheduled(cron = "*/10 * * * * *")
    fun passTenMinutes(){
        cacheService!!.updateTenMinutes()
    }

    @Transactional
    fun getAllTenCryptos(): Collection<CryptoAssetQuote> {
        return cacheService!!.getAllCache()
    }
}