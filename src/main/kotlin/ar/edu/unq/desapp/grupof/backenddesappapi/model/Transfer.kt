package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidUserTransfer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*


@Entity
@Table(name= "Transfer")
class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null
    @ManyToOne()
    lateinit var  order : Order
    @ManyToOne()
    lateinit var executingUser : User
    @Column
    var amountToTransfer:Double? = null
    @Column
    var dateTime: LocalDateTime = LocalDateTime.now()
    @Column
    var status:State = State.ACTIVE

    constructor() : super()
    constructor(
        order:Order, amountToTransfer: Double, executingUser: User
    ) {
        this.order = order
        this.executingUser = executingUser
        this.amountToTransfer = amountToTransfer
    }

    fun makeTransfer(user: User){
        if (order.operation == Operations.BUY && executingUser.id == user.id) {
            executingUser.transferMoney(order.user.cvu)
        } else if(order.operation == Operations.SELL && order.user.id == user.id) {
            order.user.transferMoney(executingUser.cvu)
        }  else {
            throw InvalidUserTransfer("The User doesn't belong to the transfer.")
        }
        order.state = State.PENDING
        status = State.PENDING
    }

    fun confirmReception(user: User){
        if (order.operation == Operations.BUY && order.user.id == user.id) {
            order.user.transferCrypto(executingUser.walletAddress!!)
        } else if(order.operation == Operations.SELL && executingUser.id == user.id) {
            order.user.transferMoney(executingUser.cvu)
        } else {
            throw InvalidUserTransfer("The User doesn't belong to the transfer.")
        }
        val amountTotal = order.argAmount!! - amountToTransfer!!
        order.setAmount(amountTotal)
        status = State.DONE
        calculateReputation()
    }

   private fun calculateReputation() {
            if ((LocalDateTime.now().minus(this.dateTime)) <= 30) {
                order.user.points += 10
                executingUser.points += 10
            } else {
                order.user.points += 5
                executingUser.points += 5
            }
        order.user.operations += 1
    }

    fun cancel(user: User){
        if (order.user.id == user.id || executingUser.id!! == user.id) {
             user.points -= 20
            status = State.CANCEL
        }
    }

    private fun performedWithin30Min(){
    }
}