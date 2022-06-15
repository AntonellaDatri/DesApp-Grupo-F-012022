package ar.edu.unq.desapp.grupof.backenddesappapi.model
data class TransferActivesDTO(
    val cryptoActive:String,
    val amount: Double,
    val quote: Double,
    val arsAmount:Double){

    companion object {
        fun fromModel(transfer: Transfer, cryptoPrice: Double): TransferActivesDTO {
            val order = transfer.order
            return TransferActivesDTO(
                order.cryptoactive!!,
                order.amount!!,
                cryptoPrice,
                cryptoPrice * order.amount!!,

            )
        }
    }
}