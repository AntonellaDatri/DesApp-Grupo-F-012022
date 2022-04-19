package ar.edu.unq.desapp.grupof.backenddesappapi.user

interface UserDAO {
    fun getAllUsers(): List<User>
    fun deleteUser(user : User)
    fun getUser(direction : Int): User?
    fun saveUser(user: User)
    fun clear()
}
