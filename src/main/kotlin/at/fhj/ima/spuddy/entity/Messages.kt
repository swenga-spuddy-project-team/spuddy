package at.fhj.ima.spuddy.entity

import org.springframework.format.annotation.DateTimeFormat
import java.text.SimpleDateFormat
import javax.persistence.*

@Entity
class Messages (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var messageId: Int? = null,
    @Column(nullable = false)
    var content: String? = null,
    var timestamp: SimpleDateFormat? = null,
    @ManyToOne
    var senderId: User,
    @ManyToOne
    var receiverId: User

) : Comparable<Messages> {
    override fun compareTo(other: Messages): Int {
        return compareValues(messageId, other.messageId)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Messages
        if (messageId != other.messageId) return false
        return true
    }

    override fun hashCode(): Int {
        return messageId.hashCode()
    }
}

