package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers.OrderFactory
import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidTransferAmount
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.Operation
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.State
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OrderTest {
    private val orderFactory = OrderFactory()
    @Test
    fun createABuyOrder() {
        val order = orderFactory.anyOrder(operation = Operation.BUY)
        order.operation?.let { assert(it == Operation.BUY) }
    }

    @Test
    fun createASellOrder() {
        val order = orderFactory.anyOrder()
        order.operation?.let { assert(it == Operation.SELL) }
    }
    @Test
    fun changeOrderStateDone() {
        val order = orderFactory.anyOrder()
        assert(order.state == State.ACTIVE)
        order.setAmount(0.0)
        assert(order.state == State.DONE)
    }

    @Test
    fun stillActive() {
        val order = orderFactory.anyOrder()
        assert(order.state == State.ACTIVE)
        order.setAmount(100.0)
        assert(order.state == State.ACTIVE)
    }

    @Test
    fun invalidTransferAmount() {
        val order = orderFactory.anyOrder()
        assertThrows<InvalidTransferAmount> {  order.setAmount(-10.0)}
    }
}