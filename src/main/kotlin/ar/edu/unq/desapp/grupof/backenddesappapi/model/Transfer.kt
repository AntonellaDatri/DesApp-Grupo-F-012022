package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidUserTransfer
import java.util.Date
import javax.persistence.*


@Entity
@Table(name= "transfer")
class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null
    @ManyToOne
    lateinit var  order : Order
    @ManyToOne()
    @JoinColumn(name = "transfer")
    lateinit var executingUser : User
    @Column
    var amountToTransfer:Double? = null
    @Column
    var cryptoPrice:Double? = null
    @Column
    var dateTime: Date = Date()
    @Column
    @Enumerated(EnumType.STRING)
    var state:State = State.ACTIVE

    constructor() : super()
    constructor(
        order:Order, amountToTransfer: Double, executingUser: User, cryptoPrice:Double
    ) {
        this.cryptoPrice = cryptoPrice
        this.order = order
        this.executingUser = executingUser
        this.amountToTransfer = amountToTransfer
    }

    fun makeTransfer(user: User){
        transferMoney(user)
        order.state = State.PENDING
        state = State.PENDING
    }

    fun confirmReception(user: User){
        transfer(user)
        val amountTotal = order.amountToOperate!! - amountToTransfer!!
        order.setAmount(amountTotal)
        state = State.DONE
        calculateReputation()
    }

    private fun calculateReputation() {
        if (performedWithin30Min()) {
            order.user.points += 10
            executingUser.points += 10
        } else {
            order.user.points += 5
            executingUser.points += 5
        }
        order.user.amountOperations += 1
    }

    fun cancel(user: User){
        if (order.user.id == user.id || executingUser.id!! == user.id) {
             user.points -= 20
            state = State.CANCEL
        }
    }

    private fun performedWithin30Min(): Boolean {
        val actualDate = Date()
        val diff: Long = actualDate.time - dateTime.time
        val min = 1800000
        return diff <= min
    }

    private fun transferMoney(user: User){
        if (order.operation == Operation.BUY && executingUser.id == user.id) {
            executingUser.transferMoney(order.user.cvu)
        } else if(order.operation == Operation.SELL && order.user.id == user.id) {
            order.user.transferMoney(executingUser.cvu)
        }  else {
            throw InvalidUserTransfer("The User doesn't belong to the transfer.")
        }
    }

    private fun transfer(user : User) {
        if (order.operation == Operation.BUY && order.user.id == user.id) {
            order.user.transferCrypto(executingUser.walletAddress!!)
        } else if(order.operation == Operation.SELL && executingUser.id == user.id) {
            order.user.transferMoney(executingUser.cvu)
        } else {
            throw InvalidUserTransfer("The User doesn't belong to the transfer.")
        }
    }
}