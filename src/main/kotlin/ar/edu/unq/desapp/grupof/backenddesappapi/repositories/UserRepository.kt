package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Configuration
@Repository
interface UserRepository: CrudRepository<User?, Int?> {
    override fun findById(id: Int): Optional<User?>
    fun findByWalletAddress(walletAddress: Int): Optional<User?>
    override fun findAll(): List<User>
    override fun deleteById(id: Int)
    override fun deleteAll()
//    fun clear()
}