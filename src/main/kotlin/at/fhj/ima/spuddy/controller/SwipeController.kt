package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.service.DistrictService
import at.fhj.ima.spuddy.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam


@Controller
class SwipeController (val userService: UserService,
                       val districtService: DistrictService
){

    @RequestMapping("/swipe", method = [RequestMethod.GET])
    fun swipe(model: Model): String {
        return "swipe"
    }

    @RequestMapping("/swipeAction", method = [RequestMethod.POST])
    fun swipeAction(){}
}

//