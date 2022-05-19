package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException

@SpringBootTest
class UserServiceTest {
	@Autowired
	private val userService: UserService? = null

	@Test
	fun findByID() {
		val userToSave = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "Password@","1234567891234567891234",12345678,"Victoria 897")
		userService!!.register(userToSave)
		val user = userService.findByID(12345678)
		assert(user.name == "Aldana")
	}

	@Test
	fun registerUser() {
		val userToSave = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "Password@","1234567891234567891234",12345677,"Victoria 897")
		userService!!.register(userToSave)
		val userSaved = userService.findByID(12345677)
		val userName = userSaved.name
		assert(userName == "Aldana")
		userService.deleteByID(12345677)
	}
	@Test
	fun registerUserSameWalletUpdate() {
		val userToSave = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "Password@","1234567891234567891235",12345677,"Victoria 897")
		userService!!.register(userToSave)
		assert(userService.findByID(12345677).email.equals("aldanacastro1999@gmail.com"))
		val userRepeat = User("Aldana", "Castro", "aldanacastro19@gmail.com", "Password@","1234567891234567891231",12345677,"Victoria 897")
		userService.register(userRepeat)
		assert(userService.findByID(12345677).email.equals("aldanacastro19@gmail.com"))
		userService.deleteByID(12345677)
	}

	@Test
	fun registerUserSameCVU() {
		val userToSave = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "Password@","1234567891234567891235",12345677,"Victoria 897")
		userService!!.register(userToSave)
		val userRepeat = User("Aldana", "Castro", "aldanacastro19@gmail.com", "Password@","1234567891234567891235",12345678,"Victoria 897")
		assertThrows<DataIntegrityViolationException> { userService.register(userRepeat) }
		userService.deleteByID(12345677)
	}

	@Test
	fun registerUserSameEmail() {
		val userToSave = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "Password@","1234567891234567891235",12345677,"Victoria 897")
		userService!!.register(userToSave)
		val userRepeat = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "Password@","1234567891234567891231",12345678,"Victoria 897")
		assertThrows<DataIntegrityViolationException> { userService.register(userRepeat) }
		userService.deleteByID(12345677)
	}


	@Test
	fun findAllUser() {
		val userToSave1 = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "Password@","1234567891234567891235",12345678,"Victoria 897")
		val userToSave2 = User("Antonella", "D'Atri", "datri.antonella@gmail.com", "Password@","1234567891234567891236",12345679,"Victoria 897")
		userService!!.register(userToSave1)
		userService.register(userToSave2)
		val list = userService.findAll()
		assert(list.size == 2)
		val user1 = list[0]
		assert(user1!!.name.equals("Aldana"))
		//assert(user1.walletAddress == 12345678)
		val user2 = list[1]
		assert(user2!!.name.equals("Antonella"))
		//assert(user2.walletAddress == 12345679)
		userService.deleteByID(12345678)
		userService.deleteByID(12345679)

	}

	@Test
	fun invalidPasswordUserNoUppercase() {
		val user = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "password@","1234567891234567891234",12345678,"Victoria 897")
		assertThrows<Exception> { userService!!.register(user) }
	}

	@Test
	fun invalidPasswordUserNoLowercase() {
		val user = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "PASSWORD@","1234567891234567891234",12345678,"Victoria 897")
		assertThrows<Exception> { userService!!.register(user) }
	}

	@Test
	fun invalidPasswordUserNoSpecialCharacter() {
		val user = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "Password","1234567891234567891234",12345678,"Victoria 897")
		assertThrows<Exception> { userService!!.register(user) }
	}

	@Test
	fun invalidPasswordUserLessMinimum() {
		val user = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "Pass@","1234567891234567891234",12345678,"Victoria 897")
		assertThrows<Exception> { userService!!.register(user) }
	}
}
