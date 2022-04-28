package ar.edu.unq.desapp.grupof.backenddesappapi.controllers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
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
    fun getUser(@RequestParam(required = true) userID : Int): ResponseEntity<User> {
        val list = userService!!.findByID(userID)
        return ResponseEntity.ok().body(list)
    }

    @PostMapping("/api/user/register")
    fun registerUser(@RequestBody newUser : User ): ResponseEntity<*> {
        val list = userService!!.register(newUser)
        return ResponseEntity.ok().body(list)
    }

    fun deleteByID(id: Int) {
        userService!!.deleteByID(id)
    }
}