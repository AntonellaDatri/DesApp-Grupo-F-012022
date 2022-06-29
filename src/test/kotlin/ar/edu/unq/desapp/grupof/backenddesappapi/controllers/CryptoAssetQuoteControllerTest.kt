package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoAssetQuote
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class CryptoAssetQuoteControllerTest {
    @Autowired
    private val cryptoAssetQuoteController : CryptoAssetQuoteController? = null
    @Test
    fun getCryptoAssetQuoteUpperCaseLast24Hours() {
        val response = cryptoAssetQuoteController!!.getLast24HoursCryptoAssetQuote("BNBUSDT")
        val quote =response.body as MutableList<CryptoAssetQuote>
        val quoteAssetName = quote[0].symbol
        assert(quoteAssetName == "BNBUSDT")
    }

    @Test
    fun getCryptoAssetQuoteLowerCaseLast24Hours() {
        val response = cryptoAssetQuoteController!!.getLast24HoursCryptoAssetQuote("bnbusdt")
        val quote =response.body as MutableList<CryptoAssetQuote>
        val quoteAssetName = quote[0].symbol
        assert(quoteAssetName == "BNBUSDT")
    }

    @Test
    fun getTenCryptoAssets() {
        val response = cryptoAssetQuoteController!!.getCryptoAssetsQuote().body
        (response as MutableList<CryptoAssetQuote>)
        assert(response.size == 14)
    }

    @Test
    fun cryptoWrongWriteLast24Hours() {
        assert(cryptoAssetQuoteController!!.getLast24HoursCryptoAssetQuote("bnbus").body == "No existe: bnbus")
    }

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
        assert(cryptoAssetQuoteController!!.getCryptoAssetQuote("bnbus").body == "No existe: bnbus")
    }
}