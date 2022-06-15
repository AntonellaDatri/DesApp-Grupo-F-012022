package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.Transfer
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*


@Configuration
@Repository
interface TransferRepository: CrudRepository<Transfer?, Int?> {
    fun findById(id: Int?): Transfer?

    @Query(value = "SELECT t FROM Transfer t WHERE t.executingUser.id = :userId AND t.dateTime BETWEEN :startDate AND :endDate")
    fun findBetween(
        @Param("startDate") start: Date,
        @Param("endDate") end: Date,
        @Param("userId") userId: Int
    ): List<Transfer?>
    override fun findAll(): List<Transfer?>
    override fun deleteById(id: Int)
    override fun deleteAll()
}
