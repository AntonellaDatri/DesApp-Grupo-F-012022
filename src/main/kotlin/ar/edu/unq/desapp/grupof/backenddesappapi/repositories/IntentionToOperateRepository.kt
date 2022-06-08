package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Configuration
@Repository
interface IntentionToOperateRepository: CrudRepository<Order?, Int?> {
    fun findById(id: Int?): Order?
    override fun findAll(): List<Order?>
    override fun deleteById(id: Int)
}