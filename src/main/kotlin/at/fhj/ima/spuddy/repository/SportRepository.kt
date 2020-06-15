package at.fhj.ima.spuddy.repository

import at.fhj.ima.spuddy.entity.Sport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SportRepository : JpaRepository<Sport, Int> {
    @Query("FROM Sport where sportId = :sportId")
    fun findSportById(@Param("sportId") sportId: Int): Sport
}