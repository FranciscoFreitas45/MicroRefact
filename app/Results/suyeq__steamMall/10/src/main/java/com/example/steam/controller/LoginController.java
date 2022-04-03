package com.example.steam.controller;
 import com.alibaba.fastjson.JSON;
import com.example.steam.entity.User;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.mq.MQProducer;
import com.example.steam.redis.RedisService;
import com.example.steam.service.ImageService;
import com.example.steam.service.UserService;
import com.example.steam.utils.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.MQProducer;
@Controller
public class LoginController {

 private  String COOKIE_ID;

@Value("${server.servlet.session.cookie.max-age}")
 private int cookieMaxAge;

@Autowired
 private UserService userService;

@Autowired
 private RedisService redisService;

@Autowired
 private ImageService imageService;

@Autowired
 private MQProducer mqProducer;

 private Logger log;


@ResponseBody
@RequestMapping("/userVerification")
public String userLogin(String email,String password,HttpServletResponse response,HttpServletRequest request){
    return JSON.toJSONString(userService.handleLogin(email, password, request, response));
}


@ResponseBody
@RequestMapping("/verificationCode")
public String sendVerificationCode(String email){
    log.info(email);
    mqProducer.productEvent(new Event(EventType.SEND_EMAIL_VERIFICATION_CODE).setEtrMsg(Event.EMAIL, email));
    return JSON.toJSONString(ResultMsg.SUCCESS);
}


@ResponseBody
@RequestMapping("/logout")
public String logout(String email){
    return JSON.toJSONString(ResultMsg.SUCCESS(userService.handleLogout(email)));
}


@ResponseBody
@RequestMapping("/findpassword")
public String findPassword(String email){
    return JSON.toJSONString(userService.updateFindPassword(email));
}


@ResponseBody
@RequestMapping("/user/logoutAdmin/{email}")
public String adminLogout(String email){
    log.info(email);
    return JSON.toJSONString(userService.handleLogoutAdmin(email));
}


@ResponseBody
@RequestMapping("/registerVerification")
public String userRegister(String email,String password,String code){
    log.info("email:" + email + "," + "pass:" + password + "," + "code:" + code);
    return JSON.toJSONString(userService.handleRegister(email, password, code));
}


@ResponseBody
@RequestMapping("/admin/userVerification")
public String adminLogin(String email,String password,HttpServletRequest request){
    log.info(email + " " + password);
    return JSON.toJSONString(userService.handleAdminLogin(email, password, request));
}


}