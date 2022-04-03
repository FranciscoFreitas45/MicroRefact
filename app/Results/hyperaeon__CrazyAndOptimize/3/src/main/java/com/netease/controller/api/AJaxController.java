package com.netease.controller.api;
 import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.netease.controller.base.BaseController;
import com.netease.service.RegisterFlowService;
import com.netease.service.SmsService;
import com.netease.util.CommonServiceUtil;
import com.netease.util.Tools;
@RestController
public class AJaxController extends BaseController{

 final  Logger logger;

@Autowired
 private  HttpSession session;

@Autowired
 private RegisterFlowService registerFlowService;

@Autowired
 private SmsService smsService;


@RequestMapping(value = "/Credit/register_2/getVerifyCode", method = RequestMethod.POST)
public String CreditRegister_2_credential(String mobile_numble,Model m){
    logger.info("请求CreditRegister_2_credential开始" + mobile_numble);
    String tcId = registerFlowService.getDynamicCode(mobile_numble);
    m.addAttribute("tcid", tcId);
    session.setAttribute("tcid", tcId);
    logger.info("tcId: " + tcId);
    return tcId;
}


@RequestMapping(value = "/Credit/smsRegister1", method = RequestMethod.POST)
public String SmsRegisterStep1(HttpServletRequest request,String phone_number,Model m){
    logger.info("smsRegister begin");
    CommonServiceUtil.getBaseURL(request, m);
    String smsCode = smsService.getRandomNum();
    // String smsCode="123456";
    logger.info(phone_number + smsCode);
    // 输入数据合法性基础校验
    if (Tools.isEmpty(phone_number) || !smsService.checkMobileNumber(phone_number)) {
        m.addAttribute("phone_number", phone_number);
        m.addAttribute("info", "请输入有效手机号码");
        return null;
    }
    try {
        smsService.sendMes(phone_number, smsCode);
    } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return smsCode;
}


}