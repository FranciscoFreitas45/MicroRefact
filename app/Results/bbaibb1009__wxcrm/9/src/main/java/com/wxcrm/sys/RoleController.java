package com.wxcrm.sys;
 import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;
@Controller
@RequestMapping("/role")
public class RoleController {

@Autowired
 private  IRoleService roleService;


@RequestMapping(value = "/updRole", method = RequestMethod.POST)
public String updRole(WcRole role,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (!chkRole(role)) {
        return null;
    }
    roleService.updRole(role);
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "��ɫ�޸ĳɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/role/queryRole", role));
    return "redirect:/admin/toMsg";
}


@RequestMapping(value = "/delRole", method = RequestMethod.POST)
public String delRole(WcRole role,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (role.getWroRoleIds() == null || role.getWroRoleIds().length == 0) {
        return null;
    }
    roleService.delRole(role.getWroRoleIds());
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "��ɫɾ���ɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/role/queryRole", role));
    return "redirect:/admin/toMsg";
}


@RequestMapping("/queryRole")
public String queryRole(WcRole role,Model model){
    model.addAttribute(SysConstant.PAGE_RESULT, roleService.queryRole(role));
    return "/sys/queryRole";
}


public boolean chkRole(WcRole role){
    if (role.getWroRoleName() == null || role.getWroRoleName().trim().length() == 0) {
        return false;
    } else {
        return true;
    }
}


@RequestMapping(value = "/toUpdRole", method = RequestMethod.POST)
public String toUpdRole(WcRole role_Q,Model model){
    WcRole role = roleService.getRoleById(role_Q.getWroRoleId());
    StringUtil.copyProperties(role_Q, role);
    model.addAttribute("command", role);
    return "/sys/updRole";
}


@RequestMapping(value = "/toAddRole", method = RequestMethod.GET)
public String toAddRole(WcRole role){
    return "/sys/addRole";
}


@RequestMapping(value = "/addRole", method = RequestMethod.POST)
public String addRole(WcRole role,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (!chkRole(role)) {
        return null;
    }
    roleService.addRole(role);
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "��ɫ��ӳɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/role/queryRole"));
    return "redirect:/admin/toMsg";
}


@RequestMapping("/showRole/{roleId}")
public String showRole(Integer roleId){
    return "/sys/showRole";
}


}