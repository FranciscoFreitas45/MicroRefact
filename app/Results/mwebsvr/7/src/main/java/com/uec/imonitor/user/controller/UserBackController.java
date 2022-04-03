package com.uec.imonitor.user.controller;
 import java.util.UUID;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.uec.imonitor.common.base.BaseController;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.user.bean.UserEntity;
import com.uec.imonitor.user.service.IUserService;
@Scope("prototype")
@Controller
@RequestMapping(value = "/{tenantMark}/user/back")
public class UserBackController extends BaseController{

@Autowired
@Qualifier("userService")
 private  IUserService userService;


@ResponseBody
@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
public ModelMap updatePassword(Integer innerid,String newPassword){
    UserEntity user = userService.findById(innerid);
    user.setSalt(UUID.randomUUID().toString());
    user.setPassword(CommonUtil.encryptPassword(newPassword, user.getSalt()));
    userService.addUser(user);
    // userService.updatePassword(innerid,oldPassword,newPassword);
    return super.getModelMap("修改密码成功");
}


@ResponseBody
@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
public ModelMap getUser(int id,Model model){
    UserEntity user = userService.findById(id);
    return super.getModelMap(user);
}


@ResponseBody
@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
public ModelMap deleteUser(int id){
    userService.deleteUser(id);
    return super.getModelMap("删除用户成功");
}


@ResponseBody
@RequestMapping(value = "/update", method = RequestMethod.POST)
public ModelMap updateUser(UserEntity user){
    userService.updateUser(user);
    return super.getModelMap("修改用户成功");
}


@ResponseBody
@RequestMapping(value = "/create", method = RequestMethod.POST)
public ModelMap createUser(UserEntity user){
    user.setSalt(UUID.randomUUID().toString());
    user.setPassword(CommonUtil.encryptPassword(new Sha256Hash(user.getUserName()).toString(), user.getSalt()));
    userService.addUser(user);
    return super.getModelMap("创建用户成功");
}


}