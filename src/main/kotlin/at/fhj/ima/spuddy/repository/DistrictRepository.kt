package at.fhj.ima.spuddy.repository

import at.fhj.ima.spuddy.entity.District
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DistrictRepository : JpaRepository<District, Int> {
    @Query("FROM District where districtId = :districtId")
    fun findByDistrictId(@Param("districtId") districtId: Int): District

    @Query("FROM District  where districtName = :districtName")
    fun findByDistrictName(@Param("districtName") districtName: String): District
}