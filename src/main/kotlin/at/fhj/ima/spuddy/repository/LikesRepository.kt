package at.fhj.ima.spuddy.repository

import at.fhj.ima.spuddy.entity.Likes
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface LikesRepository: JpaRepository<Likes, Int> {
    @Query("FROM Likes where statusLikes = :statusLikes")
    fun findLikesByName(@Param("statusLikes") name: String): Likes
}