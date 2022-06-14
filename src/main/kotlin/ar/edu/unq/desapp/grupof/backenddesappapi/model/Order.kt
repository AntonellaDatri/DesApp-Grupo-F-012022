package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidTransferAmount
import ar.edu.unq.desapp.grupof.backenddesappapi.services.CryptoAssetQuoteService
import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.*

@Autowired
private val cryptoAssetQuoteService: CryptoAssetQuoteService= CryptoAssetQuoteService()

@Entity
@Table(name= "Intention_operate")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null

    @Column
    var cryptoactive : String? = null
    @Column
    var amount:Double? = null
    @Column
    @Enumerated(EnumType.STRING)
    var operation:Operations? = null
    @ManyToOne()
    @JoinColumn(name = "orders")
    lateinit var user: User
    @OneToMany(mappedBy = "order",  cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val transfer: List<Transfer>? = null
    @Column
    @Enumerated(EnumType.STRING)
    var status:State? = State.ACTIVE

    constructor() : super()
    constructor(
        cryptoActive:String, amount: Double, user: User,
        operation:Operations
    ) : super() {
        this.cryptoactive = cryptoActive
        this.amount = amount
        this.user= user
        this.operation = operation
    }

    fun setAmount(amount:Double){
        if (amount < 0.0){
            throw InvalidTransferAmount("The amount of the transfer is greater than the amount of the intention.")
        }else if (amount == 0.0){
            this.status = State.DONE
        }
        this.amount = amount
    }
}