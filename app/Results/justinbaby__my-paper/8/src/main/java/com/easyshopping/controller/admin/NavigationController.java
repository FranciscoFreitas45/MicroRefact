package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Navigation;
import com.easyshopping.entity.Navigation.Position;
import com.easyshopping.service.ArticleCategoryService;
import com.easyshopping.service.NavigationService;
import com.easyshopping.service.ProductCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.easyshopping.Interface.ArticleCategoryService;
import com.easyshopping.Interface.ProductCategoryService;
@Controller("adminNavigationController")
@RequestMapping("/admin/navigation")
public class NavigationController extends BaseController{

@Resource(name = "navigationServiceImpl")
 private  NavigationService navigationService;

@Resource(name = "articleCategoryServiceImpl")
 private  ArticleCategoryService articleCategoryService;

@Resource(name = "productCategoryServiceImpl")
 private  ProductCategoryService productCategoryService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    model.addAttribute("positions", Position.values());
    model.addAttribute("articleCategoryTree", articleCategoryService.findTree());
    model.addAttribute("productCategoryTree", productCategoryService.findTree());
    return "/admin/navigation/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("positions", Position.values());
    model.addAttribute("articleCategoryTree", articleCategoryService.findTree());
    model.addAttribute("productCategoryTree", productCategoryService.findTree());
    model.addAttribute("navigation", navigationService.find(id));
    return "/admin/navigation/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Navigation navigation,RedirectAttributes redirectAttributes){
    if (!isValid(navigation)) {
        return ERROR_VIEW;
    }
    navigationService.save(navigation);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Navigation navigation,RedirectAttributes redirectAttributes){
    if (!isValid(navigation)) {
        return ERROR_VIEW;
    }
    navigationService.update(navigation);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("topNavigations", navigationService.findList(Position.top));
    model.addAttribute("middleNavigations", navigationService.findList(Position.middle));
    model.addAttribute("bottomNavigations", navigationService.findList(Position.bottom));
    return "/admin/navigation/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    navigationService.delete(ids);
    return SUCCESS_MESSAGE;
}


}