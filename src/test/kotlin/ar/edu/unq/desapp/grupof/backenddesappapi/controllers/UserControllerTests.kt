package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserControllerTests {
	@Autowired
	private val userController: UserController? = null
//	private val userFactory : UserFactory = UserFactory()

//	@Test
//	fun registerUser() {
//		val userToSave =userFactory.anyUser(walletAddress = 98765432)
//		userController!!.registerUser(userToSave)
//		val user = userController.getUser(98765432).body
//		val userName = (user as UserDTO).name
//		assert(userName == userToSave.name)
//	}
//	@Test
//	fun registerUserWithSameCVU() {
//		val userToSave = userFactory.anyUser(cvu = "1234567891234567891235", email = "ejemplo@mail.com", walletAddress = 11111111)
//		userController!!.registerUser(userToSave)
//		val userToSaveRepeat = userFactory.anyUser(cvu = "1234567891234567891235", email = "ejemplo2@mail.com", walletAddress = 22222222)
//		userController!!.registerUser(userToSave)
//		val hola = userController!!.registerUser(userToSaveRepeat).body
//		val response = userController.allUsers().body
//		assert("userName" == userController!!.registerUser(userToSaveRepeat).body )
//	}
//	@Test
//	fun getUser() {
//		val userToSave =userFactory.anyUser(walletAddress = 12345678)
//		userController!!.registerUser(userToSave)
//		val user = userController.getUser(12345678).body
//		val userName = (user as UserDTO).name
//		assert(userName == userToSave.name)
//	}
//	@Test
//	fun getAllUser() {
//		val user1 =userFactory.anyUser()
//		val user2 =userFactory.anyUser(cvu = "1111111111111111111122", email = "ejemplo2@mail.com", walletAddress = 22222222)
//
//		userController!!.registerUser(user1)
//		userController.registerUser(user2)
//		val response = userController.getUsers()
//		val list= response.body as ArrayList<*>
//		assert(list.size == 2)
//	}

	@AfterEach
	fun clear() {
		userController!!.deleteAll()
	}
}
