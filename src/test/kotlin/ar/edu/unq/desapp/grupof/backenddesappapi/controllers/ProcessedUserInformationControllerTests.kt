package ar.edu.unq.desapp.grupof.backenddesappapi.controllers


import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoTransaction
import ar.edu.unq.desapp.grupof.backenddesappapi.model.ProcessedUserInformation
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class ProcessedUserInformationControllerTests {
	@Autowired
	private val processedUserInformationController: ProcessedUserInformationController? = null
	@Test
	fun getAllQuotes() {
		val activityTransactionSave = ProcessedUserInformation( "BNBUSDT",100.0, 120000.0, "Aldana Castro", 12345678)
		processedUserInformationController!!.createCryptoQuote(activityTransactionSave)
		val response = processedUserInformationController.getAll()
		val activityTransactions = response.body as ArrayList<*>
		val activityTransaction1 = activityTransactions.last() as CryptoTransaction
		assert(activityTransaction1.user == "Aldana Castro")
		assert(activityTransaction1.cryptoactive == "BNBUSDT")
		assert(activityTransaction1.id == activityTransactionSave.id)
	}

	@Test
	fun createAndGetCryptoQuote() {
		val bnbusdt = CryptoAssetQuoteService().findByCryptoName("BNBUSDT", LocalDateTime.now())
		val activityTransactionSave = ProcessedUserInformation( "BNBUSDT",100.0, 120000.0, "Aldana Castro", 12345678)
		processedUserInformationController!!.createCryptoQuote(activityTransactionSave)
		val cryptoQuote = processedUserInformationController.get(activityTransactionSave.id!!).body
		assert(cryptoQuote!!.user == "Aldana Castro")
		processedUserInformationController.deleteByID(activityTransactionSave.id!!)
	}
}
