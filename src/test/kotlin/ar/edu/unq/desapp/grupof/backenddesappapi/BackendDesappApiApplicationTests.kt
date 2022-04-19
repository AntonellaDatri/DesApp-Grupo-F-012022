package ar.edu.unq.desapp.grupof.backenddesappapi

import ar.edu.unq.desapp.grupof.backenddesappapi.user.RegisterUser
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BackendDesappApiApplicationTests {
	val registerUser = RegisterUser()
	@Test
	fun createUser() {
		registerUser.registerUser("Aldana", "Castro", "aldanacastro1999@gmail.com", "Victoria 897","password","1234567891234567891234", 12345678)
		var user = registerUser.getUserBy(12345678)
		var userName = user?.name
		assert(userName == "Aldana")
	}

}
