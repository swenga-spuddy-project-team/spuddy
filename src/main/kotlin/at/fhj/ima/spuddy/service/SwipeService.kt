package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.entity.*
import at.fhj.ima.spuddy.repository.UserLikeRepository
import at.fhj.ima.spuddy.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class SwipeService (val userRepository: UserRepository,
                    val userLikeRepository: UserLikeRepository
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

    fun prepareDataAndGenerateUserLike (username: String, swipedUserId: Int, statusLikes: StatusLikes){
        val loadCurrentUser = userRepository.findByUsername(username)
        val loadSwipedUser = userRepository.findByUserId(swipedUserId)
        generateUserLike(loadCurrentUser!!, loadSwipedUser!!, statusLikes)
    }

    fun isMatch(currentUser: User, swipedUser: User){
        val findUserLikes = userLikeRepository.findLikesBySwipedUser(swipedUser)
    }
}

/*
    fun generateUserLikes(user: User, StatusLikes) {
        userLikesTupleList.forEach {
            val (swipingUser, swipedUser, statusLikes) = it
            userLikeRepository.save(
                UserLike(
                    swipingUser = swipingUser,
                    swipedUser = swipedUser,
                    statusLikes = statusLikes
                )
            )
        }
    }


    //save ruft UserLikeRepository auf
    @Transactional
    fun saveUserLike(userlike: UserLike) {
        val userLike = UserLike(swipingUser =, swipedUser =, statusLikes =)
        userLikeRepository.save(userLike)

    }


              val swipingUser = userService.findByUsername(username)
              val swipedUser =
              val statusLikes =
                  userLikeRepository.save(UserLike(swipingUser = swipingUser, swipedUser = swipedUser, statusLikes = statusLikes))}
          //überprüft Likes zwischen UserA und UserB
          //wenn Like vom anderen User schon da, dann Match
          //dh. noch einen Datenbankeintrag erstellen


      }


          fun getNextUser (username:String, district:District, sports: Set<Sport>,swipingUser:String): User {
              val sharedDistrictAndSportsUsers = userRepository.sharedDistrictAndSports(district, sports).Select(item => user.username).ToList()
              val listOfUsernamesLikes: List<UserLike> = userLikeRepository.findLikesBySwipingUser(swipingUser).Select(item => userLike.swiping.id).ToList()
              val nextUserList = sharedDistrictAndSportsUsers.filterNotIn(listOfUsernamesLikes)
              sharedDistrictAndSportUsers.reduce it.filterNotIn
              nextUserList.random()
          }

          fun <T> Collection<T>.filterNotIn(collection: Collection<T>): Collection<T> {
              val set = collection.toSet()
              return filterNot { set.contains(it) }
          }

      username:String, district:District, sports: Set<Sport>,swipingUser:String,swipedUser: String

          fun getNextUser (username:String, district:District, sports: Set<Sport>,swipingUser:String,swipedUser: String): User {
              val sharedDistrictAndSports = userRepository.sharedDistrictAndSports(district, sports)
              val user = userRepository.findByUsername(username)
              val swiping = userLikeRepository.findLikesBySwipingUser(swipingUser)
              val swiped = userLikeRepository.findLikesBySwipedUser(swipedUser)
              // val list = map {sharedDistrictAndSports.username}
              //val listOfUsernames = sharedDistrictAndSports.Select(item => user.username).ToList();
              val listOfUsernames1: sharedDistrictAndSports.Select(item => user.username).ToList()
              val listOfUsernames2: List<Any> =

                  listOfUsernames1.filterNotIn(listOfUsernames2)


                //  if (user = swiping)
                //  fun sharedDistrictAndSports.remove(User)
               //   if (user = swiped)
              //    fun sharedDistrictAndSports.remove(User)


              //sharedDistrictAndSports.filter { swiping = user }
             // if (user = swiping)
             //     User.remove(user)


        // Liste an User die alle sharedDisrtictAndSports haben
        // dort hol ich mir Username raus
        // und schau nach ob er mit einem swiping User
        // oder einem swiped user
        // aus der Like Tabelle übereinstimmt
        // wenn ja, remove User aus sharedDistrictAndSports
        // return neue FUnktion


    fun getNextUser (): User {
        val sharedDistrictAndSports = userRepository.sharedDistrictAndSports(District, Sport)
        val createListOfAllStatusEntries = userLikeRepository.createListOfAllStatusEntries()
        // nimm die Liste an User die im gleichen District sind und die gleichen Sportarten wie du machen
        // = sharedDistrictAndSports
        // Wende die Funktion createListOfAllStatusEntries auf diese Liste an schau in der UserLikeRepository nach
        //nehme nur jene User, deren Username NICHT in der createListOfAllStatusEntries vorkommen
        //val UserLikeRepository.createListOfAllStatusEntries
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


    //sharedDistrictAndSports
    //createListOfAllStatusEntries

 */