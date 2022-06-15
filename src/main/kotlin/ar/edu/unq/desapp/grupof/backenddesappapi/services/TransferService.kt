package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.dto.*
import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidTransacctionTransfer
import ar.edu.unq.desapp.grupof.backenddesappapi.model.*
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.ActivityTransactionRepository
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.OrderRepository
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
    private val usdQuoteService: USDQuoteService? = null

    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val userService : UserService? = null
    @Autowired
    private val orderRepository : OrderRepository? = null

    @Transactional
    fun createTransfer(transferRequestDTO: TransferRequestDTO) : TransferDTO {
        val order = orderRepository!!.findById(transferRequestDTO.orderID).get()
        val user = userRepository!!.findById(transferRequestDTO.userID).get()
        val cryptoPrice =  cryptoAssetQuoteService!!.findByCryptoName(order.cryptoName!! ,LocalDateTime.now()).price.toDouble()
        val transfer = save(Transfer(order, transferRequestDTO.amountToTransfer, user,cryptoPrice))
        return TransferDTO.fromModel(transfer)
    }

    @Transactional
    fun  makeTransfer(transferID: Int, userID: Int): Transfer {
        val transfer = repository!!.findById(transferID).get()
        if (transfer.state != State.ACTIVE || transfer.order.state != State.ACTIVE) { throw InvalidTransacctionTransfer("The transaction can't transfer money") }
        val user = userRepository!!.findById(userID).get()
        transfer.makeTransfer(user)
        orderService!!.save(transfer.order)
        repository.save(transfer)
        return transfer
    }

    @Transactional
    fun  confirmTransfer(transferID: Int, userID: Int): Transfer {
        val transfer = repository!!.findById(transferID).get()
        if (transfer.state != State.PENDING || transfer.order.state != State.PENDING) { throw InvalidTransacctionTransfer("The transaction can't be confirm") }
        val user = userRepository!!.findById(userID).get()
        transfer.confirmReception(user)
        orderService!!.save(transfer.order)
        repository.save(transfer)
        return transfer
    }

    @Transactional
    fun cancelTransfer(transferID: Int, userID: Int): Transfer {
        val transfer = repository!!.findById(transferID).get()
        if (transfer.state != State.PENDING && transfer.state != State.ACTIVE && transfer.order.state != State.PENDING && transfer.order.state != State.ACTIVE) { throw InvalidTransacctionTransfer("The transaction can't be cancel") }
        val user = userRepository!!.findById(userID).get()
        transfer.cancel(user)
        orderService!!.save(transfer.order)
        repository.save(transfer)
        return transfer
    }

    @Transactional
    fun findBetween(userId : Int, dateString1: String, dateString2: String): List<TransferActivesDTO> {
        val date1 = formatDate(dateString1)
        val date2 = formatDate(dateString2)
        val transfers = repository!!.findBetween(date1, date2, userId)

        return transfers.map{ TransferActivesDTO.fromModel(it!!) }
    }

    @Transactional
    fun findByID(id: Int): TransferDTO {
        return TransferDTO.fromModel(repository!!.findById(id).get())
    }

    @Transactional
    fun findAll(): List<TransferDTO?> {
        val transfers = repository!!.findAll()
        return transfers.map {
            TransferDTO.fromModel(it!!)
        }
    }

    @Transactional
    fun deleteById(id: Int) {
        repository!!.deleteById(id)
    }

    private fun formatDate(date: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.parse(date)
    }

    fun getVolumeOperation(userId : Int, date1: String, date2: String): VolumeOperationsDTO{
        val list = findBetween(userId, date1, date2)
        val user = userService!!.findByID(userId)
        val usdQuote = usdQuoteService!!.findUsdQuote()
        return VolumeOperationsDTO(user, list, usdQuote.toDouble())
    }

    private fun save(transfer: Transfer): Transfer {
        repository!!.save(transfer)
        return transfer
    }
 }