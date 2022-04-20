package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Configuration
@Repository
interface UserRepository: CrudRepository<User?, Int?> {
    fun findById(id: Int?): User?
    override fun findAll(): List<User?>
}