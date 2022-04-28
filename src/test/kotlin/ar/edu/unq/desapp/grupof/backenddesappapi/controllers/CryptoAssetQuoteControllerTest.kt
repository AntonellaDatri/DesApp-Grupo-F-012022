package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoAssetQuote
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class CryptoAssetQuoteControllerTest {
    @Autowired
    private val cryptoAssetQuoteController : CryptoAssetQuoteController? = null
    @Test
    fun getCryptoAssetQuoteUpperCase() {
        val response = cryptoAssetQuoteController!!.getCryptoAssetQuote("BNBUSDT")
        val quote =response.body as CryptoAssetQuote
        val quoteAssetName = quote.symbol
        val price = quote.price
        assert(quoteAssetName == "BNBUSDT")
        assert(price == quote.price)
        assert(quote.dateTime.toLocalDate().isEqual(LocalDateTime.now().toLocalDate()))
    }

    @Test
    fun getCryptoAssetQuoteLowerCase() {
        val response = cryptoAssetQuoteController!!.getCryptoAssetQuote("bnbusdt")
        val quote =response.body as CryptoAssetQuote
        val quoteAssetName = quote.symbol
        val price = quote.price
        assert(quoteAssetName == "BNBUSDT")
        assert(price == quote.price)
        assert(quote.dateTime.toLocalDate().isEqual(LocalDateTime.now().toLocalDate()))
    }

    @Test
    fun cryptoWrongWrite() {
        assertThrows<IllegalArgumentException> { cryptoAssetQuoteController!!.getCryptoAssetQuote("bnbus") }
    }
}