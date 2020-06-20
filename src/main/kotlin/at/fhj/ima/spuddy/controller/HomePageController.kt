package at.fhj.ima.spuddy.controller

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

/*

    @RequestMapping("/updateUser", method = [RequestMethod.POST])
    fun updateUser(
        @ModelAttribute("employee") @Valid userdto: UserDto, bindingResult: BindingResult,
        model: Model
    ): String {
        if (bindingResult.hasErrors()) {
            return "home"
        }
        try {
            userService.save(userdto)
        } catch (dive: DataIntegrityViolationException) {
            if (dive.message.orEmpty().contains("ssn_UK")) {
                bindingResult.rejectValue("ssn", "ssn.alreadyInUse", "SSN already in use.");
                return "/home"
            } else {
                throw dive;
            }
        }
        return "/home"
    }
    // Fange alle Fehler auf die mit Daten Integrität zu tun haben
    try {
        // Fange alle Fehler auf die mit Constraint Violations zu tun haben
        try {
            userService.save(userdto)
        }
        catch(cve: ConstraintViolationException){
            if(cve.message.orEmpty().contains("must be a past date")){
                bindingResult.rejectValue("dateOfBirth", "dateOfBirth.inFuture", "Date of birth must be in the past")
                return "/home""
            }
            else {
                throw cve
            }
        }
    } catch (dive: DataIntegrityViolationException) {
        // ERROR Messages related to username
        when {
            (dive.message.orEmpty().contains("username")) -> {
                bindingResult.rejectValue("username", "username.alreadyInUse", "Username already in use")
                return "/home""
            }
            // ERROR Messages related to password
            (dive.message.orEmpty().contains("passwordLength")) -> {
                // Code unterhalb gibt eine Fehlermeldung beim "Error" Path des jeweiligen Formfelds aus
                // In diesem fall bei <form:errors path="password" cssClass="invalid-feedback d-block"/>
                // Im signup.jsp
                bindingResult.rejectValue("password", "password.lengthRequirementNotMet", "Password must be between 2 and 30 characters")
                // Code unterhalb gibt eine Fehlernachricht in Form eines Header Banners aus
                // model.set("errorMessage", "Password does not meet length requirement!")
                return "/home""
            }

        }
        (dive.message.orEmpty().contains("passwordNotMatch")) -> {
            bindingResult.rejectValue("password", "password.notMatch", "Passwords don't match!")
            return "/home"
        }
        // ERROR Messages related to district
        (dive.message.orEmpty().contains("district")) -> {
            bindingResult.rejectValue("district", "district.invalidSelection", "District invalid.")
            return "/home"
        }
        // ERROR Messages related to firstName
        (dive.message.orEmpty().contains("firstName")) -> {
            bindingResult.rejectValue("firstName", "firstName.empty", "First name is required.")
            model.set("districtNames", districtService.findAll().map { it.districtName })
            return "/home"
        }
        // ERROR Messages related to lastName
        (dive.message.orEmpty().contains("lastName")) -> {
            bindingResult.rejectValue("lastName", "lastName.empty", "Last name is required.")
            return "/home"
        }
        // ERROR Messages related to dateOfBirth
        (dive.message.orEmpty().contains("dateOfBirth")) -> {
            bindingResult.rejectValue("dateOfBirth", "dateOfBirth.empty", "Date of birth is required.")
            return "/home"
        }
        // ERROR Messages related to gender
        (dive.message.orEmpty().contains("gender")) -> {
            bindingResult.rejectValue("gender", "gender.empty", "Gender is required.")
            return "/home"
        }
        // ERROR Messages related to email
        (dive.message.orEmpty().contains("email")) -> {
            bindingResult.rejectValue("email", "email.empty", "email is required.")
            return "/home"
        }
        (dive.message.orEmpty().contains("userInstantiation")) -> {
            bindingResult.rejectValue("username", "user.generalError", "Unidentifiable error during user instantiation.")
            return "/home"
        }
        else -> {
            throw dive
        }
    }
}
// Todo: Redirect auf signup success Seite um User anzuzeigen das er sich erfolgreich angemeldet hat
return "/home"
}
*/

}