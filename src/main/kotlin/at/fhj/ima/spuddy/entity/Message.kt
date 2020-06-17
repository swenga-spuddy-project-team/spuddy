package at.fhj.ima.spuddy.entity

import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp
import java.text.SimpleDateFormat
import javax.persistence.*

@Entity
class Message (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var messageId: Int? = null,
    @Column(nullable = false)
    var content: String? = null,
    @CreationTimestamp
    var timestamp : Timestamp? = null,
    @ManyToOne
    var senderId: User,
    @ManyToOne
    var receiverId: User

) : Comparable<Message> {
    override fun compareTo(other: Message): Int {
        return compareValues(messageId, other.messageId)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Message
        if (messageId != other.messageId) return false
        return true
    }

    override fun hashCode(): Int {
        return messageId.hashCode()
    }
}

