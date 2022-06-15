package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.CryptoAssetQuoteRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.springframework.stereotype.Service
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import java.io.IOException

@Service
class USDQuoteService {
    private fun createRetroFit() : Retrofit {
        val token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODYzNTU3NTYsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJhbGRpaWkuMTIzMzVAZ21haWwuY29tIn0.T0NLDQ9rYglQOpAiVyGDj4uo_swvVeskW1Z7H_anKEUMwC9DmzJorPhIdxbzJPXkTrvdnCO4qG48hAYncyb_kQ"
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request = chain.request()
                    val newRequest: Request.Builder = request.newBuilder().header("Authorization", "BEARER $token")
                    return chain.proceed(newRequest.build())
                }
            })
        val url = "https://api.estadisticasbcra.com/"
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun findUsdQuote(): String {

        val retrofit = createRetroFit()
        val response =
            retrofit.create(CryptoAssetQuoteRepository::class.java).find("usd_of").execute()
        val code = response.code()

        if (code == 400) throw IllegalArgumentException("No existe")
        if (code != 200) throw IllegalArgumentException("Server error")
        return response.body()!!.last().price
    }
}