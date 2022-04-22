package ar.edu.unq.desapp.grupof.backenddesappapi.model

import java.util.Date
import javax.persistence.*

@Entity
@Table(name= "cryptoQuote")
class CryptoQuote{
    @Id
    var id : Int? = null
    @Column
    var cryptoactive : String? = null
    @Column
    var date:Date? = null
    @Column
    var quote: Float? = null


    constructor() : super()
    constructor(
        cryptoactive:String, date: Date,
        quote:Float,
    ) : super() {
        this.cryptoactive = cryptoactive
        this.date = date
        this.quote = quote
    }
}