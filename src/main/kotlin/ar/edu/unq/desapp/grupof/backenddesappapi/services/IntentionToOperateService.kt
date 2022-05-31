package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.IntentionToOperate
import ar.edu.unq.desapp.grupof.backenddesappapi.model.IntentionToOperateDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.State
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.IntentionToOperateRepository
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IntentionToOperateService {
    @Autowired
    private val repository: IntentionToOperateRepository? = null

    @Autowired
    private val userRepository : UserRepository? = null

    @Transactional
    fun create(cryptoTransactionDTO: IntentionToOperateDTO) : IntentionToOperate {
        val userId = cryptoTransactionDTO.walletAddress
        val user = userRepository!!.findByWalletAddress(userId).orElseThrow{ Exception("User doesnt exist") }
        val cryptoTransaction = IntentionToOperate(cryptoTransactionDTO.cryptoActive, cryptoTransactionDTO.amount, user!!, cryptoTransactionDTO.operation)
        repository!!.save(cryptoTransaction)

        return cryptoTransaction
    }

    fun findByID(id: Int): IntentionToOperate {
        return repository!!.findById(id).get()
    }

    @Transactional
    fun findAll(): List<IntentionToOperate?> {
        return repository!!.findAll()
    }

    fun deleteById(id: Int) {
        repository!!.deleteById(id)
    }

    fun getActiveTransaction(): List<IntentionToOperate?> {
        return repository!!.findByState(State.ACTIVE)
    }
}