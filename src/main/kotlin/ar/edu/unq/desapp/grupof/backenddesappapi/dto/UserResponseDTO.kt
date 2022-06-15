package ar.edu.unq.desapp.grupof.backenddesappapi.dto

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User


data class UserResponseDTO(val name:String, val lastName: String, val email:String, val walletAddress : Int) {

    companion object {
        fun fromModel(user : User): UserResponseDTO {
            return UserResponseDTO(
                user.name,
                user.lastName,
                user.email,
                user.walletAddress!!
            )
        }
    }
}