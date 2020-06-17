package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.entity.User
import at.fhj.ima.spuddy.entity.UserRole
import at.fhj.ima.spuddy.service.DistrictService
import at.fhj.ima.spuddy.service.UserService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid


@Controller
class SignUpController (val userService: UserService,
                        val districtService: DistrictService){

    @RequestMapping("/signup", method = [RequestMethod.GET])
    fun signup(model: Model): String {
        val userdto = UserDto(username = "", password = "", passwordrepeat = "")
        model.set("userdto", userdto)
        model.set("districtNames", districtService.findAll().map { it.districtName })
        return "signup"
    }

    @RequestMapping("/addUser", method = [RequestMethod.POST])
    fun addUser(@ModelAttribute("userdto") @Valid userdto: UserDto,
                bindingResult: BindingResult, model: Model): String {
        if (bindingResult.hasErrors()) {
            return "signup"
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
                    return returnToSignup(model)
                }
                else {
                    throw cve
                }
            }
        } catch (dive: DataIntegrityViolationException) {
            // ERROR Messages related to username
            when {
            (dive.message.orEmpty().contains("username")) -> {
                bindingResult.rejectValue("usernameInUse", "username.alreadyInUse", "Username already in use")
                return returnToSignup(model)
            }
            // ERROR Messages related to password
            (dive.message.orEmpty().contains("passwordLength")) -> {
                // Code unterhalb gibt eine Fehlermeldung beim "Error" Path des jeweiligen Formfelds aus
                // In diesem fall bei <form:errors path="password" cssClass="invalid-feedback d-block"/>
                // Im signup.jsp
                bindingResult.rejectValue("password", "password.lengthRequirementNotMet", "Password must be between 2 and 30 characters")
                // Code unterhalb gibt eine Fehlernachricht in Form eines Header Banners aus
                // model.set("errorMessage", "Password does not meet length requirement!")
                return returnToSignup(model)
            }
            (dive.message.orEmpty().contains("passwordNull")) -> {
                bindingResult.rejectValue("password", "password.empty", "Password field must not be empty")
                return returnToSignup(model)
            }
            (dive.message.orEmpty().contains("passwordNotMatch")) -> {
                bindingResult.rejectValue("password", "password.notMatch", "Passwords don't match!")
                return returnToSignup(model)
            }
            // ERROR Messages related to district
             (dive.message.orEmpty().contains("district")) -> {
                bindingResult.rejectValue("district", "district.invalidSelection", "District invalid.")
                return returnToSignup(model)
            }
            // ERROR Messages related to firstName
            (dive.message.orEmpty().contains("firstName")) -> {
                bindingResult.rejectValue("firstName", "firstName.empty", "First name is required.")
                model.set("districtNames", districtService.findAll().map { it.districtName })
                return returnToSignup(model)
            }
            // ERROR Messages related to lastName
            (dive.message.orEmpty().contains("lastName")) -> {
                bindingResult.rejectValue("lastName", "lastName.empty", "Last name is required.")
                return returnToSignup(model)
            }
            // ERROR Messages related to dateOfBirth
            (dive.message.orEmpty().contains("dateOfBirth")) -> {
                bindingResult.rejectValue("dateOfBirth", "dateOfBirth.empty", "Date of birth is required.")
                return returnToSignup(model)
            }
            // ERROR Messages related to gender
            (dive.message.orEmpty().contains("gender")) -> {
                bindingResult.rejectValue("gender", "gender.empty", "Gender is required.")
                return returnToSignup(model)
            }
            // ERROR Messages related to email
            (dive.message.orEmpty().contains("email")) -> {
                bindingResult.rejectValue("email", "email.empty", "email is required.")
                return returnToSignup(model)
            }
            else -> {
                throw dive
            }
        }
        }
        // Todo: Redirect auf signup success Seite um User anzuzeigen das er sich erfolgreich angemeldet hat
        return "redirect:login"
    }

    // Return to signup page without reseting all the data
    fun returnToSignup(model: Model) : String{
        model.set("districtNames", districtService.findAll().map { it.districtName })
        return "signup"
    }

    @RequestMapping("/addUser", method = [RequestMethod.GET])
    fun addUserRedirect(model: Model): String{
        return "redirect:signup"
    }

    @ExceptionHandler(Exception::class)
    fun handleError(req: HttpServletRequest, ex: Exception): ModelAndView {
        val mav = ModelAndView()
        mav.addObject("exception", ex)
        mav.addObject("url", req.requestURI)
        mav.viewName = "error"
        return mav
    }

}