package ar.edu.unq.desapp.grupof.bakenddesappapi.user

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class LoadDatabase {
    @Bean
    fun initDatabase(): CommandLineRunner {
        return CommandLineRunner {
            val register = RegisterUser()
            log.info("Preloading " + register.registerUser("Aldana", "Castro", "aldanacastro1999@gmail.com", "Victoria 897","password","1234567891234567891234", 12345678, ))
            log.info("Preloading " + register.getUserBy(12345678))
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(LoadDatabase::class.java)
    }
}