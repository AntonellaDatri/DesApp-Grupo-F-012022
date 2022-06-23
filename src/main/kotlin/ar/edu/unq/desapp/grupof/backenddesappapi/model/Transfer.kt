package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidUserTransfer
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.Operation
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.State
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
    @ManyToOne
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
    var state: State = State.ACTIVE

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
        val amountTotal = order.amountToSubtract - amountToTransfer!!
        order.setAmount(amountTotal)
        state = State.DONE
        calculateReputation()
    }


    fun cancel(user: User){
        if (order.user.id == user.id || executingUser.id!! == user.id) {
             user.points -= 20
            state = State.CANCEL
        } else {
            throw InvalidUserTransfer("El usuario no puede cancelar esta transferencia")
        }
    }

    private fun transferMoney(user: User){
        if (isBuyOperation() && isExecutingUser(user)) {
            executingUser.transferMoney(order.user.cvu)
        } else if(isSellOperation() && isCreatorUser(user)) {
            order.user.transferMoney(executingUser.cvu)
        }  else {
            throw InvalidUserTransfer("El usuario no puede hacer esta accion")
        }
    }

    private fun transfer(user : User) {
        if (isBuyOperation() && isCreatorUser(user)) {
            order.user.transferCrypto(executingUser.walletAddress!!)
        } else if(isSellOperation() && isExecutingUser(user)) {
            order.user.transferMoney(executingUser.cvu)
        } else {
            throw InvalidUserTransfer("El usuario no puede hacer esta accion")
        }
    }

    private fun performedWithin30Min(): Boolean {
        val actualDate = Date()
        val diff: Long = actualDate.time - dateTime.time
        val min = 1800000
        return diff <= min
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

    private fun isExecutingUser(user: User) : Boolean {
        return executingUser.id == user.id
    }
    private fun isCreatorUser(user: User) : Boolean {
        return order.user.id == user.id
    }
    private fun isSellOperation() :Boolean {
        return isOperation(Operation.SELL)
    }
    private fun isBuyOperation() :Boolean {
        return isOperation(Operation.BUY)
    }
    private fun isOperation(operation: Operation) :Boolean {
        return order.operation == operation
    }
}