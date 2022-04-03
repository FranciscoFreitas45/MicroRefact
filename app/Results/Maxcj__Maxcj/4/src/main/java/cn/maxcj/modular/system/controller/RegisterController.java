package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.common.constant.dictmap.UserDict;
import cn.maxcj.core.common.constant.state.ManagerStatus;
import cn.maxcj.core.common.exception.BizExceptionEnum;
import cn.maxcj.core.shiro.ShiroKit;
import cn.maxcj.modular.system.factory.UserFactory;
import cn.maxcj.modular.system.model.User;
import cn.maxcj.modular.system.service.IUserService;
import cn.maxcj.modular.system.transfer.UserDto;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
@Controller
public class RegisterController extends BaseController{

@Autowired
 private  IUserService userService;


@BussinessLog(value = "注册用户")
@RequestMapping(value = "/goregister", method = RequestMethod.POST)
public String loginVali(Model model){
    String username = super.getPara("account").trim();
    String password = super.getPara("password").trim();
    // 查看是否存在此用户名
    if (userService.getByAccount(username) != null) {
        model.addAttribute("tips", "学号已存在");
        return "/register.html";
    }
    User newUser = new User();
    newUser.setAccount(username);
    newUser.setName(username);
    // 新注册用户不属于任何社团
    newUser.setRoleid("21");
    newUser.setSalt(ShiroKit.getRandomSalt(5));
    newUser.setPassword(ShiroKit.md5(password, newUser.getSalt()));
    newUser.setStatus(ManagerStatus.OK.getCode());
    newUser.setCreatetime(new Date());
    userService.insert(newUser);
    model.addAttribute("tips", "注册成功，请登录后台管理系统");
    return "/login.html";
}


@RequestMapping(value = "/register", method = RequestMethod.GET)
public String register(){
    return "/register.html";
}


}