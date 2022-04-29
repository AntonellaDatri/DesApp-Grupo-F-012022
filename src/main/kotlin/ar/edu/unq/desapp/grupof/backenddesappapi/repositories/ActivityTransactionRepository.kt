package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.ActivityTransaction
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Configuration
@Repository
interface ActivityTransactionRepository: CrudRepository<ActivityTransaction?, Int?> {
    fun findById(id: Int?): ActivityTransaction?
    override fun findAll(): List<ActivityTransaction?>
    override fun deleteById(id: Int)
}
