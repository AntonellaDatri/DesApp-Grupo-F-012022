package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.aspect.LogExecutionTime
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

    @LogExecutionTime
    @PostMapping("/api/order/create")
    fun createOrder(@RequestBody orderRequestDTO : OrderRequestDTO): ResponseEntity<*> {
        return try {
            val order = orderService!!.create(orderRequestDTO)
            ResponseEntity.ok().body(order)
        } catch(e:Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }

    }

    @LogExecutionTime
    @GetMapping("/api/order/id")
    fun getOrder(@RequestParam(required = true) id : Int): ResponseEntity<*> {
        return try {
            val order = orderService!!.findByID(id)
            ResponseEntity.ok().body(order)
        } catch (e:Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }

    }

    @LogExecutionTime
    @GetMapping("/api/order/all")
    fun getAll(): ResponseEntity<*> {
        val ordersDTO = orderService!!.findAll()
        return ResponseEntity.ok().body(ordersDTO)
    }

    @LogExecutionTime
    @GetMapping("/api/order/allOrders")
    fun getAllOrders(): ResponseEntity<*> {
        val orders = orderService!!.findAllOrders()
        return ResponseEntity.ok().body(orders)
    }

    @LogExecutionTime
    @GetMapping("/api/order/active")
    fun getActiveOrders(): ResponseEntity<*> {
        val ordersDTO = orderService!!.getActive()
        return ResponseEntity.ok().body(ordersDTO)
    }

    @LogExecutionTime
    @DeleteMapping("/api/order/id/delete")
    fun deleteByID(id: Int) {
        orderService!!.deleteByID(id)
    }
    @LogExecutionTime
    @DeleteMapping("/api/order/delete")
    fun deleteAll() {
        orderService!!.deleteAll()
    }
}