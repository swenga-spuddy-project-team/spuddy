package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.repository.DistrictRepository
import at.fhj.ima.spuddy.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@Controller
class SpuddyController(val userRepository: UserRepository,
                       val districtRepository: DistrictRepository) {



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