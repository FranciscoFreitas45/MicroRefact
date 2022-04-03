package com.sobey.cmop.mvc.web.account;
 import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.constant.AccountConstant;
import com.sobey.cmop.mvc.entity.Group;
import com.sobey.cmop.mvc.entity.User;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/account/user")
public class UserController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
public String detailForm(Integer id,Model model){
    model.addAttribute("group", comm.accountService.findGroupByUserId(id));
    model.addAttribute("user", comm.accountService.getUser(id));
    return "account/userDetail";
}


@ModelAttribute("allGroups")
public List<Group> allGroups(){
    return comm.accountService.findAllGroup();
}


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "account/userForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(User user,Integer departmentId,Integer groupId,RedirectAttributes redirectAttributes){
    user.setGroupList(comm.accountService.getGroupListById(groupId));
    user.setDepartment(comm.accountService.getDepartment(departmentId));
    user.setPlainPassword(AccountConstant.DEFAULT_PASSWORD);
    comm.accountService.registerUser(user);
    redirectAttributes.addFlashAttribute("message", "创建用户 " + user.getLoginName() + " 成功");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Integer id,String email,String phonenum,String name,Integer leaderId,Integer type,Integer departmentId,Integer groupId,RedirectAttributes redirectAttributes){
    User user = comm.accountService.getUser(id);
    user.setEmail(email);
    user.setPhonenum(phonenum);
    user.setName(name);
    user.setType(type);
    user.setLeaderId(leaderId);
    user.setDepartment(comm.accountService.getDepartment(departmentId));
    user.setGroupList(comm.accountService.getGroupListById(groupId));
    comm.accountService.updateUser(user);
    redirectAttributes.addFlashAttribute("message", "修改用户 " + user.getLoginName() + " 成功");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
public String updateForm(Integer id,Model model){
    model.addAttribute("group", comm.accountService.findGroupByUserId(id));
    model.addAttribute("user", comm.accountService.getUser(id));
    return "account/userForm";
}


@RequestMapping(value = { "list", "" })
public String list(int pageNumber,int pageSize,Model model,ServletRequest request){
    // TODO 初始化所有User的密码和LoginName
    // comm.accountService.initializeUser();
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.accountService.getUserPageable(searchParams, pageNumber, pageSize));
    // 将搜索条件编码成字符串,分页的URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    return "account/userList";
}


@RequestMapping(value = "delete/{id}")
public String delete(Integer id,RedirectAttributes redirectAttributes){
    boolean flag = comm.accountService.deleteUser(id);
    redirectAttributes.addFlashAttribute("message", flag ? "删除用户成功" : "不能删除超级管理员");
    return REDIRECT_SUCCESS_URL;
}


}