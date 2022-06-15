package ar.edu.unq.desapp.grupof.backenddesappapi.dto

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User

data class UserRequestDTO (
   val name:String, val lastName: String,
    val email:String, val password:String,
    val cvu: String, val walletAddress: Int,
    val address: String
) {
    companion object {
        fun fromModel(user : User): UserRequestDTO {
            return UserRequestDTO(
                user.name,
                user.lastName,
                user.email,
                user.password,
                user.cvu,
                user.walletAddress!!,
                user.address
            )
        }
    }
}