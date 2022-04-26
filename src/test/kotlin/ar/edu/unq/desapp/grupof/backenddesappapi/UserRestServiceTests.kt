package ar.edu.unq.desapp.grupof.backenddesappapi

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import ar.edu.unq.desapp.grupof.backenddesappapi.webservices.UserRestService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class UserRestServiceTests {
	@Autowired
	private val userRestService: UserRestService? = null
	@Test
	fun registerUser() {
		val userToSave = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "password","1234567891234567891234",98765432,"Victoria 897")
		userRestService!!.registerUser(userToSave)
		val user = userRestService.getUser(98765432).body
		val userName = user?.name

		assert(userName == "Aldana")
	}
}
