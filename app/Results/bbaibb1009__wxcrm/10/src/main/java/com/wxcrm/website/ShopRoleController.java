package com.wxcrm.website;
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
@RequestMapping("/shoprole")
public class ShopRoleController {

@Autowired
 private  IShopRoleService roleService;


@RequestMapping(value = "/updShopRole", method = RequestMethod.POST)
public String updShopRole(WcShopRole role,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (!chkShopRole(role)) {
        return null;
    }
    roleService.updShopRole(role);
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "���̽�ɫ�޸ĳɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shoprole/queryShopRole", role));
    return "redirect:/admin/toMsg";
}


@RequestMapping(value = "/delShopRole", method = RequestMethod.POST)
public String delShopRole(WcShopRole role,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (role.getWsrRoleIds() == null || role.getWsrRoleIds().length == 0) {
        return null;
    }
    roleService.delShopRole(role.getWsrRoleIds());
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "���̽�ɫɾ���ɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shoprole/queryShopRole", role));
    return "redirect:/admin/toMsg";
}


public boolean chkShopRole(WcShopRole role){
    if (role.getWsrRoleName() == null || role.getWsrRoleName().trim().length() == 0) {
        return false;
    } else {
        return true;
    }
}


@RequestMapping(value = "/toAddShopRole", method = RequestMethod.GET)
public String toAddShopRole(WcShopRole role){
    return "/website/addShopRole";
}


@RequestMapping(value = "/addShopRole", method = RequestMethod.POST)
public String addShopRole(WcShopRole role,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (!chkShopRole(role)) {
        return null;
    }
    roleService.addShopRole(role);
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "��ɫ��ӳɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/shoprole/queryShopRole"));
    return "redirect:/admin/toMsg";
}


@RequestMapping("/queryShopRole")
public String queryShopRole(WcShopRole role,Model model){
    model.addAttribute(SysConstant.PAGE_RESULT, roleService.queryShopRole(role));
    return "/website/queryShopRole";
}


@RequestMapping(value = "/toUpdShopRole", method = RequestMethod.POST)
public String toUpdRole(WcShopRole role_Q,Model model){
    WcShopRole role = roleService.getShopRoleById(role_Q.getWsrRoleId());
    StringUtil.copyProperties(role_Q, role);
    model.addAttribute("command", role);
    return "/website/updShopRole";
}


@RequestMapping("/showShopRole/{roleId}")
public String showShopRole(Integer roleId){
    return "/website/showShopRole";
}


}