package ar.edu.unq.desapp.grupof.backenddesappapi.webservices

import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration
class UserRestService {
    @Autowired
    private val userService: UserService? = null

    @GetMapping("/api/users")
    fun allUsers(): ResponseEntity<*> {
        val list = userService!!.findAll()
        return ResponseEntity.ok().body(list)
    }
}