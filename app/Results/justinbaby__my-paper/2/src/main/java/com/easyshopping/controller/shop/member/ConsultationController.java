package com.easyshopping.controller.shop.member;
 import javax.annotation.Resource;
import com.easyshopping.Pageable;
import com.easyshopping.controller.shop.BaseController;
import com.easyshopping.entity.Member;
import com.easyshopping.service.ConsultationService;
import com.easyshopping.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller("shopMemberConsultationController")
@RequestMapping("/member/consultation")
public class ConsultationController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "consultationServiceImpl")
 private  ConsultationService consultationService;


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Integer pageNumber,ModelMap model){
    Member member = memberService.getCurrent();
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("page", consultationService.findPage(member, null, null, pageable));
    return "shop/member/consultation/list";
}


}