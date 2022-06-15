package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserResponseDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
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
            //user.validateData(user.name, user.lastName, user.email, user.address, user.password, user.cvu, user.walletAddress)
            return UserResponseDTO.fromModel(repository!!.save(user))
        } catch (e:Exception) {
            throw e
        }
    }

    @Transactional
    fun findByID(id: Int): UserResponseDTO {
        try {
            return UserResponseDTO.fromModel(findUserByID(id))
        } catch (e:Exception) {
            throw Exception("There is no user with this ID")
        }
    }

    @Transactional
    fun findUserByID(id: Int): User {
        try {
            return repository!!.findById(id).get()
        } catch (e:Exception) {
            throw Exception("There is no user with this ID")
        }
    }


    @Transactional
    fun findByWalletAddress(walletAddress: Int): UserResponseDTO {
        try {
            val user = repository!!.findByWalletAddress(walletAddress)!!
            return UserResponseDTO.fromModel(user)
        } catch (e:Exception) {
            throw Exception("There is no user with this wallet Address")
        }
    }

    @Transactional
    fun findAll(): List<UserResponseDTO> {
        return repository!!.findAll().map {
            UserResponseDTO.fromModel(it)
        }
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