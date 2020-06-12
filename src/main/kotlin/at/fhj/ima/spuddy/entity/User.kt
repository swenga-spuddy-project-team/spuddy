package at.fhj.ima.spuddy.entity

import java.util.*
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
    // Die Attribute unterhalb benötigen evtl. noch Tags wie @Column(nullable= false)
    var lastname: String,
    var firstname: String,
    var dateOfBirth: Date,
    var districtId: Int,
    var gender: String,
    var descriptionText: String,
    var mailAddress: String,
    // Default Wert für isTeam ist false
    var isTeam: Boolean? = false

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