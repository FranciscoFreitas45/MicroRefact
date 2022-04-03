package com.lingxiang2014.controller.admin;
 import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.service.CacheService;
@Controller("adminCacheController")
@RequestMapping("/admin/cache")
public class CacheController extends BaseController{

@Resource(name = "cacheServiceImpl")
 private  CacheService cacheService;


@RequestMapping(value = "/clear", method = RequestMethod.POST)
public String clear(RedirectAttributes redirectAttributes){
    cacheService.clear();
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:clear.jhtml";
}


}