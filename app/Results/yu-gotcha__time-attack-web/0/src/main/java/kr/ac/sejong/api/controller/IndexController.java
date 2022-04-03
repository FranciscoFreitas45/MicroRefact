package kr.ac.sejong.api.controller;
 import kr.ac.sejong.api.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
@Controller
@RequestMapping(value = "")
public class IndexController {


@GetMapping(value = "index")
public String index(HttpSession session){
    User user = (User) session.getAttribute("userdata");
    return "index";
}


}