package ar.edu.unq.desapp.grupof.backenddesappapi.services

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class CryptoAssetQuoteServiceTest {
    @Autowired
    private val cryptoAssetQuoteService : CryptoAssetQuoteService? = null
    @Test
    fun getCryptoAssetQuoteUpperCase() {
        val response = cryptoAssetQuoteService!!.findByCryptoName("BNBUSDT", LocalDateTime.now())
        val quoteAssetName = response?.symbol
        val price = response?.price
        assert(quoteAssetName != null && quoteAssetName == "BNBUSDT")
        assert(price != null && price == response.price)
    }

    @Test
    fun getCryptoAssetQuoteLowerCas() {
        val response = cryptoAssetQuoteService!!.findByCryptoName("bnbusdt", LocalDateTime.now())
        val quoteAssetName = response?.symbol
        val price = response?.price
        assert(quoteAssetName != null && quoteAssetName == "BNBUSDT")
        assert(price != null && price == response.price)
    }

    @Test
    fun cryptoWrongWrite() {
        assertThrows<IllegalArgumentException> { cryptoAssetQuoteService!!.findByCryptoName("bnbus", LocalDateTime.now()) }
    }
}