package ar.edu.unq.desapp.grupof.backenddesappapi.controllers


import ar.edu.unq.desapp.grupof.backenddesappapi.model.ActivityTransaction
import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoTransaction
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class ActivityTransactionControllerTests {
	@Autowired
	private val activityTransactionController: ActivityTransactionController? = null
	@Test
	fun getAllQuotes() {
		val bnbusdt = CryptoAssetQuoteService().findByCryptoName("BNBUSDT", LocalDateTime.now())
		val activityTransactionSave = ActivityTransaction("BNBUSDT", 3.0, bnbusdt!!.price.toDouble(), "Aldana Castro", "12:30", 12000,5)
		activityTransactionController!!.createCryptoQuote(activityTransactionSave)
		val response = activityTransactionController.getAll()
		val cryptoTransactions = response.body as ArrayList<*>
		val cryptoTransaction1 = cryptoTransactions[0] as CryptoTransaction
		assert(cryptoTransaction1.user == "Aldana Castro")
		assert(cryptoTransaction1.cryptoactive == "BNBUSDT")
		assert(cryptoTransaction1.id == activityTransactionSave.id)
	}

	@Test
	fun createAndGetCryptoQuote() {
		val bnbusdt = CryptoAssetQuoteService().findByCryptoName("BNBUSDT", LocalDateTime.now())
		val activityTransactionSave = ActivityTransaction("BNBUSDT", 3.0, bnbusdt!!.price.toDouble(), "Aldana Castro", "12:30", 12000,5)
		activityTransactionController!!.createCryptoQuote(activityTransactionSave)
		val cryptoQuote = activityTransactionController.get(activityTransactionSave.id!!).body
		assert(cryptoQuote!!.user == "Aldana Castro")
		activityTransactionController.deleteByID(activityTransactionSave.id!!)
	}
}
