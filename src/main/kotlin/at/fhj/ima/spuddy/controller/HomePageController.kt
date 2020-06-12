package at.fhj.ima.spuddy.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class HomePageController {

    @RequestMapping("", method = [RequestMethod.GET])
    fun homePage(model: Model): String {
        return "redirect:home"
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

}