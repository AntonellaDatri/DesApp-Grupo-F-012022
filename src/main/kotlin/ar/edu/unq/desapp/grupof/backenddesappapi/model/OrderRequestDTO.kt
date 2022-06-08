package ar.edu.unq.desapp.grupof.backenddesappapi.model


data class OrderRequestDTO(
    val cryptoActive:String,
    val amount: Double,
    val quote: Double,
    val argAmount:Double,
    val user: Int,
    val operation:Operations){

    companion object {
        fun fromModel(order: Order): OrderRequestDTO {
            return OrderRequestDTO(
                order.cryptoactive!!,
                order.amount!!,
                order.quote!!,
                order.argAmount!!,
                order.user.id!!,
                order.operation!!
            )
        }
    }
}