package ar.edu.unq.desapp.grupof.backenddesappapi.model

import javax.persistence.*

@Entity
@Table(name= "transaction")
class ProcessedUserInformation : CryptoTransaction() {
    fun makeTransfer(){
        //Not code yet
    }

    fun confirmReception(){
        //Not code yet
    }

    fun cancel(){
        //Not code yet
    }
}