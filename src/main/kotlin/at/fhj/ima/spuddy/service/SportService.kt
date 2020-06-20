package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.entity.Sport
import at.fhj.ima.spuddy.repository.SportRepository
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class SportService (val sportRepository: SportRepository){

    fun findAll() : List<Sport>{
        return sportRepository.findAll()
    }

    fun delete(id:Int){
        sportRepository.delete(sportRepository.findSportById(id))
    }

    fun findNameById(id: Int): String {
        return sportRepository.findSportById(id).description.orEmpty()
    }

    fun findById(id:Int): Sport {
        return sportRepository.findSportById(id)
    }

    fun createNewSport() :Sport {
        return Sport()
    }

    fun save(sport: Sport) {
        if(sport.name.isNullOrEmpty())
        {
            throw DataIntegrityViolationException("nameEmpty")
        }
        sportRepository.save(sport)
    }
}