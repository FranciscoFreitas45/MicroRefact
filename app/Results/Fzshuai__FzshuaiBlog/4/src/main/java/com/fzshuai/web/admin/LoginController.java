package com.fzshuai.web.admin;
 import com.fzshuai.po.User;
import com.fzshuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/admin")
public class LoginController {

@Autowired
 private  UserService userService;


@GetMapping("/logout")
public String logout(HttpSession session){
    session.removeAttribute("user");
    return "redirect:/admin";
}


@GetMapping
public String loginPage(){
    return "admin/login";
}


@PostMapping("/login")
public String login(String username,String password,HttpSession session,RedirectAttributes attributes){
    User user = userService.checkUser(username, password);
    if (user != null) {
        user.setPassword(null);
        session.setAttribute("user", user);
        return "admin/index";
    } else {
        attributes.addFlashAttribute("message", "用户名或密码错误！");
        return "redirect:/admin";
    }
}


}