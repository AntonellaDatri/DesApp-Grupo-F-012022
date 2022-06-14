package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.model.OrderRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.IntentionToOperateRepository
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService {
    @Autowired
    private val repository: IntentionToOperateRepository? = null
    @Autowired
    private val userRepository: UserRepository? = null

    @Transactional
    fun create(orderDTO: OrderRequestDTO) : Order {
        val user = userRepository!!.findById(orderDTO.user).get()
        val order = Order(orderDTO.cryptoActive, orderDTO.amount, user, orderDTO.operation)
        repository!!.save(order)
        return order
    }

    fun save(order: Order): Order {
        repository!!.save(order)
        return order
    }


    fun findByID(id: Int): Order {
        return repository!!.findById(id).get()
    }

    @Transactional
    fun findAll(): List<Order?> {
        return repository!!.findAll()
    }

    fun deleteById(id: Int) {
        repository!!.deleteById(id)
    }
}