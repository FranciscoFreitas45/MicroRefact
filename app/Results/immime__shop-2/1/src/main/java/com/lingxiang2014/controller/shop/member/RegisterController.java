package com.lingxiang2014.controller.shop.member;
 import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.Principal;
import com.lingxiang2014.Setting;
import com.lingxiang2014.Setting.CaptchaType;
import com.lingxiang2014.controller.shop.BaseController;
import com.lingxiang2014.entity.BaseEntity.Save;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Member.Zone;
import com.lingxiang2014.service.AreaService;
import com.lingxiang2014.service.CaptchaService;
import com.lingxiang2014.service.MemberAttributeService;
import com.lingxiang2014.service.MemberRankService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.service.RSAService;
import com.lingxiang2014.service.StaticBonudsRankService;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.util.WebUtils;
import com.lingxiang2014.Interface.AreaService;
import com.lingxiang2014.Interface.StaticBonudsRankService;
import com.lingxiang2014.DTO.Message;
@Controller("shopMemberRegisterController")
@RequestMapping("/member/register")
public class RegisterController extends BaseController{

@Resource(name = "captchaServiceImpl")
 private  CaptchaService captchaService;

@Resource(name = "rsaServiceImpl")
 private  RSAService rsaService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "memberRankServiceImpl")
 private  MemberRankService memberRankService;

@Resource(name = "memberAttributeServiceImpl")
 private  MemberAttributeService memberAttributeService;

@Resource(name = "areaServiceImpl")
 private  AreaService areaService;

@Resource(name = "staticBonudsRankServiceImpl")
 private  StaticBonudsRankService staticBonudsRankService;


@RequestMapping(value = "/submit", method = RequestMethod.POST)
@ResponseBody
public Message submit(String captchaId,String captcha,String username,String number,String parent,String zone,String topNumber,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    String password = rsaService.decryptParameter("enPassword", request);
    rsaService.removePrivateKey(request);
    if (!captchaService.isValid(CaptchaType.memberRegister, captchaId, captcha)) {
        return Message.error("shop.captcha.invalid");
    }
    Setting setting = SettingUtils.get();
    if (!setting.getIsRegisterEnabled()) {
        return Message.error("shop.register.disabled");
    }
    if (!isValid(Member.class, "username", username, Save.class) || !isValid(Member.class, "password", password, Save.class)) {
        System.out.println("zhelwenti");
        return Message.error("shop.common.invalid");
    }
    if (username.length() < setting.getUsernameMinLength() || username.length() > setting.getUsernameMaxLength()) {
        System.out.println("username.length()");
        return Message.error("shop.common.invalid");
    }
    if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength()) {
        System.out.println("password.length()");
        return Message.error("shop.common.invalid");
    }
    if (memberService.usernameDisabled(username) || memberService.usernameExists(username)) {
        return Message.error("shop.register.disabledExist");
    }
    Member member = new Member();
    member.init();
    // 根据编号找到节点人
    Member top = memberService.findByNumber(topNumber);
    // 获取推荐人
    Member pMember = memberService.findByNumber(parent);
    if (pMember.getMyPeople() == 0) {
        // 没有推荐别人或者是推荐的人没有一个激活了。那就是放在推荐人的左边，节点人的左边
        // 找到推荐人左边的会员，判断当前选择的节点人是否是在该会员的左边
        top = memberService.findChild(pMember, Zone.left);
    } else {
        // 推荐的人有激活的。那么就是放在选择的区域
        System.out.println(zone);
        top = memberService.findChild(pMember, Zone.valueOf(zone));
        System.out.println(top);
    }
    member.setLeve(top.getLeve() + 1);
    member.setTop(top);
    member.setParent(pMember);
    pMember.getChildren().add(member);
    member.setNumber(memberService.getMemberNumber(number));
    member.setUsername(username.toLowerCase());
    member.setPassword(DigestUtils.md5Hex(password));
    member.setEmail("");
    member.setPoint(setting.getRegisterPoint());
    member.setRegisterIp(request.getRemoteAddr());
    member.setLoginIp(request.getRemoteAddr());
    member.setMemberRank(memberRankService.findDefault());
    member.setPassword2(member.getPassword());
    member.setStaticBonudsRank(staticBonudsRankService.findDefault());
    memberService.save(member);
    if (pMember.getMyPeople() == 0) {
        // 没有推荐别人或者是推荐的人没有一个激活了。那就是放在推荐人的左边，节点人的左边
        // 找到推荐人左边的会员，判断当前选择的节点人是否是在该会员的左边
        top = memberService.findChild(pMember, Zone.left);
        top.setLeftChildren(member);
    } else {
        // 推荐的人有激活的。那么就是放在选择的区域
        top = memberService.findChild(pMember, Zone.valueOf(zone));
        if (Zone.left == Zone.valueOf(zone)) {
            top.setLeftChildren(member);
        } else {
            if (top == pMember) {
                top.setRightChildren(member);
            } else {
                top.setLeftChildren(member);
            }
        }
    }
    memberService.update(top);
    Map<String, Object> attributes = new HashMap<String, Object>();
    Enumeration<?> keys = session.getAttributeNames();
    while (keys.hasMoreElements()) {
        String key = (String) keys.nextElement();
        attributes.put(key, session.getAttribute(key));
    }
    session.invalidate();
    session = request.getSession();
    for (Entry<String, Object> entry : attributes.entrySet()) {
        session.setAttribute(entry.getKey(), entry.getValue());
    }
    session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(pMember.getId(), pMember.getUsername()));
    WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME, pMember.getUsername());
    return Message.success("shop.register.success");
}


@RequestMapping(value = "/check_parent", method = RequestMethod.GET)
@ResponseBody
public boolean checkParent(String parent){
    if (StringUtils.isEmpty(parent)) {
        return false;
    }
    if (memberService.numberExists(parent)) {
        return true;
    } else {
        return false;
    }
}


@RequestMapping(value = "/check_top", method = RequestMethod.GET)
@ResponseBody
public boolean checkTop(String topNumber){
    if (StringUtils.isEmpty(topNumber)) {
        return false;
    }
    if (memberService.numberExists(topNumber)) {
        return true;
    } else {
        return false;
    }
}


@RequestMapping(value = "/check_username", method = RequestMethod.GET)
@ResponseBody
public boolean checkUsername(String username){
    if (StringUtils.isEmpty(username)) {
        return false;
    }
    if (memberService.usernameDisabled(username) || memberService.usernameExists(username)) {
        return false;
    } else {
        return true;
    }
}


@RequestMapping(method = RequestMethod.GET)
public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model,String topNumber,RedirectAttributes redirectAttributes){
    model.addAttribute("zones", Zone.values());
    model.addAttribute("captchaId", UUID.randomUUID().toString());
    model.addAttribute("number", memberService.getMemberNumber(null));
    model.addAttribute("parent", memberService.getCurrent());
    Member top = memberService.findByNumber(topNumber);
    if (top == null) {
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:/member/groupStructure.jhtml";
    }
    model.addAttribute("top", top);
    return "/shop/member/register/index";
}


@RequestMapping(value = "/changeNumber", method = RequestMethod.POST)
@ResponseBody
public Map<String,Object> changeNumber(){
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("number", memberService.getMemberNumber(null));
    data.put("message", SUCCESS_MESSAGE);
    return data;
}


@RequestMapping(value = "/check_email", method = RequestMethod.GET)
@ResponseBody
public boolean checkEmail(String email){
    if (StringUtils.isEmpty(email)) {
        return false;
    }
    if (memberService.emailExists(email)) {
        return false;
    } else {
        return true;
    }
}


}