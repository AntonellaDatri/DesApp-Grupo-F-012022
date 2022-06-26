package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.TransferFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.UserFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidUserTransfer
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.State
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TransferTest {
    private val transferFactory = TransferFactory()
    private lateinit var transfer : Transfer
    private lateinit var user : User
    private lateinit var executingUser : User
    @BeforeEach
    fun setUp() {
        user = UserFactory().anyUser()
        executingUser = UserFactory().anyUser(email = "example1@mail.com", cvu = "1234567891234567891235", walletAddress= 11111112)
        user.id = 1
        executingUser.id = 2
        transfer = transferFactory.anyTransfer(executingUser = executingUser)
    }
    @Test
    fun createUserTest() {
        transfer.amountToTransfer = 400.0
        assert(transfer.amountToTransfer == 400.0)
    }
    @Test
    fun makeTransferStatePending() {
        transfer.order.user.id = 1
        transfer.makeTransfer(user)
        assert(transfer.state == State.PENDING)
        assert(transfer.order.state == State.PENDING)
    }

    @Test
    fun cantMakeTransfer() {
        assertThrows<InvalidUserTransfer> {  transfer.makeTransfer(executingUser)}
    }

    @Test
    fun confirmTransactionTotalAmount() {
        assert(transfer.state == State.ACTIVE)
        assert(transfer.order.state == State.ACTIVE)
        transfer.confirmReception(executingUser)
        assert(transfer.state == State.DONE)
        assert(transfer.order.state == State.DONE)
    }

    @Test
    fun confirmTransactionHalfAmount() {
        transfer = transferFactory.anyTransfer(amountToTransfer = 50.0, executingUser = executingUser)
        assert(transfer.state == State.ACTIVE)
        assert(transfer.order.state == State.ACTIVE)
        transfer.confirmReception(executingUser)
        assert(transfer.state == State.DONE)
        assert(transfer.order.state == State.ACTIVE)
    }

    @Test
    fun cantConfirmTransfer() {
       assertThrows<InvalidUserTransfer> { transfer.confirmReception(user)}
    }

    @Test
    fun userCancelTransfer() {
        assert(user.points == 0)
        assert(transfer.state == State.ACTIVE)
        transfer.order.user.id = 1
        transfer.cancel(user)
        assert(user.points == -20)
        assert(transfer.state == State.CANCEL)
    }

    @Test
    fun executingUserCancelTransfer() {
        assert(executingUser.points == 0)
        assert(transfer.state == State.ACTIVE)
        transfer.cancel(executingUser)
        assert(executingUser.points == -20)
        assert(transfer.state == State.CANCEL)
    }

    @Test
    fun externalUserCantCancel() {
        val externalUser = UserFactory().anyUser(email = "example1@mail.com", cvu = "1234567891234567891235", walletAddress= 11111112)
        externalUser.id = 3
        assert(externalUser.points == 0)
        assert(transfer.state == State.ACTIVE)
       assertThrows<InvalidUserTransfer> { transfer.cancel(externalUser)  }
    }
}