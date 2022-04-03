package com.wxcrm.weixin;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.wxcrm.common.IMemcachedService;
import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.DateUtil;
import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;
import com.wxcrm.website.IWebsiteService;
import com.wxcrm.website.WcWebsite;
import com.wxcrm.weixin.pojo.LzWeiAccesstoken;
import com.wxcrm.weixin.pojo.LzWeiJsapiTicket;
import com.wxcrm.Interface.IMemcachedService;
import com.wxcrm.Interface.IWebsiteService;
import com.wxcrm.DTO.LzWeiEnter;
import com.wxcrm.DTO.WcAdmin;
import com.wxcrm.DTO.WcWebsite;
@Controller
@RequestMapping("/weixin")
public class WeixinController {

@Autowired
 private  IWeixinService weixinservice;

@Autowired
 private  IMemcachedService memcachedservice;

@Autowired
 private  IWebsiteService websiteservice;


@RequestMapping("/queryWeixinEnter")
public String queryWeixinEnter(LzWeiEnter weiEnter,Model model){
    model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryWeixinEnter(weiEnter));
    return "/weixin/queryWeixinEnter";
}


@RequestMapping("/queryJsApiTicket")
public String queryJsApiTicket(LzWeiJsapiTicket ticket,Model model){
    List<LzWeiEnter> list = weixinservice.queryWeixinEnterList();
    String wjtWecId_Q = ticket.getWatWecId_Q();
    Integer wecId = 0;
    if (wjtWecId_Q == null || wjtWecId_Q.length() == 0) {
        wecId = list.get(0).getWecId();
    } else {
        wecId = Integer.parseInt(wjtWecId_Q);
    }
    LzWeiJsapiTicket ticketCur = weixinservice.getCurrentTikcet(wecId);
    if (ticketCur != null) {
        model.addAttribute(SysConstant.JS_API_TICKET_EXPIRE, DateUtil.parseString(DateUtil.parseDate(ticketCur.getWjtExpiresIn(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
        model.addAttribute(SysConstant.JS_API_TICKET, ticketCur.getWjtJsapiTicket());
    }
    model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryJsApiTicket(ticket));
    model.addAttribute("watApp", list);
    return "/weixin/queryJsApiTicket";
}


@RequestMapping("/addWeiEnter")
public String addWeiEnter(LzWeiEnter weiEnter,HttpSession session,RedirectAttributes redirectAttributes){
    // ����
    if (weixinservice.checkAppExsit(weiEnter)) {
        redirectAttributes.addFlashAttribute("alertMsg", "��ǰӦ��ͬ�������Ѿ����ڲ����ظ����!");
        return "redirect:/weixin/queryWeixinEnter";
    }
    if (weiEnter.getWecCusType().equals("1")) {
        weiEnter.setWecEnterId(0);
    }
    weiEnter.setWecStatus("1000");
    WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO);
    weiEnter.setWecRegistor(admin.getWadId());
    weixinservice.addWeiEnter(weiEnter);
    redirectAttributes.addFlashAttribute("alertMsg", "���Ӧ�óɹ�!");
    return "redirect:/weixin/queryWeixinEnter";
}


@RequestMapping("/addWeiEnterByE/{enterId}")
public String addWeiEnterByE(LzWeiEnter weiEnter,Integer enterId,HttpSession session,HttpServletRequest request,RedirectAttributes redirectAttributes){
    // ����
    if (weixinservice.checkAppExsit(weiEnter)) {
        redirectAttributes.addFlashAttribute("alertMsg", "��ǰӦ��ͬ�������Ѿ����ڲ����ظ����!");
        return "redirect:/weixin/queryWeixinEnterBySite/" + enterId;
    }
    String[] keyWrds = request.getParameterValues("keyWrd");
    String[] keyWrdMsgIds = request.getParameterValues("keyWrdmsgId");
    if (weiEnter.getWecCusType().equals("1")) {
        weiEnter.setWecEnterId(0);
    }
    weiEnter.setWecStatus("1000");
    WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO);
    weiEnter.setWecRegistor(admin.getWadId());
    weixinservice.addWeiEnter(weiEnter);
    weixinservice.addKeyWordsByEnter(weiEnter, keyWrds, keyWrdMsgIds, admin);
    redirectAttributes.addFlashAttribute("alertMsg", "���Ӧ�óɹ�!");
    return "redirect:/weixin/queryWeixinEnterBySite/" + enterId;
}


@RequestMapping(value = "/updWeiEnterByE/{enterId}", method = RequestMethod.POST)
public String updWeiEnterByE(LzWeiEnter weiEnter,Integer enterId,HttpServletRequest request,HttpSession session,RedirectAttributes redirectAttributes){
    String[] keyWrds = request.getParameterValues("keyWrd");
    String[] keyWrdMsgIds = request.getParameterValues("keyWrdmsgId");
    WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO);
    if (weiEnter.getWecCusType().equals("1")) {
        weiEnter.setWecEnterId(0);
    }
    weixinservice.updWeiEnter(weiEnter);
    weixinservice.addKeyWordsByEnter(weiEnter, keyWrds, keyWrdMsgIds, admin);
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "�޸ĳɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/weixin/queryWeixinEnterBySite/" + enterId));
    return "redirect:/admin/toMsg";
}


