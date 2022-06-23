package ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers

import ar.edu.unq.desapp.grupof.backenddesappapi.dto.OrderRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.OrderResponseDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.Operation

class OrderFactory() {
    fun anyOrder(cryptoActive: String = "BNBUSDT",
                 amount: Double  = 100.0,
                 user: User = UserFactory().anyUser(),
                 operation: Operation = Operation.SELL): Order {
        return Order(cryptoActive,amount, user, operation)
    }

    fun anyOrderResponseDTO(order: Order = anyOrder(), cryptoPrice: Double = 210.0): OrderResponseDTO {
        return OrderResponseDTO.fromModel(order, cryptoPrice)
    }

    fun anyOrderRequestDTO(order: Order = anyOrder(), cryptoPrice: Double = 210.0): OrderRequestDTO {
        order.user.id = 1
        return OrderRequestDTO.fromModel(order)
    }
}