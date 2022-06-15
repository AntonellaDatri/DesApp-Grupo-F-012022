package ar.edu.unq.desapp.grupof.backenddesappapi.dto

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Operation
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order


data class OrderRequestDTO(
    val cryptoActive:String,
    val amount: Double,
    val quote: Double,
    val argAmount:Double,
    val user: Int,
    val operation: Operation
){

    companion object {
        fun fromModel(order: Order, cryptoPrice: Double): OrderRequestDTO {
            return OrderRequestDTO(
                order.cryptoactive!!,
                order.amount!!,
                cryptoPrice,
                cryptoPrice * order.amount!!,
                order.user.id!!,
                order.operation!!
            )
        }
    }
}