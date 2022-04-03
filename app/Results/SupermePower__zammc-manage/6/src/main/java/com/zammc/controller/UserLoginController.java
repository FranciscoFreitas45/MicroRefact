package com.zammc.controller;
 import com.zammc.domain.user.AdminUserEntity;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.user.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/login")
@Slf4j
public class UserLoginController {

@Autowired
 private  AdminUserService adminUserService;


@RequestMapping(value = "/userLogin")
public Message userLogin(AdminUserEntity adminUserEntity,HttpServletRequest request){
    Message message = null;
    try {
        message = adminUserService.userLogin(adminUserEntity, request);
    } catch (Exception e) {
        log.error("UserLoginController userLogin userRequest -> {} Exception ", adminUserEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "登录失败");
    }
    return message;
}


}