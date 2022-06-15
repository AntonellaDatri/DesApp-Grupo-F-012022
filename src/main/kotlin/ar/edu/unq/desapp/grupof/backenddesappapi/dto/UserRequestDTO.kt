package ar.edu.unq.desapp.grupof.backenddesappapi.dto

data class UserRequestDTO (
   val name:String, val lastName: String,
    val email:String, val password:String,
    val cvu: String, val walletAddress: Int,
    val address: String
)