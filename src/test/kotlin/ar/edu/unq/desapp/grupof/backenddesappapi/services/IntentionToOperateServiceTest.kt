package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.IntentionToOperateFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.UserFactory
import org.h2.engine.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IntentionToOperateServiceTest {
    @Autowired
    private val intentionToOperateService: IntentionToOperateService? = null

    @Autowired
    private val userService: UserService? = null
    @Test
    fun createAnIntentionToOperate(){
        val intention = IntentionToOperateFactory().anyIntentionDTO()
        assertDoesNotThrow { intentionToOperateService!!.create(intention) }
    }
    @Test
    fun findAnIntentionToOperate(){
        val intention = IntentionToOperateFactory().anyIntentionDTO(walletAddress = 12345678)
        val persistedIntention = intentionToOperateService!!.create(intention)
        val response  = intentionToOperateService.findByID(persistedIntention.id!!)
        assert(response.user.id == 12345678)
    }
    @Test
    fun findAllIntentionsToOperate(){
        val allOperatesBefore  = intentionToOperateService!!.findAll()
        assert(allOperatesBefore.isEmpty())

        val intention = IntentionToOperateFactory().anyIntentionDTO()
        val intention2 = IntentionToOperateFactory().anyIntentionDTO()
        intentionToOperateService.create(intention)
        intentionToOperateService.create(intention2)
        val allOperatesAfter  = intentionToOperateService.findAll()
        assert(allOperatesAfter.size == 2)

    }

    @Test
    fun test(){
        val user = UserFactory().anyUser(walletAddress = 12345678)
        userService!!.register(user)
        val intention = IntentionToOperateFactory().anyIntentionDTO(walletAddress = 12345678)
        intentionToOperateService!!.create(intention)
        val intention2 = IntentionToOperateFactory().anyIntentionDTO(walletAddress = 12345678)
        intentionToOperateService!!.create(intention2)
        val intention3 = IntentionToOperateFactory().anyIntentionDTO(walletAddress = 12345678)
        intentionToOperateService!!.create(intention3)
        print("hola" + intentionToOperateService.getActiveTransaction())

    }
}