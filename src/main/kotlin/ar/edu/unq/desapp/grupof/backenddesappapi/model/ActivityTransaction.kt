package ar.edu.unq.desapp.grupof.backenddesappapi.model

import javax.persistence.*

@Entity
class ActivityTransaction : IntentionToOperate {
    @Column
    var hour : String? = null
    @Column
    var amountOperation:Int? = null
    @Column
    var reputation:Int? = null

    constructor() : super()
    constructor(
        cryptoActive:String, amount: Double,
        quote:Double, walletAddress: Int,
        hour:String, amountOperation: Int,
        reputation:Int
    ) : super() {
        this.cryptoactive = cryptoActive
        this.amount = amount
        this.quote = quote
        this.argAmount = amount * quote
        this.userID = walletAddress
        this.hour = hour
        this.amountOperation = amountOperation
        this.reputation = reputation
    }
}