package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoAssetQuoteWrapper
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

@Configuration
@Repository
interface CryptoAssetQuoteRepository{
    @GET
    fun findByCryptoName(@Url url:String): Call<CryptoAssetQuoteWrapper>
    @GET(".")
    fun find(): Call<CryptoAssetQuoteWrapper>
}
