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
/*
    @Test
    fun createInvalidCVUUserTest() {
        var thrown = assertThrows<Exception> { userFactory.anyUser(cvu="123456789123456789123") }
        assert(thrown.message.equals("El CVU debe tener 22 caracteres"));

        thrown = assertThrows { userFactory.anyUser(cvu="12345678912345678912344") }
        assert(thrown.message.equals("El CVU debe tener 22 caracteres"));
    }

    @Test
    fun createInvalidNameUserTest() {
        var thrown = assertThrows<Exception> { userFactory.anyUser(name = "Maria Carmen Florencia Margarita Tercera") }
        assert(thrown.message.equals("El nombre no debe ser vacio y debe tener entre 3 y 30 caracteres"));

        thrown = assertThrows<Exception> { userFactory.anyUser(name = "") }
        assert(thrown.message.equals("El nombre no debe ser vacio y debe tener entre 3 y 30 caracteres"));
    }

    @Test
    fun createInvalidLastNameUserTest() {
        var thrown = assertThrows<Exception> { userFactory.anyUser(lastName = "Gomez Martin Flores Gonzales Gonzales") }
        assert(thrown.message.equals("El apellido no debe ser vacio y debe tener entre 3 y 30 caracteres"));

        thrown = assertThrows<Exception> { userFactory.anyUser(lastName = "") }
        assert(thrown.message.equals("El apellido no debe ser vacio y debe tener entre 3 y 30 caracteres"));
    }

    @Test
    fun createInvalidEmailUserTest() {
        var thrown = assertThrows<Exception> { userFactory.anyUser(email = "mail@mail.r") }
        assert(thrown.message.equals("El mail debe ser un mail valido. Con el formato (usuario)@(portal).(dominio)"));

        thrown = assertThrows<Exception> { userFactory.anyUser(email = "") }
        assert(thrown.message.equals("El mail debe ser un mail valido. Con el formato (usuario)@(portal).(dominio)"));
    }

    @Test
    fun createInvalidPasswordUserTest() {
        var thrown = assertThrows<Exception> { userFactory.anyUser(password = "Password") }
        assert(thrown.message.equals("La contaseña no puede estar vacia. Debe tener 1 mayuscula, 1 minuscula, 1 caracter especial y como minimo 6 caracteres"));

        thrown = assertThrows<Exception> { userFactory.anyUser(password = "PASSWORD@") }
        assert(thrown.message.equals("La contaseña no puede estar vacia. Debe tener 1 mayuscula, 1 minuscula, 1 caracter especial y como minimo 6 caracteres"));

        thrown = assertThrows<Exception> { userFactory.anyUser(password = "password@") }
        assert(thrown.message.equals("La contaseña no puede estar vacia. Debe tener 1 mayuscula, 1 minuscula, 1 caracter especial y como minimo 6 caracteres"));

        thrown = assertThrows<Exception> { userFactory.anyUser(password = "Pass@") }
        assert(thrown.message.equals("La contaseña no puede estar vacia. Debe tener 1 mayuscula, 1 minuscula, 1 caracter especial y como minimo 6 caracteres"));
    }

    @Test
    fun createInvalidWalletAddressUserTest() {
        var thrown = assertThrows<Exception> { userFactory.anyUser(walletAddress=123456778) }
        assert(thrown.message.equals("La billetera debe tener 8 caracteres"));

        thrown = assertThrows<Exception> { userFactory.anyUser(walletAddress=12345) }
        assert(thrown.message.equals("La billetera debe tener 8 caracteres"));
    }

    @Test
    fun createInvalidAddressUserTest() {
        var thrown = assertThrows<Exception> { userFactory.anyUser(address = "calle falsa 123 entre ninguna calle") }
        assert(thrown.message.equals("La direccion no puede ser vacia y debe tener entre 10 y 30 caracteres"));

        thrown = assertThrows<Exception> { userFactory.anyUser(address = "") }
        assert(thrown.message.equals("La direccion no puede ser vacia y debe tener entre 10 y 30 caracteres"));
    }


*/

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