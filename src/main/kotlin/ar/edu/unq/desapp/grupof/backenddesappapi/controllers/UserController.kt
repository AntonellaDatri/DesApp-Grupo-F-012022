package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.model.UserDTO
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

    @GetMapping("/api/users")
    fun allUsers(): ResponseEntity<*> {
        val list = userService!!.findAll()
        return ResponseEntity.ok().body(list)
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


    @PostMapping("/api/user/register")
    fun registerUser(@RequestBody newUser : User ): ResponseEntity<*> {
        val user : UserDTO
        try {
            userService!!.register(newUser)
            user = UserDTO(newUser.name, newUser.lastName, newUser.email, newUser.walletAddress!!)
        }
        catch (e:Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
        return ResponseEntity.ok().body(user)
    }

    fun deleteByID(id: Int) {
        userService!!.deleteByID(id)
    }

    fun deleteAll() {
        userService!!.clear()
    }
}