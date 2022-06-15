package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidTransferAmount
import javax.persistence.*

@Entity
@Table(name= "Intention_operate")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null
    @Column
    var cryptoName : String? = null
    @Column
    var amountToOperate:Double? = null
    @Column
    @Enumerated(EnumType.STRING)
    var operation:Operation? = null
    @ManyToOne
    @JoinColumn(name = "orders")
    lateinit var user: User
    @OneToMany(mappedBy = "order",  cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val transfers: List<Transfer>? = null
    @Column
    @Enumerated(EnumType.STRING)
    var state:State? = State.ACTIVE

    constructor() : super()
    constructor(
        cryptoActive:String, amount: Double, user: User,
        operation:Operation
    ) : super() {
        this.cryptoName = cryptoActive
        this.amountToOperate = amount
        this.user= user
        this.operation = operation
    }

    fun setAmount(amount:Double){
        if (amount < 0.0){
            throw InvalidTransferAmount("The amount of the transfer is greater than the amount of the intention.")
        }else if (amount == 0.0){
            this.state = State.DONE
        }else{
            this.state = State.ACTIVE
        }
        this.amountToOperate = amount
    }
}