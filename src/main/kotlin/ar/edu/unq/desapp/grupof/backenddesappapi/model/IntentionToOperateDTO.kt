package ar.edu.unq.desapp.grupof.backenddesappapi.model

data class IntentionToOperateDTO(
     val cryptoActive:String, val amount: Double, val walletAddress: Int,
    val operation:Operations, val id:Int? = null)
