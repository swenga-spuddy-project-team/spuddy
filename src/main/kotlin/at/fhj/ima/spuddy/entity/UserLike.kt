package at.fhj.ima.spuddy.entity

import javax.persistence.*


enum class statusLikes {
    LIKED,
    DISLIKED
}

@Entity
class UserLike (
        @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    var likesId: Int? = null,
        @Enumerated(EnumType.STRING)
    var statusLikes: statusLikes? = null,
        @ManyToOne
    var swipedUser: User,
        @ManyToOne
    var swipingUser: User

) : Comparable<UserLike> {
    override fun compareTo(other: UserLike): Int {
        return compareValues(likesId, other.likesId)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as UserLike
        if (likesId != other.likesId) return false
        return true
    }

    override fun hashCode(): Int {
        return likesId.hashCode()
    }
}
