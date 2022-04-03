package com.lingxiang2014.controller.shop;
 import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lingxiang2014.entity.FriendLink.Type;
import com.lingxiang2014.service.FriendLinkService;
@Controller("shopFriendLinkController")
@RequestMapping("/friend_link")
public class FriendLinkController extends BaseController{

@Resource(name = "friendLinkServiceImpl")
 private  FriendLinkService friendLinkService;


@RequestMapping(method = RequestMethod.GET)
public String index(ModelMap model){
    model.addAttribute("textFriendLinks", friendLinkService.findList(Type.text));
    model.addAttribute("imageFriendLinks", friendLinkService.findList(Type.image));
    return "/shop/friend_link/index";
}


}