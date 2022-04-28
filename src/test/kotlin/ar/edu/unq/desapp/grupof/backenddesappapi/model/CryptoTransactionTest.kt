package ar.edu.unq.desapp.grupof.backenddesappapi.model

import org.junit.jupiter.api.Test

class CryptoTransactionTest {
    @Test
    fun basicCryptoTransaction() {
        val cryptoTransaction = CryptoTransaction("BNBUSDT",100.0, 120000.0, "Aldana Castro")
        assert(cryptoTransaction.cryptoactive.equals("BNBUSDT"))
        assert(cryptoTransaction.amount == 100.0)
        assert(cryptoTransaction.quote == 120000.0)
        assert(cryptoTransaction.user.equals("Aldana Castro") )
    }

}