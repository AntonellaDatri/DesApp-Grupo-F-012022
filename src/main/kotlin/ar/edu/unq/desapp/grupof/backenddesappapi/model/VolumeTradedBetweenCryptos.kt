package ar.edu.unq.desapp.grupof.backenddesappapi.model

import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name= "operationVolume")
class VolumeTradedBetweenCryptos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null

    @Column
    var user:String? = null
    @Column
    var dateTime:LocalDateTime? = null
    @Column
    var amountInUSD:Int? = null
    @Column
    var amountInARG:Int? = null
    @Column
    var cryptoActive:String? = null
    @Column
    var nominal:Int? = null
    @Column
    var actualQuote:Int? = null
    @Column
    var amountQuoteInARG:Int? = null
    constructor() : super()
    constructor(
        user: String, dateTime: LocalDateTime, amountInUSD:Int, amountInARG:Int,
        cryptoActive:String, nominal:Int, actualQuote:Int, amountQuoteInARG:Int
    ) : super() {
        this.cryptoActive = cryptoActive
        this.amountInUSD = amountInUSD
        this.amountQuoteInARG = amountQuoteInARG
        this.dateTime = dateTime
        this.user = user
        this.amountInARG = amountInARG
        this.nominal = nominal
        this.actualQuote = actualQuote
    }
}