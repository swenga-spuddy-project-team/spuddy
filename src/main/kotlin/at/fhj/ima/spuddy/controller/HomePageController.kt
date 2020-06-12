package at.fhj.ima.spuddy.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class HomePageController {

    @RequestMapping("", method = [RequestMethod.GET])
    fun homePage(model: Model): String {
        return "redirect:home"
    }
}