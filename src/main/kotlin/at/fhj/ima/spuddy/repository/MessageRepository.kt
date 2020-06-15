package at.fhj.ima.spuddy.repository

import at.fhj.ima.spuddy.entity.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, Int> {
    @Query("FROM Message where messageId = :messageId")
    fun findByMessageId(@Param("messageId") messageId: Int): Message
}