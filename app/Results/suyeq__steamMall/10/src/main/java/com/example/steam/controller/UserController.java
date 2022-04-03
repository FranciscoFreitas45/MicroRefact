package com.example.steam.controller;
 import com.alibaba.fastjson.JSON;
import com.example.steam.service.UserService;
import com.example.steam.utils.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
@Controller
public class UserController {

 private Logger log;

@Autowired
 private UserService userService;


@ResponseBody
@RequestMapping("/user/edit/{email}")
public String handleEditUser(String email,HttpServletRequest request){
    return JSON.toJSONString(userService.handleEditUser(email, request));
}


@ResponseBody
@RequestMapping("/user/delete/{email}")
public String handleDeleteUser(String email){
    return JSON.toJSONString(userService.handleDeleteUser(email));
}


@ResponseBody
@RequestMapping("/user/edit")
public String updateUserInfo(String email,String nickName,String introduction,String country,String province,String avatar,String avatarAddress){
    if (avatar.equals("")) {
        return JSON.toJSONString(ResultMsg.SUCCESS(userService.updateNickNameAndCountryAndProvinceAndAvatarAndIntroduction(email, nickName, country, province, 0, null, introduction)));
    }
    return JSON.toJSONString(ResultMsg.SUCCESS(userService.updateNickNameAndCountryAndProvinceAndAvatarAndIntroduction(email, nickName, country, province, Long.parseLong(avatar), avatarAddress, introduction)));
}


@ResponseBody
@RequestMapping("/user/alluser")
public String findAllUser(){
    return JSON.toJSONString(ResultMsg.SUCCESS(userService.findAllUser()));
}


@ResponseBody
@RequestMapping("/user/add")
public String addUser(HttpServletRequest request){
    return JSON.toJSONString(userService.handleAddNewUser(request));
}


@ResponseBody
@RequestMapping("/user/editpass/{email}")
public String handleEditPass(String email,String newPass,String confimPass){
    return JSON.toJSONString(userService.handleEditPass(email, newPass, confimPass));
}


@ResponseBody
@RequestMapping("/user/{email}")
public String findUserByEmail(String email){
    return JSON.toJSONString(ResultMsg.SUCCESS(userService.findUserVoByEmail(email)));
}


@ResponseBody
@RequestMapping("/user/updateAdmin/{email}")
public String updateAdminStatu(String email){
    return JSON.toJSONString(userService.updateAdminStatu(email));
}


}