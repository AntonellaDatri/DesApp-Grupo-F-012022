package ar.edu.unq.desapp.grupof.backenddesappapi.model

import java.util.Date
import javax.persistence.*

@Entity
@Table(name= "transaction")
class ActivityTransaction{
    @Id
    var id : Int? = null
    @Column
    var hour : String? = null
    @Column
    var cryptoactive : String? = null
    @Column
    var amount:Float? = null
    @Column
    var quote: Float? = null
    @Column
    var ARGOperation:Float? = null
    @Column
    var user:String? = null
    @Column
    var amountOperation:Int? = null
    @Column
    var reputation:Int? = null

    constructor() : super()
    constructor(
        cryptoactive:String, amount: Float, hour:String,
        quote:Float,ARGOperation:Float, user: String,
        amountOperation:Int, reputation:Int,

        ) : super() {
        this.hour = hour
        this.cryptoactive = cryptoactive
        this.amount = amount
        this.quote = quote
        this.ARGOperation = ARGOperation
        this.user = user
        this.amountOperation = amountOperation
        this.reputation = reputation
    }
}