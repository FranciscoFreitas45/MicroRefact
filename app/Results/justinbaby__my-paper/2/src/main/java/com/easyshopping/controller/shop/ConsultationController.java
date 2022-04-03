package com.easyshopping.controller.shop;
 import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.ResourceNotFoundException;
import com.easyshopping.Setting;
import com.easyshopping.Setting.CaptchaType;
import com.easyshopping.Setting.ConsultationAuthority;
import com.easyshopping.entity.Consultation;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Product;
import com.easyshopping.service.CaptchaService;
import com.easyshopping.service.ConsultationService;
import com.easyshopping.service.MemberService;
import com.easyshopping.service.ProductService;
import com.easyshopping.util.SettingUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.easyshopping.Interface.ProductService;
@Controller("shopConsultationController")
@RequestMapping("/consultation")
public class ConsultationController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "consultationServiceImpl")
 private  ConsultationService consultationService;

@Resource(name = "productServiceImpl")
 private  ProductService productService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "captchaServiceImpl")
 private  CaptchaService captchaService;


@RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
public String add(Long id,ModelMap model){
    Setting setting = SettingUtils.get();
    if (!setting.getIsConsultationEnabled()) {
        throw new ResourceNotFoundException();
    }
    Product product = productService.find(id);
    if (product == null) {
        throw new ResourceNotFoundException();
    }
    model.addAttribute("product", product);
    model.addAttribute("captchaId", UUID.randomUUID().toString());
    return "/shop/consultation/add";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
@ResponseBody
public Message save(String captchaId,String captcha,Long id,String content,HttpServletRequest request){
    if (!captchaService.isValid(CaptchaType.consultation, captchaId, captcha)) {
        return Message.error("shop.captcha.invalid");
    }
    Setting setting = SettingUtils.get();
    if (!setting.getIsConsultationEnabled()) {
        return Message.error("shop.consultation.disabled");
    }
    if (!isValid(Consultation.class, "content", content)) {
        return ERROR_MESSAGE;
    }
    Member member = memberService.getCurrent();
    if (setting.getConsultationAuthority() != ConsultationAuthority.anyone && member == null) {
        return Message.error("shop.consultation.accessDenied");
    }
    Product product = productService.find(id);
    if (product == null) {
        return ERROR_MESSAGE;
    }
    Consultation consultation = new Consultation();
    consultation.setContent(content);
    consultation.setIp(request.getRemoteAddr());
    consultation.setMember(member);
    consultation.setProduct(product);
    if (setting.getIsConsultationCheck()) {
        consultation.setIsShow(false);
        consultationService.save(consultation);
        return Message.success("shop.consultation.check");
    } else {
        consultation.setIsShow(true);
        consultationService.save(consultation);
        return Message.success("shop.consultation.success");
    }
}


@RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
public String content(Long id,Integer pageNumber,ModelMap model){
    Setting setting = SettingUtils.get();
    if (!setting.getIsConsultationEnabled()) {
        throw new ResourceNotFoundException();
    }
    Product product = productService.find(id);
    if (product == null) {
        throw new ResourceNotFoundException();
    }
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("product", product);
    model.addAttribute("page", consultationService.findPage(null, product, true, pageable));
    return "/shop/consultation/content";
}


}