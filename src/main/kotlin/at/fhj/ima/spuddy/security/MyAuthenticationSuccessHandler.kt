package at.fhj.ima.spuddy.security

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class MyAuthenticationSuccessHandler: AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(request: HttpServletRequest,
                                         response: HttpServletResponse, authentication: Authentication){

        response.setStatus(HttpServletResponse.SC_OK)
        var admin = false

        for (auth in authentication.getAuthorities())
            if ("ROLE_ADMIN".equals(auth.getAuthority())){
                admin = true
                break
            }

        if(admin)
            response.sendRedirect("/admin")
        else
            response.sendRedirect("/home")
    }
}