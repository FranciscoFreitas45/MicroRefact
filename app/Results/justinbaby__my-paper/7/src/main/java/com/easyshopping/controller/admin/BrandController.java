package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Brand;
import com.easyshopping.entity.Brand.Type;
import com.easyshopping.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller("adminBrandController")
@RequestMapping("/admin/brand")
public class BrandController extends BaseController{

@Resource(name = "brandServiceImpl")
 private  BrandService brandService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    model.addAttribute("types", Type.values());
    return "/admin/brand/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("types", Type.values());
    model.addAttribute("brand", brandService.find(id));
    return "/admin/brand/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Brand brand,RedirectAttributes redirectAttributes){
    if (!isValid(brand)) {
        return ERROR_VIEW;
    }
    if (brand.getType() == Type.text) {
        brand.setLogo(null);
    } else if (StringUtils.isEmpty(brand.getLogo())) {
        return ERROR_VIEW;
    }
    brand.setProducts(null);
    brand.setProductCategories(null);
    brand.setPromotions(null);
    brandService.save(brand);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Brand brand,RedirectAttributes redirectAttributes){
    if (!isValid(brand)) {
        return ERROR_VIEW;
    }
    if (brand.getType() == Type.text) {
        brand.setLogo(null);
    } else if (StringUtils.isEmpty(brand.getLogo())) {
        return ERROR_VIEW;
    }
    brandService.update(brand, "products", "productCategories", "promotions");
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", brandService.findPage(pageable));
    return "/admin/brand/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    brandService.delete(ids);
    return SUCCESS_MESSAGE;
}


}