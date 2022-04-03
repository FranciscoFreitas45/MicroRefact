package com.softserve.edu.Resources.controller;
 import com.softserve.edu.Resources.service.PrivilegeService;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;
import com.softserve.edu.Resources.Interface.RoleService;
import com.softserve.edu.Resources.Interface.PrivilegeService;
@Controller
@Transactional
public class MainController {

@Autowired
 private UserService userService;

@Autowired
 private RoleService roleService;

@Autowired
 private PrivilegeService privilegeService;


@RequestMapping(value = { "/privileges" }, method = RequestMethod.GET)
public ModelAndView privilegesPage(){
    ModelAndView model = new ModelAndView("administration/privileges");
    model.addObject("list", privilegeService.getAllPrivileges());
    return model;
}


@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
public String welcomePage(Model model,HttpServletRequest request){
    System.out.println("Checking if ADMIN");
    if (request.isUserInRole("ROLE_ADMIN")) {
        System.out.println("Yes I am ADMIN");
    }
    System.out.println("Your IP is " + request.getRemoteAddr());
    model.addAttribute("title", "Resources");
    model.addAttribute("message", "Welcome to Resources");
    return "welcome";
}


@RequestMapping(value = "/lookup", method = RequestMethod.GET)
public String lookupPage(Model model){
    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    String username = loggedInUser.getName();
    model.addAttribute("currentUser", username);
    model.addAttribute("title", "Look up resources");
    return "lookup";
}


@RequestMapping(value = "/privilegetest", method = RequestMethod.GET)
public String privilegetest(){
    return "administration/privileges2";
}


@RequestMapping(value = { "/about" }, method = RequestMethod.GET)
public String aboutPage(Model model){
    model.addAttribute("title", "About");
    return "about";
}


@RequestMapping(value = "/resources", method = RequestMethod.GET)
public String resourcesPage(Model model,HttpServletRequest request){
    if (request.isUserInRole("ROLE_RESOURCE_ADMIN")) {
        return "redirect:/resources/viewTypes";
    }
    if (request.isUserInRole("ROLE_REGISTRATOR")) {
        return "redirect:/resources/registration";
    }
    return "login";
}


@RequestMapping(value = "/403", method = RequestMethod.GET)
public String accessDenied(Model model,Principal principal){
    if (principal != null) {
        model.addAttribute("message", "Hi " + principal.getName() + "<br> You do not have permission to access this page!");
    } else {
        model.addAttribute("msg", "You do not have permission to access this page!");
    }
    return "403";
}


@RequestMapping(value = "/login", method = RequestMethod.GET)
public String loginPage(Model model){
    return "login";
}


@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
public String logoutSuccessfulPage(Model model){
    model.addAttribute("title", "Logout");
    return "logoutSuccessful";
}


@RequestMapping(value = "/addRole", method = RequestMethod.GET)
public ModelAndView addRole(){
    ModelAndView model = new ModelAndView("administration/roleAdd");
    model.addObject("list", privilegeService.getAllPrivileges());
    return model;
}


}