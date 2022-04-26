package ar.edu.unq.desapp.grupof.backenddesappapi

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import ar.edu.unq.desapp.grupof.backenddesappapi.webservices.CryptoAssetQuoteRestService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct


@Service
@Transactional
class InitServiceInMemory {
    protected val logger: Log = LogFactory.getLog(javaClass)

    @Value("\${spring.datasource.driverClassName:NONE}")
    private val className: String? = null

    @Autowired
    private val userService: UserService? = null
    @PostConstruct
    fun initialize() {
        if (className == "org.h2.Driver") {
            logger.info("Init Data Using H2 DB")
            fireInitialData()
        }
    }

    private fun fireInitialData() {
      val user = User("Aldana", "Castro", "aldanacastro1999@gmail.com", "password","1234567891234567891234",12345678, "Victoria 897")
       userService!!.register(user)
       val user1 = User("Antonella", "D'Atri", "datri.antonella@gmail.com", "password","1234567891234567891235",12345679,"Victoria 897" )
       userService.register(user1)
    }
}
