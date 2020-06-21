package at.fhj.ima.spuddy.repository

import at.fhj.ima.spuddy.entity.StatusLikes
import at.fhj.ima.spuddy.entity.User
import at.fhj.ima.spuddy.entity.UserLike
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserLikeRepository: JpaRepository<UserLike, Int> {
    @Query("from UserLike where swipingUser = :swipingUser")
    fun findLikesBySwipingUser(@Param("swipingUser") swipingUser: User): List<UserLike>

    @Query("from UserLike where swipedUser = :swipedUser")
    fun findLikesBySwipedUser(@Param("swipedUser")swipingUser: User): UserLike

   // @Query ("from UserLike where swipingUser = :swipingUser and statusLikes like 'LIKED' or 'DISLIKED' or 'BLOCKED' order by likesId")
   // fun createListOfAllStatusEntries(@Param("swipingUser")swipingUser: String, @Param("statusLikes") statusLikes: StatusLikes): List<UserLike>

    /*
   @Query ("from UserLike where swipingUser = :swipingUser and statusLikes = 'LIKED'")
   fun createLikedList(@Param ("swipingUser") swipingUser: User): List<UserLike>

   @Query ("from UserLike where swipingUser = :swipingUser and statusLikes = 'DISLIKED'")
   fun createDislikedList(@Param ("swipingUser") swipingUser: User): List<UserLike>

   @Query ("from UserLike where swipingUser = :swipingUser and statusLikes = 'BLOCKED'")
   fun createBlockedList(@Param ("swipingUser") swipingUser: User): List<UserLike>*/

    //@Query("FROM UserLike WHERE swipingUser = :swipingUser AND statusLikes NOT :liked or :blocked or :disliked")
    //fun
    //ausgebessert statt swipingUser: User -> swipingUser: String
}