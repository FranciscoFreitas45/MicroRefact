package com.vino.scaffold.shiro.controller;
 import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vino.scaffold.controller.base.BaseController;
import com.vino.scaffold.entity.Constants;
import com.vino.scaffold.shiro.entity.Resource;
import com.vino.scaffold.shiro.entity.User;
import com.vino.scaffold.shiro.service.ResourceService;
import com.vino.scaffold.shiro.service.UserService;
import com.vino.scaffold.shiro.Interface.UserService;
import com.vino.scaffold.shiro.Interface.ResourceService;
import com.vino.scaffold.shiro.DTO.User;
@Controller
public class HomeController extends BaseController{

@Autowired
 private  UserService userService;

@Autowired
 private  ResourceService resourceService;


public void setResourceService(ResourceService resourceService){
    this.resourceService = resourceService;
}


public void setUserService(UserService userService){
    this.userService = userService;
}


@RequestMapping("/")
public String home(Model model,HttpServletRequest request){
    Subject curUser = SecurityUtils.getSubject();
    Session session = curUser.getSession();
    String username = (String) curUser.getPrincipal();
    User currentUser = userService.findByUsername(username);
    // ����ǰ�û�����session
    session.setAttribute(Constants.CURRENT_USER, currentUser);
    session.setAttribute(Constants.CURRENT_USERNAME, username);
    // ����ǰ��ҳ�����ɲ����
    List<Resource> resources = resourceService.findAll();
    request.setAttribute("resources", resources);
    if (currentUser.getLoginTime() != null) {
        // ���µ�½ʱ��
        currentUser.setLastLoginTime(currentUser.getLoginTime());
    }
    currentUser.setLoginTime(new Date());
    userService.update(currentUser);
    return "index";
}


}