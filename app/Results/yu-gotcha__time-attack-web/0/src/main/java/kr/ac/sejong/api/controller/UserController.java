package kr.ac.sejong.api.controller;
 import kr.ac.sejong.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
@Controller
@RequestMapping(value = "")
public class UserController {

 private  UserService userService;

@Autowired
UserController(UserService userService) {
    this.userService = userService;
}
@PostMapping(value = "/login")
public String loginProcess(String loginId,String loginPw,HttpSession session){
    System.out.println(loginId + loginPw);
    if (userService.findUser(loginId, loginPw)) {
        session.setAttribute("userdata", userService.getUser(loginId));
        System.out.println(session.getAttribute("userdata"));
        return "redirect:/index";
    }
    session.setAttribute("error", "login error");
    return "redirect:/login";
}


@PostMapping(value = "/join")
public String joinProcess(String loginId,String loginPw,String userName,HttpSession session){
    System.out.println("join 시작");
    String result = userService.userJoin(loginId, loginPw, userName);
    System.out.println(result);
    if (result.equals("success")) {
        // 이렇게 시용해도 되는건지 모르겠음
        if (session.getAttribute("error") != null)
            session.removeAttribute("error");
        return "redirect:/login";
    } else {
        session.setAttribute("error", result);
        return "redirect:/join";
    }
}


@GetMapping(value = "/logout")
public String logoutProcess(HttpSession session){
    session.removeAttribute("userdata");
    return "redirect:/";
}


@GetMapping(value = "join")
public String join(){
    return "join";
}


@GetMapping(value = "login")
public String login(){
    return "login";
}


@GetMapping(value = "/remove_login_error")
public String remove_login_error_Process(HttpSession session){
    session.removeAttribute("error");
    return "redirect:/login";
}


@GetMapping(value = "/remove_join_error")
public String remove_join_error_Process(HttpSession session){
    session.removeAttribute("error");
    return "redirect:/join";
}


}