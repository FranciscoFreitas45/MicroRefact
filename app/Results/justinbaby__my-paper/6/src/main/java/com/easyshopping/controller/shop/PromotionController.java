package com.easyshopping.controller.shop;
 import javax.annotation.Resource;
import com.easyshopping.ResourceNotFoundException;
import com.easyshopping.entity.Promotion;
import com.easyshopping.service.PromotionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller("shopPromotionController")
@RequestMapping("/promotion")
public class PromotionController extends BaseController{

@Resource(name = "promotionServiceImpl")
 private  PromotionService promotionService;


@RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
public String content(Long id,ModelMap model){
    Promotion promotion = promotionService.find(id);
    if (promotion == null) {
        throw new ResourceNotFoundException();
    }
    model.addAttribute("promotion", promotion);
    return "/shop/promotion/content";
}


}