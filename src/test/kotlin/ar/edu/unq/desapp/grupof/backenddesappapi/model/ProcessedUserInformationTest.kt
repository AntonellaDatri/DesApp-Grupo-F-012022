package ar.edu.unq.desapp.grupof.backenddesappapi.model

import org.junit.jupiter.api.Test

class ProcessedUserInformationTest {
    @Test
    fun basicProcessedUserInformation() {
        val activityTransaction = ProcessedUserInformation( "BNBUSDT",100.0, 120000.0, "Aldana Castro", 12345678)
        assert(activityTransaction.cryptoactive.equals("BNBUSDT"))
        assert(activityTransaction.amount == 100.0)
        assert(activityTransaction.quote == 120000.0)
        assert(activityTransaction.user.equals("Aldana Castro"))
        assert(activityTransaction.mailingAddress == 12345678)

    }
}