package com.example.demo.controller;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.Dao.UserDAO;
import com.example.demo.entity.Users;
import com.example.demo.service.CookieService;
import com.example.demo.service.ParamService;
import com.example.demo.service.SessionService;
import com.example.demo.service.UserService;
import com.example.demo.utils.UploadUtils;
@Controller
@RequestMapping("")
public class LoginController {

@Autowired
 private CookieService cookieService;

@Autowired
 private ParamService paramService;

@Autowired
 private SessionService sessionService;

@Autowired
 private UserService userService;

@Autowired
 private UploadUtils uploadUtils;

@Autowired
 private UserDAO dao;


@GetMapping("/login")
public String getLogin(Model model){
    String us = cookieService.getcookie("username");
    String pw = cookieService.getcookie("password");
    model.addAttribute("password", pw);
    model.addAttribute("username", us);
    sessionService.remove("user");
    return "/login";
}


@RequestMapping("/create-users")
public String create(Users item,Model model,MultipartFile photo,String passwordcf,String password,String username){
    // 
    if (!passwordcf.equals(password)) {
        model.addAttribute("message", "mat khau khong khop");
        return "redirect:/sign-up";
    }
    String path = "";
    if (userService.checknull(username)) {
        String filename = photo.getOriginalFilename();
        uploadUtils.uploadUser(filename, photo);
        item.setPhoto(filename);
        dao.save(item);
        path = "redirect:/login";
    } else {
        model.addAttribute("message", "tai khoan da ton tai");
        path = "redirect:/sign-up";
    }
    return path;
}


@PostMapping("/login")
public String postLogin(Model model,String username,String password){
    String path = "";
    boolean rm = paramService.getBoolean("chkRemember", false);
    if (!rm) {
        cookieService.add("username", username, 10);
        cookieService.add("password", password, 10);
    } else {
        cookieService.add("username", username, 0);
        cookieService.add("password", password, 0);
    }
    if (userService.checkLogin(username, password)) {
        Users user = userService.findById(username).get();
        sessionService.set("user", user);
        sessionService.set("name", user.getFullname());
        if (user.isActivated()) {
            if (user.isAdmin()) {
                path = "redirect:admin/user/index";
            } else if (!user.isAdmin()) {
                path = "redirect:/home/";
            }
        } else {
            model.addAttribute("message", "tài khoản không hoạt động");
            path = "redirect:/login";
        }
    } else {
        model.addAttribute("message", "sai tài khoản hoặc mật khẩu");
        System.out.println("that bai");
        path = "/login";
    }
    return "true";
}


@RequestMapping("/sign-up")
public String signup(Users item){
    return "true";
}


}