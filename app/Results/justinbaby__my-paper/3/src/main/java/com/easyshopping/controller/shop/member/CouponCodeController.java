package com.easyshopping.controller.shop.member;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.controller.shop.BaseController;
import com.easyshopping.entity.Coupon;
import com.easyshopping.entity.Member;
import com.easyshopping.service.CouponCodeService;
import com.easyshopping.service.CouponService;
import com.easyshopping.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.easyshopping.Interface.MemberService;
import com.easyshopping.DTO.Message;
@Controller("shopMemberCouponCodeController")
@RequestMapping("/member/coupon_code")
public class CouponCodeController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "couponServiceImpl")
 private  CouponService couponService;

@Resource(name = "couponCodeServiceImpl")
 private  CouponCodeService couponCodeService;


@RequestMapping(value = "/exchange", method = RequestMethod.POST)
@ResponseBody
public Message exchange(Long id){
    Coupon coupon = couponService.find(id);
    if (coupon == null || !coupon.getIsEnabled() || !coupon.getIsExchange() || coupon.hasExpired()) {
        return ERROR_MESSAGE;
    }
    Member member = memberService.getCurrent();
    if (member.getPoint() < coupon.getPoint()) {
        return Message.warn("shop.member.couponCode.point");
    }
    couponCodeService.exchange(coupon, member);
    return SUCCESS_MESSAGE;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Integer pageNumber,ModelMap model){
    Member member = memberService.getCurrent();
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("page", couponCodeService.findPage(member, pageable));
    return "shop/member/coupon_code/list";
}


}