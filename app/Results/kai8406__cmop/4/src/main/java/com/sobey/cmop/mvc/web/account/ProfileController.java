package com.sobey.cmop.mvc.web.account;
 import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.google.common.collect.Lists;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.Group;
import com.sobey.cmop.mvc.entity.User;
import com.sobey.cmop.mvc.service.account.ShiroDbRealm.ShiroUser;
@Controller
@RequestMapping(value = "/profile")
public class ProfileController extends BaseController{


@RequestMapping(method = RequestMethod.POST)
public String profile(Integer id,String email,String plainPassword,String phonenum,String name,Integer leaderId,Integer departmentId,Integer groupId,RedirectAttributes redirectAttributes){
    List<Group> groupList = Lists.newArrayList();
    groupList.add(comm.accountService.getGroup(groupId));
    User user = comm.accountService.getUser(id);
    user.setEmail(email);
    user.setPlainPassword(plainPassword);
    user.setPhonenum(phonenum);
    user.setName(name);
    user.setLeaderId(leaderId);
    user.setDepartment(comm.accountService.getDepartment(departmentId));
    user.setGroupList(comm.accountService.getGroupListById(groupId));
    comm.accountService.updateUser(user);
    // 更新Shiro中当前用户的用户名.
    updateCurrentUserName(user.getName());
    redirectAttributes.addFlashAttribute("message", "修改个人信息成功");
    return "redirect:/profile/";
}


public void updateCurrentUserName(String userName){
    ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    user.name = userName;
}


@RequestMapping(method = RequestMethod.GET)
public String profileForm(Model model){
    model.addAttribute("group", comm.accountService.findGroupByUserId(getCurrentUserId()));
    model.addAttribute("user", comm.accountService.getUser(getCurrentUserId()));
    return "account/profile";
}


}