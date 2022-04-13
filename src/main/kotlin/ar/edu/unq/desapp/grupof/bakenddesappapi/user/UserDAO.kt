package ar.edu.unq.desapp.grupof.bakenddesappapi.user

import ar.edu.unq.desapp.grupof.bakenddesappapi.user.User

interface UserDAO {
    fun getAllUsers(): List<User>
    fun deleteUser(user : User)
    fun getUser(direction : Int): User?
    fun saveUser(user: User)
    fun clear()
}
