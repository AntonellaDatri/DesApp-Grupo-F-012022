package ar.edu.unq.desapp.grupof.backenddesappapi.model

import java.time.LocalDate

data class OrderDTO(
    val hour:LocalDate,
    val cryptoActive:String,
    val amount: Double,
    val quote: Double,
    val argAmount:Double,
    val user: String,
    val amountOperations:Int,
    val reputation:String){

    companion object {
        fun fromModel(order: Order, cryptoPrice: Double): OrderDTO {
            return OrderDTO(
                LocalDate.now(),
                order.cryptoactive!!,
                order.amount!!,
                cryptoPrice,
                cryptoPrice * order.amount!!,
                order.user.name + " " +order.user.lastName,
                order.user.amountOperations,
                if(order.user.amountOperations != 0) (order.user.points / order.user.amountOperations).toString() else "Sin operaciones"
            )
        }
    }
}