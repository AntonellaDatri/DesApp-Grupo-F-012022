package ar.edu.unq.desapp.grupof.backenddesappapi.servicesTests

import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
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
        val quoteAssetName = response.symbol
        val price = response.price
        assert(quoteAssetName == "BNBUSDT")
        assert(price == response.price)
    }

    @Test
    fun getCryptoAssetQuoteLowerCas() {
        val response = cryptoAssetQuoteService!!.findByCryptoName("bnbusdt", LocalDateTime.now())
        val quoteAssetName = response.symbol
        val price = response.price
        assert(quoteAssetName == "BNBUSDT")
        assert(price == response.price)
    }

    @Test
    fun getTenCryptoAssets() {
        val response = cryptoAssetQuoteService!!.getTenCryptoAssets(LocalDateTime.now())
        assert(response.size == 14)
    }


    @Test
    fun cryptoWrongWrite() {
        assertThrows<IllegalArgumentException> { cryptoAssetQuoteService!!.findByCryptoName("bnbus", LocalDateTime.now()) }
    }
}