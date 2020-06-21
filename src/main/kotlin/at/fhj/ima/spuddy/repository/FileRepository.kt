package at.fhj.ima.spuddy.repository

import at.fhj.ima.spuddy.entity.File

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FileRepository : JpaRepository<File, Int> {
    @Query("FROM File where creator = :uploadingUser")
    fun findByUploadingUser(@Param("uploadingUser") uploadingUser: String): File?
}
