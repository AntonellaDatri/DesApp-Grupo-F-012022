package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.dto.*
import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidTransactionTransfer
import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidUserTransfer
import ar.edu.unq.desapp.grupof.backenddesappapi.model.*
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.State
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.TransferRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


@Service
class TransferService {
    @Autowired
    private val repository: TransferRepository? = null
    @Autowired
    private val orderService: OrderService? = null
    @Autowired
    private val cryptoAssetQuoteService: CryptoAssetQuoteService? = null
    @Autowired
    private val usdQuoteService: USDQuoteService? = null
    @Autowired
    private val userService : UserService? = null

    @Transactional
    fun createTransfer(transferRequestDTO: TransferRequestDTO) : TransferResponseDTO {
        val order = orderService!!.findOrderByID(transferRequestDTO.orderID)
        val cryptoPrice =  cryptoAssetQuoteService!!.findByCryptoName(order.cryptoName!! ,LocalDateTime.now()).price.toDouble()
        val executingUser = findUser(transferRequestDTO.userID)
        if (executingUser.id == order.user.id) {throw InvalidUserTransfer("No se puede crear una transferencia con el usuario que creo la orden")}
        val transfer = save(Transfer(order, transferRequestDTO.amountToTransfer, executingUser,cryptoPrice))
        return TransferResponseDTO.fromModel(transfer)
    }

    @Transactional
    fun makeTransfer(transferID: Int, userID: Int) {
        val transfer = findTransfer(transferID)
        validate(transfer, State.ACTIVE,"No se puede transferir  la plata")
        transfer.makeTransfer(findUser(userID))
        saveAll(transfer)
    }

    @Transactional
    fun  confirmTransfer(transferID: Int, userID: Int) {
        val transfer = findTransfer(transferID)
        validate(transfer, State.PENDING,"No se puede confirmar")
        transfer.confirmReception(findUser(userID))
        saveAll(transfer)
    }

    @Transactional
    fun cancelTransfer(transferID: Int, userID: Int) {
        val transfer = findTransfer(transferID)
        if ((transfer.state != State.PENDING && transfer.state != State.ACTIVE) || (transfer.order.state != State.PENDING && transfer.order.state != State.ACTIVE)) { throw InvalidTransactionTransfer("No se puede cancelar") }
        transfer.cancel(findUser(userID))
        saveAll(transfer)
    }

    @Transactional
    fun getVolumeOperation(userId : Int, date1: String, date2: String): TransferVolumeDTO{
        val list = findBetween(userId, date1, date2)
        val user = userService!!.findByID(userId)
        val usdQuote = usdQuoteService!!.findUsdQuote()
        return TransferVolumeDTO(user, list, usdQuote.toDouble())
    }

    @Transactional
    fun findByID(id: Int): TransferResponseDTO {
        try {
            return TransferResponseDTO.fromModel(repository!!.findById(id).get())
        } catch (e:Exception) {
            throw Exception("No existe una transferencia con ese id")
        }
    }

    @Transactional
    fun findAll(): List<TransferResponseDTO?> {
        return repository!!.findAll().map {
            TransferResponseDTO.fromModel(it!!)
        }
    }

    @Transactional
    fun deleteByID(id: Int) {
        repository!!.deleteById(id)
    }

    @Transactional
    fun deleteAll() {
        repository!!.deleteAll()
    }

    private fun findBetween(userId : Int, dateString1: String, dateString2: String): List<TransferActivesDTO> {
        val date1 = formatDate(dateString1)
        val date2 = formatDate(dateString2)
        val transfers = repository!!.findBetween(date1, date2, userId)
        return transfers.map{ TransferActivesDTO.fromModel(it!!) }
    }

    private fun formatDate(date: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.parse(date)
    }

    private fun save(transfer: Transfer): Transfer {
        repository!!.save(transfer)
        return transfer
    }

    private fun saveAll(transfer: Transfer) {
        orderService!!.save(transfer.order)
        save(transfer)
    }

    private fun findTransfer(transferID: Int) : Transfer{
        return repository!!.findById(transferID).get()
    }

    private fun findUser(userID: Int) : User{
        return userService!!.findUserByID(userID)
    }

    private fun  validate(transfer: Transfer, state : State, msgError: String) {
        if (transfer.state != state || transfer.order.state != state) { throw InvalidTransactionTransfer(msgError) }
    }
 }