package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.entity.FriendLink;
import com.easyshopping.entity.FriendLink.Type;
import com.easyshopping.service.FriendLinkService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller("adminFriendLinkController")
@RequestMapping("/admin/friend_link")
public class FriendLinkController extends BaseController{

@Resource(name = "friendLinkServiceImpl")
 private  FriendLinkService friendLinkService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    model.addAttribute("types", Type.values());
    return "/admin/friend_link/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("types", Type.values());
    model.addAttribute("friendLink", friendLinkService.find(id));
    return "/admin/friend_link/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(FriendLink friendLink,RedirectAttributes redirectAttributes){
    if (!isValid(friendLink)) {
        return ERROR_VIEW;
    }
    if (friendLink.getType() == Type.text) {
        friendLink.setLogo(null);
    } else if (StringUtils.isEmpty(friendLink.getLogo())) {
        return ERROR_VIEW;
    }
    friendLinkService.save(friendLink);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(FriendLink friendLink,RedirectAttributes redirectAttributes){
    if (!isValid(friendLink)) {
        return ERROR_VIEW;
    }
    if (friendLink.getType() == Type.text) {
        friendLink.setLogo(null);
    } else if (StringUtils.isEmpty(friendLink.getLogo())) {
        return ERROR_VIEW;
    }
    friendLinkService.update(friendLink);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", friendLinkService.findPage(pageable));
    return "/admin/friend_link/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    friendLinkService.delete(ids);
    return SUCCESS_MESSAGE;
}


}