package ar.edu.unq.desapp.grupof.backenddesappapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BackendApiApplication
//User: user, password admin123.
fun main(args: Array<String>) {
	runApplication<BackendApiApplication>(*args)
}
