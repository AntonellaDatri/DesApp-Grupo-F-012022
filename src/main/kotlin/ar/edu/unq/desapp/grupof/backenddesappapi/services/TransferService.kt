package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidTransacctionTransfer
import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoAssetQuote
import ar.edu.unq.desapp.grupof.backenddesappapi.model.State
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Transfer
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.ActivityTransactionRepository
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.CryptoAssetQuoteRepository
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.UserRepository
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import java.time.LocalDateTime
import java.util.*


@Service
class TransferService {
    @Autowired
    private val repository: ActivityTransactionRepository? = null
    @Autowired
    private val userRepository: UserRepository? = null
    @Transactional
    fun createTransfer(transfer: Transfer) : Transfer {
        repository!!.save(transfer)
        return transfer
    }

    fun findByID(id: Int): Transfer {
        return repository!!.findById(id).get()
    }

    @Transactional
    fun findAll(): List<Transfer?> {
        return repository!!.findAll()
    }

    @Transactional
    fun findBetween(userId : Int, date1: Date, date2: Date): List<Transfer?> {
        //TODO transaction
        return repository!!.findAll().filter { ((it!!.executingUser.id == userId) || (it.order.user.id == userId)) && (it.dateTime >= date1) && (it.dateTime <= date2) }
    }

    fun deleteById(id: Int) {
        repository!!.deleteById(id)
    }

    fun  makeTransfer(transferID: Int, userID: Int) {
        val transfer = repository!!.findById(transferID).get()
        if (transfer.status != State.ACTIVE || transfer.order.status != State.ACTIVE) { throw InvalidTransacctionTransfer("The transaction can't transfer money") }
        val user = userRepository!!.findById(userID).get()
        transfer.makeTransfer(user)
    }

    fun  confirmTransfer(transferID: Int, userID: Int) {
        val transfer = repository!!.findById(transferID).get()
        if (transfer.status != State.PENDING || transfer.order.status != State.PENDING) { throw InvalidTransacctionTransfer("The transaction can't be confirm") }
        val user = userRepository!!.findById(userID).get()
        transfer.confirmReception(user)
    }

    fun  cancelTransfer(transferID: Int, userID: Int) {
        val transfer = repository!!.findById(transferID).get()
        if (transfer.status != State.PENDING || transfer.status != State.ACTIVE || transfer.order.status != State.PENDING || transfer.order.status != State.ACTIVE) { throw InvalidTransacctionTransfer("The transaction can't be cancel") }
        val user = userRepository!!.findById(userID).get()
        transfer.cancel(user)
    }


 }