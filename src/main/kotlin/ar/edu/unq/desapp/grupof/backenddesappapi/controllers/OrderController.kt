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
import java.time.LocalDateTime

@RestController
@EnableAutoConfiguration
class OrderController {
    @Autowired
    private val cryptoQuoteService : OrderService? = null
    @Autowired
    private val cryptoAssetQuoteService : CryptoAssetQuoteService? = null

    @GetMapping("/api/order")
    fun getAll(): ResponseEntity<*> {
        val orders = cryptoQuoteService!!.findAll()
        val criptsPrice =  cryptoAssetQuoteService!!.getTenCryptoAssets(LocalDateTime.now())

        val ordersDTO = orders.map {
            OrderDTO.fromModel(it!!, criptsPrice[it.cryptoactive!!]!!.price.toDouble())
        }
        return ResponseEntity.ok().body(ordersDTO)
    }

    @GetMapping("/api/transaction")
    fun get(@RequestParam(required = true) id : Int): ResponseEntity<Order> {
        val list = cryptoQuoteService!!.findByID(id)
        return ResponseEntity.ok().body(list)
    }

    @PostMapping("/api/transaction/create")
    fun createIntention(@RequestBody orderRequestDTO : OrderRequestDTO): ResponseEntity<*> {
        val order : Order
        try {
            order = cryptoQuoteService!!.create(orderRequestDTO)
        } catch(e:Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
        val criptoPrice =  cryptoAssetQuoteService!!.findByCryptoName(order.cryptoactive!!, LocalDateTime.now())
        return ResponseEntity.ok().body(OrderDTO.fromModel(order, criptoPrice.price.toDouble()))
    }

    fun deleteByID(id: Int) {
        cryptoQuoteService!!.deleteById(id)
    }
}