package at.fhj.ima.spuddy.entity

import javax.persistence.*

@Entity
class District (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var districtId: Int? = null,
    @Column(nullable = false, unique = true)
    var districtName: String

    ) : Comparable<District> {
        override fun compareTo(other: District): Int {
            return compareValues(districtId, other.districtId)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            other as District
            if (districtId != other.districtId) return false
            return true
        }

        override fun hashCode(): Int {
            return districtId.hashCode()
        }
}