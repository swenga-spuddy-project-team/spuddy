package at.fhj.ima.spuddy.controller.advice

import at.fhj.ima.spuddy.repository.UserRepository
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute


// Dieser Advice ist im globalen Kontext sichtbar und ermöglicht so JSPs den Zugriff auf
// Infos wie den aktuellen User ohne eine separate Implementierung für jedes Request Mapping
// Ist zB relevant für die User HomePage um alle Informationen des jeweiligen Users abrufen zu können
@ControllerAdvice
class CurrentUserControllerAdvice(val userRepository: UserRepository) {

    @ModelAttribute
    fun addCurrentUser(model: Model) {

        val auth = SecurityContextHolder.getContext().authentication
        val username = auth.name
        // Gibt nichts zurück wenn der User anonym ist
        if(auth is AnonymousAuthenticationToken) {
            return
        }
        val currentUser = userRepository.findByUsername(username)
        model.addAttribute("currentUser", currentUser)
    }
}