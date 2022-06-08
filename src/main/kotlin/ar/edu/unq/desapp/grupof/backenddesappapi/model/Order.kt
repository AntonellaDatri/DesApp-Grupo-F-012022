package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidTransferAmount
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Autowired
private val cryptoAssetQuoteService: CryptoAssetQuoteService= CryptoAssetQuoteService()

@Entity
@Table(name= "ORDER")
open class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id : Int? = null

    @Column
    open var cryptoactive : String? = null
    @Column
    open var amount:Double? = null
    @Column
    open var quote: Double? = null
    @Column
    open var argAmount:Double? = null
    @Column
    @Enumerated(EnumType.STRING)
    open var operation:Operations? = null
    @ManyToOne()
    open lateinit var user: User
    @OneToMany(mappedBy = "order",  cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    open val transfer: List<Transfer>? = null
    @Column
    @Enumerated(EnumType.STRING)
    open var status:State? = State.ACTIVE

    constructor() : super()
    constructor(
        cryptoActive:String, amount: Double, user: User,
        operation:Operations
    ) : super() {
        this.cryptoactive = cryptoActive
        this.amount = amount
        val cryptoQuote = cryptoAssetQuoteService.findByCryptoName(cryptoActive, LocalDateTime.now())
        this.quote = cryptoQuote.price.toDouble()
        this.argAmount = amount *  quote!!
        this.user= user
        this.operation = operation
    }

    fun setAmount(amount:Double){
        if(amount > 0.0){

        }else if (amount == 0.0){
            this.status = State.DONE
        }else{
            throw InvalidTransferAmount("The amount of the transfer is greater than the amount of the intention.")
        }
    }
}