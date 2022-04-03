package com.easyshopping.controller.shop.member;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.controller.shop.BaseController;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.ProductNotify;
import com.easyshopping.service.MemberService;
import com.easyshopping.service.ProductNotifyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller("shopMemberProductNotifyController")
@RequestMapping("/member/product_notify")
public class ProductNotifyController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "productNotifyServiceImpl")
 private ProductNotifyService productNotifyService;

@Resource(name = "memberServiceImpl")
 private MemberService memberService;


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Integer pageNumber,Model model){
    Member member = memberService.getCurrent();
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("page", productNotifyService.findPage(member, null, null, null, pageable));
    return "/shop/member/product_notify/list";
}


@RequestMapping(value = "delete")
@ResponseBody
public Message delete(Long id){
    ProductNotify productNotify = productNotifyService.find(id);
    if (productNotify == null) {
        return ERROR_MESSAGE;
    }
    Member member = memberService.getCurrent();
    if (!member.getProductNotifies().contains(productNotify)) {
        return ERROR_MESSAGE;
    }
    productNotifyService.delete(productNotify);
    return SUCCESS_MESSAGE;
}


}