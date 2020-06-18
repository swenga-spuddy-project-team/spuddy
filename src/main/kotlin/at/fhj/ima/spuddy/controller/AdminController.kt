package at.fhj.ima.spuddy.controller

import at.fhj.ima.spuddy.service.AdminService
import at.fhj.ima.spuddy.service.UserService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@Controller
class AdminController (val adminService: AdminService,
                       val userService: UserService
) {

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

    @ExceptionHandler(Exception::class)
    fun handleError(req: HttpServletRequest, ex: Exception): ModelAndView {
        val mav = ModelAndView()
        mav.addObject("exception", ex)
        mav.addObject("url", req.requestURL)
        mav.viewName = "error"
        return mav
    }
}