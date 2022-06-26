package ar.edu.unq.desapp.grupof.backenddesappapi.servicesTests

import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.OrderFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.TransferFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.UserFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.OrderRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.TransferRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidTransactionTransfer
import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidUserTransfer
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.Operation
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.State
import ar.edu.unq.desapp.grupof.backenddesappapi.services.OrderService
import ar.edu.unq.desapp.grupof.backenddesappapi.services.TransferService
import ar.edu.unq.desapp.grupof.backenddesappapi.services.UserService
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@SpringBootTest
class TransferServiceTest {
    @Autowired
    private val transferService: TransferService? = null
    @Autowired
    private val userService: UserService? = null
    @Autowired
    private val orderService: OrderService? = null
    private val transferFactory : TransferFactory = TransferFactory()
    private lateinit var user : User
    private lateinit var orderUser : User
    private lateinit var order: Order
    private val userFactory : UserFactory = UserFactory()


    @BeforeEach
    fun setUp() {
        val user1 = userFactory.anyUser()
        val user2 = userFactory.anyUser(cvu = "1111111111111111111122", email = "ejemplo2@mail.com", walletAddress = 22222222)
        val userToSave = UserRequestDTO.fromModel(user1)
        val userToSave2 = UserRequestDTO.fromModel(user2)
        val userSaved = userService!!.register(userToSave)
        val userSaved2 = userService.register(userToSave2)
        orderUser = userService.findUserByID(userSaved.id)
        user = userService.findUserByID(userSaved2.id)
        val orderToCreate = OrderRequestDTO.fromModel(OrderFactory().anyOrder(user = orderUser))
        val orderSaved = orderService!!.create(orderToCreate)
        order = orderService.findOrderByID(orderSaved.id)
    }

    @Test
    fun registerTransfer() {
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        Assertions.assertDoesNotThrow {  transferService!!.createTransfer(transferToCreate)  }
    }

    @Test
    fun registerTransferWithSameUserMakeOrder() {
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = orderUser))

        assertThrows<InvalidUserTransfer>("No se puede crear una transferencia con el usuario que creo la orden")
        { transferService!!.createTransfer(transferToCreate) }
    }

    @Test
    fun cantMakeTransferWhenTransferIsNoActive() {
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        val transfer1 = transferService!!.createTransfer(transferToCreate)
        transferService.makeTransfer(transfer1.id,  orderUser.id!!)
        val transfer2 = transferService.createTransfer(transferToCreate)

        assertThrows<InvalidTransactionTransfer>("No se puede transferir  la plata")
        { transferService.makeTransfer(transfer2.id,  orderUser.id!!) }
    }

    @Test
    fun makeTransferInActiveTransferWhenOrderIsSell() {
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        var transfer = transferService!!.createTransfer(transferToCreate)
        Assertions.assertDoesNotThrow {  transferService.makeTransfer(transfer.id, orderUser.id!!) }
        transfer = transferService.findByID(transfer.id)

        assert(transfer.state == State.PENDING)
    }

    @Test
    fun makeTransferInActiveTransferWhenOrderIsBuy() {
        changeOrderToBuy()
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        var transfer = transferService!!.createTransfer(transferToCreate)
        Assertions.assertDoesNotThrow {  transferService.makeTransfer(transfer.id, user.id!!) }
        transfer = transferService.findByID(transfer.id)

        assert(transfer.state == State.PENDING)
    }

    @Test
    fun cantMakeTransferWhenOrderIsSellAndUserIsHowCreateTheTransfer() {
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        val transfer = transferService!!.createTransfer(transferToCreate)

        assertThrows<InvalidUserTransfer>("El usuario no puede hacer esta accion")
        {  transferService.makeTransfer(transfer.id, user.id!!) }
    }

    @Test
    fun cantMakeTransferWhenOrderIsBuyUserIsHowCreateTheOrder() {
        changeOrderToBuy()
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        val transfer = transferService!!.createTransfer(transferToCreate)

        assertThrows<InvalidUserTransfer>("El usuario no puede hacer esta accion")
        {  transferService.makeTransfer(transfer.id,  orderUser.id!!) }
    }

    @Test
    fun confirmTransferIsPendingTransfer() {
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        var transfer = transferService!!.createTransfer(transferToCreate)
        transferService.makeTransfer(transfer.id,  orderUser.id!!)
        Assertions.assertDoesNotThrow {  transferService.confirmTransfer(transfer.id, user.id!!) }
        transfer = transferService.findByID(transfer.id)

        val userConfirm = userService!!.findUserByID(user.id!!)
        val order = orderService!!.findOrderByID(order.id!!)
        assert(transfer.state == State.DONE)
        assert(userConfirm.points == 10)
        assert(order.user.points == 10)
    }

    @Test
    fun cantConfirmTransferWhenTransferIsNotPending() {
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        val transfer = transferService!!.createTransfer(transferToCreate)

        assertThrows<InvalidTransactionTransfer>("No se puede confirmar")
        { transferService.confirmTransfer(transfer.id, user.id!!) }
    }

    @Test
    fun cancelTransfer() {
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        var transfer = transferService!!.createTransfer(transferToCreate)
        Assertions.assertDoesNotThrow {  transferService.cancelTransfer(transfer.id, user.id!!) }

        transfer = transferService.findByID(transfer.id)
        val userConfirm = userService!!.findUserByID(user.id!!)
        val order = orderService!!.findOrderByID(order.id!!)
        assert(transfer.state == State.CANCEL)
        assert(order.state == State.ACTIVE)
        assert(userConfirm.points == -20)
        assert(order.user.points == 0)
    }

    @Test
    fun cantCancelTransferWhenTrasnferStateIsCancel() {
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        val transfer = transferService!!.createTransfer(transferToCreate)
        transferService.cancelTransfer(transfer.id, user.id!!)

        assertThrows<InvalidTransactionTransfer>("No se puede cancelar")
        { transferService.cancelTransfer(transfer.id, user.id!!) }

    }

    @Test
    fun getVolumenOperation() {
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        transferService!!.createTransfer(transferToCreate)
        transferService.createTransfer(transferToCreate)
        transferService.createTransfer(transferToCreate)

        val tomorrow = LocalDate.now().plusDays(1)
        val yesterday = LocalDate.now().minusDays(1)
        val formattedTomorrow: String = tomorrow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val formattedYesterday: String = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val volumen = transferService.getVolumeOperation(user.id!!, formattedYesterday , formattedTomorrow)
        assert(volumen.listActives.size == 3)
    }

    @Test
    fun findAllTransfers() {
        val transferToCreate = TransferRequestDTO.fromModel(transferFactory.anyTransfer(order = order, executingUser = user))
        transferService!!.createTransfer(transferToCreate)
        transferService.createTransfer(transferToCreate)
        transferService.createTransfer(transferToCreate)

        val trasfers = transferService.findAll()
        assert(trasfers.size == 3)
    }

    @AfterEach
    fun clear() {
        userService!!.deleteAll()
        transferService!!.deleteAll()
        orderService!!.deleteAll()
    }

    private fun changeOrderToBuy(){
        order.operation = Operation.BUY
        orderService!!.save(order)
    }
}