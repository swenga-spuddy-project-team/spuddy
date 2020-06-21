package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.service.MessageService
import at.fhj.ima.spuddy.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid


@Controller
class ChatController(val userService: UserService,
                     val messageService: MessageService
                      ) {


    @RequestMapping("/chatOverview", method = [RequestMethod.GET])
    fun chatOverview(model: Model): String {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val name: String = auth.getName() //get logged in username


        model.addAttribute("username", name)
        model.set("matchesList", messageService.getMatchesList(userService.findByUsername(name)!!))
        return "chatOverview"
    }


    @RequestMapping("/chat", method = [RequestMethod.POST])
    fun openchat(@ModelAttribute("sender") @Valid sender: UserDto,
                 @RequestParam("receiver", required = true) receiver: UserDto, model: Model): String {
        model.set("sender",messageService.findMessagesOfSender(sender!!, receiver!!))
        return "chat"
    }



}