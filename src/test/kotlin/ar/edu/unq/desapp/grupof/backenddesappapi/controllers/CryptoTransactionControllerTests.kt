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
		var allTransaction = cryptoQuoteController?.getAll()?.body as ArrayList<*>
		val allTransactionSizeBefore = allTransaction.size
		val bnbusdt = CryptoAssetQuoteService().findByCryptoName("BNBUSDT", LocalDateTime.now())
		val cryptoQuoteToSave = CryptoTransaction("BNBUSDT", 3.0, bnbusdt!!.price.toDouble(), "Aldana Castro")
		cryptoQuoteController!!.createCryptoQuote(cryptoQuoteToSave)

		allTransaction = cryptoQuoteController?.getAll()?.body as ArrayList<*>
		val allTransactionSizeAfter = allTransaction.size
		assert(allTransactionSizeBefore + 1  == allTransactionSizeAfter)
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
