package com.wxcrm.mysite;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.wxcrm.common.IMemcachedService;
import com.wxcrm.goods.IGoodsService;
import com.wxcrm.goods.WcGoods;
import com.wxcrm.goods.WcGoodsFenlei;
import com.wxcrm.goods.WcGoodsIn;
import com.wxcrm.member.IWeiMemberService;
import com.wxcrm.member.LzWeiMember;
import com.wxcrm.sys.IAdminService;
import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.DateUtil;
import com.wxcrm.util.SessionUtil;
import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;
import com.wxcrm.util.ThreeDes;
import com.wxcrm.website.IWebsiteService;
import com.wxcrm.website.WcWebsite;
import com.wxcrm.weixin.IWeixinService;
import com.wxcrm.weixin.LzWeiEnter;
import com.wxcrm.Interface.IMemcachedService;
import com.wxcrm.Interface.IAdminService;
import com.wxcrm.Interface.IWeiMemberService;
import com.wxcrm.Interface.IWeixinService;
import com.wxcrm.Interface.IWebsiteService;
import com.wxcrm.DTO.WcWebsite;
import com.wxcrm.DTO.WcAdmin;
import com.wxcrm.DTO.LzWeiEnter;
import com.wxcrm.DTO.LzWeiMember;
@Controller
@RequestMapping("/mysite")
public class MySiteController {

@Autowired
 private  IMemcachedService memcachedservice;

@Autowired
 private  IAdminService adminService;

@Autowired
 private  IWeiMemberService weimemberservice;

@Autowired
 private  IWeixinService weixinservice;

@Autowired
 private  IWebsiteService websiteservice;

@Autowired
 private  IGoodsService goodsService;


@RequestMapping(value = "/toAddGoods/{wgfId}")
public String toAddGoods(WcGoods goods,Integer wgfId,Model model){
    WcGoodsFenlei fenlei = goodsService.getFeileiById(wgfId);
    goods.setWgsWgfId(wgfId);
    goods.setWgsWcsId(fenlei.getWgfWcsId());
    goods.setWgfName(fenlei.getWgfName());
    return "/goods/addGoods";
}


@RequestMapping(value = "/toUpdGoodsFenlei", method = RequestMethod.POST)
public String toUpdGoodsFenlei(WcGoodsFenlei Fenlei_Q,Model model){
    WcGoodsFenlei fenlei = goodsService.getFeileiById(Fenlei_Q.getWgfId());
    StringUtil.copyProperties(Fenlei_Q, fenlei);
    model.addAttribute("command", fenlei);
    return "/goods/updGoodsFenlei";
}


@RequestMapping(value = "/updGoods", method = RequestMethod.POST)
public String updGoods(WcGoods goods,HttpServletRequest request,RedirectAttributes redirectAttribute){
    goodsService.updGoods(goods);
    redirectAttribute.addFlashAttribute("msgCode", "2");
    redirectAttribute.addFlashAttribute("alertMsg", "��Ʒ�޸ĳɹ�");
    redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/mysite/queryGoods/" + goods.getWgsWgfId(), goods));
    return "redirect:/admin/toMsg";
}


@RequestMapping(value = "/queryGoodsFenlei")
public String queryGoodsFenlei(WcGoodsFenlei feilei,HttpSession session,Model model){
    WcWebsite website = (WcWebsite) session.getAttribute(SysConstant.WEBSITE_INFO);
    feilei.setWgfWcsId(website.getWcsId());
    model.addAttribute(SysConstant.PAGE_RESULT, goodsService.queryGoodsFeilei(feilei));
    return "/goods/queryGoodsFenlei";
}


@RequestMapping(value = "/queryGoods/{wfgId}")
public String queryGoods(WcGoods goods,Integer wfgId,Model model){
    goods.setWgsWgfId(wfgId);
    WcGoodsFenlei fenlei = goodsService.getFeileiById(wfgId);
    model.addAttribute("fenlei", fenlei);
    model.addAttribute(SysConstant.PAGE_RESULT, goodsService.queryGoods(goods));
    return "/goods/queryGoods";
}


@RequestMapping(value = "/toInGoods", method = RequestMethod.POST)
public String toInGoods(WcGoods goods_Q,Model model){
    WcGoods goods = goodsService.getGoodsById(goods_Q.getWgsId());
    WcGoodsIn goodsIn = new WcGoodsIn();
    goodsIn.setWgiInTime(DateUtil.parseString(new Date(), "yyyy-MM-dd"));
    goodsIn.setWgiWgsId(goods.getWgsId());
    goodsIn.setWgiWcsId(goods.getWgsWcsId());
    model.addAttribute("goods", goods);
    model.addAttribute("command", goodsIn);
    return "/goods/addInGoods";
}


@RequestMapping(value = "/addInGoods")
public String addInGoods(WcGoodsIn goodsIn,HttpSession session,RedirectAttributes redirectAttribute){
    WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO_WX);
    goodsIn.setWgiInAdmin(admin.getWadId());
    goodsIn.setWgiSurplusNum(goodsIn.getWgiInNum());
    goodsIn.setWgiStatus("1000");
    goodsIn.setWgiRegistor(admin.getWadId());
    goodsIn.setWgiRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
    goodsService.addGoodsIn(goodsIn);
    redirectAttribute.addFlashAttribute("alertMsg", "���ɹ�!");
    WcGoods goods = goodsService.getGoodsById(goodsIn.getWgiWgsId());
    return "redirect:/mysite/queryGoods/" + goods.getWgsWgfId();
}


