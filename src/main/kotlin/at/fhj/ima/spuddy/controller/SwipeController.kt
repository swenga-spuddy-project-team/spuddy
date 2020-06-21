package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.entity.*
import at.fhj.ima.spuddy.repository.UserRepository
import at.fhj.ima.spuddy.service.DistrictService
import at.fhj.ima.spuddy.service.SportService
import at.fhj.ima.spuddy.service.SwipeService
import at.fhj.ima.spuddy.service.UserService
import org.springframework.data.repository.query.Param
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam


@Controller
class SwipeController (val userService: UserService,
                       val districtService: DistrictService,
                       val sportService: SportService,
                       val swipeService: SwipeService
){

    @RequestMapping("/swipe", method = [RequestMethod.GET])
    fun swipe(model: Model): String {
        val currentUser = model.getAttribute("currentUser") as UserDto
        setModelData (currentUser,model)
        return "swipe"
    }

    private fun setModelData(currentUser: UserDto, model: Model){
        val district = districtService.findByDistrictName(currentUser.district.orEmpty())
        model.addAttribute("nextUser", userService.convertEntityToDto(swipeService.getNextUser(district!!, emptySet(),currentUser.username)))
    }
    //wenn keine User in Datenbank, swipeService raus in eigene val
    //überprüfen ob leer
    //wenn leer fehlermeldung
    //sonst normal weiter

    @RequestMapping("/swipeLike", method = [RequestMethod.GET])
    fun swipeLike(model: Model, userLikeId: Int): String {
        val currentUser = model.getAttribute("currentUser") as UserDto
        model.set(
            "like",
            swipeService.prepareDataAndGenerateUserLike(currentUser.username, userLikeId, StatusLikes.LIKED)
        )
        setModelData(currentUser, model)
        return "swipe"
    }

    @RequestMapping("/swipeDislike", method = [RequestMethod.GET])
    fun swipeDislike(model: Model, userDislikeId: Int): String {
        val currentUser = model.getAttribute("currentUser") as UserDto
        model.set(
            "dislike",
            swipeService.prepareDataAndGenerateUserLike(currentUser.username, userDislikeId, StatusLikes.DISLIKED)
        )
        setModelData(currentUser, model)
        return "swipe"
    }
/*
    fun allUsersStatusSet(user: User) :List<User>{
        val createLikedList = createBlockedList()
        val createDislikedList = createBlockedList()
        val createBlockedList = createBlockedList()
    }

    @RequestMapping("/swipeAction", method = [RequestMethod.POST])
    fun swipeAction(model: Model, user: User, userLike: UserLike): String {
        val prefilteredList = findByUsername.filter {sharedDistrictAndSports(@Param "district", @Param "sport")}
        val noStatus = prefilteredList.reduce {allUsersStatusSet()}
        model.set()
        return "swipeAction"
    }*/
}
