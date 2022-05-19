package ar.edu.unq.desapp.grupof.backenddesappapi.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityCFG : WebSecurityConfigurerAdapter() {

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        val encoder = encoder()
        auth
            .inMemoryAuthentication()
            .withUser("user")
            .password(encoder!!.encode("admin123"))
            .roles("USER", "ADMIN")
    }

    @Throws(java.lang.Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/swagger-ui/*",
                "/webjars/**",  /*Probably not needed*/
                "/swagger.json",
                "/v2/**"
            )
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
            .and()
            .cors().and()
            .headers().frameOptions().disable();

    }

    @Throws(java.lang.Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/v2/api-docs/**")
        web.ignoring().antMatchers("/swagger.json")
        web.ignoring().antMatchers("/swagger-ui.html")
        web.ignoring().antMatchers("/swagger-resources/**")
        web.ignoring().antMatchers("/webjars/**")
    }
}