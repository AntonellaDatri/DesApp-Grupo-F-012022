package ar.edu.unq.desapp.grupof.backenddesappapi.mongoDB

import ar.edu.unq.desapp.grupof.backenddesappapi.User
import ar.edu.unq.desapp.grupof.backenddesappapi.UserDAO

class DataUserMongo : GenericMongoDAO<User>(User::class.java), UserDAO {
    override fun getAllUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun getUser(direction: Int): User? {
        return getBy("direction",direction)
    }

    override fun saveUser(user: User) {
        save(user)
    }

    override fun clear() {
        deleteAll()
    }

}