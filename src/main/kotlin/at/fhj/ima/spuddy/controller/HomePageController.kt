package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.controller.advice.CurrentUserControllerAdvice
import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.repository.UserRepository
import at.fhj.ima.spuddy.service.DistrictService
import at.fhj.ima.spuddy.service.UserService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDate
import javax.validation.Valid

@Controller
class HomePageController(val userService: UserService,
                         val districtService: DistrictService

) {


    @RequestMapping("", method = [RequestMethod.GET])
    fun homePage(model: Model): String {
        return "redirect:home"


    }


    @RequestMapping("/home", method = [RequestMethod.GET])
    fun home(model: Model): String {

        model.set("districtNames", districtService.findAll().map { it.districtName })
        return "home"

    }



    @RequestMapping("/updateUser", method = [RequestMethod.POST])
    fun updateUser(
        @ModelAttribute("employee") @Valid userdto: UserDto, bindingResult: BindingResult,
        model: Model
    ): String {
        if (bindingResult.hasErrors()) {
            return "/home"        }
        try {
            val employee = userService.findByUsername("admin");
            model.set("userdto", userdto)
        } catch (dive: DataIntegrityViolationException) {
            if (1 != 1){
                return "/home"
            } else {
                throw dive;
            }
        }
        return "redirect:home"
    }
    }

// Todo: Redirect auf signup success Seite um User anzuzeigen das er sich erfolgreich angemeldet hat


