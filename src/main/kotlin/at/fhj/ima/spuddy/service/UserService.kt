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

    fun createNewUser(): UserDto {
        val newUser = User(username = "", password = "", role = UserRole.ROLE_USER)
        return convertEntityToDto(newUser)!!
    }

    fun findByUsername(username: String): UserDto? {
        return convertEntityToDto(userRepository.findByUsername(username))
    }

    private fun convertEntityToDto(user: User?): UserDto? {
        if (user != null) {
            val dto = UserDto(user.username)
            dto.username = user.username
            dto.firstName = user.firstName
            dto.lastName = user.lastName
            dto.dateOfBirth = user.dateOfBirth
            dto.district = user.district?.districtName
            dto.gender = user.gender
            dto.email = user.email
            dto.isTeam = user.isTeam
            return dto
        } else
            return null
    }

    @Transactional
    fun save(dto: UserDto) {
        val user = convertDtoToEntity(dto)
        if (user != null) {
            userRepository.save(user)
        } else {
            throw DataIntegrityViolationException("Error: User could not be saved because it is null!")
        }
    }

    private fun convertDtoToEntity(dto: UserDto): User? {
        // User Daten Prüfung -> Bei Fehler wird DataIntegrityViolationException aufgerufen
        // when entspricht verkettetem if-Statement bzw. Guards aus Haskell
        // Geschwungene Klammern von "when" umfassen alle zu bearbeitenden Fäll
        // Links vom Pfeil steht Condition
        // Rechts vom Pfeil (bzw. im Klammerblock danach) steht was bei Eintreten des Falls passieren soll
        when {
            ((userRepository.findByUsername(dto.username) != null)) -> {
                throw DataIntegrityViolationException("Error: usernameInUse already in use!")
            }

            (dto.password != dto.passwordrepeat) -> {
                throw DataIntegrityViolationException("Error: passwordNotMatch passwords don't match!")
            }

            ((dto.password.isNullOrEmpty()) || (districtRepository.findByDistrictName(dto.district!!) == null)) -> {
                throw DataIntegrityViolationException("Error: passwordNull password null or district not found!")
            }

            ((dto.password!!.length !in 2..30)) -> {
                throw DataIntegrityViolationException("Error: passwordLength doesn't meet length requirements!")
            }

            (dto.firstName.isNullOrEmpty()) -> {
                throw DataIntegrityViolationException("Error: firstName empty!")
            }

            (dto.lastName.isNullOrEmpty()) -> {
                throw DataIntegrityViolationException("Error: lastName empty!")
            }

            (dto.dateOfBirth == null) -> {
                throw DataIntegrityViolationException("Error: dateOfBirth empty!")
            }

            (dto.gender == null) -> {
                throw DataIntegrityViolationException("Error: gender empty!")
            }

            (dto.email.isNullOrEmpty()) -> {
                throw DataIntegrityViolationException("Error: email field empty!")
            }
            else -> {
                try {
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
                catch (ex : Exception){
                    throw DataIntegrityViolationException("Error: userInstantiation failed!")
                }
            }

        }
    }
}