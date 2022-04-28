package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest {
	@Autowired
	private val userService: UserService? = null

	@Test
	fun findByID() {
		val user = userService!!.findByID(12345678)
		assert(user.name == "Aldana")
	}
	@Test
	fun registerUser() {
		val userToSave = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "password","1234567891234567891234",12345677,"Victoria 897")
		userService!!.register(userToSave)
		val userSaved = userService.findByID(12345677)
		val userName = userSaved.name
		assert(userName == "Aldana")
		userService.deleteByID(12345677)
	}

	@Test
	fun findAllUser() {
		val list = userService!!.findAll()
		assert(list.size == 2)
		val user1 = list[0]
		assert(user1!!.name.equals("Aldana"))
		assert(user1.walletAddress == 12345678)
		val user2 = list[1]
		assert(user2!!.name.equals("Antonella"))
		assert(user2.walletAddress == 12345679)
	}
}
