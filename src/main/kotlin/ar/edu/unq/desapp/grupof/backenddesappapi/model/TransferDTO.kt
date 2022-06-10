package ar.edu.unq.desapp.grupof.backenddesappapi.model
data class TransferDTO(
    val cryptoActive:String,
    val amount: Double,
    val quote: Double,
    val amountToTransfer:Double,
    val user: String,
    val amountOperations:Int,
    val reputation:String,
    val addressShipping : String
    ){

    companion object {
        fun fromModel(transfer: Transfer): TransferDTO {
            val order = transfer.order
            val user = order.user
            return TransferDTO(
                order.cryptoactive!!,
                order.amount!!,
                order.quote!!,
                transfer.amountToTransfer!!,
                user.name + " " +user.lastName,
                user.amountOperations,
                if(order.user.amountOperations != 0) (order.user.points / order.user.amountOperations).toString() else "Sin operaciones",
                getShippingAddress(order)
            )
        }
        fun getShippingAddress(order: Order) : String {
            if (order.operation == Operations.BUY) {
                return order.user.walletAddress.toString()
            } else {
                return order.user.cvu
            }
        }
    }
}