package ar.edu.unq.desapp.grupof.backenddesappapi.mongoDB

import com.mongodb.*
import com.mongodb.client.MongoClient
import com.mongodb.client.*

class MongoConnection {
    private var client: MongoClient
    private var dataBase: MongoDatabase


    fun <T> getCollection(name:String, entityType: Class<T> ): MongoCollection<T> {
        try{
            dataBase.createCollection(name)
        } catch (exception: MongoCommandException){
            println("Ya existe la coleccion $name")
        }
        return dataBase.getCollection(name, entityType)
    }

    init {
        val uri = System.getenv().getOrDefault("MONGO_URI", "mongodb://localhost:27017")
        val database = System.getenv().getOrDefault("MONGO_DB", "desaApi")
        val connectionString = ConnectionString(uri)
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        client = MongoClients.create(settings)
        dataBase = client.getDatabase(database)
    }
}