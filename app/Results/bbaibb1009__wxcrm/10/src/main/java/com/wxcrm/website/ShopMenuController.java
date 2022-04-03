package com.wxcrm.website;
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
@Controller
@RequestMapping("/shopmenu")
public class ShopMenuController {

@Autowired
 public  IShopMenuService menuservice;

@Autowired
 public  IShopRoleService roleService;


@RequestMapping(value = "/delShopMenu", method = RequestMethod.POST)
public String delShopMenu(WcShopMenu menu,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (menu.getMenuIds() == null || menu.getMenuIds().length == 0) {
        return null;
    }
    menuservice.delShopMenu(menu.getMenuIds());
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "���̲˵�ɾ���ɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shopmenu/queryShopMenu", menu));
    return "redirect:/admin/toMsg";
}


@RequestMapping("/getShopMenuTreeForRole/{disabled}/{roleId}")
@ResponseBody
public List<Map<String,Object>> getShopMenuTreeForRole(String disabled,Integer roleId){
    List<String> checkedList = null;
    if (roleId != -1) {
        checkedList = roleService.queryShopRoleMenusById(roleId);
    }
    return getShopMenuTree(disabled, null, checkedList);
}


@RequestMapping(value = "/addShopMenu", method = RequestMethod.POST)
public String addMenu(WcShopMenu menu,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (!chkShopMenu(menu)) {
        return null;
    }
    menuservice.addShopMenu(menu);
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "���̲˵���ӳɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/shopmenu/queryShopMenu"));
    return "redirect:/admin/toMsg";
}


public List<Map<String,Object>> getShopMenuTree(String disabled,List<String> disabledList,List<String> checkedList){
    List<Map<String, Object>> menuList = menuservice.queryShopMenuToCache();
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


@RequestMapping(value = "/toUpdShopMenu", method = RequestMethod.POST)
public String toUpdShopMenu(WcShopMenu menu_Q,Model model){
    WcShopMenu menu = menuservice.getShopMenuById(menu_Q.getWsmId());
    StringUtil.copyProperties(menu_Q, menu);
    model.addAttribute("command", menu);
    return "/website/updShopMenu";
}


public boolean chkShopMenu(WcShopMenu menu){
    if (menu.getWsmName() == null || menu.getWsmName().trim().length() == 0 || menu.getWsmLevel() == null || menu.getWsmLevel().trim().length() == 0 || menu.getWsmOrder() == null) {
        return false;
    } else {
        return true;
    }
}


@RequestMapping(value = "/updShopMenu", method = RequestMethod.POST)
public String updShopMenu(WcShopMenu menu,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (!chkShopMenu(menu)) {
        return null;
    }
    menuservice.updShopMenu(menu);
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "���̲˵��޸ĳɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shopmenu/queryShopMenu", menu));
    return "redirect:/admin/toMsg";
}


@RequestMapping(value = "/queryShopMenu")
public String queryShopMenu(WcShopMenu menu,Model model){
    model.addAttribute(SysConstant.PAGE_RESULT, menuservice.queryShopMenu(menu));
    return "/website/queryShopMenu";
}


@RequestMapping("/getShopParentMenuTree/{menuLevel}/{parentId}")
@ResponseBody
public List<Map<String,Object>> getShopParentMenuTree(String menuLevel,String parentId){
    List<Map<String, Object>> menuList = menuservice.queryShopMenuToCache();
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


@RequestMapping(value = "/toAddShopMenu", method = RequestMethod.GET)
public String toAddShopMenu(WcShopMenu menu){
    return "/website/addShopMenu";
}


}