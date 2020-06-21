package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.controller.advice.CurrentUserControllerAdvice
import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.helpers.Quadruple
import at.fhj.ima.spuddy.repository.UserRepository
import at.fhj.ima.spuddy.service.DistrictService
import at.fhj.ima.spuddy.service.SportService
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
import javax.validation.ConstraintViolationException
import javax.validation.Valid

@Controller
class HomePageController(val userService: UserService,
                         val districtService: DistrictService,
                         val sportService: SportService

) {


    @RequestMapping("", method = [RequestMethod.GET])
    fun homePage(model: Model): String {
        return "redirect:home"


    }


    @RequestMapping("/home", method = [RequestMethod.GET])
    fun home(model: Model): String {
        model.set("sports", sportService.findAll())
        model.set("districtNames", districtService.findAll().map { it.districtName })
        return "home"

    }



    @RequestMapping("/updateUser", method = [RequestMethod.POST])
    fun updateUser(
        @ModelAttribute("currentUser") @Valid userdto: UserDto, bindingResult: BindingResult, model: Model): String {
        if (bindingResult.hasErrors()) {
            return "home"
        }
        try {
            // Fange alle Fehler auf die mit Constraint Violations zu tun haben
            try {
                userService.update(userdto)
            } catch (cve: ConstraintViolationException) {
                if (cve.message.orEmpty().contains("must be a past date")) {
                    bindingResult.rejectValue(
                        "dateOfBirth",
                        "dateOfBirth.inFuture",
                        "Date of birth must be in the past"
                    )
                    return "home"
                } else {
                    throw cve
                }
            }
        } catch (dive: DataIntegrityViolationException) {
            // ERROR Messages related to username

            val errorQuadruple: Quadruple<String, String, String, String> =
                userService.userInputExceptionHandling(dive.message)
            bindingResult.rejectValue(errorQuadruple.second, errorQuadruple.third, errorQuadruple.fourth)
            model.set("districtNames", districtService.findAll().map { it.districtName })
            model.set("sports", sportService.findAll())
            return "home"
        }
        model.set("districtNames", districtService.findAll().map { it.districtName })
        model.set("sports", sportService.findAll())
        return "home"
    }
}


// Todo: Redirect auf signup success Seite um User anzuzeigen das er sich erfolgreich angemeldet hat