@RequestMapping("/login")
public String adminLogin(WcAdmin admin,HttpServletResponse response,HttpSession session,Model model,String usernameCookie,String pwdMd5Cookie,String pwd3desCookie,String rediUriCookie){
    // cookie��½
    if (usernameCookie != null && pwdMd5Cookie != null && pwd3desCookie != null) {
        admin.setWadUsername(usernameCookie);
        admin.setWadPwdMd5(pwdMd5Cookie);
        admin.setWadPwd(ThreeDes.decode(pwd3desCookie));
    } else {
        // ��ת��½ҳ��
        if (admin.getWadUsername() == null) {
            return "/mysite/login/login";
        } else // ҳ���½
        {
            admin.setWadPwdMd5(DigestUtils.md5DigestAsHex(admin.getWadPwd().getBytes()));
        }
    }
    if (adminService.chkUsernameUnique(admin.getWadUsername())) {
        model.addAttribute("usernameError", "�û��������ڣ��Ƿ�ע�᣿");
        return "/mysite/login/login";
    }
    WcAdmin adminResult = adminService.adminLogin(admin);
    if (adminResult == null) {
        model.addAttribute("alertMsg", "�������");
        return "/mysite/login/login";
    } else {
        adminResult.setWadPwdMd5(adminResult.getWadPwd());
        adminResult.setWadPwd(admin.getWadPwd());
        if (admin.isRemember()) {
            Cookie cookieUsername = new Cookie(SysConstant.ADMIN_USERNAME_WX, adminResult.getWadUsername());
            cookieUsername.setMaxAge(SysConstant.COOKIE_AGE);
            cookieUsername.setPath("/");
            response.addCookie(cookieUsername);
            Cookie cookiePwdMd5 = new Cookie(SysConstant.ADMIN_PWD_MD5_WX, adminResult.getWadPwdMd5());
            cookiePwdMd5.setMaxAge(SysConstant.COOKIE_AGE);
            cookiePwdMd5.setPath("/");
            response.addCookie(cookiePwdMd5);
            Cookie cookiePwd3des = new Cookie(SysConstant.ADMIN_PWD_3DES_WX, ThreeDes.encode(adminResult.getWadPwd()));
            cookiePwd3des.setMaxAge(SysConstant.COOKIE_AGE);
            cookiePwd3des.setPath("/");
            response.addCookie(cookiePwd3des);
        }
        List<Map<String, Object>> menuList1 = adminService.queryAdminMenus(adminResult.getWadId(), "1");
        List<Map<String, Object>> menuList2 = adminService.queryAdminMenus(adminResult.getWadId(), "2");
        List<Map<String, Object>> menuList3 = adminService.queryAdminMenus(adminResult.getWadId(), "3");
        session.setAttribute(SysConstant.ADMIN_MENU_ID1_WX, menuList1.get(0).get("WME_ID").toString());
        session.setAttribute(SysConstant.ADMIN_MENU_ID2_WX, -1);
        session.setAttribute(SysConstant.ADMIN_MENU_ID3_WX, -1);
        session.setAttribute(SysConstant.ADMIN_INFO_WX, adminResult);
        WcWebsite webSite = websiteservice.queryMySiteByAdmin(adminResult.getWadId());
        session.setAttribute(SysConstant.WEBSITE_INFO, webSite);
        session.setAttribute(SysConstant.ADMIN_MENUS_LEVEL1_WX, menuList1);
        session.setAttribute(SysConstant.ADMIN_MENUS_LEVEL2_WX, menuList2);
        session.setAttribute(SysConstant.ADMIN_MENUS_LEVEL3_WX, menuList3);
        SessionUtil.removeAdminSession(session);
        adminService.updLoginTime(adminResult.getWadId());
        if (rediUriCookie == null) {
            return "redirect:/mysite/adminLoginSuccess";
        } else {
            Cookie cookieUri = new Cookie(SysConstant.LOGIN_REDIRECT_URI_WX, null);
            cookieUri.setMaxAge(0);
            cookieUri.setPath("/");
            response.addCookie(cookieUri);
            return "redirect:" + rediUriCookie;
        }
    }
}


