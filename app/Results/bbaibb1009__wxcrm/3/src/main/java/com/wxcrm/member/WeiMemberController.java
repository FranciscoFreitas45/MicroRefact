package com.wxcrm.member;
 import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.oilchem.weixin.OAuth2.authorizeUtil;
import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.SysConstant;
import com.wxcrm.weixin.IWeixinService;
import com.wxcrm.weixin.LzWeiEnter;
import com.wxcrm.weixin.pojo.LzWeiJsapiTicket;
import com.wxcrm.Interface.IWeixinService;
import com.wxcrm.DTO.LzWeiEnter;
import com.wxcrm.DTO.LzWeiJsapiTicket;
@Controller
@RequestMapping("/weimember")
public class WeiMemberController {

@Autowired
 private  IWeiMemberService weimemberService;

@Autowired
 private  IWeixinService weixinService;


@RequestMapping("/bindMember")
public String bindMember(LzWeiMember weimember,Model model){
    // ˳�����ɿ���
    weimember = weimemberService.getWeiMemberByid(weimember.getWmbId());
    // ��֮ǰ�ȼ���Ƿ��Ѿ���
    if (weimember.getWmbCardId() != null && weimember.getWmbCardId().trim().length() > 0) {
    // �п��ŵĲ��ð���
    } else {
        String wmbCardId = weimemberService.getMemberCardId();
        weimember.setWmbCardId(wmbCardId);
        // ���û�Ա״̬Ϊ����Ա��
        weimember.setWmbType("2");
        weimemberService.updateWeiMember(weimember);
    }
    model.addAttribute("command", weimember);
    return "/weimember/index";
}


@RequestMapping(value = "/toBindMember/{appId}")
public String toBindMember(LzWeiMember weimember,String appId,HttpServletRequest request,Model model){
    String code = (String) request.getParameter("code") == null ? "" : (String) request.getParameter("code");
    String state = (String) request.getParameter("state") == null ? "" : (String) request.getParameter("state");
    String url = null;
    String openid = weimember.getWmbOpenid();
    LzWeiEnter enter = weixinService.getWeiEnterByAppId(appId);
    if (code != null && code.length() > 0) {
        // ����û���OpenId
        openid = authorizeUtil.getOpenId(enter.getWecAppId(), enter.getWecAppSecret(), code);
    }
    if (openid != null && openid.length() > 0) {
        // ������еĻ�Ա��Ϣ
        weimember = weimemberService.getWeiMemberByOpenId(openid, appId);
        if (weimember != null) {
            // �û��ڵ����ע��ʱ����Ѿ�������˺���
            url = "/weimember/index";
        } else {
            weimember.setWmbOpenid(openid);
            url = "/weimember/bindMember";
        }
    }
    model.addAttribute("command", weimember);
    return url;
}


@RequestMapping(value = "/queryWeiMember")
public String queryTest(LzWeiMember weimember,HttpSession session,Model model){
    WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO);
    model.addAttribute(SysConstant.PAGE_RESULT, weimemberService.queryWeiMember(weimember));
    return "/weimember/queryWeiMember";
}


@RequestMapping("/toMemberIndex/{memberId}/{appId}")
public String toMemberIndex(LzWeiMember weimember,Integer memberId,String appId,HttpServletRequest request,Model model){
    String timestamp = Long.toString(new Date().getTime() / 1000);
    String nonceStr = "abc123";
    String path = request.getRequestURL().toString();
    weimember = weimemberService.getWeiMemberByid(memberId);
    LzWeiJsapiTicket jsApiTicket = weixinService.getCurrentTikcet(weimember.getWmbWecId());
    // if(path.indexOf("?")>=0)
    // {
    // path= path.substring(0, path.indexOf("?"));
    // }
    String rootPath = path.substring(0, path.indexOf("/weimember/toMemberIndex/"));
    String shareImgUrl = rootPath + "/view/weimember/images/logo1.png";
    String url = path;
    String jsApiTicketStr = "";
    String shareTitle = "�������";
    String shareLink = path;
    // String shareLink 		= "http://www.163.com";
    jsApiTicketStr = jsApiTicket.getWjtJsapiTicket();
    String string1 = com.oilchem.weixin.mp.aes.SHA1.getJsSignatureString1(jsApiTicketStr, nonceStr, timestamp, url);
    String signature = com.oilchem.weixin.mp.aes.SHA1.getJsSignatureSHA1(string1);
    String path1 = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path1 + "/";
    System.out.println("basePath:" + basePath);
    model.addAttribute("command", weimember);
    model.addAttribute("appId", appId);
    model.addAttribute("timestamp", timestamp);
    model.addAttribute("nonceStr", nonceStr);
    model.addAttribute("signature", signature);
    model.addAttribute("shareTitle", shareTitle);
    model.addAttribute("shareLink", shareLink);
    model.addAttribute("shareImgUrl", shareImgUrl);
    model.addAttribute("jsApiTicketStr", jsApiTicketStr);
    return "/weimember/index";
}


}