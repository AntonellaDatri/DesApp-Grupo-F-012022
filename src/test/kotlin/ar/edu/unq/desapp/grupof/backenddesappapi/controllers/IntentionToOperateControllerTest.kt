package ar.edu.unq.desapp.grupof.backenddesappapi.controllers


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IntentionToOperateControllerTest {
    @Autowired
    private val intentionToOperateController: IntentionToOperateController? = null
    /*@Test
    fun createAndFindAnIntentention() {
        val intention = IntentionToOperateFactory().anyIntentionDTO(walletAddress= 12345678)
        val persistedIntention = intentionToOperateController!!.createIntention(intention)
        val userID = (persistedIntention as IntentionToOperateDTO).id

        val response = intentionToOperateController.get(userID!!).body
        val userWalletAddress = (response as IntentionToOperateDTO).walletAddress
        assert(userWalletAddress == 12345678)
    }
    @Test
    fun getAllQuotes() {
        val intention = IntentionToOperateFactory().anyIntentionDTO(walletAddress= 12345678)
        val intention2 = IntentionToOperateFactory().anyIntentionDTO(walletAddress= 12345678)
        intentionToOperateController!!.createIntention(intention)
        intentionToOperateController!!.createIntention(intention2)
        val response = intentionToOperateController.getAll().body
        val intentions = response as ArrayList<*>
        assert(intentions.size == 2)
    }*/

}
