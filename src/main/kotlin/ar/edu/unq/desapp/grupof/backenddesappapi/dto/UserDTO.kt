package ar.edu.unq.desapp.grupof.backenddesappapi.dto

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User


data class UserDTO(val name:String, val lastName: String, val email:String, val walletAddress : Int) {

    companion object {
        fun fromModel(user : User): UserDTO {
            return UserDTO(
                user.name,
                user.lastName,
                user.email,
                user.walletAddress!!
            )
        }
    }
}