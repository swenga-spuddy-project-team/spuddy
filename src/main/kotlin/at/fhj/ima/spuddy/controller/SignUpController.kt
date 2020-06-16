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
import javax.validation.Valid


@Controller
class SignUpController (val userService: UserService,
                        val districtService: DistrictService){
    @RequestMapping("/signup", method = [RequestMethod.GET])
    fun signup(model: Model): String {
        var userdto = UserDto(username = "", password = "", passwordrepeat = "")
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
        try {
            // Todo: Dieser Bereich muss mehrfach und genau auf potentielle Input Fehler geprüft werden
            //  Ein User darf unter keinen Umständen falsche Daten eingeben können - nicht nur durch ein Formular
            //  sondern auch über eventuelle böse Requests
            // Prüft ob der User bereits in der Datenbank vorhanden ist - falls nein können wir einen neuen User
            // Mit diesem Usernamen anlegen
            if (userService.findByUsername(userdto.username) == null) {

                // Prüft ob die beiden Passwörter aus den Eingabefeldern übereinstimmen
                if (userdto.password == userdto.passwordrepeat ) {
                    if(!userdto.password.isNullOrEmpty()){
                        if(userdto.password!!.length in 2..30) {
                            // Versuche den neuen User hinzuzufügen, ist der District nicht vorhanden oder ungültig gib dies aus
                            // und kehre auf die Signup Page zurück
                            try {
                                var newUser = userService.save(userdto)
                            } catch (dive: DataIntegrityViolationException) {
                                if (dive.message.orEmpty().contains("district")) {
                                    bindingResult.rejectValue("district", "district.invalidSelection", "District invalid.")
                                    model.set("errorMessage", "Invalid district!")
                                    return "signup"
                                } else {
                                    throw dive;
                                }
                            }
                        }
                        else {
                            bindingResult.rejectValue("password", "password.LengthRequirementNotMet",
                                    "Password doesn't match length requirement min. 2 max. 30 characters")
                            return "signup"
                        }
                    }
                    else {
                        bindingResult.rejectValue("password", "password.Empty", "Password field must not be empty")
                        return "signup"
                    }
                } else {
                    // Code unterhalb gibt eine Fehlermeldung beim "Error" Path des jeweiligen Formfelds aus
                    // In diesem fall bei <form:errors path="password" cssClass="invalid-feedback d-block"/>
                    // Im signup.jsp
                    bindingResult.rejectValue("password", "password.noMatchOnRepeat", "Passwords don't match")
                    // Code unterhalb gibt eine Fehlernachricht in Form eines Header Banners aus
                    // model.set("errorMessage", "Passwords did not match!")
                    return "signup"
                }
            }
            else {
                throw DataIntegrityViolationException("Error: username already in use!")
            }
        } catch (dive: DataIntegrityViolationException) {
            if (dive.message.orEmpty().contains("username")) {
                bindingResult.rejectValue("username", "username.alreadyInUse", "Username already in use");
                return "signup"
            } else {
                throw dive;
            }
        }
        return "redirect:login"
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