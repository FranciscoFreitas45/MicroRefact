package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.service.CacheService;
import com.easyshopping.service.SearchService;
import com.easyshopping.service.StaticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller("adminCacheController")
@RequestMapping("/admin/cache")
public class CacheController extends BaseController{

@Resource(name = "cacheServiceImpl")
 private  CacheService cacheService;

@Resource(name = "staticServiceImpl")
 private  StaticService staticService;

@Resource(name = "searchServiceImpl")
 private  SearchService searchService;


@RequestMapping(value = "/clear", method = RequestMethod.POST)
public String clear(RedirectAttributes redirectAttributes){
    cacheService.clear();
    staticService.buildAll();
    searchService.purge();
    searchService.index();
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:clear.jhtml";
}


}