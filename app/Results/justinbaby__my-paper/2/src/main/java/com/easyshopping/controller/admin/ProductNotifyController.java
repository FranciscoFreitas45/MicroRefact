package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.service.ProductNotifyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller("ProductNotifyntroller")
@RequestMapping("/admin/product_notify")
public class ProductNotifyController extends BaseController{

@Resource(name = "productNotifyServiceImpl")
 private  ProductNotifyService productNotifyService;


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Boolean isMarketable,Boolean isOutOfStock,Boolean hasSent,Pageable pageable,ModelMap model){
    model.addAttribute("isMarketable", isMarketable);
    model.addAttribute("isOutOfStock", isOutOfStock);
    model.addAttribute("hasSent", hasSent);
    model.addAttribute("page", productNotifyService.findPage(null, isMarketable, isOutOfStock, hasSent, pageable));
    return "/admin/product_notify/list";
}


@RequestMapping(value = "/send", method = RequestMethod.POST)
@ResponseBody
public Message send(Long[] ids){
    int count = productNotifyService.send(ids);
    return Message.success("admin.productNotify.sentSuccess", count);
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    productNotifyService.delete(ids);
    return SUCCESS_MESSAGE;
}


}