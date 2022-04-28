package ar.edu.unq.desapp.grupof.backenddesappapi

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoTransaction
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import ar.edu.unq.desapp.grupof.backenddesappapi.webservices.CryptoTransactionRestService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class CryptoTransactionRestServiceTests {
	@Autowired
	private val cryptoQuoteRestService: CryptoTransactionRestService? = null
	@Test
	fun createAndGetCryptoQuote() {
		val bnbusdt =CryptoAssetQuoteService().findByCryptoName ("BNBUSDT")

		val cryptoQuoteToSave = CryptoTransaction("BNBUSDT", 3.0, bnbusdt!!.price.toDouble(), "Aldana Castro")
		cryptoQuoteRestService!!.createCryptoQuote(cryptoQuoteToSave)
		val cryptoQuote = cryptoQuoteRestService.get(cryptoQuoteToSave.id!!).body

		assert(cryptoQuote!!.user == "Aldana Castro")
	}
}
