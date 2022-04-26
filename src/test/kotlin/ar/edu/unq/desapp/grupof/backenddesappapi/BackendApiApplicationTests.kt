package ar.edu.unq.desapp.grupof.backenddesappapi

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class BackendApiApplicationTests {
	@Autowired
	private val userService: UserService? = null
	@Test
	fun createUser() {
		val userToSave = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "password","1234567891234567891234",12345678,"Victoria 897")
		userService!!.register(userToSave)
		val userSaved = userService.findByID(12345678)
		val userName = userSaved.name
		assert(userName == "Aldana")
	}

	@Test
	fun registerUser() {
		val userToSave = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "password","1234567891234567891234",12345678,"Victoria 897")
		userService!!.register(userToSave)
		val userSaved = userService.findByID(12345678)
		val userName = userSaved.name
		assert(userName == "Aldana")
	}
	@Test
	fun getCryptoAssetQuote() {
		val response =CryptoAssetQuoteService().findByCryptoName ("BNBUSDT")
		val quoteAssetName = response?.symbol
		val price = response?.price
		assert(quoteAssetName != null && quoteAssetName == "BNBUSDT")
		assert(price != null && price == response.price)
	}
}
