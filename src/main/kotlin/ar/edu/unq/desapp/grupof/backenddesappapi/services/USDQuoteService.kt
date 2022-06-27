package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.CryptoAssetQuoteRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit

@Service
class USDQuoteService {
    @Transactional
    fun findUsdQuote(): String {
        val retrofit = createRetroFit()
        val response =
            retrofit.create(CryptoAssetQuoteRepository::class.java).find("usd_of").execute()
        val code = response.code()

        if (code == 400) throw IllegalArgumentException("No existe")
        if (code != 200) throw IllegalArgumentException("Server error")
        return response.body()!!.last().price
    }

    private fun createRetroFit() : Retrofit {
        val token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODYzNTU3NTYsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJhbGRpaWkuMTIzMzVAZ21haWwuY29tIn0.T0NLDQ9rYglQOpAiVyGDj4uo_swvVeskW1Z7H_anKEUMwC9DmzJorPhIdxbzJPXkTrvdnCO4qG48hAYncyb_kQ"
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder
            .addInterceptor { chain ->
                val request: Request = chain.request()
                val newRequest: Request.Builder = request.newBuilder().header("Authorization", "BEARER $token")
                chain.proceed(newRequest.build())
            }
        val url = "https://api.estadisticasbcra.com/"
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}