package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.aspect.LogExecutionTime
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration
class UserController {
    @Autowired
    private val userService: UserService? = null

    @LogExecutionTime
    @PostMapping("/api/user/register")
    fun registerUser(@RequestBody userRequestDTO: UserRequestDTO ): ResponseEntity<*> {
        return try {
            val user = userService!!.register(userRequestDTO)
            ResponseEntity.ok().body(user)
        } catch (e:Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @LogExecutionTime
    @GetMapping("/api/user/id")
    fun getUser(@RequestParam(required = true) id : Int): ResponseEntity<*> {
        return try {
            val user = userService!!.findByID(id)
            ResponseEntity.ok().body(user)
        } catch (e:Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @LogExecutionTime
    @GetMapping("/api/user/walletAddress")
    fun getUserByWalletAddress(@RequestParam(required = true) walletAddress : Int): ResponseEntity<*> {
        return try {
            val user = userService!!.findByWalletAddress(walletAddress)
            ResponseEntity.ok().body(user)
        } catch (e:Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @LogExecutionTime
    @GetMapping("/api/user/all")
    fun getAll(): ResponseEntity<*> {
        val users = userService!!.findAll()
        return ResponseEntity.ok().body(users)
    }

    @LogExecutionTime
    @DeleteMapping("/api/user/id/delete")
    fun deleteByID(id: Int) {
        userService!!.deleteByID(id)
    }

    @LogExecutionTime
    @DeleteMapping("/api/user/delete")
    fun deleteAll() {
        userService!!.deleteAll()
    }
}