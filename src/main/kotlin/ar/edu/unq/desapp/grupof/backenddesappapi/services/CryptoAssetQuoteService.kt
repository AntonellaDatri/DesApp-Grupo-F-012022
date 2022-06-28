package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoAssetQuote
import ar.edu.unq.desapp.grupof.backenddesappapi.model.enumeration.CryptoAsset
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.CryptoAssetQuoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import java.time.LocalDateTime

@Service
class CryptoAssetQuoteService {

    @Transactional
    fun findByCryptoName(cryptoName: String, dateTime: LocalDateTime): CryptoAssetQuote {
        val retrofit = createRetroFit()
        val response =
            retrofit.create(CryptoAssetQuoteRepository::class.java).findByCryptoName("price?symbol=${cryptoName.uppercase()}").execute()
        val code = response.code()
        if (code == 400) throw IllegalArgumentException("No existe: $cryptoName")
        if (code != 200) throw IllegalArgumentException("Server error")
        val price = response.body()!!.price
        val symbol = response.body()!!.symbol
        return CryptoAssetQuote(price, symbol, dateTime)
    }

    @Transactional
    fun getTenCryptoAssets(dateTime: LocalDateTime): List<CryptoAssetQuote> {
        return CryptoAsset.values().map {
             findByCryptoName(it.name, dateTime)
        }
    }

    private fun createRetroFit() : Retrofit{
        val url = "https://api1.binance.com/api/v3/ticker/"
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

