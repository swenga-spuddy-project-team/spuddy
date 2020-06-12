package at.fhj.ima.spuddy.entity

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*


enum class UserRole {
    ROLE_USER,
    ROLE_ADMIN
}

// User Klasse beschreibt den Login User

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column(nullable = false, unique = true)
    var username: String,
    var password: String,
    @Enumerated(EnumType.STRING)
    var role: UserRole,
    // Todo: Die Attribute unterhalb benötigen noch Tags wie @Column(nullable= false)
    var lastname: String? = null,
    var firstname: String? = null,
    var dateOfBirth: LocalDate? = null,
    var districtId: Int? = null,
    var gender: String? = null,
    var descriptionText: String? = null,
    var mailAddress: String? = null,
    var isTeam: Boolean? = false
    // Todo: ManyToMany Beziehung zu SportArten,
    //  ManyToOne Beziehungen zu Sport,
    //  ManyToMany Beziehung zu Pictures oder hat jeder User nur ein einziges Bild? Falls ja -> ManyToOne,
    //  OneToMany Beziehung zu Likes Entity,
    //  OneToMany Beziehung zu Messages,
    //  Entweder zweifache OneToMany Beziehung zu Chats oder ManyToMany,
    //  letzteres würde bedeuten das einfach mehrere Nutzer dem selben Chat zugewiesen werden können
    //  ohne das es einen Unterschied gibt - alternativ müsste der User der als erstes schreibt
    //  die erste AccountId sein und der Empfänger die zweite - wäre ws leichter

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