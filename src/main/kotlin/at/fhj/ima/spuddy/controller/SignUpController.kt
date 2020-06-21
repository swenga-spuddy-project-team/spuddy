package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.helpers.Quadruple
import at.fhj.ima.spuddy.service.DistrictService
import at.fhj.ima.spuddy.service.UserService
import org.springframework.dao.DataIntegrityViolationException
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
                        val districtService: DistrictService
){

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

            val errorQuadruple: Quadruple<String, String, String, String> = userService.userInputExceptionHandling(dive.message)
            bindingResult.rejectValue(errorQuadruple.second, errorQuadruple.third, errorQuadruple.fourth)
            return returnToSignup(model)

        }
        // Todo: Redirect auf signup success Seite um User anzuzeigen das er sich erfolgreich angemeldet hat
        return "login"
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