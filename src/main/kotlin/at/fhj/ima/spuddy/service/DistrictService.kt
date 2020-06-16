package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.entity.District
import at.fhj.ima.spuddy.repository.DistrictRepository
import org.springframework.stereotype.Service

@Service
class DistrictService (val districtRepository: DistrictRepository){
    fun findAll(): List<District> {
        return districtRepository.findAll()
    }
}