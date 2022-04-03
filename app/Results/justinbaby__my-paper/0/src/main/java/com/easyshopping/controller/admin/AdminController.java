package com.easyshopping.controller.admin;
 import java.util.HashSet;
import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Admin;
import com.easyshopping.entity.BaseEntity.Save;
import com.easyshopping.entity.Role;
import com.easyshopping.service.AdminService;
import com.easyshopping.service.RoleService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller("adminAdminController")
@RequestMapping("/admin/admin")
public class AdminController extends BaseController{

@Resource(name = "adminServiceImpl")
 private  AdminService adminService;

@Resource(name = "roleServiceImpl")
 private  RoleService roleService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    model.addAttribute("roles", roleService.findAll());
    return "/admin/admin/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("admin", adminService.find(id));
    return "/admin/admin/edit";
}


@RequestMapping(value = "/check_username", method = RequestMethod.GET)
@ResponseBody
public boolean checkUsername(String username){
    if (StringUtils.isEmpty(username)) {
        return false;
    }
    if (adminService.usernameExists(username)) {
        return false;
    } else {
        return true;
    }
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Admin admin,Long[] roleIds,RedirectAttributes redirectAttributes){
    admin.setRoles(new HashSet<Role>(roleService.findList(roleIds)));
    if (!isValid(admin, Save.class)) {
        return ERROR_VIEW;
    }
    if (adminService.usernameExists(admin.getUsername())) {
        return ERROR_VIEW;
    }
    admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
    admin.setIsLocked(false);
    admin.setLoginFailureCount(0);
    admin.setLockedDate(null);
    admin.setLoginDate(null);
    admin.setLoginIp(null);
    admin.setOrders(null);
    adminService.save(admin);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Admin admin,Long[] roleIds,RedirectAttributes redirectAttributes){
    admin.setRoles(new HashSet<Role>(roleService.findList(roleIds)));
    if (!isValid(admin)) {
        return ERROR_VIEW;
    }
    Admin pAdmin = adminService.find(admin.getId());
    if (pAdmin == null) {
        return ERROR_VIEW;
    }
    if (StringUtils.isNotEmpty(admin.getPassword())) {
        admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
    } else {
        admin.setPassword(pAdmin.getPassword());
    }
    if (pAdmin.getIsLocked() && !admin.getIsLocked()) {
        admin.setLoginFailureCount(0);
        admin.setLockedDate(null);
    } else {
        admin.setIsLocked(pAdmin.getIsLocked());
        admin.setLoginFailureCount(pAdmin.getLoginFailureCount());
        admin.setLockedDate(pAdmin.getLockedDate());
    }
    adminService.update(admin, "username", "loginDate", "loginIp", "orders");
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", adminService.findPage(pageable));
    return "/admin/admin/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    if (ids.length >= adminService.count()) {
        return Message.error("admin.common.deleteAllNotAllowed");
    }
    adminService.delete(ids);
    return SUCCESS_MESSAGE;
}


}