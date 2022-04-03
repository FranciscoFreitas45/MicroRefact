package com.lingxiang2014.controller.admin;
 import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.CommonAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.Principal;
import com.lingxiang2014.Setting;
import com.lingxiang2014.entity.Admin;
import com.lingxiang2014.entity.Area;
import com.lingxiang2014.entity.Deposit;
import com.lingxiang2014.entity.BaseEntity.Save;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Member.Gender;
import com.lingxiang2014.entity.MemberAttribute;
import com.lingxiang2014.entity.MemberAttribute.Type;
import com.lingxiang2014.entity.Withdraw.Status;
import com.lingxiang2014.service.AdminService;
import com.lingxiang2014.service.AreaService;
import com.lingxiang2014.service.BonudsService;
import com.lingxiang2014.service.DepositService;
import com.lingxiang2014.service.MemberAttributeService;
import com.lingxiang2014.service.MemberRankService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.service.StaticBonudsRankService;
import com.lingxiang2014.service.WithdrawService;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.util.WebUtils;
import com.lingxiang2014.Interface.AreaService;
import com.lingxiang2014.Interface.AdminService;
import com.lingxiang2014.Interface.StaticBonudsRankService;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.DepositService;
import com.lingxiang2014.Interface.WithdrawService;
@Controller("adminMemberController")
@RequestMapping("/admin/member")
public class MemberController extends BaseController{

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "memberRankServiceImpl")
 private  MemberRankService memberRankService;

@Resource(name = "memberAttributeServiceImpl")
 private  MemberAttributeService memberAttributeService;

@Resource(name = "areaServiceImpl")
 private  AreaService areaService;

@Resource(name = "adminServiceImpl")
 private  AdminService adminService;

@Resource(name = "staticBonudsRankServiceImpl")
 private  StaticBonudsRankService staticBonudsRankService;

@Resource(name = "bonudsServiceImpl")
 private  BonudsService bonudsService;

@Resource(name = "depositServiceImpl")
 private  DepositService depositService;

@Resource(name = "withdrawServiceImpl")
 private  WithdrawService withdrawService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    model.addAttribute("genders", Gender.values());
    model.addAttribute("memberRanks", memberRankService.findAll());
    model.addAttribute("memberAttributes", memberAttributeService.findList());
    return "/admin/member/add";
}


public int compare(String s1,String s2){
    return s1.hashCode() - s2.hashCode();
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("genders", Gender.values());
    model.addAttribute("memberRanks", memberRankService.findAll());
    model.addAttribute("memberAttributes", memberAttributeService.findList());
    model.addAttribute("member", memberService.find(id));
    return "/admin/member/edit";
}


