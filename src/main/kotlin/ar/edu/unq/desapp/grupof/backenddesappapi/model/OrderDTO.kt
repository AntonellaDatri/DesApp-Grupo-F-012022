package ar.edu.unq.desapp.grupof.backenddesappapi.model

import java.sql.Date
import java.time.LocalDate

data class OrderDTO(
    val hour:LocalDate,
    val cryptoActive:String,
    val amount: Double,
    val quote: Double,
    val argAmount:Double,
    val user: String,
    val amountOperations:Int,
    val reputation:Int){

    companion object {
        fun fromModel(order: Order): OrderDTO {
            return OrderDTO(
                LocalDate.now(),
                order.cryptoactive!!,
                order.amount!!,
                order.quote!!,
                order.argAmount!!,
                order.user.name + " " +order.user.lastName,
                order.user.amountOperations,
                order.user.points / order.user.amountOperations
            )
        }
    }
}