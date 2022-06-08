package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Transfer
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Configuration
@Repository
interface ActivityTransactionRepository: CrudRepository<Transfer?, Int?> {
    fun findById(id: Int?): Transfer?
    override fun findAll(): List<Transfer?>
    override fun deleteById(id: Int)
}
