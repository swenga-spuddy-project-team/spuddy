package at.fhj.ima.spuddy.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class LoginController {

    @RequestMapping("/login", method = [RequestMethod.GET])
    fun loginPage(model: Model): String {
        return "login"
    }
}