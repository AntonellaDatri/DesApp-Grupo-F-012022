package ar.edu.unq.desapp.grupof.backenddesappapi.controllers


import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.OrderFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.UserFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.OrderRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.OrderResponseDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OrderControllerTest {
    @Autowired
    private val orderController: OrderController? = null
    @Autowired
    private val userService: UserService? = null
    private val userFactory : UserFactory = UserFactory()
    private lateinit var orderToCreate : OrderRequestDTO
    @BeforeEach
    fun setUp() {
        val user = userFactory.anyUser()
        val userToSave = UserRequestDTO.fromModel(user)
        userService!!.register(userToSave)
        val userId = userService.findAllUsers().first().id
        val userFind = userService.findUserByID(userId!!)
        orderToCreate = OrderRequestDTO.fromModel(OrderFactory().anyOrder(user = userFind))
    }

    @Test
    fun createAndFindOrder() {
        orderController!!.createOrder(orderToCreate)
        val orderID = (orderController.getAllOrders().body as ArrayList<Order>)[0].id
        val orderFind = orderController.getOrder(orderID!!).body
        val response = (orderFind as OrderResponseDTO)
        assert(response.amount == orderToCreate.amountToOperate)
        assert(response.cryptoActive == orderToCreate.cryptoActive)
        assert(response.operation == orderToCreate.operation)
    }
    @Test
    fun getAllQuotes() {
        orderController!!.createOrder(orderToCreate)
        orderController.createOrder(orderToCreate)
        val response = orderController.getAll().body
        val orders = response as ArrayList<*>
        assert(orders.size == 2)
    }
    @AfterEach
    fun clear() {
        orderController!!.deleteAll()
        userService!!.deleteAll()
    }
}
