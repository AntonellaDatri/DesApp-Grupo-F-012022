package ar.edu.unq.desapp.grupof.backenddesappapi.model
data class TransferRequestDTO(
    val amountToTransfer:Double,
    val userID: Int,
    val orderID: Int){

    companion object {
        fun fromModel(transfer: Transfer): TransferRequestDTO {
            val order = transfer.order
            val user = order.user
            return TransferRequestDTO(
                transfer.amountToTransfer!!,
                user.id!!,
                order.id!!
            )
        }
    }
}