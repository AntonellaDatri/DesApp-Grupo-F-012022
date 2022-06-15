package ar.edu.unq.desapp.grupof.backenddesappapi.dto

import com.google.gson.annotations.SerializedName

data class USDQuoteDTO (@SerializedName("d") var date:String, @SerializedName("v") var price: String)