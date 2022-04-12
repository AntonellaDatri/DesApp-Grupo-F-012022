package ar.edu.unq.desapp.grupof.backenddesappapi

import ar.edu.unq.desapp.grupof.backenddesappapi.mongoDB.DataUserMongo

class RegisterUser(
    private val name:String, private val lastname: String, private val email: String,
    private val address: String, private val password: String,
    private val cvu: Int, private val walletAddress: Int) {
    private val mongoDB = DataUserMongo()
    /*The minimum characters of name is 3, the maximum is 30*/
    private fun validateName(): Boolean {
        return (name.length in 3..30)
    }
    /*The minimum characters of lastname is 3, the maximum is 30*/
    private fun validateLastName(): Boolean {
        return (lastname.length in 3..30)
    }

    private fun validateEmail(): Boolean {
        return (email.contains('@') && (email.endsWith(".com") || email.endsWith(".ar")))
    }

    private fun validateAddress(): Boolean {
        return (address.length in 10..30)
    }

    private fun validateCVU(): Boolean {
        return (cvu.toString().length == 22)
    }

    private fun validateAddressWallet(): Boolean {
        return (walletAddress.toString().length == 8)
    }

    fun registerUser() {
        if (validateName() && validateLastName() && validateEmail() && validateAddress() && validateAddressWallet()
            && validateCVU()) {
            var user = User(name, lastname, email, password, cvu, walletAddress, address)
            mongoDB.saveUser(user)
        }
        else
            throw Exception("Something is wrong")
    }
}