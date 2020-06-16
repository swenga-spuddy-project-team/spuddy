package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.entity.User
import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.entity.UserRole
import at.fhj.ima.spuddy.repository.DistrictRepository
import at.fhj.ima.spuddy.repository.UserRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Controller
class SpuddyController(val userRepository: UserRepository,
                       val districtRepository: DistrictRepository) {


    // Simple Request mapping that returns the home.jsp landing page
    @RequestMapping("/home", method = [RequestMethod.GET])
    fun home(model: Model, @RequestParam(required = false) search: String?): String {
        return "home"
    }

    // Simple Request mapping that will probably be later expanded to include the administrative functions
    @Secured("ROLE_ADMIN")
    @RequestMapping("/admin", method = [RequestMethod.GET])
    fun admin(model: Model, @RequestParam(required = false) search: String?): String {
        return "admin"
    }

    // Einfaches Test Mapping um etwa zu testen ob Form Submits etc. funktionieren
    @RequestMapping("/test", method = [RequestMethod.GET])
    fun test(model: Model, @RequestParam(required = false) search: String?): String {
        return "test"
    }

    // Redirect to error page on problem
    @ExceptionHandler(Exception::class)
    fun handleError(req: HttpServletRequest, ex: Exception): ModelAndView {
        val mav = ModelAndView()
        mav.addObject("exception", ex)
        mav.addObject("url", req.requestURL)
        mav.viewName = "error"
        return mav
    }

}

//Todo: RequestMapping für Swipe implementieren bzw dahinter liegende Business Logik und Aufbau
//Todo: RequestMapping für Chats Matches implementieren -> Profilbild der letzten Matches anzeigen +
//      letzte Nachricht aus dem Chat
//Todo: RequestMapping für Chats implementieren
//Todo: RequestMapping für Persönliche Einstellungen implementieren
//Todo: RequestMapping für Filter implementieren -> Filter am besten direkt über die DB
// d.h. Filter sind eigene Entity - wobei jeder User einen Filter als OneToOne Beziehung bekommt

//Todo: Drei Mappings für Admin -> ähnlich "listEmployees" - wo also alle vorhandenen User, Districts und Sportarten
//      angezeigt und gelöscht werden können