package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.entity.*
import at.fhj.ima.spuddy.repository.MessageRepository
import at.fhj.ima.spuddy.repository.SportRepository
import at.fhj.ima.spuddy.repository.UserLikeRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service



@Service
class MessageService (val MessageRepository: MessageRepository,
                        val UserService: UserService,
                        val userLikeRepository: UserLikeRepository){

    fun findMessagesOfSender(sender: UserDto, receiver: UserDto) : List<Message>{
        return MessageRepository.createMsgList( UserService.convertDtoToEntity(sender, true)!!, UserService.convertDtoToEntity(receiver, true)!!).sortedBy { it.timestamp }
    }


    fun findContentById(id: Int): String {
        return MessageRepository.findByMessageId(id).content.orEmpty()
    }

    fun findById(id:Int): Message {
        return MessageRepository.findByMessageId(id)
    }

    fun getMatchesList(sender: UserDto) : List<User>
    {
        var list1 = userLikeRepository.findUserMatches(UserService.convertDtoToEntity(sender, true)!!, StatusLikes.MATCHED)
        val swipinglist = list1.map { it.swipingUser}
        val swipedlist = list1.map {it.swipedUser}
        val joinedList = swipedlist + swipinglist
        val cleanSwipinglist = joinedList.filter { it != UserService.convertDtoToEntity(sender, true) }
        return cleanSwipinglist
    }
   // messageService.getMatchesList(userService.findByUsername(name)!!)

}

