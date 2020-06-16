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
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
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
                if (userdto.password == userdto.passwordrepeat) {

                    // Versuche den neuen User hinzuzufügen, ist der District nicht vorhanden oder ungültig gib dies aus
                    // und kehre auf die Signup Page zurück
                    try{
                        var newUser = userService.save(userdto)
                    }
                    catch (dive: DataIntegrityViolationException){
                        if(dive.message.orEmpty().contains("username"))
                        {
                            bindingResult.rejectValue("district", "district.invalidSelection", "District invalid.")
                            model.set("errorMessage", "Invalid district!")
                            return signup(model)
                        }
                        else {
                            throw dive;
                        }
                    }
                } else {
                    bindingResult.rejectValue("password", "password.noMatchOnRepeat", "Passwords don't match")
                    model.set("errorMessage", "Passwords did not match!")
                    return signup(model)
                }
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


}