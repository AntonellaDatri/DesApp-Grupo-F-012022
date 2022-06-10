package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.CryptoAssetQuoteRepository
import okhttp3.OkHttpClient
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import java.time.LocalDateTime
import java.util.Date

class VolumeOperationsDTO {
    lateinit var user : User
    lateinit var dateTime: Date
    private var totalAmountARS : Double = 0.0
    var totalAmountUSD : Double = 0.0
    lateinit var listActives: List<TransferActivesDTO>
    constructor() : super()
    constructor(
        user: User, listActives : List<TransferActivesDTO>
    ) : super() {
        this.user = user
        this.dateTime = Date()
        this.totalAmountARS = listActives.sumOf{ it.arsAmount }
        this.totalAmountUSD = listActives.sumOf { it.arsAmount * findUsdQuote().toDouble()}
        this.listActives = listActives
    }

    fun createRetroFit() : Retrofit {
        val token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODYxNzkyODMsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJhbGRpaWkuMTIzMzVAZ21haWwuY29tIn0.-IMthM4jMcVGaKylL-TX2fhi4vOLhR63x1ZD4wDPlErv1c_-gZCdK23monp_pS4GWBww9mDR_p3uNK3H64cAGQ"
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }.build()
        val url = "https://api.estadisticasbcra.com/usd_of/"
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun findUsdQuote(): String {
        val retrofit = createRetroFit()
        val response =
            retrofit.create(CryptoAssetQuoteRepository::class.java).find().execute()
        val code = response.code()
        print("error" + code)

        if (code == 400) throw IllegalArgumentException("No existe")
        if (code != 200) throw IllegalArgumentException("Server error")
        return response.body()!!.price
    }
}