@RequestMapping("/setAccessToken")
public String setAccessToken(LzWeiAccesstoken accesstoken,RedirectAttributes redirectattributes){
    try {
        Integer watWedId = Integer.parseInt(accesstoken.getWatWecId_Q());
        weixinservice.updAccessToken(watWedId);
        redirectattributes.addFlashAttribute("command", accesstoken);
        redirectattributes.addFlashAttribute("alertMsg", "�ֶ�����AccessToken�ɹ�!");
        return "redirect:/weixin/queryAccessToken";
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


@RequestMapping("/queryWebSite")
public String queryWebSite(WcWebsite website,Model model){
    model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryWebSite(website));
    return "/weixin/queryWebSite";
}


@RequestMapping("/toAddWeiEnter")
public String toAddWeiEnter(LzWeiEnter weiEnter,Model model){
    return "/weixin/addWeiEnter";
}


@RequestMapping(value = "/toUpdWeiEnterByE", method = RequestMethod.POST)
public String toUpdWeiEnterByE(LzWeiEnter weiEnter_Q,Model model){
    LzWeiEnter weiEnter = weixinservice.getWeiEnterById(weiEnter_Q.getWecId());
    StringUtil.copyProperties(weiEnter_Q, weiEnter);
    WcWebsite website = websiteservice.getWebSiteById(weiEnter_Q.getWecEnterId());
    if (weiEnter != null) {
        weiEnter.setWecEnterName_Q(website.getWcsWebSiteName());
    } else {
        weiEnter.setWecEnterName_Q("�ڲ�");
    }
    model.addAttribute("command", weiEnter);
    return "/weixin/updWeiEnterByE";
}


@RequestMapping(value = "/queryWeixinEnterBySite/{wcsId}")
public String queryWeixinEnterByE(LzWeiEnter weiEnter,Integer wcsId,Model model){
    // �̼�ID
    weiEnter.setWecEnterId(wcsId);
    model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryWeixinEnter(weiEnter));
    return "/weixin/queryWeixinEnterBySite";
}


@RequestMapping(value = "/updWeiEnter", method = RequestMethod.POST)
public String updWeiEnter(LzWeiEnter weiEnter,HttpServletRequest request,HttpSession session,RedirectAttributes redirectAttributes){
    String[] keyWrds = request.getParameterValues("keyWrd");
    String[] keyWrdMsgIds = request.getParameterValues("keyWrdmsgId");
    WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO);
    if (weiEnter.getWecCusType().equals("1")) {
        weiEnter.setWecEnterId(0);
    }
    weixinservice.updWeiEnter(weiEnter);
    // if(keyWrdMsgIds!=null&&keyWrdMsgIds.length>0)
    // {
    weixinservice.addKeyWordsByEnter(weiEnter, keyWrds, keyWrdMsgIds, admin);
    // }
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "�޸ĳɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/weixin/queryWeixinEnter"));
    return "redirect:/admin/toMsg";
}


@RequestMapping("/addErrorCode")
public String addErrorCode(LzWeiErrorcode code,RedirectAttributes redirectattributes){
    weixinservice.initErrorCode();
    return "redirect:/weixin/queryErrorCode";
}


