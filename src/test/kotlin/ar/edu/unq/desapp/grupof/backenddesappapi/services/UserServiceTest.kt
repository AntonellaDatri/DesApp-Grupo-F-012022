package ar.edu.unq.desapp.grupof.backenddesappapi.services

import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest {
	@Autowired
	private val userService: UserService? = null
//	private val userFactory : UserFactory = UserFactory()
//	@Test
//	fun registerUser() {
//		val userToSave = userFactory.anyUser()
//		userService!!.register(UserDTO.fromModel(userToSave))
//		assertDoesNotThrow { userService.register(userToSave) }
//	}
//
//	@Test
//	fun findById() {
//		val userToSave = userFactory.anyUser(walletAddress = 12345677)
//		val userSaved = userService!!.register(userToSave)
//		val user = userService.findByID(userSaved.id!!)
//		assert(user.walletAddress == 12345677)
//	}
//	@Test
//	fun findByWalletAddess() {
//		val userToSave = userFactory.anyUser(walletAddress = 12345677)
//		userService!!.register(userToSave)
//		val user = userService.findByWalletAddress(12345677)
//		assert(user.walletAddress == 12345677)
//	}
//
//	@Test
//	fun registerUserSameCVU() {
//		val userToSave = userFactory.anyUser(cvu = "1234567891234567891235", email = "ejemplo@mail.com", walletAddress = 11111111)
//		userService!!.register(userToSave)
//		val userToSaveRepeat = userFactory.anyUser(cvu = "1234567891234567891235", email = "ejemplo2@mail.com", walletAddress = 22222222)
//		assertThrows<DataIntegrityViolationException> { userService.register(userToSaveRepeat) }
//	}
//
//	@Test
//	fun registerUserSameEmail() {
//		val userToSave = userFactory.anyUser(cvu = "1111111111111111111111", email = "ejemplo@mail.com", walletAddress = 11111111)
//		userService!!.register(userToSave)
//		val userToSaveRepeat = userFactory.anyUser(cvu = "1111111111111111111122", email = "ejemplo@mail.com", walletAddress = 22222222)
//		assertThrows<DataIntegrityViolationException> { userService.register(userToSaveRepeat) }
//	}
//
//
//	@Test
//	fun findAllUser() {
//		val user1 =userFactory.anyUser()
//		val user2 =userFactory.anyUser(cvu = "1111111111111111111122", email = "ejemplo2@mail.com", walletAddress = 22222222)
//
//		userService!!.register(user1)
//		userService.register(user2)
//		val list = userService.findAll()
//		assert(list.size == 2)
//	}
//
//	@Test
//	fun invalidPasswordUserNoUppercase() {
//		val user = userFactory.anyUser(password = "password@")
//		assertThrows<Exception> { userService!!.register(user) }
//	}
//
//	@Test
//	fun invalidPasswordUserNoLowercase() {
//		val user = userFactory.anyUser(password = "PASSWORD@")
//		assertThrows<Exception> { userService!!.register(user) }
//	}
//
//	@Test
//	fun invalidPasswordUserNoSpecialCharacter() {
//		val user = userFactory.anyUser(password = "Password")
//		assertThrows<Exception> { userService!!.register(user) }
//	}
//
//	@Test
//	fun invalidPasswordUserLessMinimum() {
//		val user = userFactory.anyUser(password = "Pass@")
//		assertThrows<Exception> { userService!!.register(user) }
//	}

	@AfterEach
	fun clear() {
		userService!!.clear()
	}
}
