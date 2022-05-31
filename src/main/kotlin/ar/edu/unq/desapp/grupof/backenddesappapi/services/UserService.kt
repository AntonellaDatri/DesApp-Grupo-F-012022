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
    fun register(user: User): User {
        try {
            user.validateData(user.name, user.lastName, user.email, user.address, user.password, user.cvu, user.walletAddress)
            return repository!!.save(user)
        } catch (e:Exception) {
            throw e
        }
    }

    fun findByID(id: Int): UserDTO {
        try {
            val user = repository!!.findById(id).get()
            return UserDTO(user.name, user.lastName, user.email, user.walletAddress!!)
        } catch (e:Exception) {
            throw Exception("There is no user with this ID")
        }

    }

    fun findByWalletAddress(walletAddress: Int): UserDTO {
        try {
            val user = repository!!.findByWalletAddress(walletAddress).get()
            return UserDTO(user.name, user.lastName, user.email, user.walletAddress!!)
        } catch (e:Exception) {
            throw Exception("There is no user with this wallet Address")
        }

    }

    fun deleteByID(id: Int) {
        repository!!.deleteById(id)
    }
    @Transactional
    fun findAll(): MutableList<UserDTO?> {
        val listDTO =  mutableListOf<UserDTO?>()
        repository!!.findAll().forEach {
            listDTO.add(UserDTO(it.name, it.lastName, it.email, it.walletAddress!!))
        }
        return listDTO
    }

    fun clear() {
        repository!!.deleteAll()
    }
}