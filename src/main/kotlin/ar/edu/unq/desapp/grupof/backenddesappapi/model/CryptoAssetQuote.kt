package ar.edu.unq.desapp.grupof.backenddesappapi.model

import java.io.Serializable
import java.time.LocalDateTime

data class CryptoAssetQuote(var price: String, var symbol: String, var dateTime: LocalDateTime) : Serializable
