package ar.edu.unq.desapp.grupof.backenddesappapi.servicesTests

import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.IntentionToOperateFactory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IntentionToOperateServiceTest {
//    @Autowired
//    private val intentionToOperateService: OrderService? = null
//    @Test
//    fun createAnIntentionToOperate(){
//        val intention = IntentionToOperateFactory().anyIntention()
//        assertDoesNotThrow { intentionToOperateService!!.create(intention) }
//    }
//    @Test
//    fun findAnIntentionToOperate(){
//        val intention = IntentionToOperateFactory().anyIntention(walletAddress = 12345678)
//        val persistedIntention = intentionToOperateService!!.create(intention)
//        val response  = intentionToOperateService.findByID(persistedIntention.id!!)
//        assert(response.userID == 12345678)
//    }
//    @Test
//    fun findAllIntentionsToOperate(){
//        val allOperatesBefore  = intentionToOperateService!!.findAll()
//        assert(allOperatesBefore.isEmpty())
//
//        val intention = IntentionToOperateFactory().anyIntention()
//        val intention2 = IntentionToOperateFactory().anyIntention()
//        intentionToOperateService.create(intention)
//        intentionToOperateService.create(intention2)
//        val allOperatesAfter  = intentionToOperateService.findAll()
//        assert(allOperatesAfter.size == 2)
//
//    }

}