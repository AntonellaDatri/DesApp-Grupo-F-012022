package ar.edu.unq.desapp.grupof.backenddesappapi.model

import com.google.gson.annotations.SerializedName

data class CryptoAssetQuote (@SerializedName("symbol") var symbol:String, @SerializedName("price") var price: String)