package ar.edu.unq.desapp.grupof.backenddesappapi.dto

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Transfer

data class TransferActivesDTO(
    val cryptoActive:String,
    val amount: Double,
    val quote: Double,
    val arsAmount:Double){

    companion object {
        fun fromModel(transfer: Transfer): TransferActivesDTO {
            val order = transfer.order
            return TransferActivesDTO(
                order.cryptoName!!,
                order.amountToOperate!!,
                transfer.cryptoPrice!!,
                transfer.cryptoPrice!! * order.amountToOperate!!
            )
        }
    }
}