package ar.edu.unq.desapp.grupof.backenddesappapi.model

import java.util.Date
import javax.persistence.*

@Entity
@Table(name= "transaction")
class CryptoTransaction{
    @Id
    var id : Int? = null
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
    var operation:Operation? = null

    constructor() : super()
    constructor(
        cryptoactive:String, amount: Float,
        quote:Float,ARGOperation:Float, user: String,
        operation:Operation,
    ) : super() {
        this.cryptoactive = cryptoactive
        this.amount = amount
        this.quote = quote
        this.ARGOperation = ARGOperation
        this.user = user
        this.operation = operation
    }
}