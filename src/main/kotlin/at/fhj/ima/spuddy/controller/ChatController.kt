package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.dto.MsgDto
import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.service.MessageService
import at.fhj.ima.spuddy.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

@Controller
class ChatController(val userService: UserService,
                     val messageService: MessageService) {


    @RequestMapping("/chatOverview", method = [RequestMethod.GET])
    fun chatOverview(model: Model): String {
        return "chatOverview"
    }


    @RequestMapping("/chat", method = [RequestMethod.GET])
    fun homePage(model: Model): String {
        return "chat"
    }

}