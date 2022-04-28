package ar.edu.unq.desapp.grupof.backenddesappapi.model

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserTest {
    @Test
    fun basicUser() {
        val user = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "password","1234567891234567891234",12345678,"Victoria 897")
        assert(user.name.equals("Aldana"))
        assert(user.lastName.equals("Castro"))
        assert(user.email.equals("aldanacastro1999@gmail.com"))
        assert(user.password.equals("password"))
        assert(user.cvu.equals("1234567891234567891234"))
        assert(user.walletAddress!! == 12345678)
        assert(user.address.equals("Victoria 897"))
    }
}