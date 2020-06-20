package at.fhj.ima.spuddy.repository

import at.fhj.ima.spuddy.entity.District
import at.fhj.ima.spuddy.entity.Sport
import at.fhj.ima.spuddy.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
    @Query("FROM User where username = :username")
    fun findByUsername(@Param("username") username: String): User?

    @Query("FROM User WHERE district = :district")
    fun createMyDistrictList(@Param("district") district: District?): List<User>

    @Query("FROM User WHERE sport = :sport")
    fun createMySportList(@Param("sport") sport:Set<Sport>?): List<User>
}