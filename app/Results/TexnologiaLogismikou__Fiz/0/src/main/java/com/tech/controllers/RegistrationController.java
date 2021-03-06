package com.tech.controllers;
 import com.tech.configurations.tools.AvailableRoles;
import com.tech.configurations.tools.Host;
import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.controllers.superclass.BaseController;
import com.tech.models.dtos.user.RegisteredUserDTO;
import com.tech.models.entities.user.User;
import com.tech.models.entities.user.UserInfo;
import com.tech.models.entities.user.UserRole;
import com.tech.services.interfaces.IUserInfoService;
import com.tech.services.interfaces.IUserRoleService;
import com.tech.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import com.tech.DTO.RegisteredUserDTO;
@CrossOrigin(origins = Host.apache)
@RestController
@RequestMapping("/register")
public class RegistrationController extends BaseController{

@Autowired
 private IUserService service;

@Autowired
 private IUserInfoService infoService;

@Autowired
 private IUserRoleService roleService;


@RequestMapping(method = RequestMethod.POST)
public HttpEntity<String> register(RegisteredUserDTO userDTO){
    Pair<Boolean, ResponseEntity> response = userDTO.validate();
    if (!response.getLeft()) {
        System.out.println(userDTO.getLast_name());
        return response.getRight();
    }
    if (service.checkUsername(userDTO.getUsername())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.FOUND);
    }
    if (infoService.checkMail(userDTO.getEmail())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.FOUND);
    }
    User user = new User(service.getNextID(), userDTO);
    UserInfo userInfo = new UserInfo(user.getId(), userDTO);
    UserRole userRole = new UserRole(user.getId(), AvailableRoles.ROLE_USER.getData());
    service.addUser(user);
    infoService.addUserInfo(userInfo);
    roleService.addUserRole(userRole);
    return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
}


}