package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.ActivityTransaction
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.ActivityTransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ActivityTransactionService {
    @Autowired
    private val repository: ActivityTransactionRepository? = null

    @Transactional
    fun create(activityTransaction: ActivityTransaction)  {
        repository!!.save(activityTransaction)
    }

    fun findByID(id: Int): ActivityTransaction {
        return repository!!.findById(id).get()
    }

    @Transactional
    fun findAll(): List<ActivityTransaction?> {
        return repository!!.findAll()
    }

    fun deleteById(id: Int) {
        repository!!.deleteById(id)
    }
}