package com.easyshopping.controller.shop.member;
 import javax.annotation.Resource;
import com.easyshopping.Pageable;
import com.easyshopping.controller.shop.BaseController;
import com.easyshopping.entity.Member;
import com.easyshopping.service.MemberService;
import com.easyshopping.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.easyshopping.Interface.ReviewService;
@Controller("shopMemberReviewController")
@RequestMapping("/member/review")
public class ReviewController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "reviewServiceImpl")
 private  ReviewService reviewService;


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Integer pageNumber,ModelMap model){
    Member member = memberService.getCurrent();
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("page", reviewService.findPage(member, null, null, null, pageable));
    return "shop/member/review/list";
}


}