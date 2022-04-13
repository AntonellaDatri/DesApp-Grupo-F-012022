package ar.edu.unq.desapp.grupof.bakenddesappapi.mongoDB

import ar.edu.unq.desapp.grupof.bakenddesappapi.user.User
import ar.edu.unq.desapp.grupof.bakenddesappapi.user.UserDAO

class DataUserMongo() : GenericMongoDAO<User>(User::class.java), UserDAO {
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