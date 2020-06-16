package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.entity.Gender
import at.fhj.ima.spuddy.entity.User
import at.fhj.ima.spuddy.entity.UserRole
import at.fhj.ima.spuddy.repository.DistrictRepository
import at.fhj.ima.spuddy.repository.UserRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService (val userRepository: UserRepository,
                   val districtRepository: DistrictRepository) {

    fun createNewUser(): UserDto{
        val newUser = User(username = "", password = "", role = UserRole.ROLE_USER)
        return convertEntityToDto(newUser)
    }

    fun findByUsername(username: String) : UserDto? {
        if (userRepository.findByUsername(username) != null)
            return convertEntityToDto(userRepository.findByUsername(username))
        else
            return null
    }

    private fun convertEntityToDto(user: User?): UserDto {
        if (user != null){
            return UserDto(username = user.username,
                    firstName = user.firstName,
                    lastName = user.lastName,
                    dateOfBirth= user.dateOfBirth,
                    district = user.district?.districtName,
                    gender = user.gender,
                    email = user.email,
                    isTeam = user.isTeam)
        }
        else
            return UserDto(username = "",
                           password = "")
    }

    @Transactional
    fun save(dto: UserDto){
        val user = convertDtoToEntity(dto)
        if(user != null){
            userRepository.save(user)
        }
        else
        {
            throw DataIntegrityViolationException("Error: User could not be saved because it is null!")
        }
    }

    private fun convertDtoToEntity (dto: UserDto): User? {
        // Prüfe ob ein Passwort mitgegeben wurde
        // Prüfe ob der mitgegebene District in der Datenbank vorhanden ist
        // Falls beides zutrifft erstelle einen User, falls nicht liefere null zurück
        // Ansonsten wirf eine DataIntegrityViolation Exception
        if((dto.password != null) && (districtRepository.findByDistrictName(dto.district!!) != null)) {
            val user = User(
                    username = dto.username,
                    password = BCryptPasswordEncoder().encode(dto.password!!),
                    firstName = dto.firstName,
                    lastName = dto.lastName,
                    dateOfBirth = dto.dateOfBirth,
                    district = districtRepository.findByDistrictName(dto.district!!),
                    gender = dto.gender,
                    email = dto.email,
                    isTeam = dto.isTeam
            )
            return user
        }
        else {
            throw DataIntegrityViolationException("Error: password null or district not found!")
        }
        return null
    }
}