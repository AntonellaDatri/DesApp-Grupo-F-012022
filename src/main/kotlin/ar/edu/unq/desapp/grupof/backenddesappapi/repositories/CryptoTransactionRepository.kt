package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.IntentionToOperate
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Configuration
@Repository
interface CryptoTransactionRepository: CrudRepository<IntentionToOperate?, Int?> {
    fun findById(id: Int?): IntentionToOperate?
    override fun findAll(): List<IntentionToOperate?>
    override fun deleteById(id: Int)
}