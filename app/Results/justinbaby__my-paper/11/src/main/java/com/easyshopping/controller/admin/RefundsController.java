package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.service.RefundsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller("adminRefundsController")
@RequestMapping("/admin/refunds")
public class RefundsController extends BaseController{

@Resource(name = "refundsServiceImpl")
 private  RefundsService refundsService;


@RequestMapping(value = "/view", method = RequestMethod.GET)
public String view(Long id,ModelMap model){
    model.addAttribute("refunds", refundsService.find(id));
    return "/admin/refunds/view";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", refundsService.findPage(pageable));
    return "/admin/refunds/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    refundsService.delete(ids);
    return SUCCESS_MESSAGE;
}


}