package ar.edu.unq.desapp.grupof.backenddesappapi.dto

import com.google.gson.annotations.SerializedName

data class CryptoAssetQuoteDTO (@SerializedName("symbol") var symbol:String, @SerializedName("price") var price: String)
