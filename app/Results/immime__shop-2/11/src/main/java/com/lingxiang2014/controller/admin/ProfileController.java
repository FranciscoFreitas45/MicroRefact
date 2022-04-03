package com.lingxiang2014.controller.admin;
 import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.entity.Admin;
import com.lingxiang2014.service.AdminService;
@Controller("adminProfileController")
@RequestMapping("/admin/profile")
public class ProfileController extends BaseController{

@Resource(name = "adminServiceImpl")
 private  AdminService adminService;


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(ModelMap model){
    model.addAttribute("admin", adminService.getCurrent());
    return "/admin/profile/edit";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(String currentPassword,String password,String email,RedirectAttributes redirectAttributes){
    if (!isValid(Admin.class, "email", email)) {
        return ERROR_VIEW;
    }
    Admin pAdmin = adminService.getCurrent();
    if (StringUtils.isNotEmpty(currentPassword) && StringUtils.isNotEmpty(password)) {
        if (!isValid(Admin.class, "password", password)) {
            return ERROR_VIEW;
        }
        if (!StringUtils.equals(DigestUtils.md5Hex(currentPassword), pAdmin.getPassword())) {
            return ERROR_VIEW;
        }
        pAdmin.setPassword(DigestUtils.md5Hex(password));
    }
    pAdmin.setEmail(email);
    adminService.update(pAdmin);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:edit.jhtml";
}


@RequestMapping(value = "/check_current_password", method = RequestMethod.GET)
@ResponseBody
public boolean checkCurrentPassword(String currentPassword){
    if (StringUtils.isEmpty(currentPassword)) {
        return false;
    }
    Admin admin = adminService.getCurrent();
    if (StringUtils.equals(DigestUtils.md5Hex(currentPassword), admin.getPassword())) {
        return true;
    } else {
        return false;
    }
}


}