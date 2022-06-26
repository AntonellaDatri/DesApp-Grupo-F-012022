package ar.edu.unq.desapp.grupof.backenddesappapi.controllers


import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.OrderFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.TransferFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.UserFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.*
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.services.OrderService
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TransferControllerTest {
    @Autowired
    private val trasferController: TransferController? = null
    @Autowired
    private val userService: UserService? = null
    @Autowired
    private val orderService: OrderService? = null
    private val transferFactory : TransferFactory = TransferFactory()

    private val userFactory : UserFactory = UserFactory()
    private lateinit var user : User
    private lateinit var orderUser : User
    private lateinit var order: Order
    @BeforeEach
    fun setUp() {
        val user1 = userFactory.anyUser()
        val user2 = userFactory.anyUser(cvu = "1111111111111111111122", email = "ejemplo2@mail.com", walletAddress = 22222222)
        val userToSave1 = UserRequestDTO.fromModel(user1)
        val userToSave2 = UserRequestDTO.fromModel(user2)

        val userSaved = userService!!.register(userToSave1)
        val userSaved2 = userService.register(userToSave2)
        orderUser = userService.findUserByID(userSaved.id)
        user = userService.findUserByID(userSaved2.id)
        val orderToCreate = OrderRequestDTO.fromModel(OrderFactory().anyOrder(user = orderUser))
        val orderSaved = orderService!!.create(orderToCreate)
        order = orderService.findOrderByID(orderSaved.id)

    }

    @Test
    fun createAndFindTransfer() {
        val transfer = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        val transferToCreate = trasferController!!.createTransfer(transfer).body
        transferToCreate as TransferResponseDTO
        val transferSaved = trasferController.getTransfer(transferToCreate.id).body
        transferSaved as TransferResponseDTO

        assert(transferSaved.user == user.name + " " +user.lastName)

    }

    @Test
    fun getAllTransfers() {
        trasferController!!.createTransfer(TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user)))
        trasferController.createTransfer(TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user)))
        val response = trasferController.getAll().body
        val orders = response as ArrayList<*>
        assert(orders.size == 2)
    }


    @Test
    fun deleteAnOrder() {
        val transfer = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        val transferToCreate = trasferController!!.createTransfer(transfer).body
        transferToCreate as TransferResponseDTO

        val transferId = transferToCreate.id
        trasferController.deleteByID(transferId)
        assert(trasferController.getTransfer(transferId).body == "No existe una transferencia con ese id")
    }

    @AfterEach
    fun clear() {
        orderService!!.deleteAll()
        userService!!.deleteAll()
        trasferController!!.deleteAll()
    }
}