@RequestMapping(value = "/groupStructure", method = RequestMethod.GET)
public String groupStructure(ModelMap model,Long memberId){
    Member member1 = memberService.find(memberId);
    model.addAttribute("member1", member1);
    return "admin/member/groupStructure";
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


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Member member,Long memberRankId,HttpServletRequest request,RedirectAttributes redirectAttributes){
    member.init();
    member.setNumber(memberService.getMemberNumber(null));
    member.setMemberRank(memberRankService.find(memberRankId));
    if (!isValid(member, Save.class)) {
        return ERROR_VIEW;
    }
    Setting setting = SettingUtils.get();
    if (member.getUsername().length() < setting.getUsernameMinLength() || member.getUsername().length() > setting.getUsernameMaxLength()) {
        return ERROR_VIEW;
    }
    if (member.getPassword().length() < setting.getPasswordMinLength() || member.getPassword().length() > setting.getPasswordMaxLength()) {
        return ERROR_VIEW;
    }
    if (memberService.usernameDisabled(member.getUsername()) || memberService.usernameExists(member.getUsername())) {
        return ERROR_VIEW;
    }
    if (!setting.getIsDuplicateEmail() && memberService.emailExists(member.getEmail())) {
        return ERROR_VIEW;
    }
    member.removeAttributeValue();
    for (MemberAttribute memberAttribute : memberAttributeService.findList()) {
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
    member.setUsername(member.getUsername().toLowerCase());
    member.setPassword(DigestUtils.md5Hex(member.getPassword()));
    member.setPoint(setting.getRegisterPoint());
    member.setRegisterIp(request.getRemoteAddr());
    member.setLoginIp(request.getRemoteAddr());
    member.setMemberRank(memberRankService.findDefault());
    member.setPassword2(DigestUtils.md5Hex(DigestUtils.md5Hex(member.getPassword2())));
    member.setStaticBonudsRank(staticBonudsRankService.findDefault());
    memberService.save(member, adminService.getCurrent());
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Member member,Long memberRankId,Integer modifyPoint,BigDecimal modifyBalance,String depositMemo,HttpServletRequest request,RedirectAttributes redirectAttributes){
    member.setMemberRank(memberRankService.find(memberRankId));
    if (!isValid(member)) {
        return ERROR_VIEW;
    }
    Setting setting = SettingUtils.get();
    if (member.getPassword() != null && (member.getPassword().length() < setting.getPasswordMinLength() || member.getPassword().length() > setting.getPasswordMaxLength())) {
        return ERROR_VIEW;
    }
    Member pMember = memberService.find(member.getId());
    if (pMember == null) {
        return ERROR_VIEW;
    }
    if (!setting.getIsDuplicateEmail() && !memberService.emailUnique(pMember.getEmail(), member.getEmail())) {
        return ERROR_VIEW;
    }
    member.removeAttributeValue();
    for (MemberAttribute memberAttribute : memberAttributeService.findList()) {
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
    if (StringUtils.isEmpty(member.getPassword())) {
        member.setPassword(pMember.getPassword());
    } else {
        member.setPassword(DigestUtils.md5Hex(member.getPassword()));
    }
    if (pMember.getIsLocked() && !member.getIsLocked()) {
        member.setLoginFailureCount(0);
        member.setLockedDate(null);
    } else {
        member.setIsLocked(pMember.getIsLocked());
        member.setLoginFailureCount(pMember.getLoginFailureCount());
        member.setLockedDate(pMember.getLockedDate());
    }
    BeanUtils.copyProperties(member, pMember, Member.NOT_COPY_PROPERTIES);
    memberService.update(pMember, modifyPoint, modifyBalance, depositMemo, adminService.getCurrent());
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("memberRanks", memberRankService.findAll());
    model.addAttribute("memberAttributes", memberAttributeService.findAll());
    model.addAttribute("page", memberService.findPage(pageable));
    return "/admin/member/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    if (ids != null) {
        for (Long id : ids) {
            Member member = memberService.find(id);
            if (member != null && member.getBalance().compareTo(new BigDecimal(0)) > 0) {
                return Message.error("admin.member.deleteExistDepositNotAllowed", member.getUsername());
            }
        }
        try {
            memberService.delete(ids);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return ERROR_MESSAGE;
        }
    }
    return SUCCESS_MESSAGE;
}


@RequestMapping(value = "/entry", method = RequestMethod.GET)
public String entry(ModelMap model,Long memberId,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    Admin admin = adminService.getCurrent();
    if (admin != null) {
        Member member = memberService.find(memberId);
        if (member != null) {
            session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(member.getId(), member.getNumber()));
            WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME, member.getNumber());
            model.addAttribute("member", member);
            return "redirect:/member/index.jhtml?type=admin&number=" + member.getNumber();
        } else {
            return "redirect:list.jhtml";
        }
    }
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/view", method = RequestMethod.GET)
public String view(Date month,Long id,ModelMap model){
    Member member = memberService.find(id);
    if (month == null) {
        month = new Date();
    }
    Map<String, BigDecimal> map0 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map1 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map2 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map3 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map4 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map5 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map6 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map7 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map8 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map9 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map10 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    BigDecimal money0 = new BigDecimal(0);
    BigDecimal money1 = new BigDecimal(0);
    BigDecimal money2 = new BigDecimal(0);
    BigDecimal money3 = new BigDecimal(0);
    BigDecimal money4 = new BigDecimal(0);
    BigDecimal money5 = new BigDecimal(0);
    BigDecimal money6 = new BigDecimal(0);
    BigDecimal money7 = new BigDecimal(0);
    BigDecimal money8 = new BigDecimal(0);
    BigDecimal money9 = new BigDecimal(0);
    BigDecimal money10 = new BigDecimal(0);
    BigDecimal money00 = new BigDecimal(0);
    BigDecimal money11 = new BigDecimal(0);
    BigDecimal money22 = new BigDecimal(0);
    BigDecimal money33 = new BigDecimal(0);
    BigDecimal money44 = new BigDecimal(0);
    BigDecimal money55 = new BigDecimal(0);
    BigDecimal money66 = new BigDecimal(0);
    BigDecimal money77 = new BigDecimal(0);
    BigDecimal money88 = new BigDecimal(0);
    BigDecimal money99 = new BigDecimal(0);
    BigDecimal money100 = new BigDecimal(0);
    Calendar beginCalendar = DateUtils.toCalendar(month);
    Integer days = beginCalendar.getActualMaximum(Calendar.DATE);
    DateTime date = new DateTime(beginCalendar.get(Calendar.YEAR), beginCalendar.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
    Date beginDate = date.toDate();
    date = new DateTime(beginCalendar.get(Calendar.YEAR), beginCalendar.get(Calendar.MONTH) + 1, 1, 23, 59, 59);
    Date endDate = date.toDate();
    for (int i = 0; i < days; i++) {
        money00 = depositService.count(member, Deposit.Type.memberRecharge, beginDate, endDate);
        money11 = bonudsService.count(member, null, beginDate, endDate);
        money22 = bonudsService.count(member, com.lingxiang2014.entity.Bonuds.Type.duiPengBonuds, beginDate, endDate);
        money33 = bonudsService.count(member, com.lingxiang2014.entity.Bonuds.Type.huzhuBonuds, beginDate, endDate);
        money44 = bonudsService.count(member, com.lingxiang2014.entity.Bonuds.Type.jianDianBonuds, beginDate, endDate);
        money55 = bonudsService.count(member, com.lingxiang2014.entity.Bonuds.Type.leaderBonuds, beginDate, endDate);
        money66 = bonudsService.count(member, com.lingxiang2014.entity.Bonuds.Type.salesBonuds, beginDate, endDate);
        money77 = bonudsService.count(member, com.lingxiang2014.entity.Bonuds.Type.serviceBonuds, beginDate, endDate);
        money88 = bonudsService.count(member, com.lingxiang2014.entity.Bonuds.Type.signInBonuds, beginDate, endDate);
        money99 = bonudsService.count(member, com.lingxiang2014.entity.Bonuds.Type.staticBonuds, beginDate, endDate);
        money100 = withdrawService.count(member, Status.success, beginDate, endDate);
        map0.put((i + 1) + "", money00);
        map1.put((i + 1) + "", money11);
        map2.put((i + 1) + "", money22);
        map3.put((i + 1) + "", money33);
        map4.put((i + 1) + "", money44);
        map5.put((i + 1) + "", money55);
        map6.put((i + 1) + "", money66);
        map7.put((i + 1) + "", money77);
        map8.put((i + 1) + "", money88);
        map9.put((i + 1) + "", money99);
        map10.put((i + 1) + "", money100);
        money0 = money0.add(money00);
        money1 = money1.add(money11);
        money2 = money2.add(money22);
        money3 = money3.add(money33);
        money4 = money4.add(money44);
        money5 = money5.add(money55);
        money6 = money6.add(money66);
        money7 = money7.add(money77);
        money8 = money8.add(money88);
        money9 = money9.add(money99);
        money10 = money10.add(money100);
        date = new DateTime(beginCalendar.get(Calendar.YEAR), beginCalendar.get(Calendar.MONTH) + 1, (i + 1), 0, 0, 0);
        beginDate = date.toDate();
        date = new DateTime(beginCalendar.get(Calendar.YEAR), beginCalendar.get(Calendar.MONTH) + 1, (i + 1), 23, 59, 59);
        endDate = date.toDate();
    }
    model.addAttribute("month", month);
    model.addAttribute("map0", map0);
    model.addAttribute("map1", map1);
    model.addAttribute("map2", map2);
    model.addAttribute("map3", map3);
    model.addAttribute("map4", map4);
    model.addAttribute("map5", map5);
    model.addAttribute("map6", map6);
    model.addAttribute("map7", map7);
    model.addAttribute("map8", map8);
    model.addAttribute("map9", map9);
    model.addAttribute("map10", map10);
    model.addAttribute("money0", money0);
    model.addAttribute("money1", money1);
    model.addAttribute("money2", money2);
    model.addAttribute("money3", money3);
    model.addAttribute("money4", money4);
    model.addAttribute("money5", money5);
    model.addAttribute("money6", money6);
    model.addAttribute("money7", money7);
    model.addAttribute("money8", money8);
    model.addAttribute("money9", money9);
    model.addAttribute("money10", money10);
    model.addAttribute("genders", Gender.values());
    model.addAttribute("memberAttributes", memberAttributeService.findList());
    model.addAttribute("member", member);
    return "/admin/member/view";
}


@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
@ResponseBody
public Message resetPwd(Long id){
    if (id != null) {
        Member member = memberService.find(id);
        if (member != null) {
            Setting setting = SettingUtils.get();
            member.setPassword(DigestUtils.md5Hex(setting.getDefaultPassword()));
            member.setPassword2(DigestUtils.md5Hex(setting.getDefaultPassword1()));
            memberService.update(member);
        }
    }
    return SUCCESS_MESSAGE;
}


@RequestMapping(value = "/check_email", method = RequestMethod.GET)
@ResponseBody
public boolean checkEmail(String previousEmail,String email){
    if (StringUtils.isEmpty(email)) {
        return false;
    }
    if (memberService.emailUnique(previousEmail, email)) {
        return true;
    } else {
        return false;
    }
}


}