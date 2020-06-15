package at.fhj.ima.spuddy.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Sport (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var sportId: Int? = null,
    var name: String? = null,
    var iconUrl: String? = null,
    var description: String? = null

) : Comparable<Sport> {
    override fun compareTo(other: Sport): Int {
        return compareValues(sportId, other.sportId)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Sport
        if (sportId != other.sportId) return false
        return true
    }

    override fun hashCode(): Int {
        return sportId.hashCode()
    }
}


