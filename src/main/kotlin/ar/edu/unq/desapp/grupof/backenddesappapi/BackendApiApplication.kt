package ar.edu.unq.desapp.grupof.backenddesappapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BackendApiApplication
//User: user, the password is printing in the console.
fun main(args: Array<String>) {
	runApplication<BackendApiApplication>(*args)
}
