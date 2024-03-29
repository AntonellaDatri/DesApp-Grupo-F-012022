package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.dto.OrderResponseDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.OrderRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.State
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
    fun create(orderDTO: OrderRequestDTO) : OrderResponseDTO {
        val user = userRepository!!.findById(orderDTO.user).get()
        val cryptoPrice = getCryptoPrice(orderDTO.cryptoActive)
        val order = save(Order.fromModel(orderDTO, user))
        return OrderResponseDTO.fromModel(order, cryptoPrice)
    }

    @Transactional
    fun getActive(): List<OrderResponseDTO> {
        val activeOrders = repository!!.findByStateEquals(State.ACTIVE)
        return activeOrders.map {
            val cryptoPrice = getCryptoPrice(it!!.cryptoName!!)
            OrderResponseDTO.fromModel(it, cryptoPrice)
        }
    }

    @Transactional
    fun findByID(id: Int): OrderResponseDTO {
        try {
            val order = repository!!.findById(id).get()
            return OrderResponseDTO.fromModel(order, getCryptoPrice(order.cryptoName!!))
        } catch (e:Exception) {
            throw Exception("No existe una orden con ese id")
        }
    }

    @Transactional
    fun findOrderByID(id: Int): Order {
        return repository!!.findById(id).get()
    }

    @Transactional
    fun findAll(): List<OrderResponseDTO> {
        return repository!!.findAll().map {
            val cryptoPrice = getCryptoPrice(it!!.cryptoName!!)
            OrderResponseDTO.fromModel(it, cryptoPrice)
        }
    }

    @Transactional
    fun findAllOrders(): List<Order?> {
        return repository!!.findAll()
    }

    @Transactional
    fun deleteByID(id: Int) {
        repository!!.deleteById(id)
    }

    @Transactional
    fun deleteAll() {
        repository!!.deleteAll()
    }

    private fun getCryptoPrice(cryptoName : String) : Double {
        return cryptoAssetQuoteService!!.findByCryptoName(cryptoName, LocalDateTime.now()).price.toDouble()
    }

    fun save(order: Order): Order {
        repository!!.save(order)
        return order
    }
}