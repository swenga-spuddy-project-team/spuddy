package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.entity.Sport
import at.fhj.ima.spuddy.repository.SportRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class SportService (val sportRepository: SportRepository){

    fun findAll() : List<Sport>{
        return sportRepository.findAll()
    }

    fun delete(id:Int){
        sportRepository.delete(sportRepository.findSportById(id))
    }

    fun findDescriptionById(id: Int): String {
        return sportRepository.findSportById(id).description.orEmpty()
    }

    fun findById(id:Int): Sport {
        return sportRepository.findSportById(id)
    }

    fun createNewSport() :Sport {
        return Sport()
    }

    fun save(sport: Sport) {
        if(sport.description.isNullOrEmpty())
        {
            throw DataIntegrityViolationException("descriptionEmpty")
        }
        sportRepository.save(sport)
    }
}