package at.fhj.ima.spuddy.repository

import at.fhj.ima.spuddy.entity.User
import at.fhj.ima.spuddy.entity.UserLike
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserLikeRepository: JpaRepository<UserLike, Int> {
    @Query("FROM UserLike where swipingUser = :swipingUser")
    fun findLikesBySwipingUser(@Param("swipingUser") swipingUser: User): UserLike
}