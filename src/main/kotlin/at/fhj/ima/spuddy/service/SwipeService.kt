package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.entity.*
import at.fhj.ima.spuddy.repository.UserLikeRepository
import at.fhj.ima.spuddy.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class SwipeService (val userRepository: UserRepository,
                    val userLikeRepository: UserLikeRepository,
                    val userService: UserService

) {
    fun getNextUser(district: District, sport: Set<Sport>, username: String): User? {
        val loadCurrentUser = userRepository.findByUsername(username)
        val sharedDistrictAndSportsUsers = userRepository.sharedDistrictAndSports(district)
        val listOfLikesBySwipingUser = userLikeRepository.findLikesBySwipingUser(loadCurrentUser!!)
        val usersToHide = listOfLikesBySwipingUser.map { it.swipedUser }

        val newUserList = sharedDistrictAndSportsUsers.filter { user: User -> !usersToHide.contains(user) }
        val nextUser = newUserList.getOrNull(0)

        return nextUser
    }

    fun generateUserLike(currentUser: User, user: User, statusLikes: StatusLikes) {
        userLikeRepository.save(UserLike(swipingUser = currentUser, swipedUser = user, statusLikes = statusLikes))
    }

    fun prepareDataAndGenerateUserLike(username: String, swipedUserId: Int, statusLikes: StatusLikes) {
        val loadCurrentUser = userRepository.findByUsername(username)
        val loadSwipedUser = userRepository.findByUserId(swipedUserId)
        generateUserLike(loadCurrentUser!!, loadSwipedUser!!, statusLikes)
    }

    fun handleMatch(currentUser: UserDto, swipedUser: UserDto) {
        userLikeRepository.findMatchByUsers(userService.convertDtoToEntity(currentUser, true)!!, userService.convertDtoToEntity(swipedUser, true)!!, StatusLikes.LIKED) ?: return
        generateUserLike(userService.convertDtoToEntity(currentUser)!!, userService.convertDtoToEntity(swipedUser)!!, StatusLikes.MATCHED)
    }
}