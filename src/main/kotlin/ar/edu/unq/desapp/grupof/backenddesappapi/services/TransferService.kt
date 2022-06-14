package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidTransacctionTransfer
import ar.edu.unq.desapp.grupof.backenddesappapi.model.*
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.ActivityTransactionRepository
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


@Service
class TransferService {
    @Autowired
    private val repository: ActivityTransactionRepository? = null
    @Autowired
    private val orderService: OrderService? = null
    @Autowired
    private val cryptoAssetQuoteService: CryptoAssetQuoteService? = null
    @Autowired
    private val userRepository: UserRepository? = null
    @Transactional
    fun createTransfer(transfer: TransferRequestDTO) : Transfer {
        val order = orderService!!.findByID(transfer.orderID)
        val user = userRepository!!.findById(transfer.userID).get()
        val transfer = Transfer(order, transfer.amountToTransfer, user )
        repository!!.save(transfer)
        return transfer
    }

    fun findByID(id: Int): Transfer {
        return repository!!.findById(id).get()
    }

    @Transactional
    fun findAll(): List<TransferDTO?> {
        val transfers = repository!!.findAll()
        val criptsPrice =  cryptoAssetQuoteService!!.getTenCryptoAssets(LocalDateTime.now())
        return transfers.map {
            TransferDTO.fromModel(it!!, criptsPrice[it.order.cryptoactive!!]!!.price.toDouble())
        }
    }

    @Transactional
    fun findBetween(userId : Int, dateString1: String, dateString2: String): List<TransferActivesDTO> {
        //TODO transaction
        val date1 = formateDate(dateString1)
        val date2 = formateDate(dateString2)
        val filter1 = repository!!.findAll().filter {
            val dateTime = formateDate(it!!.dateTime    .toString())
            ((it.executingUser.id == userId) || (it.order.user.id == userId))
            && (dateTime >= date1) && (dateTime <= date2) }

        val criptsPrice =  cryptoAssetQuoteService!!.getTenCryptoAssets(LocalDateTime.now())
        return filter1.map{
            TransferActivesDTO.fromModel(it!!, criptsPrice[it.order.cryptoactive!!]!!.price.toDouble())
        }

    }

    fun deleteById(id: Int) {
        repository!!.deleteById(id)
    }

    fun  makeTransfer(transferID: Int, userID: Int): Transfer {
        val transfer = repository!!.findById(transferID).get()
        if (transfer.status != State.ACTIVE || transfer.order.status != State.ACTIVE) { throw InvalidTransacctionTransfer("The transaction can't transfer money") }
        val user = userRepository!!.findById(userID).get()
        transfer.makeTransfer(user)
        orderService!!.save(transfer.order)
        repository.save(transfer)
        return transfer
    }

    fun  confirmTransfer(transferID: Int, userID: Int) {
        val transfer = repository!!.findById(transferID).get()
        if (transfer.status != State.PENDING || transfer.order.status != State.PENDING) { throw InvalidTransacctionTransfer("The transaction can't be confirm") }
        val user = userRepository!!.findById(userID).get()
        transfer.confirmReception(user)
        orderService!!.save(transfer.order)
        repository.save(transfer)
    }

    fun  cancelTransfer(transferID: Int, userID: Int) {
        val transfer = repository!!.findById(transferID).get()
        if (transfer.status != State.PENDING || transfer.status != State.ACTIVE || transfer.order.status != State.PENDING || transfer.order.status != State.ACTIVE) { throw InvalidTransacctionTransfer("The transaction can't be cancel") }
        val user = userRepository!!.findById(userID).get()
        transfer.cancel(user)
    }

    private fun formateDate(date: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.parse(date)

    }


 }