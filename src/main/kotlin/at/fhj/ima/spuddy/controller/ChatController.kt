package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.dto.MsgDto
import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.service.MessageService
import at.fhj.ima.spuddy.service.UserService
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@Controller
class ChatController(val userService: UserService,
                     val messageService: MessageService) {


    @RequestMapping("/chatOverview", method = [RequestMethod.GET])
    fun chatOverview(model: Model): String {
        return "chatOverview"
    }


    @RequestMapping("/chat", method = [RequestMethod.POST])
    fun openchat(@ModelAttribute("sender") @Valid sender: UserDto,
                 @RequestParam("receiver", required = true) receiver: UserDto, model: Model): String {
        model.set("sender",messageService.findMessagesOfSender(sender!!, receiver!!))
        return "chat"
    }



}