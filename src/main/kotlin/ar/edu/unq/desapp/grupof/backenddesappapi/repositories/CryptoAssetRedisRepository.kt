package ar.edu.unq.desapp.grupof.backenddesappapi.repositories

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoAssetQuote
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Configuration
@Repository
class CryptoAssetRedisRepository(@Qualifier("redisTemplate") @Autowired private var redisTemplate: RedisTemplate<String, CryptoAssetQuote>?) {

        //HashOperations: access Redis cache
        private var hashOperations: HashOperations<String, String, CryptoAssetQuote>? = null

        init {
            hashOperations = this.redisTemplate!!.opsForHash()
        }

        fun findAll(): Map<String, CryptoAssetQuote> {
            return hashOperations!!.entries("CRYPTO")
        }

        fun save(crypto: CryptoAssetQuote){
            hashOperations!!.put("CRYPTO", crypto.symbol, crypto)
        }

        fun saveCrypto(crypto: CryptoAssetQuote){
            hashOperations!!.put(crypto.symbol, crypto.dateTime.toString(), crypto)
        }

        fun findAllCrypto(cryptoName: String): Map<String, CryptoAssetQuote> {
            return hashOperations!!.entries(cryptoName)
        }

        fun update(crypto: CryptoAssetQuote){
            save(crypto)
        }

        fun updateCrypto(crypto: CryptoAssetQuote){
            saveCrypto(crypto)
        }

        fun findById(cryptoName: String): CryptoAssetQuote? {
            return hashOperations!!.get("CRYPTO", cryptoName)
        }

        fun delete(cryptoName: String){
            hashOperations!!.delete("CRYPTO", cryptoName)
        }
}