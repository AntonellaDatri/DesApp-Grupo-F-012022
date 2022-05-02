package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserControllerTests {
	@Autowired
	private val userController: UserController? = null

	@Test
	fun getAllUser() {
		val response = userController!!.allUsers()
		val list= response.body as ArrayList<*>
		assert(list.size == 2)
		val user1 = list[0] as User
		assert(user1.name.equals("Aldana"))
		assert(user1.walletAddress == 12345678)
		val user2 = list[1] as User
		assert(user2.name.equals("Antonella"))
		assert(user2.walletAddress == 12345679)
	}

	@Test
	fun getUser() {
		val user = userController!!.getUser(12345678).body
		assert(user?.name == "Aldana")
	}

	@Test
	fun registerUser() {
		val userToSave = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "Password@","1234567891234567891234",98765432,"Victoria 897")
		userController!!.registerUser(userToSave)
		val user = userController.getUser(98765432).body
		val userName = user?.name
		assert(userName == "Aldana")
		userController.deleteByID(98765432)
	}
}
