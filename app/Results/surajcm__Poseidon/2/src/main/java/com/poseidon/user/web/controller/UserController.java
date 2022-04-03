package com.poseidon.user.web.controller;
 import com.poseidon.user.domain.UserVO;
import com.poseidon.user.service.SecurityService;
import com.poseidon.user.service.UserService;
import com.poseidon.user.web.form.UserForm;
import com.poseidon.init.util.CommonUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.poseidon.user.web.form.Role.populateRoles;
@Controller
@SuppressWarnings("unused")
public class UserController {

 private  Logger logger;

 private  String USER_FORM;

 private  String USER_LOG_IN;

 private  String DANGER;

 private  String SUCCESS;

 private  String USER_LIST;

 private  String UNKNOWN_ERROR;

 private  String DB_ERROR;

 private  String EXCEPTION_IN_CONTROLLER;

 private  UserService userService;

 private  SecurityService securityService;

public UserController(final UserService userService, final SecurityService securityService) {
    this.userService = userService;
    this.securityService = securityService;
}
public UserVO populateUserVO(String selectName,String selectLogin,String selectRole){
    var ajaxUserVo = new UserVO();
    ajaxUserVo.setName(selectName);
    ajaxUserVo.setEmail(selectLogin);
    ajaxUserVo.setRole(selectRole);
    ajaxUserVo.setPassword("password");
    return ajaxUserVo;
}


@PostMapping("/user/passwordExpire.htm")
@ResponseBody
public Boolean passwordExpiry(String id,BindingResult result){
    userService.expireUser(Long.valueOf(id));
    return Boolean.TRUE;
}


public Map<String,String> populateUserEditMap(UserVO userVO){
    Map<String, String> userEditMap = new HashMap<>();
    userEditMap.put("id", String.valueOf(userVO.getId()));
    userEditMap.put("name", userVO.getName());
    userEditMap.put("email", userVO.getEmail());
    userEditMap.put("role", userVO.getRole());
    return userEditMap;
}


public String allUsers(){
    List<UserVO> userList = userService.getAllUserDetails();
    // todo: return a map instead
    userList.forEach(u -> u.setPassword(""));
    return fetchJsonUserList(userList);
}


@PutMapping("/user/updateUserAjax.htm")
@ResponseBody
public String updateUserAjax(String id,String name,String email,String role,BindingResult result){
    var sanitizedId = CommonUtils.sanitizedString(id);
    var sanitizedName = CommonUtils.sanitizedString(name);
    var sanitizedEmail = CommonUtils.sanitizedString(email);
    var sanitizedRole = CommonUtils.sanitizedString(role);
    logger.info("updateUserAjax method of user controller with id {}, name {}, email {}, role {}", sanitizedId, sanitizedName, sanitizedEmail, sanitizedRole);
    try {
        var userVO = userService.getUserDetailsFromId(Long.valueOf(id));
        if (userVO.isPresent()) {
            userVO.get().setName(name);
            userVO.get().setEmail(email);
            userVO.get().setRole(role);
            userService.updateUser(userVO.get(), currentLoggedInUser());
        }
    } catch (Exception ex) {
        logger.error(ex.getLocalizedMessage(), ex);
    }
    return allUsers();
}


@GetMapping("/user/getForEdit.htm")
@ResponseBody
public String getForEdit(String id,BindingResult result){
    var sanitizedId = CommonUtils.sanitizedString(id);
    logger.info("getForEdit method of user controller : {}", sanitizedId);
    String response = null;
    var userVO = userService.getUserDetailsFromId(Long.valueOf(id));
    if (userVO.isPresent()) {
        var userEditMap = populateUserEditMap(userVO.get());
        response = parseUserVO(userEditMap);
    }
    return response;
}


@GetMapping("/")
public ModelAndView index(){
    logger.info("Index method of user controller ");
    var userForm = new UserForm();
    userForm.setUser(new UserVO());
    return new ModelAndView("MainPage", USER_FORM, userForm);
}


@PostMapping("/user/ToHome.htm")
public ModelAndView toHome(UserForm userForm){
    logger.info(" Inside ToHome method of user controller ");
    userForm.setLoggedInUser(userForm.getLoggedInUser());
    userForm.setLoggedInRole(userForm.getLoggedInRole());
    return new ModelAndView("MainPage", USER_FORM, userForm);
}


@PostMapping("/user/changePasswordAndSaveIt.htm")
@ResponseBody
public String changePass(String current,String newPass,BindingResult result){
    var sanitizedCurrent = CommonUtils.sanitizedString(current);
    var sanitizedPass = CommonUtils.sanitizedString(newPass);
    logger.info("ChangePass of user controller from {} to {}", sanitizedCurrent, sanitizedPass);
    String message;
    var auth = SecurityContextHolder.getContext().getAuthentication();
    // get the user info
    var userList = userService.searchUserDetails(formSearch(auth.getName()), true, true);
    if (userService.comparePasswords(current, userList.get(0).getPassword())) {
        var userVO = userList.get(0);
        userService.updateWithNewPassword(userVO, newPass, currentLoggedInUser());
        message = messageJSON(SUCCESS, "The password has been reset !!");
    } else {
        message = messageJSON("message", "The password didnt match with the one already saved !!");
    }
    return message;
}


@GetMapping("/login")
public String login(Model model,String error,String logout){
    if (error != null) {
        model.addAttribute(DANGER, "Your username and password is invalid.");
    }
    if (logout != null) {
        model.addAttribute("message", "You have been logged out successfully.");
    }
    return USER_LOG_IN;
}


@PostMapping("/user/LogMeOut.htm")
public ModelAndView logMeOut(HttpServletRequest request){
    logger.info(" Inside LogMeOut method of user controller ");
    var session = request.getSession(false);
    SecurityContextHolder.clearContext();
    if (session != null) {
        session.invalidate();
    }
    return new ModelAndView(USER_LOG_IN, USER_FORM, new UserForm());
}


@PostMapping("/user/SearchUser.htm")
public ModelAndView searchUser(UserForm userForm){
    logger.info(" Inside SearchUser method of user controller ");
    List<UserVO> userList = userService.searchUserDetails(userForm.getSearchUser(), userForm.isStartsWith(), userForm.isIncludes());
    if (!userList.isEmpty()) {
        userForm.setStatusMessage("Successfully fetched " + userList.size() + " Users");
        userForm.setStatusMessageType("info");
        userList.stream().map(UserVO::toString).forEach(logger::info);
    } else {
        userForm.setStatusMessage("No results found");
        userForm.setStatusMessageType(DANGER);
    }
    userForm.setUserVOs(userList);
    userForm.setRoleList(populateRoles());
    return new ModelAndView(USER_LIST, USER_FORM, userForm);
}


@PostMapping("/user/saveUserAjax.htm")
@ResponseBody
public String saveUserAjax(String selectName,String selectLogin,String selectRole,BindingResult result){
    logger.info("SaveUserAjax method of user controller ");
    UserVO ajaxUserVo = populateUserVO(selectName, selectLogin, selectRole);
    userService.save(ajaxUserVo, currentLoggedInUser());
    logger.info("Successfully saved user");
    return allUsers();
}


public UserVO formSearch(String name){
    var userVO = new UserVO();
    userVO.setName(name);
    return userVO;
}


@PostMapping("/user/DeleteUser.htm")
public ModelAndView deleteUser(UserForm userForm){
    logger.info(" Inside DeleteUser method of user controller ");
    try {
        userService.deleteUser(userForm.getId());
        userForm.setStatusMessage("Successfully deleted the user");
        userForm.setStatusMessageType(SUCCESS);
    } catch (Exception ex) {
        userForm.setStatusMessage("Error occurred during deletion");
        userForm.setStatusMessageType(DANGER);
        logger.error(ex.getLocalizedMessage(), ex);
    }
    return listAll(userForm);
}


@PostMapping("/user/PasswordReset.htm")
public ModelAndView reset(UserForm userForm){
    logger.info("Password reset view of user controller");
    return new ModelAndView("user/PasswordReset", USER_FORM, userForm);
}


public String fetchJsonUserList(List<UserVO> userList){
    String response;
    ObjectMapper mapper = new ObjectMapper();
    try {
        response = mapper.writeValueAsString(userList);
    } catch (IOException ex) {
        response = DANGER;
        logger.error("error parsing to json : {}", ex.getMessage());
    }
    logger.info("user list json : {}", response);
    return response;
}


@PostMapping("/user/ListAll.htm")
public ModelAndView listAll(UserForm userForm){
    logger.info("ListAll method of user controller ");
    List<UserVO> userList = userService.getAllUserDetails();
    if (userList.isEmpty()) {
        userForm.setStatusMessage("No user found");
        userForm.setStatusMessageType(DANGER);
    }
    userForm.setUserVOs(userList);
    userForm.setRoleList(populateRoles());
    return new ModelAndView(USER_LIST, USER_FORM, userForm);
}


public String currentLoggedInUser(){
    String username = "";
    var auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
        username = auth.getName();
    }
    return username;
}


public String parseUserVO(Map<String,String> userEditMap){
    String response;
    ObjectMapper mapper = new ObjectMapper();
    try {
        response = mapper.writeValueAsString(userEditMap);
    } catch (IOException ex) {
        response = DANGER;
        logger.error("Error parsing to json : {}", ex.getMessage());
    }
    logger.info("User list json : {}", response);
    return response;
}


public String messageJSON(String type,String message){
    String response;
    Map<String, String> messageMap = Map.of(type, message);
    ObjectMapper mapper = new ObjectMapper();
    try {
        response = mapper.writeValueAsString(messageMap);
    } catch (IOException ex) {
        response = DANGER;
        logger.error("Error parsing to json : {}", ex.getMessage());
    }
    return response;
}


}