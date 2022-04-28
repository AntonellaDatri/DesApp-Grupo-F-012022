package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoTransaction
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class CryptoTransactionServiceTest {
    @Autowired
    private val cryptoQuoteService: CryptoTransactionService? = null

    @Test
    fun findAllQuotes() {
        val bnbusdt = CryptoAssetQuoteService().findByCryptoName("BNBUSDT", LocalDateTime.now())
        val cryptoQuoteToSave = CryptoTransaction("BNBUSDT", 3.0, bnbusdt!!.price.toDouble(), "Aldana Castro")
        cryptoQuoteService!!.create(cryptoQuoteToSave)
        val response = cryptoQuoteService.findAll()
        val cryptoTransaction1 = response[0]
        assert(cryptoTransaction1!!.user == "Aldana Castro")
        assert(cryptoTransaction1.cryptoactive == "BNBUSDT")
    }

    @Test
    fun createAndFindCryptoQuote() {
        val bnbusdt = CryptoAssetQuoteService().findByCryptoName("BNBUSDT", LocalDateTime.now())
        val cryptoQuoteToSave = CryptoTransaction("BNBUSDT", 3.0, bnbusdt!!.price.toDouble(), "Aldana Castro")
        cryptoQuoteService!!.create(cryptoQuoteToSave)
        val cryptoQuote = cryptoQuoteService.findByID(cryptoQuoteToSave.id!!)
        assert(cryptoQuote.user == "Aldana Castro")
        cryptoQuoteService.deleteById(cryptoQuoteToSave.id!!)
    }
}