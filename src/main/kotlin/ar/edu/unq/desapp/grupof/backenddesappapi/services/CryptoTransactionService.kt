package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoTransaction
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.CryptoTransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CryptoTransactionService {
    @Autowired
    private val repository: CryptoTransactionRepository? = null

    @Transactional
    fun create(cryptoQuote: CryptoTransaction)  {
        repository!!.save(cryptoQuote)
    }

    fun findByID(id: Int): CryptoTransaction {
        return repository!!.findById(id).get()
    }

    @Transactional
    fun findAll(): List<CryptoTransaction?> {
        return repository!!.findAll()
    }

    fun deleteById(id: Int) {
        repository!!.deleteById(id)
    }
}