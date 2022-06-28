 package ar.edu.unq.desapp.grupof.backenddesappapi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


 @EnableWebSecurity
@Configuration
class SecurityCFG : WebSecurityConfigurerAdapter() {

     @Bean
     fun passwordEncoder(): PasswordEncoder {
         return BCryptPasswordEncoder()
     }
    @Throws(java.lang.Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .addFilterAfter(JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .authorizeRequests().antMatchers("/swagger-ui/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/cryptoQuote/firstTen","/api/cryptoQuote").permitAll()
            .antMatchers(HttpMethod.POST, "/api/user/register").permitAll()
            .anyRequest()
            .authenticated().and().cors()//.and().httpBasic()
              }

     @Throws(Exception::class)
     override fun configure(web: WebSecurity) {
         web.ignoring().mvcMatchers(HttpMethod.OPTIONS, "/**")
         web.ignoring().mvcMatchers(
             "/swagger-ui.html/**",
             "/configuration/**",
             "/swagger-resources/**",
             "/v2/api-docs",
             "/webjars/**",
             "/h2-console/**"
         )
     }
}