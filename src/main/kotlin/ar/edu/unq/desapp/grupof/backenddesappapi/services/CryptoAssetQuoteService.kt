package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoAssetQuote
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.CryptoAssetQuoteRepository
import org.springframework.stereotype.Service
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit

@Service
class CryptoAssetQuoteService {

    fun findByCryptoName(cryptoName : String) : CryptoAssetQuote? {
        val url = "https://api1.binance.com/api/v3/ticker/"
        val quote = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val response = quote.create(CryptoAssetQuoteRepository::class.java).findByCryptoName("price?symbol=$cryptoName").execute()
        return response.body()
    }
}

