package ar.edu.unq.desapp.grupof.backenddesappapi.dto

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Order
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.Operation
import java.time.LocalDate

data class OrderResponseDTO(
    val id: Int,
    val hour:LocalDate,
    val cryptoActive:String,
    val amount: Double,
    val quote: Double,
    val argAmount:Double,
    val user: String,
    val operation : Operation,
    val amountOperations:Int,
    val reputation:String){

    companion object {
        fun fromModel(order: Order, cryptoPrice: Double): OrderResponseDTO {
            return OrderResponseDTO(
                order.id!!,
                LocalDate.now(),
                order.cryptoName!!,
                order.amountToOperate,
                cryptoPrice,
                cryptoPrice * order.amountToOperate,
                order.user.name + " " +order.user.lastName,
                order.operation!!,
                order.user.amountOperations,
                if(order.user.amountOperations != 0) (order.user.points / order.user.amountOperations).toString() else "Sin operaciones"
            )
        }
    }
}