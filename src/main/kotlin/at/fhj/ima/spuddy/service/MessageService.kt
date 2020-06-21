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
class MessageService (val MessageRepository: MessageRepository){

    fun findMessagesOfSender(sender: User, receiver: User) : List<Message>{
        return MessageRepository.createMsgList(sender, receiver)
    }


    fun findContentById(id: Int): String {
        return MessageRepository.findByMessageId(id).content.orEmpty()
    }

    fun findById(id:Int): Message {
        return MessageRepository.findByMessageId(id)
    }




}