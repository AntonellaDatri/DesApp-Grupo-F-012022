package ar.edu.unq.desapp.grupof.backenddesappapi.model
data class TransferActivesDTO(
    val cryptoActive:String,
    val amount: Double,
    val quote: Double,
    val arsAmount:Double){

    companion object {
        fun fromModel(transfer: Transfer): TransferActivesDTO {
            val order = transfer.order
            val user = order.user
            return TransferActivesDTO(
                order.cryptoactive!!,
                order.amount!!,
                order.quote!!,
                transfer.amountToTransfer!!,

            )
        }
    }
}