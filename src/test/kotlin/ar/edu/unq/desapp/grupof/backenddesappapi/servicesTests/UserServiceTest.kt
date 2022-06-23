package ar.edu.unq.desapp.grupof.backenddesappapi.servicesTests

import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.UserFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException

@SpringBootTest
class UserServiceTest {
	@Autowired
	private val userService: UserService? = null
	private val userFactory : UserFactory = UserFactory()
	private lateinit var userToSave : UserRequestDTO
	@BeforeEach
	fun setUp(){
		val user = userFactory.anyUser()
		userToSave = UserRequestDTO.fromModel(user)
	}

	@Test
	fun registerUser() {
		val userResponseDTO = userService!!.register(userToSave)
		assertThrows<DataIntegrityViolationException> { userService.register(userToSave) }
		assert(userResponseDTO.walletAddress == 11111111)
	}

	@Test
	fun registerUserSameCVU() {
		val userToSave = UserRequestDTO.fromModel(userFactory.anyUser(cvu = "1234567891234567891235", email = "ejemplo@mail.com", walletAddress = 11111111))
		userService!!.register(userToSave)
		val userToSaveRepeat = UserRequestDTO.fromModel(userFactory.anyUser(cvu = "1234567891234567891235", email = "ejemplo2@mail.com", walletAddress = 22222222))
		assertThrows<DataIntegrityViolationException> { userService.register(userToSaveRepeat) }
	}

	@Test
	fun registerUserSameEmail() {
		val userToSave = UserRequestDTO.fromModel(userFactory.anyUser(cvu = "1111111111111111111111", email = "ejemplo@mail.com", walletAddress = 11111111))
		userService!!.register(userToSave)
		val userToSaveRepeat = UserRequestDTO.fromModel(userFactory.anyUser(cvu = "1111111111111111111122", email = "ejemplo@mail.com", walletAddress = 22222222))
		assertThrows<DataIntegrityViolationException> { userService.register(userToSaveRepeat) }
	}

	@Test
	fun findById() {
		//En conjunto fall solo no, no entiendo
		userService!!.register(userToSave)
		val userId = userService.findAllUsers().first().id
		val userFind = userService.findByID(userId!!)
		assert(userFind.walletAddress == userToSave.walletAddress)
	}

	@Test
	fun noUserByID() {
		assertThrows<Exception>("No hay usuario con ese ID") { userService!!.findByID(1)}
	}

	@Test
	fun findByWalletAddress() {
		userService!!.register(userToSave)
		val userFind = userService.findByWalletAddress(userToSave.walletAddress)
		assert(userFind.walletAddress == userToSave.walletAddress)
	}

	@Test
	fun noUserByWalletAddress() {
		assertThrows<Exception>("No hay un usuario con esa billetera") { userService!!.findByWalletAddress(12345678)}
	}
	@Test
	fun findUserById() {
		//En conjunto fall solo no, no entiendo
		userService!!.register(userToSave)
		val userId = userService.findAllUsers().first().id
		val userFind = userService.findUserByID(userId!!)
		assert(userFind.id == userId)
	}

	@Test
	fun noUserId() {
		assertThrows<Exception>("No hay usuario con ese ID") { userService!!.findUserByID(1)}
	}

	@Test
	fun findAllUser() {
		val user2 = UserRequestDTO.fromModel(userFactory.anyUser(cvu = "1111111111111111111122", email = "ejemplo2@mail.com", walletAddress = 22222222))
		userService!!.register(userToSave)
		userService.register(user2)
		val list = userService.findAll()
		assert(list.size == 2)
	}

	@AfterEach
	fun clear() {
		userService!!.deleteAll()
	}
}
