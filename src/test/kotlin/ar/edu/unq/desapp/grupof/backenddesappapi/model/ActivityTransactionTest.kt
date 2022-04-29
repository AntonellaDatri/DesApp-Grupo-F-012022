package ar.edu.unq.desapp.grupof.backenddesappapi.model

import org.junit.jupiter.api.Test

class ActivityTransactionTest {
    @Test
    fun basicActivityTransaction() {
        val activityTransaction = ActivityTransaction( "BNBUSDT",100.0, 120000.0, "Aldana Castro","12:30", 12000,5)
        assert(activityTransaction.hour.equals("12:30"))
        assert(activityTransaction.amountOperation == 12000)
        assert(activityTransaction.reputation == 5)
    }
}