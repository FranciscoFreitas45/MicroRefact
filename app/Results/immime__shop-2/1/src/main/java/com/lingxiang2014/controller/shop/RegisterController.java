package com.lingxiang2014.controller.shop;
 import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lingxiang2014.CommonAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.Principal;
import com.lingxiang2014.Setting;
import com.lingxiang2014.Setting.CaptchaType;
import com.lingxiang2014.entity.Area;
import com.lingxiang2014.entity.BaseEntity.Save;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Member.Gender;
import com.lingxiang2014.entity.MemberAttribute;
import com.lingxiang2014.entity.MemberAttribute.Type;
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
@Controller("shopRegisterController")
@RequestMapping("/register")
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
public Message submit(String captchaId,String captcha,String username,String number,String topNumber,String password1,String parent,String email,Integer zone,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    String password = rsaService.decryptParameter("enPassword", request);
    rsaService.removePrivateKey(request);
    Member pMember = memberService.findByNumber(parent);
    if (!captchaService.isValid(CaptchaType.memberRegister, captchaId, captcha)) {
        return Message.error("shop.captcha.invalid");
    }
    Setting setting = SettingUtils.get();
    if (!setting.getIsRegisterEnabled()) {
        return Message.error("shop.register.disabled");
    }
    if (!isValid(Member.class, "username", username, Save.class) || !isValid(Member.class, "password", password, Save.class) || !isValid(Member.class, "email", email, Save.class)) {
        return Message.error("shop.common.invalid");
    }
    if (username.length() < setting.getUsernameMinLength() || username.length() > setting.getUsernameMaxLength()) {
        return Message.error("shop.common.invalid");
    }
    if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength()) {
        return Message.error("shop.common.invalid");
    }
    if (memberService.usernameDisabled(username) || memberService.usernameExists(username)) {
        return Message.error("shop.register.disabledExist");
    }
    if (!setting.getIsDuplicateEmail() && memberService.emailExists(email)) {
        return Message.error("shop.register.emailExist");
    }
    Member member = new Member();
    List<MemberAttribute> memberAttributes = memberAttributeService.findList();
    for (MemberAttribute memberAttribute : memberAttributes) {
        String parameter = request.getParameter("memberAttribute_" + memberAttribute.getId());
        if (memberAttribute.getType() == Type.name || memberAttribute.getType() == Type.address || memberAttribute.getType() == Type.zipCode || memberAttribute.getType() == Type.phone || memberAttribute.getType() == Type.mobile || memberAttribute.getType() == Type.text || memberAttribute.getType() == Type.select) {
            if (memberAttribute.getIsRequired() && StringUtils.isEmpty(parameter)) {
                return Message.error("shop.common.invalid");
            }
            member.setAttributeValue(memberAttribute, parameter);
        } else if (memberAttribute.getType() == Type.gender) {
            Gender gender = StringUtils.isNotEmpty(parameter) ? Gender.valueOf(parameter) : null;
            if (memberAttribute.getIsRequired() && gender == null) {
                return Message.error("shop.common.invalid");
            }
            member.setGender(gender);
        } else if (memberAttribute.getType() == Type.birth) {
            try {
                Date birth = StringUtils.isNotEmpty(parameter) ? DateUtils.parseDate(parameter, CommonAttributes.DATE_PATTERNS) : null;
                if (memberAttribute.getIsRequired() && birth == null) {
                    return Message.error("shop.common.invalid");
                }
                member.setBirth(birth);
            } catch (ParseException e) {
                return Message.error("shop.common.invalid");
            }
        } else if (memberAttribute.getType() == Type.area) {
            Area area = StringUtils.isNotEmpty(parameter) ? areaService.find(Long.valueOf(parameter)) : null;
            if (area != null) {
                member.setArea(area);
            } else if (memberAttribute.getIsRequired()) {
                return Message.error("shop.common.invalid");
            }
        } else if (memberAttribute.getType() == Type.checkbox) {
            String[] parameterValues = request.getParameterValues("memberAttribute_" + memberAttribute.getId());
            List<String> options = parameterValues != null ? Arrays.asList(parameterValues) : null;
            if (memberAttribute.getIsRequired() && (options == null || options.isEmpty())) {
                return Message.error("shop.common.invalid");
            }
            member.setAttributeValue(memberAttribute, options);
        }
    }
    member.init();
    /**
     * 查询推荐人线上的第一个叶子节点（从左往右）
     */
    Member top = memberService.findLeaf(1);
    member.setTop(top);
    member.setLeve(top.getLeve() + 1);
    member.setParent(pMember);
    pMember.getChildren().add(member);
    member.setNumber(memberService.getMemberNumber(number));
    member.setUsername(username.toLowerCase());
    member.setPassword(DigestUtils.md5Hex(password));
    member.setEmail(email);
    member.setPoint(setting.getRegisterPoint());
    member.setRegisterIp(request.getRemoteAddr());
    member.setLoginIp(request.getRemoteAddr());
    member.setMemberRank(memberRankService.findDefault());
    member.setPassword2(DigestUtils.md5Hex(password1));
    member.setStaticBonudsRank(staticBonudsRankService.findDefault());
    memberService.save(member);
    if (top != null) {
        if (zone == null) {
            // 随便放
            if (top.getLeftChildren() == null) {
                top.setLeftChildren(member);
            } else if (top.getRightChildren() == null) {
                top.setRightChildren(member);
            }
        } else if (zone == 0) {
            // 左边
            if (top.getLeftChildren() == null) {
                top.setLeftChildren(member);
            }
        } else {
            // 右边
            if (top.getRightChildren() == null) {
                top.setRightChildren(member);
            }
        }
        if (top.getLeftChildren() != null && top.getRightChildren() == null) {
            top.setIsLeaf(false);
        }
        memberService.update(top);
    }
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
    session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(member.getId(), member.getUsername()));
    WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME, member.getUsername());
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
public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model,String parent){
    model.addAttribute("genders", Gender.values());
    model.addAttribute("captchaId", UUID.randomUUID().toString());
    model.addAttribute("number", memberService.getMemberNumber(null));
    model.addAttribute("parent", parent);
    model.addAttribute("top", memberService.findLeaf(memberService.getCurrent(), null, null));
    return "/shop/register/index";
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