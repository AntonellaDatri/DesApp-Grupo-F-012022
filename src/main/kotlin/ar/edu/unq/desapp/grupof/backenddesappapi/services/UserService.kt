package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserResponseDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService {
    @Autowired
    private val repository: UserRepository? = null

    @Transactional
    fun register(userRequestDTO: UserRequestDTO): UserResponseDTO {
        try {
            val user = User.fromModel(userRequestDTO)
            return UserResponseDTO.fromModel(repository!!.save(user))
        } catch (error: DataIntegrityViolationException) {
            throw error
        }
    }

    @Transactional
    fun findByID(id: Int): UserResponseDTO {
        try {
            return UserResponseDTO.fromModel(findUserByID(id))
        } catch (e:Exception) {
            throw Exception("No hay usuario con ese ID")
        }
    }

    @Transactional
    fun findUserByID(id: Int): User {
        try {
            return repository!!.findById(id).get()
        } catch (e:Exception) {
            throw Exception("No hay usuario con ese ID")
        }
    }


    @Transactional
    fun findByWalletAddress(walletAddress: Int): UserResponseDTO {
        try {
            val user = repository!!.findByWalletAddress(walletAddress)!!
            return UserResponseDTO.fromModel(user)
        } catch (e:Exception) {
            throw Exception("No hay un usuario con esa billetera")
        }
    }

    @Transactional
    fun findAll(): List<UserResponseDTO> {
        return repository!!.findAll().map {
            UserResponseDTO.fromModel(it)
        }
    }

    @Transactional
    fun findAllUsers(): List<User> {
        return repository!!.findAll()
    }


    @Transactional
    fun deleteByID(id: Int) {
        repository!!.deleteById(id)
    }

    @Transactional
    fun deleteAll() {
        repository!!.deleteAll()
    }
}