package at.fhj.ima.spuddy.repository

import at.fhj.ima.spuddy.entity.Messages
import at.fhj.ima.spuddy.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MessagesRepository : JpaRepository<Messages, Int> {
    @Query("FROM Messages where messageId = :messageId")
    fun findByMessageId(@Param("messageId") messageId: String): Messages
}