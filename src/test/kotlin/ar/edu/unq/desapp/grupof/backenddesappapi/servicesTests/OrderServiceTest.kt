package ar.edu.unq.desapp.grupof.backenddesappapi.servicesTests

import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.OrderFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.UserFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.OrderRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.services.OrderService
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private val orderService: OrderService? = null
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
    fun createAnOrder(){
        val order = orderService!!.create(orderToCreate)
        assert(order.amount == orderToCreate.amountToOperate)
        assert(orderToCreate.cryptoActive == order.cryptoActive)
        assert(orderToCreate.operation == order.operation)
    }

   @Test
    fun findAnOrder(){
       orderService!!.create(orderToCreate)
       val response  = orderService.findByID(orderService.findAllOrders().first()!!.id!!)
       assert(response.user ==  "Example Example")
       assert(response.operation == orderToCreate.operation)
       assert(response.amount == orderToCreate.amountToOperate)
    }

    @Test
    fun findAllOrders(){
        val allOperatesBefore  = orderService!!.findAll()
        assert(allOperatesBefore.isEmpty())
        orderService.create(orderToCreate)
        orderService.create(orderToCreate)
        val allOperatesAfter  = orderService.findAll()
        assert(allOperatesAfter.size == 2)
    }

    @Test
    fun findActivesOrders(){
        orderService!!.create(orderToCreate)
        orderService.create(orderToCreate)
        val allOperatesAfter  = orderService.getActive()
        assert(allOperatesAfter.size == 2)
    }

    @AfterEach
    fun clear() {
        orderService!!.deleteAll()
        userService!!.deleteAll()
    }
}