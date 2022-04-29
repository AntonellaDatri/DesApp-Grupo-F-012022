package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.ProcessedUserInformation
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class ProcessedUserInformationServiceTest {
    @Autowired
    private val processedUserInformationService: ProcessedUserInformationService? = null

    @Test
    fun findAllQuotes() {
        val bnbusdt = CryptoAssetQuoteService().findByCryptoName("BNBUSDT", LocalDateTime.now())
        val activityTransactionToSave = ProcessedUserInformation( "BNBUSDT",100.0, 120000.0, "Aldana Castro", 12345678)
        processedUserInformationService!!.create(activityTransactionToSave)
        val response = processedUserInformationService.findAll()
        val cryptoTransaction1 = response[0]
        assert(cryptoTransaction1!!.user == "Aldana Castro")
        assert(cryptoTransaction1.cryptoactive == "BNBUSDT")
    }

    @Test
    fun createAndFindCryptoQuote() {
        val bnbusdt = CryptoAssetQuoteService().findByCryptoName("BNBUSDT", LocalDateTime.now())
        val activityTransactionToSave = ProcessedUserInformation( "BNBUSDT",100.0, 120000.0, "Aldana Castro", 12345678)
        processedUserInformationService!!.create(activityTransactionToSave)
        val cryptoQuote = processedUserInformationService.findByID(activityTransactionToSave.id!!)
        assert(cryptoQuote.user == "Aldana Castro")
        processedUserInformationService.deleteById(activityTransactionToSave.id!!)
    }
}