package ar.edu.unq.desapp.grupof.backenddesappapi.model

import java.util.Date
import javax.persistence.*

@Entity
@Table(name= "transaction")
class ProcesstedUserInformation : CryptoTransaction() {
    fun makeTransfer(){
    }

    fun confirmReception(){

    }

    fun cancel(){

    }
}