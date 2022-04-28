package ar.edu.unq.desapp.grupof.backenddesappapi.controllers


import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoTransaction
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class CryptoTransactionControllerTests {
	@Autowired
	private val cryptoQuoteController: CryptoTransactionController? = null
	@Test
	fun getAllQuotes() {
		val bnbusdt = CryptoAssetQuoteService().findByCryptoName("BNBUSDT", LocalDateTime.now())
		val cryptoQuoteToSave = CryptoTransaction("BNBUSDT", 3.0, bnbusdt!!.price.toDouble(), "Aldana Castro")
		cryptoQuoteController!!.createCryptoQuote(cryptoQuoteToSave)
		val response = cryptoQuoteController.getAll()
		val cryptoTransactions = response.body as ArrayList<*>
		val cryptoTransaction1 = cryptoTransactions[0] as CryptoTransaction
		assert(cryptoTransaction1.user == "Aldana Castro")
		assert(cryptoTransaction1.cryptoactive == "BNBUSDT")
		assert(cryptoTransaction1.id == cryptoQuoteToSave.id)
	}

	@Test
	fun createAndGetCryptoQuote() {
		val bnbusdt = CryptoAssetQuoteService().findByCryptoName("BNBUSDT", LocalDateTime.now())
		val cryptoQuoteToSave = CryptoTransaction("BNBUSDT", 3.0, bnbusdt!!.price.toDouble(), "Aldana Castro")
		cryptoQuoteController!!.createCryptoQuote(cryptoQuoteToSave)
		val cryptoQuote = cryptoQuoteController.get(cryptoQuoteToSave.id!!).body
		assert(cryptoQuote!!.user == "Aldana Castro")
		cryptoQuoteController.deleteByID(cryptoQuoteToSave.id!!)
	}
}
