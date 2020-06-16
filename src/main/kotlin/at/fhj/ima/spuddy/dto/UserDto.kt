package at.fhj.ima.spuddy.dto

import at.fhj.ima.spuddy.entity.Gender
import java.time.LocalDate
import javax.validation.constraints.Size

// Datatransfer Objekt für User - wird verwendet um zu verhindern das der User Attribute manipulieren kann
// die er nicht manipulieren können soll
class UserDto (
        var username: String,
        @Size(min = 2, max = 30)
    var password: String? = null,
        @Size(min = 2, max = 30)
    var passwordrepeat: String? = null
) {
    var lastName: String? = null
    var firstName: String? = null
    var dateOfBirth: LocalDate? = null
    var district: String? = null
    var gender: Gender? = null
    var email: String? = null
    var isTeam: Boolean? = false
}