package com.weflors.controller;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.weflors.entity.UserEntity;
import com.weflors.service.UserDetailsServiceImpl;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/registration")
public class RegistrationController {

 private  UserDetailsServiceImpl userService;

@Autowired
public RegistrationController(UserDetailsServiceImpl userService) {
    this.userService = userService;
}
@PostMapping
public String addUser(UserEntity userForm,Model model){
    if (userService.findUserByLoginAndEmail(userForm).isPresent()) {
        model.addAttribute("usernameError", "Пользователь с таким Логин и E-mail уже существует");
        return "registration";
    } else {
        userService.saveUser(userForm);
        return "redirect:/login";
    }
/*
		if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
			model.addAttribute("passwordError", "Пароли не совпадают");
			return "registration";
		}
		if (!userService.saveUser(userForm)) {
			model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
			return "registration";
		}
		return "redirect:/login";//To do
*/
}


@GetMapping
public String registration(Model model){
    model.addAttribute("userForm", new UserEntity());
    return "registration";
}


@PostMapping(params = "backtologin")
public String backToLogin(HttpServletRequest request){
    return "redirect:/login";
}


}