package ar.edu.unq.desapp.grupof.backenddesappapi.model

import javax.persistence.*

@Entity
class ProcessedUserInformation : IntentionToOperate {
    @Column
    var mailingAddress:Int? = null

    constructor() : super()
    constructor(
        cryptoActive:String, amount: Double,
        quote:Double, walletAddress: Int, mailingAddress: Int,
    ) : super() {
        this.cryptoactive = cryptoActive
        this.amount = amount
        this.quote = quote
        this.argAmount = amount * quote
        this.userID = walletAddress
        this.mailingAddress = mailingAddress
    }
    fun makeTransfer(){
        TODO()
    }

    fun confirmReception(){
        TODO()
    }

    fun cancel(){
        TODO()
    }
}