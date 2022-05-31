package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.IntentionToOperate
import ar.edu.unq.desapp.grupof.backenddesappapi.model.State
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Configuration
@Repository
interface IntentionToOperateRepository: CrudRepository<IntentionToOperate?, Int?> {
    fun findById(id: Int?): IntentionToOperate?
    override fun findAll(): List<IntentionToOperate?>
    override fun deleteById(id: Int)
    fun findByState(state: State): List<IntentionToOperate?>
}