package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.service.ShippingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller("adminShippingController")
@RequestMapping("/admin/shipping")
public class ShippingController extends BaseController{

@Resource(name = "shippingServiceImpl")
 private  ShippingService shippingService;


@RequestMapping(value = "/view", method = RequestMethod.GET)
public String view(Long id,ModelMap model){
    model.addAttribute("shipping", shippingService.find(id));
    return "/admin/shipping/view";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", shippingService.findPage(pageable));
    return "/admin/shipping/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    shippingService.delete(ids);
    return SUCCESS_MESSAGE;
}


}