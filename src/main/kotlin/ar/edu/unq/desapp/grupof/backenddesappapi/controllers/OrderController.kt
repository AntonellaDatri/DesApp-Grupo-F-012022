package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.dto.OrderDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.OrderRequestDTO
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
            order = orderService!!.create(orderRequestDTO)
        } catch(e:Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
        return ResponseEntity.ok().body(order)
    }

    @GetMapping("/api/order")
    fun getOrder(@RequestParam(required = true) id : Int): ResponseEntity<*> {
        val order : OrderDTO
        try { order = orderService!!.findByID(id) }
        catch (e:Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
        return ResponseEntity.ok().body(order)
    }

    @GetMapping("/api/order/all")
    fun getAll(): ResponseEntity<*> {
        val ordersDTO = orderService!!.getAll()
        return ResponseEntity.ok().body(ordersDTO)
    }

    @GetMapping("/api/order/active")
    fun getActiveOrders(): ResponseEntity<*> {
        val ordersDTO = orderService!!.getActive()
        return ResponseEntity.ok().body(ordersDTO)
    }

    @DeleteMapping("/api/order/delete")
    fun deleteByID(id: Int) {
        orderService!!.deleteById(id)
    }
}