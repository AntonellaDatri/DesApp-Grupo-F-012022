package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import javax.persistence.*

@Autowired
private val cryptoAssetQuoteService: CryptoAssetQuoteService= CryptoAssetQuoteService()

@Entity
@Table(name= "intent_transactions")
open class IntentionToOperate {
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
    var argAmount:Double? = null
    @Column
    var userID: Int? = null
    @Column
    @Enumerated(EnumType.STRING)
    var operation:Operations? = null

    constructor() : super()
    constructor(
        cryptoActive:String, amount: Double, walletAddress: Int,
        operation:Operations
    ) : super() {
        this.cryptoactive = cryptoActive
        this.amount = amount
        val cryptoQuote = cryptoAssetQuoteService.findByCryptoName(cryptoActive, LocalDateTime.now())
        this.quote = cryptoQuote.price.toDouble()
        this.argAmount = amount *  quote!!
        this.userID= walletAddress
        this.operation = operation
    }
}