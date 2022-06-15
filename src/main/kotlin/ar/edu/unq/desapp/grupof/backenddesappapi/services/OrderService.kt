package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.dto.OrderDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.OrderRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.State
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.OrderRepository
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class OrderService {
    @Autowired
    private val repository: OrderRepository? = null
    @Autowired
    private val userRepository: UserRepository? = null
    @Autowired
    private val cryptoAssetQuoteService : CryptoAssetQuoteService? = null

    @Transactional
    fun create(orderDTO: OrderRequestDTO) : OrderDTO {
        val user = userRepository!!.findById(orderDTO.user).get()
        val order = save(Order(orderDTO.cryptoActive, orderDTO.amount, user, orderDTO.operation))
        val cryptoPrice = getCryptoPrice(order)
        return OrderDTO.fromModel(order, cryptoPrice)
    }

    @Transactional
    fun getAll(): List<OrderDTO> {
        return findAll().map {
            val cryptoPrice = getCryptoPrice(it!!)
            OrderDTO.fromModel(it, cryptoPrice)
        }
    }

    @Transactional
    fun getActive(): List<OrderDTO> {
        val activeOrders = repository!!.findByStateEquals(State.ACTIVE)
        return activeOrders.map {
            val cryptoPrice = getCryptoPrice(it!!)
            OrderDTO.fromModel(it, cryptoPrice)
        }
    }

    @Transactional
    fun findByID(id: Int): OrderDTO {
        val order = repository!!.findById(id).get()
        return OrderDTO.fromModel(order, getCryptoPrice(order))
    }

    @Transactional
    fun findAll(): List<Order?> {
        return repository!!.findAll()
    }

    @Transactional
    fun deleteById(id: Int) {
        repository!!.deleteById(id)
    }

    private fun getCryptoPrice(order : Order) : Double {
        return cryptoAssetQuoteService!!.findByCryptoName(order.cryptoName!!, LocalDateTime.now()).price.toDouble()
    }

    fun save(order: Order): Order {
        repository!!.save(order)
        return order
    }
}