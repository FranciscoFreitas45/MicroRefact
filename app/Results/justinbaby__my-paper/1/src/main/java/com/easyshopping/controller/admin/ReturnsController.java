package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.service.ReturnsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller("adminReturnsController")
@RequestMapping("/admin/returns")
public class ReturnsController extends BaseController{

@Resource(name = "returnsServiceImpl")
 private  ReturnsService returnsService;


@RequestMapping(value = "/view", method = RequestMethod.GET)
public String view(Long id,ModelMap model){
    model.addAttribute("returns", returnsService.find(id));
    return "/admin/returns/view";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", returnsService.findPage(pageable));
    return "/admin/returns/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    returnsService.delete(ids);
    return SUCCESS_MESSAGE;
}


}