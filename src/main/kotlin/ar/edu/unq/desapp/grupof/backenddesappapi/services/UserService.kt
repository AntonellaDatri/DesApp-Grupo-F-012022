package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.model.UserDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService {
    @Autowired
    private val repository: UserRepository? = null

    @Transactional
    fun register(user: User) {
        try {
            user.validateData(user.name, user.lastName, user.email, user.address, user.password, user.cvu, user.walletAddress)
            repository!!.save(user)
        } catch (e:Exception) {
            throw e
        }
    }

    fun findByID(id: Int): UserDTO {
        if (repository!!.existsById(id)) {
            val user = repository.findById(id).get()
            return UserDTO(user.name, user.lastName, user.email)
        } else {
            throw Exception("No existe ese ID")
        }

    }

    fun findByIDComplete(id: Int): User {
        if (repository!!.existsById(id)) {
            val user = repository.findById(id).get()
            return user
        } else {
            throw Exception("No existe ese ID")
        }

    }
    fun deleteByID(id: Int) {
        repository!!.deleteById(id)
    }
    @Transactional
    fun findAll(): MutableList<UserDTO?> {
        var listDTO =  mutableListOf<UserDTO?>()
        repository!!.findAll().forEach {
            listDTO.add(UserDTO(it.name, it.lastName, it.email))
        }
        return listDTO
    }
}