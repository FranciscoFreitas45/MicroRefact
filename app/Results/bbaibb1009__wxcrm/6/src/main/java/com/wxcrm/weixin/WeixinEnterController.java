package com.wxcrm.weixin;
 import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.oilchem.weixin.OAuth2.authorizeUtil;
import com.oilchem.weixin.mp.aes.AesException;
import com.wxcrm.member.IWeiMemberService;
import com.wxcrm.member.LzWeiMember;
import com.wxcrm.util.SysConstant;
import com.wxcrm.Interface.IWeixinService;
import com.wxcrm.Interface.IWeiMemberService;
import com.wxcrm.DTO.LzWeiMember;
@Controller
@RequestMapping("/weixine")
public class WeixinEnterController {

@Autowired
 private  IWeixinService weixinservice;

@Autowired
 private  IWeixinEnterService weixinEnterService;

@Autowired
 private  IWeiMemberService weimemberservice;

 private  Logger log;


@RequestMapping("/delWeiMember/{wmbWecId}")
public String delWeiMember(LzWeiMember member,Integer wmbWecId,RedirectAttributes redirectAttribute){
    String[] wmbIds = member.getWmbIds();
    weimemberservice.delWeiMember(wmbIds);
    redirectAttribute.addFlashAttribute("alertMsg", "ɾ����Ա�ɹ���");
    return "redirect:/weixine/queryWeiMemberByApp/" + wmbWecId;
}


@RequestMapping("/toIntroEnter/{appId}")
public String toIntroEnterMenu(LzWeiEnter weiEnter,String appId,HttpServletRequest request,HttpSession session,Model model){
    String code = (String) request.getParameter("code") == null ? "" : (String) request.getParameter("code");
    String openid = weiEnter.getWecCustOpenId_Q();
    weiEnter = weixinservice.getWeiEnterByAppId(appId);
    if (code != null && code.length() > 0) {
        // �˵����:����code�������ڲ���½�ģ�����û���OpenId
        openid = authorizeUtil.getOpenId(weiEnter.getWecAppId(), weiEnter.getWecAppSecret(), code);
    } else {
        if (openid != null && openid.length() > 0) {
        // �ǲ˵�������Ҳ��Ǳ��ύ���ⲿ���ӵ��ȥ�ģ��ʺ��ڷ��������ߵ��ҳ��Ĵ���
        } else {
        // �ǲ˵���������Ǳ��ύ����ҳ�汾������ӵ�������
        }
    }
    weiEnter.setWecCustOpenId_Q(openid);
    // model.addAttribute("custEnter", webuserservice.getCustEnteById(weiEnter.getWecEnterId()));
    model.addAttribute("command", weiEnter);
    return "/weixine/introEnter";
}


public void main(String[] args){
    try {
        String appId = "wx699aebd2bc63a9fb";
        System.out.println("appId:" + appId);
        String jsApiTicketStr = "kPAVMQm0WKdsodEfj5UKJPrtId4aDLMnZLjOQRTvIr9rgVJqZ2gw0DK-OuFiV5U44ISqBpAeQrA1ARGPTsfMgz0lGGnYspS4MYp91G42mMI";
        System.out.println("jsApiTicketStr:" + jsApiTicketStr);
        String timestamp = Long.toString(new Date().getTime());
        System.out.println("timestamp:" + timestamp);
        String nonceStr = "abc123";
        System.out.println("nonceStr:" + nonceStr);
        String url = "http://gl.oilchem.net/oilchem/weixine/toTestJsApi/" + appId;
        System.out.println("url:" + url);
        String string1 = "jsapi_ticket=TICKET&noncestr=NONCESTR&timestamp=TIMESTAMP&url=URL";
        string1 = string1.replace("TICKET", jsApiTicketStr).replace("NONCESTR", nonceStr).replace("TIMESTAMP", timestamp).replace("URL", url);
        System.out.println("string1:" + string1);
        String signature = com.oilchem.weixin.mp.aes.SHA1.getJsSignatureSHA1(string1);
        System.out.println("signature:" + signature);
    } catch (AesException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}


@RequestMapping("/toContactUs/{appId}")
public String toContactUsMenu(LzWeiEnter weiEnter,String appId,HttpServletRequest request,HttpSession session,Model model){
    String code = (String) request.getParameter("code") == null ? "" : (String) request.getParameter("code");
    String openid = weiEnter.getWecCustOpenId_Q();
    weiEnter = weixinservice.getWeiEnterByAppId(appId);
    if (code != null && code.length() > 0) {
        // �˵����:����code�������ڲ���½�ģ�����û���OpenId
        openid = authorizeUtil.getOpenId(weiEnter.getWecAppId(), weiEnter.getWecAppSecret(), code);
    } else {
        if (openid != null && openid.length() > 0) {
        // �ǲ˵�������Ҳ��Ǳ��ύ���ⲿ���ӵ��ȥ�ģ��ʺ��ڷ��������ߵ��ҳ��Ĵ���
        } else {
        // �ǲ˵���������Ǳ��ύ����ҳ�汾������ӵ�������
        }
    }
    weiEnter.setWecCustOpenId_Q(openid);
    // �������ҵ�����й���
    // model.addAttribute("custEnter",  	webuserservice.getCustEnteById(weiEnter.getWecEnterId()));
    model.addAttribute("command", weiEnter);
    return "/weixine/contactUs";
}


@RequestMapping("/queryWeiMemberByApp/{wetCueId}")
public String queryWeiEnterCustByApp(LzWeiMember member,Integer wetCueId,Model model){
    member.setWmbWecId_Q(wetCueId);
    model.addAttribute(SysConstant.PAGE_RESULT, weimemberservice.queryWeiMember(member));
    return "/weixin/queryWeiMember";
}


@RequestMapping("/queryBusiOpport/{appId}")
public String queryBusiOpportMenu(LzWeiEnter weiEnter,String appId,HttpServletRequest request,HttpSession session,Model model){
    String code = (String) request.getParameter("code") == null ? "" : (String) request.getParameter("code");
    String openidTest = (String) request.getParameter("openidTest");
    String openid = weiEnter.getWecCustOpenId_Q();
    weiEnter = weixinservice.getWeiEnterByAppId(appId);
    if (code != null && code.length() > 0) {
        // �˵����:����code�������ڲ���½�ģ�����û���OpenId
        openid = authorizeUtil.getOpenId(weiEnter.getWecAppId(), weiEnter.getWecAppSecret(), code);
    } else {
        if (openid != null && openid.length() > 0) {
        // �ǲ˵�������Ҳ��Ǳ��ύ���ⲿ���ӵ��ȥ�ģ��ʺ��ڷ��������ߵ��ҳ��Ĵ���
        } else {
        // �ǲ˵���������Ǳ��ύ����ҳ�汾������ӵ�������
        }
    }
    // ������
    if (openidTest != null) {
        openid = openidTest;
    }
    weiEnter.setWecCustOpenId_Q(openid);
    // �����ֵ��Ϊ�˷������µ���ʱ���ܹ�ͨ�����ύ��ȡopenid
    weiEnter.setWetOpenId_Q(openid);
    // Ҫ�����û���openId ��ѯ��ǰ�û��ǲ��ǵ�ǰ΢���˺ŵ��ڲ�����Ա ����ǹ���Ա�Ͳ���ת������ҳ��
    LzWeiEnterCust cust = weixinEnterService.getWeiEnterCustByOpenId(openid);
    // LzCustomerEnterprise custEnter = webuserservice.getCustEnteById(weiEnter.getWecEnterId());
    if (cust != null && cust.getWetType().equals("2")) {
        // ������ڲ�����Ա ����������
        model.addAttribute("command", cust);
        // model.addAttribute("custEnter", custEnter);
        // model.addAttribute(SysConstant.PAGE_RESULT,weixinEnterService.queryGqByWeiAdmin(cust.getWetCuuId()));
        return "/weixine/adminIndex";
    } else {
        // ����ǿͻ����������ҵ�����й���
        model.addAttribute("command", weiEnter);
        // model.addAttribute("custEnter",	custEnter);
        model.addAttribute("list", weixinEnterService.queryGqEnterList(weiEnter.getWecEnterId()));
        return "/weixine/busiOpport";
    }
}


}