package ar.edu.unq.desapp.grupof.bakenddesappapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BakendDesappApiApplication
//User: user, the password is printing in the console.
fun main(args: Array<String>) {
	runApplication<BakendDesappApiApplication>(*args)
}
