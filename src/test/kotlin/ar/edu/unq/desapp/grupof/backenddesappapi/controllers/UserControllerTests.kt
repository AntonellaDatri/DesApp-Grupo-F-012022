package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.UserFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserResponseDTO
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserControllerTests {
	@Autowired
	private val userController: UserController? = null
	private val userFactory : UserFactory = UserFactory()

	@Test
	fun registerUser() {
		val userToSave = UserRequestDTO.fromModel(userFactory.anyUser(walletAddress = 98765432))
		userController!!.registerUser(userToSave)
		val user = userController.getUserByWalletAddress(98765432).body
		val userName = (user as UserResponseDTO).name
		assert(userName == userToSave.name)
	}

	@Test
	fun registerUserWithSameCVU() {
		val userToSave = UserRequestDTO.fromModel(userFactory.anyUser(cvu = "1234567891234567891235", email = "ejemplo@mail.com", walletAddress = 11111111))
		userController!!.registerUser(userToSave)
		val userToSaveRepeat = UserRequestDTO.fromModel(userFactory.anyUser(cvu = "1234567891234567891235", email = "ejemplo2@mail.com", walletAddress = 22222222))
		userController.registerUser(userToSaveRepeat).body
	}

	@Test
	fun getUserByWalletAddress() {
		val user =userFactory.anyUser(walletAddress = 12345678)
		val userToSave = UserRequestDTO.fromModel(user)
		userController!!.registerUser(userToSave)
		val res = userController.getUserByWalletAddress(12345678).body
		val userName = (res as UserResponseDTO).name
		assert(userName == userToSave.name)
	}

	@Test
	fun getUser() {
		val user =userFactory.anyUser(walletAddress = 12345678)
		val userToSave = UserRequestDTO.fromModel(user)
		val userToSaved = userController!!.registerUser(userToSave).body
		(userToSaved as UserResponseDTO)
		val response = userController.getUser(userToSaved.id).body
		val userName = (response as UserResponseDTO).name
		assert(userName == user.name)
	}

	@Test
	fun getAllUser() {
		val user1 =userFactory.anyUser()
		val user2 =userFactory.anyUser(cvu = "1111111111111111111122", email = "ejemplo2@mail.com", walletAddress = 22222222)
		val userToSave1 = UserRequestDTO.fromModel(user1)
		val userToSave2 = UserRequestDTO.fromModel(user2)

		userController!!.registerUser(userToSave1)
		userController.registerUser(userToSave2)
		val response = userController.getAll()
		val list= response.body as ArrayList<*>
		assert(list.size == 2)
	}

	@Test
	fun getNotRegisteredUser() {
		assert(userController!!.getUser(123).body == "No hay usuario con ese ID")
	}

	@Test
	fun deleteAnUser() {
		val user = userFactory.anyUser(walletAddress = 12345678)
		val userToSave = UserRequestDTO.fromModel(user)
		val userToSaved = userController!!.registerUser(userToSave).body
		(userToSaved as UserResponseDTO)
		userController.deleteByID(userToSaved.id)
		assert(userController.getUserByWalletAddress(12345678).body == "No hay un usuario con esa billetera")
	}

	@AfterEach
	fun clear() {
		userController!!.deleteAll()
	}
}
