package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller("adminLogController")
@RequestMapping("/admin/log")
public class LogController extends BaseController{

@Resource(name = "logServiceImpl")
 private  LogService logService;


@RequestMapping(value = "/view", method = RequestMethod.GET)
public String view(Long id,ModelMap model){
    model.addAttribute("log", logService.find(id));
    return "/admin/log/view";
}


@RequestMapping(value = "/clear", method = RequestMethod.POST)
@ResponseBody
public Message clear(){
    logService.clear();
    return SUCCESS_MESSAGE;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", logService.findPage(pageable));
    return "/admin/log/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    logService.delete(ids);
    return SUCCESS_MESSAGE;
}


}