package com.wxcrm.sys;
 import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;
import com.wxcrm.Interface.IRoleService;
@Controller
@RequestMapping("/menu")
public class MenuController {

@Autowired
 public  IMenuService menuservice;

@Autowired
 public  IRoleService roleService;


@RequestMapping(value = "/toAddMenu", method = RequestMethod.GET)
public String toAddMenu(WcMenu menu){
    return "/sys/addMenu";
}


@RequestMapping("/getMenuTreeForRole/{disabled}/{roleId}")
@ResponseBody
public List<Map<String,Object>> getMenuTreeForRole(String disabled,Integer roleId){
    List<String> checkedList = null;
    if (roleId != -1) {
        checkedList = roleService.queryRoleMenusById(roleId);
    }
    return getMenuTree(disabled, null, checkedList);
}


@RequestMapping(value = "/delMenu", method = RequestMethod.POST)
public String delMenu(WcMenu menu,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (menu.getMenuIds() == null || menu.getMenuIds().length == 0) {
        return null;
    }
    menuservice.delMenu(menu.getMenuIds());
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "�˵�ɾ���ɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/menu/queryMenu", menu));
    return "redirect:/admin/toMsg";
}


@RequestMapping(value = "/updMenu", method = RequestMethod.POST)
public String updMenu(WcMenu menu,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (!chkMenu(menu)) {
        return null;
    }
    menuservice.updMenu(menu);
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "�˵��޸ĳɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/menu/queryMenu", menu));
    return "redirect:/admin/toMsg";
}


@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
public String addMenu(WcMenu menu,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (!chkMenu(menu)) {
        return null;
    }
    menuservice.addMenu(menu);
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "�˵���ӳɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/menu/queryMenu"));
    return "redirect:/admin/toMsg";
}


@RequestMapping("/getParentMenuTree/{menuLevel}/{parentId}")
@ResponseBody
public List<Map<String,Object>> getParentMenuTree(String menuLevel,String parentId){
    List<Map<String, Object>> menuList = menuservice.queryMenuToCache();
    for (Map<String, Object> map : menuList) {
        if (parentId != null && map.get("id").toString().equals(parentId)) {
            map.put("checked", true);
        }
        if (map.get("menuLevel").toString().equals("1")) {
            if (menuLevel.equals("3")) {
                map.put("nocheck", true);
            }
        } else if (map.get("menuLevel").toString().equals("2")) {
            if (menuLevel.equals("2")) {
                map.put("nocheck", true);
            }
        } else {
            map.put("nocheck", true);
        }
    }
    return menuList;
}


@RequestMapping(value = "/toUpdMenu", method = RequestMethod.POST)
public String toUpdMenu(WcMenu menu_Q,Model model){
    WcMenu menu = menuservice.getMenuById(menu_Q.getWmeId());
    StringUtil.copyProperties(menu_Q, menu);
    model.addAttribute("command", menu);
    return "/sys/updMenu";
}


public boolean chkMenu(WcMenu menu){
    if (menu.getWmeName() == null || menu.getWmeName().trim().length() == 0 || menu.getWmeLevel() == null || menu.getWmeLevel().trim().length() == 0 || menu.getWmeOrder() == null) {
        return false;
    } else {
        return true;
    }
}


@RequestMapping(value = "/queryMenu")
public String queryMenu(WcMenu menu,Model model){
    model.addAttribute(SysConstant.PAGE_RESULT, menuservice.queryMenu(menu));
    return "/sys/queryMenu";
}


public List<Map<String,Object>> getMenuTree(String disabled,List<String> disabledList,List<String> checkedList){
    // List<Map<String, Object>> menuList = memcachedService.getMenuAll();
    List<Map<String, Object>> menuList = menuservice.queryMenuToCache();
    for (Map<String, Object> map : menuList) {
        if (disabled.equals("1")) {
            map.put("chkDisabled", true);
        } else if (disabled.equals("2") && disabledList != null && disabledList.contains(map.get("id").toString())) {
            map.put("chkDisabled", true);
        }
        if (checkedList != null && checkedList.contains(map.get("id").toString())) {
            map.put("checked", true);
        }
    }
    return menuList;
}


}