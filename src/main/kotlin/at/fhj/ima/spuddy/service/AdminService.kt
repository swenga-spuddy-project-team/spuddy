package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.entity.*
import at.fhj.ima.spuddy.repository.*
import org.aspectj.bridge.Message
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.Collections.min
import javax.persistence.Tuple


@Service
class AdminService (val districtRepository: DistrictRepository,
                    val messageRepository: MessageRepository,
                    val sportRepository: SportRepository,
                    val userLikeRepository: UserLikeRepository,
                    val userRepository: UserRepository,
                    val userService: UserService
){
    fun generateTestData(){

        val testUserA : UserDto = UserDto("UserA", "test", "test")
        testUserA.firstName = "Hans"
        testUserA.lastName = "Huber"
        testUserA.dateOfBirth = LocalDate.of(1991, 10,1)
        testUserA.email = "Hans@Huber.at"
        testUserA.gender = Gender.MALE
        testUserA.district = districtRepository.findByDistrictName("Graz")!!.districtName
        testUserA.isTeam = false

        val testUserB : UserDto = UserDto("UserB", "test", "test")
        testUserB.firstName = "Harald"
        testUserB.lastName = "Huberst"
        testUserB.dateOfBirth = LocalDate.of(1991, 10,1)
        testUserB.email = "Harald@Huberst.at"
        testUserB.gender = Gender.MALE
        testUserB.district = districtRepository.findByDistrictName("Graz")!!.districtName
        testUserB.isTeam = false

        val testUserC : UserDto = UserDto("UserC", "test", "test")
        testUserC.firstName = "Bertl"
        testUserC.lastName = "Bansi"
        testUserC.dateOfBirth = LocalDate.of(1991, 10,1)
        testUserC.email = "Bertl@Bansi.at"
        testUserC.gender = Gender.MALE
        testUserC.district = districtRepository.findByDistrictName("Graz")!!.districtName
        testUserC.isTeam = false



        val testUserDtoList : List<UserDto> = listOf(testUserA, testUserB, testUserC)

        generateTestUsers(testUserDtoList)

        val testUserMessagePair : Pair<User, User> = Pair(userRepository.findByUsername(testUserA.username)!!,
                                                            userRepository.findByUsername(testUserB.username)!!)
        val testMessageList : List<String> = listOf("Hi", "Hi", "Was geht", "Ciao du Spasti")

        val testUserLikes : List<Triple<User, User, StatusLikes>> =
                listOf(
                        Triple(userRepository.findByUsername(testUserA.username)!!, userRepository.findByUsername(testUserB.username)!!, StatusLikes.LIKED),
                        Triple(userRepository.findByUsername(testUserB.username)!!, userRepository.findByUsername(testUserA.username)!!, StatusLikes.LIKED),
                        Triple(userRepository.findByUsername(testUserB.username)!!, userRepository.findByUsername(testUserA.username)!!, StatusLikes.MATCHED))


        generateTestUserLikes(testUserLikes)
        generateTestMessages(testUserMessagePair, testMessageList)

    }

    fun generateTestUsers(userDtoList: List<UserDto>){
        userDtoList.forEach { userService.save(it) }
    }

    fun generateTestMessages(userMessagePair: Pair<User,User>, userMessageContentList: List<String>){
        // Generiere eine Konversation zweier User, bei jedem Durchlauf werden dabei die Plätze zwischen
        // den beiden Usern getauscht
        var (user1, user2) = userMessagePair
        userMessageContentList.forEach {
            val newMessage = Message(senderId = user1, receiverId = user2, content = it)
            messageRepository.save(newMessage)
            // CodeSnippet unten dient zum Swappen von user1 und user2
            user1 = user2.also { user2 = user1 }
        }
    }

    fun generateTestUserLikes(userLikesTupleList: List<Triple<User, User, StatusLikes>>){
        userLikesTupleList.forEach {
            val (swipingUser, swipedUser, statusLikes) = it
            userLikeRepository.save(UserLike(swipingUser = swipingUser, swipedUser = swipedUser, statusLikes = statusLikes))}
    }

    fun generateTestSports(nameList : List<String>, urlList : List<String>, descriptionList : List<String>){
        // Nutze drei Listen um eine Reihe an SportArten zu generieren
        val maxLength : Int = min(listOf(nameList.size, urlList.size, descriptionList.size))

        if (maxLength > 1){
            for (i in 1..maxLength){
                val newSport = Sport(name = nameList[i], iconUrl = urlList[i], description = descriptionList[i])
                sportRepository.save(newSport)
            }
        }
    }

    fun generateTestDistricts(){

    }
}