@RequestMapping("/queryMyMember")
public String queryMyMember(LzWeiMember member,HttpSession session,Model model){
    WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO_WX);
    LzWeiEnter wec = weixinservice.getWeiEnterByAdminId(admin.getWadId());
    if (wec != null) {
        member.setWmbWecId(wec.getWecId());
    }
    model.addAttribute(SysConstant.PAGE_RESULT, weimemberservice.queryWeiMember(member));
    return "/mysite/member/queryMember";
}


@RequestMapping(value = "/addGoods")
public String addGoodsFenlei(WcGoods goods,HttpSession session,RedirectAttributes redirectAttribute){
    WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO_WX);
    goods.setWgsKucun(0.0);
    goods.setWgsRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
    goods.setWgsStatus("1000");
    goods.setWgsRegistor(admin.getWadId());
    goodsService.addGoods(goods);
    redirectAttribute.addFlashAttribute("alertMsg", "�����Ʒ�ɹ�!");
    return "redirect:/mysite/queryGoods/" + goods.getWgsWgfId();
}


@RequestMapping("/updGoodsFenlei")
public String updGoodsFenlei(WcGoodsFenlei Fenlei,HttpServletRequest request,RedirectAttributes redirectAttribute){
    goodsService.updGoodsFenlei(Fenlei);
    redirectAttribute.addFlashAttribute("msgCode", "2");
    redirectAttribute.addFlashAttribute("alertMsg", "վ���޸ĳɹ�");
    redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/mysite/queryGoodsFenlei", Fenlei));
    return "redirect:/admin/toMsg";
}


@RequestMapping("/adminLoginSuccess")
public String adminLoginSuccess(Model model,HttpSession session){
    WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO_WX);
    // �����ǰ����Ա��Ӧ���̼���ʲô
    WcWebsite mysite = websiteservice.queryMySiteByAdmin(admin.getWadId());
    model.addAttribute("mysite", mysite);
    return "/mysite/login/adminLoginSuccess";
}


@RequestMapping(value = "/toAddGoodsFenlei/{wcsId}")
public String toAddGoodsFenlei(WcGoodsFenlei feilei,Integer wcsId,Model model){
    feilei.setWgfWcsId(wcsId);
    return "/goods/addGoodsFenlei";
}


@RequestMapping(value = "/delGoods/{wgfId}")
public String delGoods(WcGoods goods,Integer wgfId,HttpServletRequest request,RedirectAttributes redirectAttribute){
    goods.setWgsWgfId(wgfId);
    goodsService.delGoods(goods);
    redirectAttribute.addFlashAttribute("msgCode", "2");
    redirectAttribute.addFlashAttribute("alertMsg", "��Ʒɾ���ɹ�");
    redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/mysite/queryGoods/" + goods.getWgsWgfId(), goods));
    return "redirect:/admin/toMsg";
}


@RequestMapping(value = "/toUpdGoods", method = RequestMethod.POST)
public String toUpdGoods(WcGoods goods_Q,Model model){
    WcGoods goods = goodsService.getGoodsById(goods_Q.getWgsId());
    WcGoodsFenlei fenlei = goodsService.getFeileiById(goods.getWgsWgfId());
    StringUtil.copyProperties(goods_Q, goods);
    model.addAttribute("fenlei", fenlei);
    model.addAttribute("command", goods);
    return "/goods/updGoods";
}


@RequestMapping(value = "/delGoodsFenlei/{wcsId}")
public String delGoodsFenlei(WcGoodsFenlei fenlei,Integer wcsId,HttpServletRequest request,RedirectAttributes redirectAttribute){
    fenlei.setWgfWcsId(wcsId);
    goodsService.delGoodsFenlei(fenlei);
    redirectAttribute.addFlashAttribute("msgCode", "2");
    redirectAttribute.addFlashAttribute("alertMsg", "��Ʒ����ɾ���ɹ�");
    redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/mysite/queryGoodsFenlei", fenlei));
    return "redirect:/admin/toMsg";
}


public boolean chkAdminInfo(WcAdmin admin){
    boolean result = true;
    return result;
}


}