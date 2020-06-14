package at.fhj.ima.spuddy.entity

import javax.persistence.*

@Entity
class Likes (
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    var idLikes: Int? = null,
    @Enumerated(EnumType.STRING)
    var statusLikes: String? = null,
    @OneToOne
    var swipedUser: User,
    @OneToOne
    var swipingUser: User

) : Comparable<Likes> {
    override fun compareTo(other: Likes): Int {
        return compareValues(idLikes, other.idLikes)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Likes
        if (idLikes != other.idLikes) return false
        return true
    }

    override fun hashCode(): Int {
        return idLikes.hashCode()
    }
}
