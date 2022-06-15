package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserDTO
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

    @PostMapping("/api/user/register")
    fun registerUser(@RequestBody newUser : UserRequestDTO ): ResponseEntity<*> {
        val user : UserDTO
        try {
            user = userService!!.register(newUser)
        }
        catch (e:Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
        return ResponseEntity.ok().body(user)
    }

    @GetMapping("/api/user")
    fun getUser(@RequestParam(required = true) walletAddress : Int): ResponseEntity<*> {
        val user : UserDTO
        try { user = userService!!.findByWalletAddress(walletAddress) }
        catch (e:Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
        return ResponseEntity.ok().body(user)
    }

    @GetMapping("/api/user/all")
    fun getUsers(): ResponseEntity<*> {
        val list = userService!!.findAll()
        return ResponseEntity.ok().body(list)
    }

    @DeleteMapping("/api/user/id/delete")
    fun deleteByID(id: Int) {
        userService!!.deleteByID(id)
    }
    
    @DeleteMapping("/api/user/delete")
    fun deleteAll() {
        userService!!.clear()
    }
}