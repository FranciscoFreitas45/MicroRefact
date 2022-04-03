package com.easyshopping.controller.shop;
 import javax.annotation.Resource;
import com.easyshopping.service.ProductCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.easyshopping.Interface.ProductCategoryService;
@Controller("shopProductCategoryController")
@RequestMapping("/product_category")
public class ProductCategoryController extends BaseController{

@Resource(name = "productCategoryServiceImpl")
 private  ProductCategoryService productCategoryService;


@RequestMapping(method = RequestMethod.GET)
public String index(ModelMap model){
    model.addAttribute("rootProductCategories", productCategoryService.findRoots());
    return "/shop/product_category/index";
}


}