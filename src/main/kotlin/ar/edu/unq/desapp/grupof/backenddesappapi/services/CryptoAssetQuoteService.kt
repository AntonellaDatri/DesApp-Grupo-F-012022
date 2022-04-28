package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoAssetQuote
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.CryptoAssetQuoteRepository
import org.springframework.stereotype.Service
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import java.time.LocalDateTime

@Service
class CryptoAssetQuoteService {

    fun findByCryptoName(cryptoName: String, dateTime: LocalDateTime): CryptoAssetQuote? {
        val url = "https://api1.binance.com/api/v3/ticker/"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val response =
            retrofit.create(CryptoAssetQuoteRepository::class.java).findByCryptoName("price?symbol=${cryptoName.uppercase()}").execute()
        val code = response.code()

        if (code == 400) throw IllegalArgumentException("No existe esa cripto")
        if (code != 200) throw IllegalArgumentException("Error del server")
        val price = response.body()!!.price
        val symbol = response.body()!!.symbol
        return CryptoAssetQuote(price, symbol, dateTime)
    }
}

