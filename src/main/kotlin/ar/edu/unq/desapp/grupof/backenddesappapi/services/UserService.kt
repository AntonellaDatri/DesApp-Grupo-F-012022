package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.User
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService {
    @Autowired
    private val repository: UserRepository? = null
    /*The minimum characters of name is 3, the maximum is 30*/

    private fun validateName(name: String): Boolean {
        return (name.length in 3..30)
    }
    /*The minimum characters of lastname is 3, the maximum is 30*/
    private fun validateLastName(lastname: String): Boolean {
        return (lastname.length in 3..30)
    }

    private fun validateEmail(email: String): Boolean {
        return (email.contains('@') && (email.endsWith(".com") || email.endsWith(".ar")))
    }

    private fun validateAddress(address: String): Boolean {
        return (address.length in 10..30)
    }

    private fun validateCVU(cvu: String): Boolean {
        return (cvu.length == 22)
    }
    private fun validateAddressWallet(walletAddress: Int): Boolean {
        return (walletAddress.toString().length == 8)
    }
    @Transactional
    fun save(user: User) {
        if (validateName(user.name!!) && validateLastName(user.lastName!!) && validateEmail(user.email!!) && validateAddress(user.address!!) && validateAddressWallet(user.walletAddress!!)
            && validateCVU(user.cvu!!)) {
            repository!!.save(user)
        }
        else {
            throw Exception("Something is wrong")
        }
    }

    fun findByID(id: Int): User {
        return repository!!.findById(id).get()
    }

    @Transactional
    fun findAll(): List<User?> {
        return repository!!.findAll()
    }
}