package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.dto.UserDto
import at.fhj.ima.spuddy.entity.Message
import at.fhj.ima.spuddy.entity.Sport
import at.fhj.ima.spuddy.entity.User
import at.fhj.ima.spuddy.repository.MessageRepository
import at.fhj.ima.spuddy.repository.SportRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class MessageService (val MessageRepository: MessageRepository,
                        val UserService: UserService){

    fun findMessagesOfSender(sender: UserDto, receiver: UserDto) : List<Message>{
        return MessageRepository.createMsgList( UserService.convertDtoToEntity(sender)!!, UserService.convertDtoToEntity(receiver)!!).sortedBy { it.timestamp }
    }


    fun findContentById(id: Int): String {
        return MessageRepository.findByMessageId(id).content.orEmpty()
    }

    fun findById(id:Int): Message {
        return MessageRepository.findByMessageId(id)
    }

}

