package com.lingxiang2014.controller.shop.member;
 import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.CommonAttributes;
import com.lingxiang2014.Setting;
import com.lingxiang2014.controller.shop.BaseController;
import com.lingxiang2014.entity.Area;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.MemberAttribute;
import com.lingxiang2014.entity.Member.Gender;
import com.lingxiang2014.entity.MemberAttribute.Type;
import com.lingxiang2014.service.AreaService;
import com.lingxiang2014.service.MemberAttributeService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.Interface.AreaService;
@Controller("shopMemberProfileController")
@RequestMapping("/member/profile")
public class ProfileController extends BaseController{

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "memberAttributeServiceImpl")
 private  MemberAttributeService memberAttributeService;

@Resource(name = "areaServiceImpl")
 private  AreaService areaService;


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(ModelMap model){
    model.addAttribute("genders", Gender.values());
    model.addAttribute("memberAttributes", memberAttributeService.findList());
    return "shop/member/profile/edit";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(String email,String qq,String password1,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (!isValid(Member.class, "email", email)) {
        return ERROR_VIEW;
    }
    Setting setting = SettingUtils.get();
    Member member = memberService.getCurrent();
    if (!setting.getIsDuplicateEmail() && !memberService.emailUnique(member.getEmail(), email)) {
        return ERROR_VIEW;
    }
    if (password1 == null || "".equals(password1)) {
        member.setPassword2(member.getPassword2());
    } else {
        member.setPassword2(DigestUtils.md5Hex(password1));
    }
    member.setEmail(email);
    member.setIsComplate(true);
    member.setQq(qq);
    List<MemberAttribute> memberAttributes = memberAttributeService.findList();
    for (MemberAttribute memberAttribute : memberAttributes) {
        String parameter = request.getParameter("memberAttribute_" + memberAttribute.getId());
        if (memberAttribute.getType() == Type.name || memberAttribute.getType() == Type.address || memberAttribute.getType() == Type.zipCode || memberAttribute.getType() == Type.phone || memberAttribute.getType() == Type.mobile || memberAttribute.getType() == Type.text || memberAttribute.getType() == Type.select) {
            if (memberAttribute.getIsRequired() && StringUtils.isEmpty(parameter)) {
                return ERROR_VIEW;
            }
            member.setAttributeValue(memberAttribute, parameter);
        } else if (memberAttribute.getType() == Type.gender) {
            Gender gender = StringUtils.isNotEmpty(parameter) ? Gender.valueOf(parameter) : null;
            if (memberAttribute.getIsRequired() && gender == null) {
                return ERROR_VIEW;
            }
            member.setGender(gender);
        } else if (memberAttribute.getType() == Type.birth) {
            try {
                Date birth = StringUtils.isNotEmpty(parameter) ? DateUtils.parseDate(parameter, CommonAttributes.DATE_PATTERNS) : null;
                if (memberAttribute.getIsRequired() && birth == null) {
                    return ERROR_VIEW;
                }
                member.setBirth(birth);
            } catch (ParseException e) {
                return ERROR_VIEW;
            }
        } else if (memberAttribute.getType() == Type.area) {
            Area area = StringUtils.isNotEmpty(parameter) ? areaService.find(Long.valueOf(parameter)) : null;
            if (area != null) {
                member.setArea(area);
            } else if (memberAttribute.getIsRequired()) {
                return ERROR_VIEW;
            }
        } else if (memberAttribute.getType() == Type.checkbox) {
            String[] parameterValues = request.getParameterValues("memberAttribute_" + memberAttribute.getId());
            List<String> options = parameterValues != null ? Arrays.asList(parameterValues) : null;
            if (memberAttribute.getIsRequired() && (options == null || options.isEmpty())) {
                return ERROR_VIEW;
            }
            member.setAttributeValue(memberAttribute, options);
        }
    }
    memberService.update(member);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:edit.jhtml";
}


@RequestMapping(value = "/check_email", method = RequestMethod.GET)
@ResponseBody
public boolean checkEmail(String email){
    if (StringUtils.isEmpty(email)) {
        return false;
    }
    Member member = memberService.getCurrent();
    if (memberService.emailUnique(member.getEmail(), email)) {
        return true;
    } else {
        return false;
    }
}


}