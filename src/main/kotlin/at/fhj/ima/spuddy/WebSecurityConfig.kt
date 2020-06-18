package at.fhj.ima.spuddy

import at.fhj.ima.spuddy.security.MyAuthenticationSuccessHandler
import at.fhj.ima.spuddy.security.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var myUserDetailsService: MyUserDetailsService
    // customized LoginSuccessHandler übernimmt Trennung zwischen User und Admin Seite
    // das heißt nach erfolgreichem Login werden Admins auf /admin umgeleitet
    @Autowired
    lateinit var myLoginSuccessHandler: MyAuthenticationSuccessHandler


    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            // you anonymous urls here
            .antMatchers("/signup").permitAll()
            .antMatchers("/test").permitAll()
            .antMatchers("/addUser").permitAll()
            //.antMatchers("/anonymous3").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().successHandler(myLoginSuccessHandler)
            .and()
            .rememberMe().key("uniqueAndSecret").userDetailsService(myUserDetailsService)
    }
}
