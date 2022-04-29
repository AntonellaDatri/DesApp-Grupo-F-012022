package ar.edu.unq.desapp.grupof.backenddesappapi.model

import javax.persistence.*

@Entity
@Table(name= "transaction")
class ProcessedUserInformation : CryptoTransaction {
    @Column
    var mailingAddress:Int? = null

    constructor() : super()
    constructor(
        cryptoActive:String, amount: Double,
        quote:Double, user: String, mailingAddress: Int,
    ) : super() {
        this.cryptoactive = cryptoActive
        this.amount = amount
        this.quote = quote
        this.ARGOperation = amount * quote
        this.user = user
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