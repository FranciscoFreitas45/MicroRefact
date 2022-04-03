package com.lingxiang2014.controller.admin;
 import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Role;
import com.lingxiang2014.service.RoleService;
@Controller("adminRoleController")
@RequestMapping("/admin/role")
public class RoleController extends BaseController{

@Resource(name = "roleServiceImpl")
 private  RoleService roleService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(){
    return "/admin/role/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("role", roleService.find(id));
    return "/admin/role/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Role role,RedirectAttributes redirectAttributes){
    if (!isValid(role)) {
        return ERROR_VIEW;
    }
    role.setIsSystem(false);
    role.setAdmins(null);
    roleService.save(role);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Role role,RedirectAttributes redirectAttributes){
    if (!isValid(role)) {
        return ERROR_VIEW;
    }
    Role pRole = roleService.find(role.getId());
    if (pRole == null || pRole.getIsSystem()) {
        return ERROR_VIEW;
    }
    roleService.update(role, "isSystem", "admins");
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", roleService.findPage(pageable));
    return "/admin/role/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    if (ids != null) {
        for (Long id : ids) {
            Role role = roleService.find(id);
            if (role != null && (role.getIsSystem() || (role.getAdmins() != null && !role.getAdmins().isEmpty()))) {
                return Message.error("admin.role.deleteExistNotAllowed", role.getName());
            }
        }
        roleService.delete(ids);
    }
    return SUCCESS_MESSAGE;
}


}