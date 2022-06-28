package ar.edu.unq.desapp.grupof.backenddesappapi

import ar.edu.unq.desapp.grupof.backenddesappapi.model.CryptoAssetQuote
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.stereotype.Component
import redis.clients.jedis.Jedis
import java.net.URI


@Component
class RedisConfig {

    @Value("\${redis.port}")
    var port = 6379

    final var jedisConnectionFactory: JedisConnectionFactory? = null

    init {
        this.jedisConnectionFactory = getNewJedisConnectionFactory()
    }

    @Bean
    @Autowired
    fun redisTemplate(): RedisTemplate<String, CryptoAssetQuote>? {
        val redisTemplate: RedisTemplate<String, CryptoAssetQuote> = RedisTemplate<String, CryptoAssetQuote>()
        redisTemplate.setConnectionFactory(jedisConnectionFactory!!)
        redisTemplate.isExposeConnection = true
      //  redisTemplate.setDefaultSerializer(StringRedisSerializer())
        redisTemplate.setKeySerializer(StringRedisSerializer())
        redisTemplate.setHashKeySerializer(StringRedisSerializer())
        redisTemplate.setValueSerializer(GenericJackson2JsonRedisSerializer())
        redisTemplate.setEnableTransactionSupport(true)
        jedisConnectionFactory!!.afterPropertiesSet()
        return redisTemplate
    }

    private final fun getNewJedisConnectionFactory(): JedisConnectionFactory {

        val host = "localhost"
        val redisHost = System.getenv("REDIS_URL")
        val redisStandaloneConfiguration = if(redisHost != null ){
            RedisStandaloneConfiguration(URI.create(redisHost).host)
        }else{
            RedisStandaloneConfiguration(host, port)
        }
        val factory = JedisConnectionFactory(redisStandaloneConfiguration)
        return factory
    }

    @Bean
    fun getJedisClientPublisher(): Jedis {
        return Jedis()
    }

}