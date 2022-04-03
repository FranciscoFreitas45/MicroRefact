package com.wxcrm.weixin;
 import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.oilchem.weixin.mp.aes.AesException;
import com.oilchem.weixin.mp.aes.SHA1;
import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.DateUtil;
import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;
import com.wxcrm.weixin.pojo.LzWeiArticleMessage;
@Controller
@RequestMapping("/weixinmsg")
public class WeixinMessageController {

@Autowired
 private  IWeixinMessageService weixinmsgservice;

@Autowired
 private  IWeixinService weixinservice;

 private  Logger log;


@RequestMapping(value = "/querySubscribeMsg")
public String querySubscribeMsg(LzWeiMessage msg,Model model){
    msg.setWmgReplyType_Q("3");
    model.addAttribute(SysConstant.PAGE_RESULT, weixinmsgservice.queryLzWeiMsg(msg));
    return "/weixin/showSubscribeMsg";
}


@RequestMapping(value = "/toAddNewsMsg")
public String toAddNewsMsg(LzWeiArticleMessage article,Model model){
    model.addAttribute("command", article);
    return "/weixin/addNewsMsg";
}


@RequestMapping(value = "/queryDefaultMsg")
public String queryDefaultMsg(LzWeiMessage msg,Model model){
    msg.setWmgReplyType_Q("2");
    model.addAttribute(SysConstant.PAGE_RESULT, weixinmsgservice.queryLzWeiMsg(msg));
    return "/weixin/showDefaultMsg";
}


@RequestMapping(value = "/queryKeyWordsMsg")
public String queryKeyWordsMsg(LzWeiMessage msg,Model model){
    msg.setWmgReplyType_Q("1");
    model.addAttribute(SysConstant.PAGE_RESULT, weixinmsgservice.queryLzWeiMsg(msg));
    return "/weixin/showKeyWordsMsg";
}


@RequestMapping(value = "/queryLzWeiMsg")
public String queryLzWeiMsg(LzWeiMessage msg,Model model){
    model.addAttribute(SysConstant.PAGE_RESULT, weixinmsgservice.queryLzWeiMsg(msg));
    return "/weixin/queryLzWeiMsg";
}


public void main(String[] args){
}


@RequestMapping(value = "/addNewsMsg")
public String addNewsMsg(LzWeiArticleMessage article,Model model,HttpSession session){
    WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO);
    article.setWamRegistor(admin.getWadId());
    article.setWamRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
    article.setWamStatus("1000");
    weixinmsgservice.addLzWeiArticleMsg(article);
    model.addAttribute("alertMsg", article.getWamId());
    return "/weixin/addNewsMsg";
}


@RequestMapping(value = "/addLzWeiMsg")
public String addLzWeiMsg(LzWeiMessage msg,HttpSession session,RedirectAttributes redirectAttribute){
    String content = msg.getWmgContent() == null ? "" : msg.getWmgContent();
    String msgType = msg.getWmgMsgType() == null ? "" : msg.getWmgMsgType();
    String aesType = msg.getWmgAesType() == null ? "" : msg.getWmgAesType();
    WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO);
    msg.setWmgStatus("1000");
    msg.setWmgRegistor(admin.getWadId());
    msg.setWmgRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
    // Ҫ�ȸ�����Ϣ���ͺͼ������� ������Ϣ��XML��ʽ
    if (msgType.equals("1")) {
    // ͼ����Ϣ�ȱ�����Ϣ
    // �ٱ���ͼ�Ĺ�ϵ
    } else if (msgType.equals("2")) {
        // ������Ϣֱ�ӱ���
        weixinmsgservice.addLzWeiMsg(msg);
    }
    return "redirect:/weixinmsg/queryLzWeiMsg";
}


public String getTextMsgXMLByType(String content,String aesType){
    return "";
}


@RequestMapping(value = "/toAddLzWeiMsg")
public String toAddLzWeiMsg(LzWeiMessage msg,Model model){
    return "/weixin/addLzWeiMsg";
}


@RequestMapping(value = "/getServeEchoTest/{appId}", method = RequestMethod.POST)
public String getServeEchoTestPost(String appId,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    // �ȸ���appId���������΢���˺�
    LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId);
    // pudding
    String token_test = enter.getWecToken();
    // 
    String encodingAESKey_test = enter.getWecEncodingAesKey();
    String appId_test = enter.getWecAppId();
    // ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    // ���ú���ҵ���������Ϣ��������Ϣ
    String respMessage = weixinmsgservice.processRequest_Jar(request, token_test, encodingAESKey_test, appId_test);
    // ��Ӧ��Ϣ
    PrintWriter out = response.getWriter();
    out.print(respMessage);
    out.close();
    return null;
}


@RequestMapping(value = "/updLzWeiMsg", method = RequestMethod.POST)
public String updLzWeiMsg(LzWeiMessage msg,HttpServletRequest request,RedirectAttributes redirectAttributes){
    weixinmsgservice.updLzWeiMsg(msg);
    redirectAttributes.addFlashAttribute("msgCode", "2");
    redirectAttributes.addFlashAttribute("alertMsg", "�ظ���Ϣ�޸ĳɹ�");
    redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/weixinmsg/queryLzWeiMsg", msg));
    return "redirect:/admin/toMsg";
}


@RequestMapping(value = "/toUpdLzWeiMsg")
public String toUpdLzWeiMsg(LzWeiMessage msg_Q,Model model){
    LzWeiMessage msg = weixinmsgservice.getLzWeiMessageById(msg_Q.getWmgId());
    StringUtil.copyProperties(msg_Q, msg);
    model.addAttribute("command", msg);
    return "/weixin/updLzWeiMsg";
}


@RequestMapping(value = "/getServeEchoTest/{appId}", method = RequestMethod.GET)
public String getServeEchoTestGet(String appId,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    try {
        LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId);
        // pudding
        String token = enter.getWecToken();
        // ΢�ż���ǩ��
        String signature = request.getParameter("signature");
        // ����ַ���
        String echostr = request.getParameter("echostr");
        // ʱ���
        String timestamp = request.getParameter("timestamp");
        // �����
        String nonce = request.getParameter("nonce");
        String encrypt = "";
        String[] str = { token, timestamp, nonce };
        // �ֵ�������
        Arrays.sort(str);
        // SHA1����
        String digest = SHA1.getSHA1(token, timestamp, nonce, encrypt);
        // ȷ����������΢��
        if (digest.equals(signature)) {
            response.getWriter().print(echostr);
        } else {
        }
    } catch (AesException ae) {
        ae.printStackTrace();
    } catch (IOException io) {
        io.printStackTrace();
    } finally {
        return null;
    }
}


@RequestMapping("/getArticleJson/{id}")
@ResponseBody
public Map<String,Object> getArticleJson(Integer id){
    Map<String, Object> map = weixinmsgservice.getWeiArticleMap(id);
    return map;
}


}