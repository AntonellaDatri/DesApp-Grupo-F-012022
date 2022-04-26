package ar.edu.unq.desapp.grupof.backenddesappapi.webservices

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
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

    @GetMapping("/api/user")
    fun getUser(@RequestParam(required = true) userID : Int): ResponseEntity<User> {
        val list = userService!!.findByID(userID)
        return ResponseEntity.ok().body(list)
    }


    @PostMapping("/api/user/register")
//    @ResponseBody
    fun registerUser(@RequestBody newUser : User ): ResponseEntity<*> {
        val list = userService!!.register(newUser)
        return ResponseEntity.ok().body(list)
    }

//    @RequestMapping(value = ["/custom"], method = [RequestMethod.POST])
//    fun custom(): String? {
//        return "custom"
//    }
}