@RequestMapping("/queryAccessToken/{wecId}")
public String queryAccessToken1(LzWeiAccesstoken token,Integer wecId,Model model){
    List<LzWeiEnter> list = weixinservice.queryWeixinEnterList();
    token.setWatWecId_Q(Integer.toString(wecId));
    LzWeiAccesstoken tokenCur = weixinservice.getCurrentAccessToken(wecId);
    if (tokenCur != null) {
        model.addAttribute(SysConstant.ACCESSTOKEN_EXPIRE, DateUtil.parseString(DateUtil.parseDate(tokenCur.getWatExpiresIn(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
        model.addAttribute(SysConstant.ACCESS_TOKEN, tokenCur.getWatToken());
    }
    model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryAccessToken(token));
    model.addAttribute("watApp", list);
    return "/weixin/queryAccessToken";
}


@RequestMapping("/updateWatcherOnline")
public String updateWatcherOnline(LzWeiWatcher watcher,HttpSession session,RedirectAttributes redirectattributes){
    Integer num = 0;
    Integer total = 0;
    String nextOpenId = "";
    Integer wecId = Integer.parseInt(watcher.getWacWecId_Q());
    LzWeiEnter enter = weixinservice.getWeiEnterById(wecId);
    do {
        nextOpenId = (String) session.getAttribute("curNextOpenId") == null ? "" : (String) session.getAttribute("curNextOpenId");
        num = weixinservice.updWeixinWatcher(nextOpenId, session, wecId, enter.getWecAppId());
        total += num;
        System.out.println("��" + (total / (num == 0 ? 1 : num)) + "�κ�:" + total + "��");
    } while (num > 0);
    session.removeAttribute("curNextOpenId");
    redirectattributes.addFlashAttribute("alertMsg", "�������!���û�����" + total + "��");
    return "redirect:/weixin/queryWatcher";
}


@RequestMapping("/setJsApiTicket")
public String setJsApiTicket(LzWeiJsapiTicket ticket,RedirectAttributes redirectattributes){
    try {
        Integer watWedId = Integer.parseInt(ticket.getWatWecId_Q());
        weixinservice.updJsApiTicket(watWedId);
        redirectattributes.addFlashAttribute("command", ticket);
        redirectattributes.addFlashAttribute("alertMsg", "�ֶ�����JsApiTicket�ɹ�!");
        return "redirect:/weixin/queryJsApiTicket";
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


@RequestMapping(value = "/toUpdWeiEnter", method = RequestMethod.POST)
public String toUpdWeiEnter(LzWeiEnter weiEnter_Q,Model model){
    LzWeiEnter weiEnter = weixinservice.getWeiEnterById(weiEnter_Q.getWecId());
    StringUtil.copyProperties(weiEnter_Q, weiEnter);
    // LzCustomerEnterprise enter = webuserservice.getCustEnteById(weiEnter.getWecEnterId());
    // if(enter!=null)
    // {
    // weiEnter.setWecEnterName_Q(enter.getCueName());
    // }
    // else
    // {
    // weiEnter.setWecEnterName_Q("�ڲ�");
    // }
    model.addAttribute("command", weiEnter);
    return "/weixin/updWeiEnter";
}


@RequestMapping("/queryWatcher")
public String queryWatcher(LzWeiWatcher watcher,Model model){
    List<LzWeiEnter> list = weixinservice.queryWeixinEnterList();
    model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryWatcher(watcher));
    model.addAttribute("watApp", list);
    return "/weixin/queryWatcher";
}


@RequestMapping("/queryAccessToken")
public String queryAccessToken(LzWeiAccesstoken token,Model model){
    List<LzWeiEnter> list = weixinservice.queryWeixinEnterList();
    String watWecId_Q = token.getWatWecId_Q();
    Integer wecId = 0;
    if (watWecId_Q == null || watWecId_Q.length() == 0) {
        wecId = list.get(0).getWecId();
    } else {
        wecId = Integer.parseInt(watWecId_Q);
    }
    LzWeiAccesstoken tokenCur = weixinservice.getCurrentAccessToken(wecId);
    if (tokenCur != null) {
        model.addAttribute(SysConstant.ACCESSTOKEN_EXPIRE, DateUtil.parseString(DateUtil.parseDate(tokenCur.getWatExpiresIn(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
        model.addAttribute(SysConstant.ACCESS_TOKEN, tokenCur.getWatToken());
    }
    model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryAccessToken(token));
    model.addAttribute("watApp", list);
    return "/weixin/queryAccessToken";
}


@RequestMapping("/queryErrorCode")
public String queryErrorCode(LzWeiErrorcode code,Model model){
    model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryErrorCode(code));
    return "/weixin/queryErrorCode";
}


@RequestMapping("/queryWatcherByEnter/{wecId}")
public String queryWatcherByEnter(LzWeiWatcher watcher,Integer wecId,Model model){
    watcher.setWacWecId_Q(Integer.toString(wecId));
    List<LzWeiEnter> list = weixinservice.queryWeixinEnterList();
    model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryWatcher(watcher));
    model.addAttribute("watApp", list);
    return "/weixin/queryWatcher";
}


@RequestMapping("/sendWeixin")
public String toSendWeixin(LzWeiWatcher watcher,Model model){
    String msg = watcher.getMsg();
    String openId = watcher.getWacOpenid();
    Integer wecId = watcher.getWacWecId();
    weixinservice.sendMsg(openId, msg, wecId);
    model.addAttribute("alertMsg", "������Ϣ�ɹ�!");
    return "/weixin/sendWeixin";
}


@RequestMapping(value = "/toAddWeiEnterByE/{enterId}")
public String toAddWeiEnterByE(LzWeiEnter weiEnter,Integer enterId,Model model){
    WcWebsite webSite = websiteservice.getWebSiteById(enterId);
    weiEnter.setWecEnterName_Q(webSite.getWcsWebSiteName());
    weiEnter.setWecEnterId(enterId);
    weiEnter.setWecCusType("2");
    model.addAttribute("command", weiEnter);
    return "/weixin/addWeiEnterByE";
}


@RequestMapping("/delWeiEnterByE/{enterId}")
public String delWeiEnter(LzWeiEnter weiEnter,Integer enterId,HttpServletRequest request,RedirectAttributes redirectAttributes){
    String[] wecIds = weiEnter.getWecIds();
    weixinservice.deleteWeiEnter(wecIds);
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "ɾ���ɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/weixin/queryWeixinEnterBySite/" + enterId));
    return "redirect:/admin/toMsg";
}


}