package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidTransferAmount
import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidUserTransfer
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import javax.persistence.*

@Autowired
private val cryptoAssetQuoteService: CryptoAssetQuoteService= CryptoAssetQuoteService()

@Entity
@Table(name= "intent_transactions")
open class Order {
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
    @ManyToOne()
    lateinit var user: User
    @OneToMany(mappedBy = "transfer",  cascade = [CascadeType.ALL], orphanRemoval = true)
    val transfer: List<Transfer>? = null
    @Column
    @Enumerated(EnumType.STRING)
    var state:State? = State.ACTIVE

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

    fun setAmount(amount:Double){
        if(amount > 0.0){

        }else if (amount == 0.0){
            this.state = State.DONE
        }else{
            throw InvalidTransferAmount("The amount of the transfer is greater than the amount of the intention.")
        }
    }
}