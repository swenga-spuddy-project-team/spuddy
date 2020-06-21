package at.fhj.ima.spuddy.dto

import at.fhj.ima.spuddy.entity.File
import at.fhj.ima.spuddy.entity.Gender
import at.fhj.ima.spuddy.entity.User
import org.hibernate.annotations.CreationTimestamp
import java.nio.file.Path
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Size

// Datatransfer Objekt für User - wird verwendet um zu verhindern das der User Attribute manipulieren kann
// die er nicht manipulieren können soll
class MsgDto (


) {
    var id: Int? = null
    var lastName: String? = null
    var content: String? = null
    var timestamp: LocalDateTime? = null
    var sender: User? = null
    var receiver: User? = null

}
