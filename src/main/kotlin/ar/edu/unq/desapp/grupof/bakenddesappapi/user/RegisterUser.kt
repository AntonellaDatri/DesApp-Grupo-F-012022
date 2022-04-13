package ar.edu.unq.desapp.grupof.bakenddesappapi.user

import ar.edu.unq.desapp.grupof.bakenddesappapi.mongoDB.DataUserMongo


class RegisterUser {
    private val mongoDB = DataUserMongo()
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

    fun registerUser(name:String, lastname: String, email: String,address: String, password: String, cvu: String, walletAddress: Int) {
        if (validateName(name) && validateLastName(lastname) && validateEmail(email) && validateAddress(address) && validateAddressWallet(walletAddress)
            && validateCVU(cvu)) {
            val user = User(name, lastname, email, password, cvu, walletAddress, address)
            mongoDB.saveUser(user)
        }
        else
            throw Exception("Something is wrong")
    }

    fun getUserBy(walletAddress: Int): User? {
        return mongoDB.getBy("walletAddress", walletAddress)
    }
}