package ar.edu.unq.desapp.grupof.backenddesappapi.services

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoAssetQuote
import ar.edu.unq.desapp.grupof.backenddesappapi.repositories.CryptoAssetRedisRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CacheService{
    @Autowired
    private var repositoryCache: CryptoAssetRedisRepository? = null
    @Autowired
    private val cryptoService: CryptoAssetQuoteService? = null
    fun findById(cryptoName : String): CryptoAssetQuote? {
        return repositoryCache!!.findById(cryptoName)
    }

    fun getAllCache(): Collection<CryptoAssetQuote> {
        return repositoryCache!!.findAll().values
    }

    fun getAllCryptoCache(cryptoName : String): Collection<CryptoAssetQuote> {
        return repositoryCache!!.findAllCrypto(cryptoName).values
    }

    fun updateTenMinutes(){
        var results: List<CryptoAssetQuote> = cryptoService!!.getTenCryptoAssets(LocalDateTime.now())
        results.forEach{
            repositoryCache!!.update(it)
        }
    }
    fun saveCrypto(cryptoName: String) {
        repositoryCache!!.updateCrypto(cryptoService!!.findByCryptoName(cryptoName, LocalDateTime.now()))
    }

    fun updateFiveMinutes() {
        var results: List<CryptoAssetQuote> = cryptoService!!.getTenCryptoAssets(LocalDateTime.now())
        results.forEach{
            repositoryCache!!.updateCrypto(it)
        }
    }
}