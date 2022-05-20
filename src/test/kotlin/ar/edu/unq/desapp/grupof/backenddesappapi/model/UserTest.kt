package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.UserFactory
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserTest {
    private val userFactory : UserFactory = UserFactory()
    @Test
    fun createUserTest() {
        val user = userFactory.anyUser()
        user.cvu = "1234567891234567891234"
        assert(user.cvu.equals("1234567891234567891234"))
    }

}