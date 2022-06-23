package ar.edu.unq.desapp.grupof.backenddesappapi.dto

import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.Operation
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order


data class OrderRequestDTO(
    val cryptoActive:String,
    val amountToOperate: Double,
    val user: Int,
    val operation: Operation
){

    companion object {
        fun fromModel(order: Order): OrderRequestDTO {
            return OrderRequestDTO(
                order.cryptoName!!,
                order.amountToOperate,
                order.user.id!!,
                order.operation!!
            )
        }
    }
}