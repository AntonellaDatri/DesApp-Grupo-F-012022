package ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.UserRepository
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.springframework.beans.factory.annotation.Autowired

class UserFactory() {

    private val userService: UserService = UserService()

    fun anyUser(
        name: String = "Example",
        lastName: String = "Example",
        email: String = "example@mail.com",
        password: String = "Password@",
        cvu: String = "1234567891234567891234",
        walletAddress: Int = 11111111,
        address: String = "123 Fake Street"
    ): User {

        return User(name, lastName, email, password, cvu, walletAddress, address)
    }
}