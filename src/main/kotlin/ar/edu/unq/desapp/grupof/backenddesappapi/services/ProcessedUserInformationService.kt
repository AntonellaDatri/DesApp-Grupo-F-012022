package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Transfer
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.ProcessedUserInformationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProcessedUserInformationService {
    @Autowired
    private val repository: ProcessedUserInformationRepository? = null

    @Transactional
    fun create(processedUserInformation: Transfer)  {
        repository!!.save(processedUserInformation)
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
}