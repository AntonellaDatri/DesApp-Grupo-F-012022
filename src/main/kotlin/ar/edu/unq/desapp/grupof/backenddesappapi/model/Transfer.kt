package ar.edu.unq.desapp.grupof.backenddesappapi.model

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null
    @Column
    lateinit var  order : Order
    @Column
    lateinit var executingUser : User
    @Column
    var amountToTransfer:Double? = null
    @Column
    var dateTime: LocalDateTime = LocalDateTime.now()
    @Column
    var status = Status.Active

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
            throw Exception }
        order.status = Status.PENDING
        status = Status.PENDING
    }

    fun confirmReception(user: User){
        if (order.operation == Operations.BUY && order.user.id == user.id) {
            order.user.transferCrypto(executingUser.walletAddress)
        } else if(order.operation == Operations.SELL && executingUser.id == user.id) {
            order.user.transferMoney(executingUser.cvu)
        } else { throw  Exception}
        val amountTotal = order.argAmount - amountToTransfer!!
        order.setAmount(amountTotal)
        status = Status.DONE
        calculateReputation()
    }

   private fun calculateReputation() {
            if (LocalDateTime.now() - dateTime <= 30) {
                order.user.points += 10
                executingUser.points += 10
            } else {
                order.user.points += 5
                executingUser.points += 5
            }
        order.user.amountOperation += 1
    }

    fun cancel(user: User){
        if (order.user.id = user.id or executingUser.id = user.id) {
             user.points -= 20
            status = Status.CANCEL
        }
    }
}