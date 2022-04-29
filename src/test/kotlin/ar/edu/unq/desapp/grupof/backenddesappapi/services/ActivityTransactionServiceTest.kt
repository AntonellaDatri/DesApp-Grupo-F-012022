package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.ActivityTransaction
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class ActivityTransactionServiceTest {
    @Autowired
    private val activityTransactionService: ActivityTransactionService? = null

    @Test
    fun findAllQuotes() {
        val bnbusdt = CryptoAssetQuoteService().findByCryptoName("BNBUSDT", LocalDateTime.now())
        val activityTransactionToSave =  ActivityTransaction( "BNBUSDT",100.0, 120000.0, "Aldana Castro","12:30", 12000,5)
        activityTransactionService!!.create(activityTransactionToSave)
        val response = activityTransactionService.findAll()
        val cryptoTransaction1 = response[0]
        assert(cryptoTransaction1!!.user == "Aldana Castro")
        assert(cryptoTransaction1.cryptoactive == "BNBUSDT")
    }

    @Test
    fun createAndFindCryptoQuote() {
        val bnbusdt = CryptoAssetQuoteService().findByCryptoName("BNBUSDT", LocalDateTime.now())
        val activityTransactionToSave =  ActivityTransaction( "BNBUSDT",100.0, 120000.0, "Aldana Castro","12:30", 12000,5)
        activityTransactionService!!.create(activityTransactionToSave)
        val cryptoQuote = activityTransactionService.findByID(activityTransactionToSave.id!!)
        assert(cryptoQuote.user == "Aldana Castro")
        activityTransactionService.deleteById(activityTransactionToSave.id!!)
    }
}