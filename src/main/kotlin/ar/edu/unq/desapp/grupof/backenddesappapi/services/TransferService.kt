package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.ActivityTransaction
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Transfer
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.ActivityTransactionRepository
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransferService {
    @Autowired
    private val repository: ActivityTransactionRepository? = null
    @Autowired
    private val userRepository: UserRepository? = null
    @Transactional
    fun createTransfer(transfer: Transfer)  {
        repository!!.save(transfer)
    }

    fun findByID(id: Int): Transfer {
        return repository!!.findById(id).get()
    }

    @Transactional
    fun findAll(): List<Transfer?> {
        return repository!!.findAll()
    }

    fun deleteById(id: Int) {
        repository!!.deleteById(id)
    }

    fun  makeTransfer(transferID: Int, userID: Int) {
        val transfer = repository!!.findById(transferID).get()
        if (transfer.status != Status.ACTIVE or transfer.order.status != Status.ACTIVE) { throw Exception }
        val user = userRepository!!.findById(userID).get()
        transfer.makeTransfer(user)
    }

    fun  confirmTransfer(transferID: Int, userID: Int) {
        val transfer = repository!!.findById(transferID).get()
        if (transfer.status != Status.PENDING or transfer.order.status != Status.PENDING) { throw Exception }
        val user = userRepository!!.findById(userID).get()
        transfer.confirmReception(user)
    }

    fun  cancelTransfer(transferID: Int, userID: Int) {
        val transfer = repository!!.findById(transferID).get()
        if (transfer.status != Status.PENDING or transfer.status != Status.ACTIVE or transfer.order.status != Status.PENDING or transfer.order.status != Status.ACTIVE) { throw Exception }
        val user = userRepository!!.findById(userID).get()
        transfer.cancel(user)
    }
 }