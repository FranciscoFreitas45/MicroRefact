package com.lingxiang2014.controller.admin;
 import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Seo;
import com.lingxiang2014.service.SeoService;
@Controller("adminSeoController")
@RequestMapping("/admin/seo")
public class SeoController extends BaseController{

@Resource(name = "seoServiceImpl")
 private  SeoService seoService;


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("seo", seoService.find(id));
    return "/admin/seo/edit";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Seo seo,RedirectAttributes redirectAttributes){
    if (!isValid(seo)) {
        return ERROR_VIEW;
    }
    seoService.update(seo, "type");
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", seoService.findPage(pageable));
    return "/admin/seo/list";
}


}