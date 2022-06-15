package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.State
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Configuration
@Repository
interface OrderRepository: CrudRepository<Order?, Int?> {
    fun findById(id: Int?): Order?
    fun findByStateEquals(state: State): List<Order?>
    override fun findAll(): List<Order?>
    override fun deleteById(id: Int)
    override fun deleteAll()
}