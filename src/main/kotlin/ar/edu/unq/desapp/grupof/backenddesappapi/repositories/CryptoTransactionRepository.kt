package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoTransaction
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Configuration
@Repository
interface CryptoTransactionRepository: CrudRepository<CryptoTransaction?, Int?> {
    fun findById(id: Int?): CryptoTransaction?
    override fun findAll(): List<CryptoTransaction?>
    override fun deleteById(id: Int)
}