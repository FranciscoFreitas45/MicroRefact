package io.delivery.controller;
 import io.delivery.entity.User;
import io.delivery.model.BackupCreator;
import io.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation;
import java.util.List;
@Controller
@RequestMapping("/admin")
public class AdminController {

@Autowired
 private  BackupCreator backupCreator;

 final  UserService userService;


@RequestMapping(value = "/users")
public String getUsersInfo(){
    return "/admin/users";
}


@RequestMapping(value = "/backup")
public String backup(Model model){
    model.addAttribute("status", backupCreator.createBackup());
    return "create";
}


@RequestMapping(value = "/users/get/id/{id}", method = RequestMethod.GET)
@ResponseBody
public User getUserById(String InputID){
    return userService.findById(Long.parseLong(InputID));
}


@RequestMapping(value = "/adduser", method = RequestMethod.GET)
public String getUserForm(Model model){
    model.addAttribute("id", "-1");
    return "/admin/user";
}


@RequestMapping(value = "/users/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@ResponseBody
public User addUser(User user){
    user.EncodePassword();
    return userService.create(user);
}


@RequestMapping(value = "/users/delete/{id}", method = RequestMethod.DELETE)
@ResponseBody
public User deleteUser(String InputID){
    return userService.deleteUser(Long.parseLong(InputID));
}


@RequestMapping(value = "/users/get/login/{login}", method = RequestMethod.GET)
@ResponseBody
public List<User> getUserByLogin(String InputLogin){
    return userService.findByLogin(InputLogin);
}


@RequestMapping(value = "/users/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
@ResponseBody
public User updateUser(User user){
    return userService.updateUser(user);
}


@RequestMapping(value = "/users/get/all", method = RequestMethod.GET)
@ResponseBody
public List<User> getUserList(){
    return userService.getUserList();
}


@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
public String getUserFormUpdate(String id,Model model){
    model.addAttribute("id", id);
    return "/admin/user";
}


}