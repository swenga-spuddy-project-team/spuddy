package at.fhj.ima.spuddy.entity

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Past
import javax.validation.constraints.Size


enum class UserRole {
    ROLE_USER,
    ROLE_ADMIN
}

enum class Gender {
    MALE,
    FEMALE,
    OTHER
}

// User Klasse beschreibt den Login User

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column(nullable = false, unique = true)
    var username: String,
    @Column(nullable = false)
    @Size(min = 2, max = 30)
    var password: String,
    @Enumerated(EnumType.STRING)
    var role: UserRole? = UserRole.ROLE_USER,
    @Column(nullable = false)
    var lastName: String? = null,
    @Column(nullable = false)
    var firstName: String? = null,
    @Column(nullable = false)
    @field:Past
    var dateOfBirth: LocalDate? = null,
    @ManyToOne
    var district: District? = null,
    @Enumerated(EnumType.STRING)
    var gender: Gender? = null,
    var descriptionText: String? = null,
    var email: String? = null,
    @Column(nullable = false)
    var isTeam: Boolean? = false,
    var profilePictureUrl: String? = null,
    @ManyToMany
    var sport : Set<Sport>? = null
    //  ManyToMany Beziehung zu Pictures oder hat jeder User nur ein einziges Bild? Falls ja -> ManyToOne,
    //  OneToMany Beziehung zu Likes Entity (Status wird dabei als enum Class dargestellt - also Liked, Disliked, not seen,
    //  ManyToMany Messages - jede Message enthält Absender und Empfänger, sowie
    //  Timestamp und Inhalt -> Nachrichtenverläufe werden folgendermaßen aufgebaut
    //  Filter nach allen Nachrichten wo A Empfänger und B Sender ist und umgekehrt und ordne diese nach Timestamp

) : Comparable<User> {
    override fun compareTo(other: User): Int {
        return compareValues(id, other.id)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}