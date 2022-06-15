package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.model.OrderDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.OrderRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import ar.edu.unq.desapp.grupof.backenddesappapi.services.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration
class OrderController {
    @Autowired
    private val orderService : OrderService? = null

    @PostMapping("/api/order/create")
    fun createOrder(@RequestBody orderRequestDTO : OrderRequestDTO): ResponseEntity<*> {
        val order : OrderDTO
        try {
            order = cryptoQuoteService!!.create(orderRequestDTO)
        } catch(e:Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
        val criptoPrice =  cryptoAssetQuoteService!!.findByCryptoName(order.cryptoactive!!, LocalDateTime.now())
        return ResponseEntity.ok().body(OrderDTO.fromModel(order, criptoPrice.price.toDouble()))
    }

    @GetMapping("/api/order/active")
    fun getActiveOrders(): ResponseEntity<*> {
        val ordersDTO = orderService!!.getActive()
        return ResponseEntity.ok().body(ordersDTO)
    }

    fun deleteByID(id: Int) {
        cryptoQuoteService!!.deleteById(id)
    }
}