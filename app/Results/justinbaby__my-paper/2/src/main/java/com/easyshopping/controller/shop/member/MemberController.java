package com.easyshopping.controller.shop.member;
 import javax.annotation.Resource;
import com.easyshopping.controller.shop.BaseController;
import com.easyshopping.entity.Member;
import com.easyshopping.service.ConsultationService;
import com.easyshopping.service.CouponCodeService;
import com.easyshopping.service.MemberService;
import com.easyshopping.service.MessageService;
import com.easyshopping.service.OrderService;
import com.easyshopping.service.ProductNotifyService;
import com.easyshopping.service.ProductService;
import com.easyshopping.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.easyshopping.Interface.OrderService;
import com.easyshopping.Interface.CouponCodeService;
import com.easyshopping.Interface.MessageService;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ReviewService;
@Controller("shopMemberController")
@RequestMapping("/member")
public class MemberController extends BaseController{

 private  int NEW_ORDER_COUNT;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "orderServiceImpl")
 private  OrderService orderService;

@Resource(name = "couponCodeServiceImpl")
 private  CouponCodeService couponCodeService;

@Resource(name = "messageServiceImpl")
 private  MessageService messageService;

@Resource(name = "productServiceImpl")
 private  ProductService productService;

@Resource(name = "productNotifyServiceImpl")
 private  ProductNotifyService productNotifyService;

@Resource(name = "reviewServiceImpl")
 private  ReviewService reviewService;

@Resource(name = "consultationServiceImpl")
 private  ConsultationService consultationService;


@RequestMapping(value = "/index", method = RequestMethod.GET)
public String index(Integer pageNumber,ModelMap model){
    Member member = memberService.getCurrent();
    model.addAttribute("waitingPaymentOrderCount", orderService.waitingPaymentCount(member));
    model.addAttribute("waitingShippingOrderCount", orderService.waitingShippingCount(member));
    model.addAttribute("messageCount", messageService.count(member, false));
    model.addAttribute("couponCodeCount", couponCodeService.count(null, member, null, false, false));
    model.addAttribute("favoriteCount", productService.count(member, null, null, null, null, null, null));
    model.addAttribute("productNotifyCount", productNotifyService.count(member, null, null, null));
    model.addAttribute("reviewCount", reviewService.count(member, null, null, null));
    model.addAttribute("consultationCount", consultationService.count(member, null, null));
    model.addAttribute("newOrders", orderService.findList(member, NEW_ORDER_COUNT, null, null));
    return "shop/member/index";
}


}