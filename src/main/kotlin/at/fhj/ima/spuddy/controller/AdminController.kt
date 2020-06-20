package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.entity.District
import at.fhj.ima.spuddy.entity.Sport
import at.fhj.ima.spuddy.service.*
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Controller
class AdminController (val adminService: AdminService,
                       val userService: UserService,
                       val sportService: SportService,
                       val districtService: DistrictService,
                       val fileService: FileService
) {
    // Controller für alle administrativen Funktionen

    // Simple Request mapping that will probably be later expanded to include the administrative functions
    @Secured("ROLE_ADMIN")
    @RequestMapping("/admin", method = [RequestMethod.GET])
    fun admin(model: Model): String {
        return "admin"
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/generateTestData", method = [RequestMethod.GET])
    fun generateTestData(model: Model): String{
        try{
            adminService.generateTestData()
        }
        catch (dive: DataIntegrityViolationException){
            model.set("errorMessage", "Test data already in place")
            return "admin"
        }
        return "admin"
    }

    // User bezogene Mappings

    @Secured("ROLE_ADMIN")
    @RequestMapping("/adminListUsers", method = [RequestMethod.GET])
    fun adminListUsers(model: Model) : String {
        model.set("userdtos", userService.findAll())

        return "adminListUsers"
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/adminDeleteUser", method = [RequestMethod.POST])
    fun deleteUser(model: Model, @RequestParam id: Int): String {
        val username = userService.findById(id)
        userService.delete(id)
        model.set("message", "$username was deleted!")
        return "adminListUsers"
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("adminCreateUser", method = [RequestMethod.POST])
    fun adminCreateUser(model: Model): String{
        return "adminListUsers"
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("adminEditUser", method = [RequestMethod.GET])
    fun editUser(model: Model, @RequestParam(required = false) id:Int?): String {
        // Wird eine ID mitgegeben dann hol den passenden User aus der DB
        // Wird mit der ID nichts gefunden erstelle einen neuen User
        if (id != null){
            val userdto = userService.findById(id)!!
            if (userdto != null){
                model.set("userdto", userdto)
            }
            else {
                model.set("userdto", userService.createNewUser())
            }
        }
        else {
            model.set("userdto", userService.createNewUser())
        }
        return "adminEditSport"
    }

    // District bezogene Mappings

    @Secured("ROLE_ADMIN")
    @RequestMapping("/adminListDistricts", method = [RequestMethod.GET])
    fun adminListDistricts(model: Model) : String {
        model.set("districts", districtService.findAll())
        return "adminListDistricts"
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/importDistrictData", method = [RequestMethod.POST])
    fun importDistrictData(model: Model, @RequestParam("file") file: MultipartFile):String{

        if (!file.isEmpty) {
            try {
                val newSportList = fileService.importDistrictJson(file)
                newSportList.map { districtService.save(it) }
            } catch (ex: Exception) {
                model.set("errorMessage", "Error importing file! ")
            }
        }
        else {
            model.set("errorMessage", "Please provide a file for upload")
        }
        return "adminListDistricts"
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping("/adminEditDistrict", method = [RequestMethod.GET])
    fun editDistrict(model: Model, @RequestParam(required = false) districtId:Int?): String {
        if (districtId != null){
            model.set("district", districtService.findById(districtId))
        }
        else {
            model.set("district", districtService.createNewDistrict(""))
        }
        return "adminEditDistrict"
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/adminChangeDistrict", method = [RequestMethod.POST])
    fun changeDistrict(@ModelAttribute("district") @Valid district: District, bindingResult: BindingResult, model: Model): String {
        try {
            districtService.save(district)
        }
        catch (dive: DataIntegrityViolationException){
            when {
                (dive.message.orEmpty().contains("districtNameEmpty")) -> {
                bindingResult.rejectValue("districtName", "districtNameEmpty", "Please enter a district name")
                return "adminEditDistrict"
            }
                (dive.message.orEmpty().contains("districtAlreadyExists")) -> {
                    bindingResult.rejectValue("districtName", "districtAlreadyExists", "A district with this name already exists")
                    return "adminEditDistrict"
                }
            else -> throw dive
            }
        }
        return "redirect:adminListDistricts"
    }


    // Sport bezogene Mappings
    // Todo: Add JSON import functionality
    @Secured("ROLE_ADMIN")
    @RequestMapping("/adminListSports", method = [RequestMethod.GET])
    fun adminListSports(model: Model) : String {
        model.set("sports", sportService.findAll())
        return "adminListSports"
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/importSportData", method = [RequestMethod.POST])
    fun importSportData(model: Model, @RequestParam("file") file: MultipartFile):String{

        if (!file.isEmpty) {
            try {
                val newSportList = fileService.importSportJson(file)
                newSportList.map { sportService.save(it) }
            } catch (ex: Exception) {
                model.set("errorMessage", "Error importing file! ")
            }
        }
        else {
            model.set("errorMessage", "Please provide a file for upload")
        }
        return "adminListSports"
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/adminDeleteSport", method = [RequestMethod.POST])
    fun deleteSport(model: Model, @RequestParam sportId: Int): String {
        val name = sportService.findNameById(sportId)
        sportService.delete(sportId)
        model.set("message", "$name was deleted!")
        return "adminListSports"
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("adminEditSport", method = [RequestMethod.GET])
    fun editSport(model: Model, @RequestParam(required = false) sportId:Int?): String {
        if (sportId != null){
            model.set("sport", sportService.findById(sportId))
        }
        else {
            model.set("sport", sportService.createNewSport())
        }
        return "adminEditSport"
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/adminChangeSport", method = [RequestMethod.POST])
    fun changeSport(@ModelAttribute("sport") @Valid sport: Sport, bindingResult: BindingResult, model: Model): String {
        try {
            sportService.save(sport)
        }
        catch (dive: DataIntegrityViolationException){
            if(dive.message.orEmpty().contains("nameEmpty")) {
                bindingResult.rejectValue("name", "namenEmpty", "Please enter a name")
                return "adminEditSport"
            }
            else {
                throw dive
            }
        }
        return "redirect:adminListSports"
    }

    @ExceptionHandler(Exception::class)
    fun handleError(req: HttpServletRequest, ex: Exception): ModelAndView {
        val mav = ModelAndView()
        mav.addObject("exception", ex)
        mav.addObject("url", req.requestURL)
        mav.viewName = "error"
        return mav
    }
}