package ar.edu.unq.desapp.grupof.backenddesappapi.model

data class IntentionToOperateDTO(
     val cryptoActive:String, val amount: Double, val walletAddress: Int,
    val operation:Operations, val id:Int? = null){

    companion object {
        fun fromModel(intentionToOperate: IntentionToOperate): IntentionToOperateDTO {
            return IntentionToOperateDTO(
                intentionToOperate.cryptoactive!!,
                intentionToOperate.amount!!,
                intentionToOperate.user.walletAddress!!,
                intentionToOperate.operation!!,
                intentionToOperate.id
            )
        }
    }
}
