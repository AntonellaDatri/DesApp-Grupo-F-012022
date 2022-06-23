package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.UserFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidDataUser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserTest {
    private val userFactory : UserFactory = UserFactory()
    @Test
    fun createUserTest() {
        val user = userFactory.anyUser()
        user.cvu = "1234567891234567891234"
        assert(user.cvu == "1234567891234567891234")
    }

    @Test
    fun invalidName() {
        assertThrows<InvalidDataUser>("El nombre no debe ser vacio y debe tener entre 3 y 30 caracteres")
        { userFactory.anyUser(name = "Al") }
    }

    @Test
    fun invalidNameEmpty() {
        assertThrows<InvalidDataUser>("El nombre no debe ser vacio y debe tener entre 3 y 30 caracteres")
        { userFactory.anyUser(name = " ") }
    }

    @Test
    fun invalidNameToManyCharacters() {
        assertThrows<InvalidDataUser>("El nombre no debe ser vacio y debe tener entre 3 y 30 caracteres")
        { userFactory.anyUser(name = "12345678912345678912345678912345678912") }
    }

    @Test
    fun invalidLastName() {
        assertThrows<InvalidDataUser>("El apellido no debe ser vacio y debe tener entre 3 y 30 caracteres")
        { userFactory.anyUser(lastName = "Al") }
    }

    @Test
    fun invalidLastNameEmpty() {
        assertThrows<InvalidDataUser>("El apellido no debe ser vacio y debe tener entre 3 y 30 caracteres")
        { userFactory.anyUser(lastName = " ") }
    }

    @Test
    fun invalidLastNameToManyCharacters() {
        assertThrows<InvalidDataUser>("El apellido no debe ser vacio y debe tener entre 3 y 30 caracteres")
        { userFactory.anyUser(lastName = "12345678912345678912345678912345678912") }
    }

    @Test
    fun invalidEmail() {
        assertThrows<InvalidDataUser>("El mail debe ser un mail valido")
        { userFactory.anyUser(email = "Al") }
    }

    @Test
    fun invalidEmail2() {
        assertThrows<InvalidDataUser>("El mail debe ser un mail valido")
        { userFactory.anyUser(email = "al@gmail") }
    }

    @Test
    fun invalidEmailEmpty() {
        assertThrows<InvalidDataUser>("El mail debe ser un mail valido")
        { userFactory.anyUser(email = " ") }
    }

    @Test
    fun invalidAddress() {
        assertThrows<InvalidDataUser>("La direccion no puede ser vacia y debe tener entre 10 y 30 caracteres")
        { userFactory.anyUser(address = "Al") }
    }

    @Test
    fun invalidAddressEmpty() {
        assertThrows<InvalidDataUser>("La direccion no puede ser vacia y debe tener entre 10 y 30 caracteres")
        { userFactory.anyUser(address = " ") }
    }

    @Test
    fun invalidAddressToManyCharacters() {
        assertThrows<InvalidDataUser>("La direccion no puede ser vacia y debe tener entre 10 y 30 caracteres")
        { userFactory.anyUser(address = "12345678912345678912345678912345678912") }
    }

    @Test
    fun invalidCVUToMany() {
        assertThrows<InvalidDataUser>("El CVU debe tener 22 caracteres")
        { userFactory.anyUser(cvu = "123456789012345678901") }
    }

    @Test
    fun invalidCVUToManyCharacters() {
        assertThrows<InvalidDataUser>("El CVU debe tener 22 caracteres")
        { userFactory.anyUser(cvu = "12345678901234567890123") }
    }

    @Test
    fun invalidCVUEmpty() {
        assertThrows<InvalidDataUser>("El CVU debe tener 22 caracteres")
        { userFactory.anyUser(cvu = " ") }
    }

    @Test
    fun invalidWalletAddress() {
        assertThrows<InvalidDataUser>("La billetera debe tener 8 caracteres")
        { userFactory.anyUser(walletAddress = 1234567) }
    }

    @Test
    fun invalidWalletAddressToManyCharacters() {
        assertThrows<InvalidDataUser>("La billetera debe tener 8 caracteres")
        { userFactory.anyUser(walletAddress = 123456789) }
    }

    @Test
    fun invalidPasswordUserNoUppercase() {
        assertThrows<InvalidDataUser>("La contaseña no puede estar vacia. Debe tener 1 mayuscula, 1 minuscula, 1 caracter especial y como minimo 6 caracteres")
        { userFactory.anyUser(password = "password@") }
    }

    @Test
    fun invalidPasswordUserNoLowercase() {
        assertThrows<InvalidDataUser>("La contaseña no puede estar vacia. Debe tener 1 mayuscula, 1 minuscula, 1 caracter especial y como minimo 6 caracteres")
        { userFactory.anyUser(password = "PASSWORD@") }
    }

    @Test
    fun invalidPasswordUserNoSpecialCharacter() {
        assertThrows<InvalidDataUser>("La contaseña no puede estar vacia. Debe tener 1 mayuscula, 1 minuscula, 1 caracter especial y como minimo 6 caracteres")
        { userFactory.anyUser(password = "Password") }
    }

    @Test
    fun invalidPasswordUserLessMinimum() {
        assertThrows<InvalidDataUser>("La contaseña no puede estar vacia. Debe tener 1 mayuscula, 1 minuscula, 1 caracter especial y como minimo 6 caracteres")
        { userFactory.anyUser(password = "Pass@") }
    }
}