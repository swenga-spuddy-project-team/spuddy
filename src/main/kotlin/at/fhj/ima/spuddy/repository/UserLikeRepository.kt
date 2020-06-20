package at.fhj.ima.spuddy.repository

import at.fhj.ima.spuddy.entity.User
import at.fhj.ima.spuddy.entity.UserLike
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserLikeRepository: JpaRepository<UserLike, Int> {
    @Query("FROM UserLike WHERE swipingUser = :swipingUser")
    fun findLikesBySwipingUser(@Param("swipingUser") swipingUser: User): UserLike

    @Query ("FROM UserLike WHERE swipingUser = :swipingUser AND statusLikes = 'liked'")
    fun createLikedList(@Param ("swipingUser") swipingUser: User): List<UserLike>

    @Query ("FROM UserLike WHERE swipingUser = :swipingUser AND statusLikes = 'disliked'")
    fun createDislikedList(@Param ("swipingUser") swipingUser: User): List<UserLike>

    @Query ("FROM UserLike WHERE swipingUser = :swipingUser AND statusLikes = 'blocked'")
    fun createBlockedList(@Param ("swipingUser") swipingUser: User): List<UserLike>


    //@Query("FROM UserLike WHERE swipingUser = :swipingUser AND statusLikes NOT :liked or :blocked or :disliked")
    //fun
}