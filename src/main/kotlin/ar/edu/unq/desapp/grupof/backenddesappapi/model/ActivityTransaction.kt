package ar.edu.unq.desapp.grupof.backenddesappapi.model

import javax.persistence.*

@Entity
@Table(name= "transaction")
class ActivityTransaction : CryptoTransaction {
    @Column
    var hour : String? = null
    @Column
    var amountOperation:Int? = null
    @Column
    var reputation:Int? = null

    constructor() : super()
    constructor(
        cryptoActive:String, amount: Double,
        quote:Double, user: String,
        hour:String, amountOperation: Int,
        reputation:Int
    ) : super() {
        this.cryptoactive = cryptoActive
        this.amount = amount
        this.quote = quote
        this.ARGOperation = amount * quote
        this.user = user
        this.hour = hour
        this.amountOperation = amountOperation
        this.reputation = reputation
    }
}