package at.fhj.ima.spuddy.entity

import java.io.Serializable
import javax.persistence.*

@Entity
class File(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var contentType: String? = null,
    var size: Long? = null,
    var originalFileName: String? = null,
    @Column(nullable = false, unique = true)
    var creator : String? = null
) : Comparable<File>, Serializable {
    override fun compareTo(other: File): Int {
        return compareValues(id, other.id)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as File
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
