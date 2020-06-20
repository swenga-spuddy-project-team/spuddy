package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.repository.UserRepository
import at.fhj.ima.spuddy.service.DistrictService
import at.fhj.ima.spuddy.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDate

@Controller
class HomePageController(
    val userService: UserService,
    val districtService: DistrictService
) {





    @RequestMapping("/home", method = [RequestMethod.GET])
    fun home(model: Model, @RequestParam(required = true) username: String): String {
            val userdto = UserDto(username = "", password = "", passwordrepeat = "");
            model.set("userdto", userdto)
            model.set("districtNames", districtService.findAll().map { it.districtName })
            return "home"

        }
    }
