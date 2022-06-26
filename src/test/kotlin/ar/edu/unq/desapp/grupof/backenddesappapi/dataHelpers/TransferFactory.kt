package ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Transfer
import ar.edu.unq.desapp.grupof.backenddesappapi.model.User

class TransferFactory {
    fun anyTransfer(order: Order = OrderFactory().anyOrder(),
                    amountToTransfer: Double  = 100.0,
                    executingUser: User = UserFactory().anyUser(),
                    cryptoPrice: Double = 210.0): Transfer {
        return Transfer(order,amountToTransfer, executingUser, cryptoPrice)
    }
}