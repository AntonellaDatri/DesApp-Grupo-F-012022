package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.IntentionToOperateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService {
    @Autowired
    private val repository: IntentionToOperateRepository? = null

    @Transactional
    fun create(cryptoQuote: Order) : Order {
        repository!!.save(cryptoQuote)
        return cryptoQuote
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