package simple.java.spring.example.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import simple.java.spring.example.entity.UserProfile;
import simple.java.spring.example.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public ModelAndView login() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        UserProfile userProfile = new UserProfile();
        modelAndView.addObject("userProfile", userProfile);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid UserProfile userProfile, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        UserProfile userProfileExists = userService.findByEmail(userProfile.getEmail());
        if (userProfileExists != null) {
            bindingResult.rejectValue("email", "error.userProfile",
                    "There is already a userProfile registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.save(userProfile);
            modelAndView.addObject("successMessage", "UserProfile has been registered successfully");
            modelAndView.addObject("userProfile", new UserProfile());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        UserProfile userProfile = userService.findByEmail(auth.getName());
        modelAndView.addObject("userName",
                "Welcome " + userProfile.getFirstName() + " " + userProfile.getLastName() + " (" + userProfile.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin");
        return modelAndView;
    }
}