package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.Template;
import com.easyshopping.service.StaticService;
import com.easyshopping.service.TemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller("adminSitemapController")
@RequestMapping("/admin/sitemap")
public class SitemapController extends BaseController{

@Resource(name = "templateServiceImpl")
 private  TemplateService templateService;

@Resource(name = "staticServiceImpl")
 private  StaticService staticService;


@RequestMapping(value = "/build", method = RequestMethod.POST)
public String build(RedirectAttributes redirectAttributes){
    staticService.buildSitemap();
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:build.jhtml";
}


}