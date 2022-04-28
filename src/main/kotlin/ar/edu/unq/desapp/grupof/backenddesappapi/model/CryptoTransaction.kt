package ar.edu.unq.desapp.grupof.backenddesappapi.model

import org.bson.types.ObjectId
import javax.persistence.*

@Entity
@Table(name= "transaction")
open class CryptoTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null

    @Column
    var cryptoactive : String? = null
    @Column
    var amount:Double? = null
    @Column
    var quote: Double? = null
    @Column
    var ARGOperation:Double? = null
    @Column
    var user:String? = null
//    @Column
//    var operation:Operation? = null

    constructor() : super()
    constructor(
        cryptoactive:String, amount: Double,
        quote:Double, user: String,
//        operation:Operation,
    ) : super() {
        this.cryptoactive = cryptoactive
        this.amount = amount
        this.quote = quote
        this.ARGOperation = amount * quote
        this.user = user
//        this.operation = operation
    }
}