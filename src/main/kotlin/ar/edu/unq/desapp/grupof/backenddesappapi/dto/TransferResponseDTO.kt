package ar.edu.unq.desapp.grupof.backenddesappapi.dto

import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.Operation
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Transfer
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.State

data class TransferResponseDTO(
    val id:Int,
    val cryptoActive:String,
    val amountToOperate: Double,
    val quote: Double,
    val amountToTransfer:Double,
    val user: String,
    val amountOperations:Int,
    val reputation:String,
    val addressShipping : String,
    val state:State
    ){

    companion object {
        fun fromModel(transfer: Transfer): TransferResponseDTO {
            val order = transfer.order
            val user = order.user
            return TransferResponseDTO(
                transfer.id!!,
                order.cryptoName!!,
                order.amountToOperate,
                transfer.cryptoPrice!!,
                transfer.amountToTransfer!!,
                user.name + " " +user.lastName,
                user.amountOperations,
                if(order.user.amountOperations != 0) (order.user.points / order.user.amountOperations).toString() else "Sin operaciones",
                getShippingAddress(order),
                transfer.state
            )
        }
        private fun getShippingAddress(order: Order) : String {
            return if (order.operation == Operation.BUY) {
                order.user.walletAddress.toString()
            } else {
                order.user.cvu
            }
        }
    }
}