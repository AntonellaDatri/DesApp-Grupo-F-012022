package ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers

import ar.edu.unq.desapp.grupof.backenddesappapi.dto.TransferRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.TransferResponseDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Transfer
import ar.edu.unq.desapp.grupof.backenddesappapi.model.User

class TransferFactory {
    fun anyTransfer(order: Order = OrderFactory().anyOrder(),
                    amountToTransfer: Double  = 100.0,
                    executingUser: User = UserFactory().anyUser(),
                    cryptoPrice: Double = 210.0): Transfer {
        executingUser.id = 2
        return Transfer(order,amountToTransfer, executingUser, cryptoPrice)
    }

    fun anyTransferRequestDTO(transfer: Transfer = anyTransfer()): TransferRequestDTO {
        return TransferRequestDTO.fromModel(transfer)
    }

    fun anyTransferResponseDTO(transfer: Transfer = anyTransfer()): TransferResponseDTO {
        return TransferResponseDTO.fromModel(transfer)
    }
}