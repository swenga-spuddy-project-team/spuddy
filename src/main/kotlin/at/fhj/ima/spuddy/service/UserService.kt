package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.entity.User
import at.fhj.ima.spuddy.entity.UserRole
import at.fhj.ima.spuddy.helpers.Quadruple
import at.fhj.ima.spuddy.repository.DistrictRepository
import at.fhj.ima.spuddy.repository.UserRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.nio.file.Paths


@Service
class UserService (val userRepository: UserRepository,
                   val districtRepository: DistrictRepository,
                   val fileService: FileService) {

    fun createNewUser(): UserDto {
        val newUser = User(username = "", password = "", role = UserRole.ROLE_USER)
        return convertEntityToDto(newUser)!!
    }

    fun findAll(): List<UserDto> {
        return userRepository.findAll().map { convertEntityToDto(it) }.filterNotNull()
    }

    fun findByUsername(username: String): UserDto? {
        return convertEntityToDto(userRepository.findByUsername(username))
    }

    fun findById(id: Int): UserDto? {
        val user = userRepository.findByIdOrNull(id)
        if(user != null){
            return convertEntityToDto(user)
        }
        return null
    }

    fun findRoleByUsername(username: String) : UserRole? {
        val user = userRepository.findByUsername(username)
        if (user != null)
            return user.role
        else
            return null
    }

    fun delete(id: Int) {
        userRepository.delete(userRepository.findByIdOrNull(id)!!)
    }

    fun convertEntityToDto(user: User?): UserDto? {
        if (user != null) {
            val dto = UserDto(user.username)
            dto.id = user.id
            dto.firstName = user.firstName
            dto.lastName = user.lastName
            dto.dateOfBirth = user.dateOfBirth
            dto.district = user.district?.districtName
            dto.gender = user.gender
            dto.email = user.email
            dto.isTeam = user.isTeam
            if (user.profilePicture != null) {
                dto.profilePicturePath = Paths.get("/files/" + user.profilePicture!!.id!!)
            }
            dto.descriptionText = user.descriptionText
            dto.sport = user.sport
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

    @Transactional
    fun update(dto: UserDto) {
        val user = convertDtoToEntity(dto, true)
        if (user != null) {
            userRepository.save(user)

        } else {
            throw DataIntegrityViolationException("Error: User could not be saved because it is null!")
        }
    }




     fun convertDtoToEntity(dto: UserDto, update: Boolean = false): User? {

        // User Daten Prüfung -> Bei Fehler wird DataIntegrityViolationException aufgerufen
        // when entspricht verkettetem if-Statement bzw. Guards aus Haskell
        // Geschwungene Klammern von "when" umfassen alle zu bearbeitenden Fäll
        // Links vom Pfeil steht Condition
        // Rechts vom Pfeil (bzw. im Klammerblock danach) steht was bei Eintreten des Falls passieren soll

        verifyUsername(dto, update)
        verifyPassword(dto)
        verifyFirstName(dto)
        verifyLastName(dto)
        verifyDate(dto)
        verifyDistrict(dto)
        verifyGender(dto)
        verifyEmail(dto)

        //Todo: Verify Profilepicture
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
                    isTeam = dto.isTeam,
                    profilePicture = dto.profilePicture,
                    descriptionText = dto.descriptionText,
                    sport = dto.sport
            )
            if(update)
                user.id = userRepository.findByUsername(dto.username)!!.id

            return user
        }
        catch (ex : Exception){
            throw DataIntegrityViolationException("Error: userInstantiation failed!")
        }
    }


    fun verifyPassword(dto: UserDto){
        when {
            (dto.password != dto.passwordrepeat) -> {
                throw DataIntegrityViolationException("Error: passwordNotMatch passwords don't match!")
            }

            (dto.password.isNullOrEmpty()) -> {
                throw DataIntegrityViolationException("Error: passwordNull password null.")
            }

            ((dto.password!!.length !in 2..30)) -> {
                throw DataIntegrityViolationException("Error: passwordLength doesn't meet length requirements!")
            }
            else -> return
        }
    }



    fun verifyUsername(dto:UserDto, update: Boolean = false){
        when {
            ((userRepository.findByUsername(dto.username) != null) && !update) -> {
                throw DataIntegrityViolationException("Error: usernameInUse name already in use!")
            }
            (((dto.username) == null) ) -> {
                throw DataIntegrityViolationException("Error: usernameEmpty name field empty !")
            }
            else -> return
        }
    }

    fun verifyFirstName(dto: UserDto){
        when {
            (dto.firstName.isNullOrEmpty()) -> {
                throw DataIntegrityViolationException("Error: firstName empty!")
            }
            else -> return
        }
    }

    fun verifyLastName(dto: UserDto){
        when {
            (dto.lastName.isNullOrEmpty()) -> {
                throw DataIntegrityViolationException("Error: lastName empty!")
            }
            else -> return
        }
    }

    fun verifyDistrict(dto: UserDto){
        when {
            (districtRepository.findByDistrictName(dto.district!!) == null) -> {
                throw DataIntegrityViolationException("Error: district empty!")
            }
            else -> return
        }
    }

    fun verifyDate(dto: UserDto) {
        when {
            (dto.dateOfBirth == null) -> {
                throw DataIntegrityViolationException("Error: dateOfBirth empty!")
            }
            else -> return
        }
    }

    fun verifyGender(dto: UserDto){
        when {
            (dto.gender == null) -> {
                throw DataIntegrityViolationException("Error: gender empty!")
            }
            else -> return
        }
    }

    fun verifyEmail(dto: UserDto){
        when {
            (dto.email.isNullOrEmpty()) -> {
                throw DataIntegrityViolationException("Error: email field empty!")
            }
            else -> return
        }
    }

    fun userInputExceptionHandling(diveMessage: String?) : Quadruple<String, String, String, String>{
        val errorList = listOf(
            Quadruple("username", "username","username.alreadyInUse", "Username already in use"),
            Quadruple("passwordLength", "password","password.lengthRequirementNotMet", "Password must be between 2 and 30 characters"),
            Quadruple("passwordNull", "password","password.empty", "Password field must not be empty"),
            Quadruple("passwordNotMatch", "password","password.notMatch", "Passwords don't match!"),
            Quadruple("district", "district","district.invalidSelection", "District invalid"),
            Quadruple("firstName", "firstName","firstName.empty", "First name is required"),
            Quadruple("lastName", "lastName","lastName.empty", "Last name is required"),
            Quadruple("dateOfBirth", "dateOfBirth","dateOfBirth.empty", "Date of birth is required"),
            Quadruple("gender", "gender","gender.empty", "Gender is required"),
            Quadruple("email", "email","email.empty", "email is required"),
            Quadruple("userInstantiation", "username","user.generalError", "Unidentifiable error during user instantiation"))

        return errorList.reduce { acc, quadruple -> verifyUserExceptions(diveMessage, quadruple, acc)  }
    }


    fun verifyUserExceptions(diveMessage: String?,
                             quadruple: Quadruple<String, String, String, String>,
                             acc: Quadruple<String, String, String, String>) : Quadruple<String, String, String, String>{

        val (contains, field, verrorCode, errorMessage) = quadruple
        if (diveMessage.orEmpty().contains(contains))
            return Quadruple(contains, field, verrorCode, errorMessage)
        else
            return acc
    }
}