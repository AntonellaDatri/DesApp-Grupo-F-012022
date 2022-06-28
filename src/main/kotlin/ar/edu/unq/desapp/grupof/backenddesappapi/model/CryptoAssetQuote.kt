package ar.edu.unq.desapp.grupof.backenddesappapi.model

import org.springframework.data.redis.core.RedisHash
import java.io.Serializable
import java.time.LocalDateTime

@RedisHash(value = "CryptoAssetQuote", timeToLive = 1000000)
data class CryptoAssetQuote(var price: String, var symbol: String, var dateTime: LocalDateTime) : Serializable
