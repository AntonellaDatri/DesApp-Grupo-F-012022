package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.IntentionToOperate
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.IntentionToOperateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IntentionToOperateService {
    @Autowired
    private val repository: IntentionToOperateRepository? = null

    @Transactional
    fun create(cryptoQuote: IntentionToOperate) : IntentionToOperate {
        repository!!.save(cryptoQuote)
        return cryptoQuote
    }

    fun findByID(id: Int): IntentionToOperate {
        return repository!!.findById(id).get()
    }

    @Transactional
    fun findAll(): List<IntentionToOperate?> {
        return repository!!.findAll()
    }

    fun clear() {
        repository!!.deleteAll()
    }
}