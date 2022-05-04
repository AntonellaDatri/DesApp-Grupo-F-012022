package ar.edu.unq.desapp.grupof.backenddesappapi.model

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class VolumeTradedBetweenCryptosTest {
    @Test
    fun basicTraded() {
        val date= LocalDateTime.now()
        val traded = VolumeTradedBetweenCryptos("Aldana", date, 10, 2000, "BNB", 5, 2, 400)
        assert(traded.user!! == "Aldana")
        assert(traded.dateTime!!.hour == date.hour)
        assert(traded.amountInUSD!! == 10)
        assert(traded.amountInARG!! == 2000)
        assert(traded.cryptoActive.equals("BNB"))
        assert(traded.nominal!! == 5)
        assert(traded.actualQuote == 2)
        assert(traded.amountQuoteInARG == 400)
    }
}