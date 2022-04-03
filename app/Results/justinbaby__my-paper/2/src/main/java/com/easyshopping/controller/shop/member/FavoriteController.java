package com.easyshopping.controller.shop.member;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.controller.shop.BaseController;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Product;
import com.easyshopping.service.MemberService;
import com.easyshopping.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.easyshopping.Interface.ProductService;
@Controller("shopMemberFavoriteController")
@RequestMapping("/member/favorite")
public class FavoriteController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "productServiceImpl")
 private  ProductService productService;


@RequestMapping(value = "/add", method = RequestMethod.POST)
@ResponseBody
public Message add(Long id){
    Product product = productService.find(id);
    if (product == null) {
        return ERROR_MESSAGE;
    }
    Member member = memberService.getCurrent();
    if (member.getFavoriteProducts().contains(product)) {
        return Message.warn("shop.member.favorite.exist");
    }
    if (Member.MAX_FAVORITE_COUNT != null && member.getFavoriteProducts().size() >= Member.MAX_FAVORITE_COUNT) {
        return Message.warn("shop.member.favorite.addCountNotAllowed", Member.MAX_FAVORITE_COUNT);
    }
    member.getFavoriteProducts().add(product);
    memberService.update(member);
    return Message.success("shop.member.favorite.success");
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Integer pageNumber,ModelMap model){
    Member member = memberService.getCurrent();
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("page", productService.findPage(member, pageable));
    return "shop/member/favorite/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long id){
    Product product = productService.find(id);
    if (product == null) {
        return ERROR_MESSAGE;
    }
    Member member = memberService.getCurrent();
    if (!member.getFavoriteProducts().contains(product)) {
        return ERROR_MESSAGE;
    }
    member.getFavoriteProducts().remove(product);
    memberService.update(member);
    return SUCCESS_MESSAGE;
}


}