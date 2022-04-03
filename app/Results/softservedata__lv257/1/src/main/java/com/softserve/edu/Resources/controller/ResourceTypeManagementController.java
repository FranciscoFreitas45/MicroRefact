package com.softserve.edu.Resources.controller;
 import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.service.PropertyService;
import com.softserve.edu.Resources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.stream.Collectors;
import com.softserve.edu.Resources.Interface.PropertyService;
@Controller
@Transactional
@RequestMapping("/resources")
public class ResourceTypeManagementController {

@Autowired
 private  PropertyService propertyService;

@Autowired
 private  UserService userService;

@Autowired
 private  Environment env;


@RequestMapping(value = "/editType", method = RequestMethod.GET)
public String editResourceType(long typeId,long requestId,Model model){
    model.addAttribute("typeId", typeId);
    model.addAttribute("requestId", requestId);
    return "editType";
}


@RequestMapping(value = "/cloneType", method = RequestMethod.GET)
public String cloneResourceType(long typeId,Model model){
    return editResourceType(-typeId, 0, model);
}


@RequestMapping(value = "/viewTypes", method = RequestMethod.GET)
public String viewsResourceTypes(Model model){
    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    String username = loggedInUser.getName();
    model.addAttribute("currentAdmin", username);
    List<String> administratorNames = userService.getUsersWithRoles("ROLE_RESOURCE_ADMIN").stream().map(User::getUsername).sorted().collect(Collectors.toList());
    model.addAttribute("administrators", administratorNames);
    return "viewTypes";
}


@RequestMapping(value = "/addType", method = RequestMethod.GET)
public String addResourceType(long requestId,Model model){
    return editResourceType(0, requestId, model);
}


}