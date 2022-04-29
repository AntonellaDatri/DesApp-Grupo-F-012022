package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.ProcessedUserInformation
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Configuration
@Repository
interface ProcessedUserInformationRepository: CrudRepository<ProcessedUserInformation?, Int?> {
    fun findById(id: Int?): ProcessedUserInformation?
    override fun findAll(): List<ProcessedUserInformation?>
    override fun deleteById(id: Int)
}
