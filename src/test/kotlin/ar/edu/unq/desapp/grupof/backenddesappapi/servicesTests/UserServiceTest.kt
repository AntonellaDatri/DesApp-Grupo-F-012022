package ar.edu.unq.desapp.grupof.backenddesappapi.servicesTests

import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.UserFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.junit.jupiter.api.assertThrows


@SpringBootTest
class UserServiceTest {
	@Autowired
	private val userService: UserService? = null
	private val userFactory : UserFactory = UserFactory()

	@Test
	fun registerUser() {
	val userToSave = UserRequestDTO.fromModel(userFactory.anyUser())
		assertDoesNotThrow { userService!!.register(userToSave) }
	}

	@Test
	fun findById() {
		val user = userFactory.anyUser(walletAddress = 12345677)
		val userToSave = UserRequestDTO.fromModel(user)
		val userSaved = userService!!.register(userToSave)
		val res = userService.findByID(userSaved.id)
		assert(res.walletAddress == 12345677)
	}

	@Test
	fun findByWalletAddess() {
		val user = userFactory.anyUser(walletAddress = 12345677)
		val userToSave = UserRequestDTO.fromModel(user)
		userService!!.register(userToSave)
		val res = userService.findByWalletAddress(12345677)
		assert(res.walletAddress == 12345677)
	}

	@Test
	fun registerUserSameCVU() {
		val user = userFactory.anyUser(cvu = "1234567891234567891235", email = "ejemplo@mail.com", walletAddress = 11111111)
		val userToSave = UserRequestDTO.fromModel(user)
		userService!!.register(userToSave)
		val userRepeat = userFactory.anyUser(cvu = "1234567891234567891235", email = "ejemplo2@mail.com", walletAddress = 22222222)
		val userToSaveRepeat = UserRequestDTO.fromModel(userRepeat)
		assertThrows<DataIntegrityViolationException> { userService.register(userToSaveRepeat) }
	}

	@Test
	fun registerUserSameEmail() {
		val user = userFactory.anyUser(cvu = "1111111111111111111111", email = "ejemplo@mail.com", walletAddress = 11111111)
		val userToSave = UserRequestDTO.fromModel(user)
		userService!!.register(userToSave)
		val userRepeat = userFactory.anyUser(cvu = "1111111111111111111122", email = "ejemplo@mail.com", walletAddress = 22222222)
		val userToSaveRepeat = UserRequestDTO.fromModel(userRepeat)
		assertThrows<DataIntegrityViolationException> { userService.register(userToSaveRepeat) }
	}


	@Test
	fun findAllUser() {
		val user1 =userFactory.anyUser()
		val user2 =userFactory.anyUser(cvu = "1111111111111111111122", email = "ejemplo2@mail.com", walletAddress = 22222222)
		val userToSave1 = UserRequestDTO.fromModel(user1)
		val userToSave2 = UserRequestDTO.fromModel(user2)

		userService!!.register(userToSave1)
		userService.register(userToSave2)
		val list = userService.findAll()
		assert(list.size == 2)
	}

	@AfterEach
	fun clear() {
		userService!!.deleteAll()
	}
}
