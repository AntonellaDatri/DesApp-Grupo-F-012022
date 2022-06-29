package ar.edu.unq.desapp.grupof.backenddesappapi

import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration.LettuceClientConfigurationBuilder


@Configuration
internal class AppConfig {
    @Bean
    fun lettuceClientConfigurationBuilderCustomizer(): LettuceClientConfigurationBuilderCustomizer {
        return LettuceClientConfigurationBuilderCustomizer { clientConfigurationBuilder: LettuceClientConfigurationBuilder ->
            if (clientConfigurationBuilder.build().isUseSsl) {
                clientConfigurationBuilder.useSsl().disablePeerVerification()
            }
        }
    }
}