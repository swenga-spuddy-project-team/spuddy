package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.entity.District
import at.fhj.ima.spuddy.entity.Sport
import at.fhj.ima.spuddy.repository.DistrictRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile


@Service
class DistrictService (val districtRepository: DistrictRepository){
    fun findAll(): List<District> {
        return districtRepository.findAll()
    }

    fun delete(id:Int){
        districtRepository.delete(districtRepository.findByDistrictId(id))
    }

    fun findByDistrictName(districtName : String): District? {
        return districtRepository.findByDistrictName(districtName)
    }

    fun findById(id:Int): District {
        return districtRepository.findByDistrictId(id)
    }

    fun createNewDistrict(districtName: String) : District {
        return District(districtName = districtName)
    }

    fun save(district: District){
        when {
            district.districtName.isNullOrEmpty() -> {
                throw DataIntegrityViolationException("districtNameEmpty")
            }
            findByDistrictName(district.districtName) != null -> {
                throw DataIntegrityViolationException("districtAlreadyExists")
            }
            else -> districtRepository.save(district)
        }
    }
}