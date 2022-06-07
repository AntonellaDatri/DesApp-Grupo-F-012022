package ar.edu.unq.desapp.grupof.backenddesappapi.controllers


import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.IntentionToOperateFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.model.IntentionToOperate
import ar.edu.unq.desapp.grupof.backenddesappapi.model.UserDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class IntentionToOperateControllerTest {

//    val userService: UserService = mock(UserService::class.java)
    @Autowired
    private val intentionToOperateController: IntentionToOperateController? = null//IntentionToOperateController(userService = userService)

    @BeforeEach
    fun init() {
        val userDTO: UserDTO = mock(UserDTO::class.java)
        val userService: UserService = mock(UserService::class.java)
        intentionToOperateController?.userService =userService
        given(userService.findByWalletAddress(12345678)).willReturn(userDTO)
    }

    @Test
    fun createAndFindAnIntentention() {
        val intention = IntentionToOperateFactory().anyIntentionDTO(walletAddress= 12345678)
        val persistedIntention = intentionToOperateController!!.createIntention(intention).body
        val userID = (persistedIntention as IntentionToOperate).id

        val response = intentionToOperateController.get(userID!!).body
        val userWalletAddress = (response as IntentionToOperate).userID
        assert(userWalletAddress == 12345678)
    }
    @Test
    fun getAllQuotes() {
        val intention = IntentionToOperateFactory().anyIntentionDTO(walletAddress= 12345678)
        val intention2 = IntentionToOperateFactory().anyIntentionDTO(walletAddress= 12345678)
        intentionToOperateController!!.createIntention(intention)
        intentionToOperateController.createIntention(intention2)
        val response = intentionToOperateController.getAll().body
        val intentions = response as ArrayList<*>
        assert(intentions.size == 2)
    }

    @AfterEach
    fun clear() {
        intentionToOperateController!!.deleteAll()
    }

}
