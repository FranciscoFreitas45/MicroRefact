package org.gliderwiki.admin;
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gliderwiki.framework.util.Base64Coder;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.SecretKeyPBECipher;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.PropertyUtil;
import org.gliderwiki.util.SendMailSMTP;
import org.gliderwiki.util.SessionUtil;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeAccess;
import org.gliderwiki.web.domain.WeMenu;
import org.gliderwiki.web.domain.WeSendMail;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.DTO.WeAccess;
import org.gliderwiki.DTO.WeMenu;
@Controller
public class SystemController {

 private Logger logger;

@Autowired
 private  EntityService entityService;


@RequestMapping(value = "/admin/mail/sendtest", method = RequestMethod.GET)
public ModelAndView adminMailSendTest(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String testUserMail = StringUtil.strNull(request.getParameter("testUserMail"));
    String svcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
    Properties props = PropertyUtil.getMailPropertyInfo(svcPath);
    String mailUserId = props.getProperty("mail.user.id");
    String mailUserPassword = props.getProperty("mail.user.password");
    String mailUserKey = props.getProperty("mail.user.key");
    String smtpHostName = props.getProperty("smtp.host.name");
    String smtpServerPort = props.getProperty("smtp.server.port");
    String mailCharset = props.getProperty("mail.charset");
    String siteDomain = props.getProperty("mail.site.domain");
    String siteOwner = props.getProperty("mail.site.owner");
    String emailMsgTxt = "안녕하세요. " + siteOwner + " 입니다. <br><br>" + "관리자 모드에서 보내는 테스트 메일 입니다.<br><br>";
    // 제목
    String emailTitle = siteOwner + " Wiki 안내 메일입니다.";
    Map<String, Object> param = new HashMap<String, Object>();
    int result = 0;
    try {
        SendMailSMTP sender = new SendMailSMTP();
        result = sender.sendSimpleMail(testUserMail, emailTitle, emailMsgTxt, request);
        logger.debug("###result : " + result);
        if (result > 0) {
            param.put("result", "SUCCESS");
            param.put("status", SystemConst.CALL_SUCCESS);
            param.put("rtnResult", result);
            param.put("rtnMsg", "전송 되었습니다.");
        } else {
            param.put("result", "FAIL");
            param.put("status", SystemConst.CALL_FAIL);
            param.put("rtnResult", -1);
            param.put("rtnMsg", "전송 실패 하였습니다. 메일 설정 정보를 확인한 후  다시 시도 하세요");
        }
    } catch (UnsupportedEncodingException e) {
        result = -1;
        param.put("result", "FAIL");
        param.put("status", SystemConst.CALL_FAIL);
        param.put("rtnResult", -2);
        param.put("rtnMsg", "에러가 발생하였습니다.");
    }
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/admin/mail/regist", method = RequestMethod.GET)
public String adminMailRegist(HttpServletRequest request,HttpServletResponse response,ModelMap model){
    String mailUserId = StringUtil.strNull(request.getParameter("mailUserId"));
    String mailUserPassword = StringUtil.strNull(request.getParameter("mailUserPassword"));
    String mailUserKey = StringUtil.strNull(request.getParameter("mailUserKey"));
    String smtpHostName = StringUtil.strNull(request.getParameter("smtpHostName"));
    String smtpServerPort = StringUtil.strNull(request.getParameter("smtpServerPort"));
    String mailCharset = StringUtil.strNull(request.getParameter("mailCharset"));
    String siteDomain = StringUtil.strNull(request.getParameter("siteDomain"));
    String siteOwner = StringUtil.strNull(request.getParameter("siteOwner"));
    String svcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
    logger.debug("### 원래 패스워드 :" + mailUserPassword);
    // 패스워드 암호화
    String passwd = SecretKeyPBECipher.setUserPassword(mailUserPassword, mailUserKey);
    logger.debug("### 변환 패스워드 :" + passwd);
    Properties prop = new Properties();
    prop.setProperty("mail.user.id", mailUserId);
    prop.setProperty("mail.user.password", passwd);
    prop.setProperty("mail.user.key", mailUserKey);
    prop.setProperty("smtp.host.name", smtpHostName);
    prop.setProperty("smtp.server.port", smtpServerPort);
    prop.setProperty("mail.charset", mailCharset);
    prop.setProperty("mail.site.domain", siteDomain);
    prop.setProperty("mail.site.owner", siteOwner);
    File file = new File(svcPath);
    // File file = new File(svcPath);
    logger.debug("file : " + file.toString());
    logger.debug("##################################");
    logger.debug(svcPath);
    logger.debug("##################################");
    // 추후 jdbc.properties로 변경해야 합니다.
    prop.store(new FileOutputStream(svcPath + "/mail.properties"), null);
    return "redirect:/admin/mail";
}


@RequestMapping(value = "/admin/filter/delete", method = RequestMethod.POST)
public String deleteFilter(MemberSessionVo loginUser,Integer targetIdx){
    WeAccess weAccess = new WeAccess(targetIdx);
    entityService.deleteEntity(weAccess);
    return "redirect:/admin/filter";
}


@RequestMapping(value = "/admin/filter", method = RequestMethod.GET)
public String adminFilter(HttpServletRequest request,HttpServletResponse response,ModelMap model){
    model.addAttribute("menu", "3");
    model.addAttribute("menuCode", "8");
    model.addAttribute("restrictList", entityService.getListEntity(new WeAccess()));
    return "/admin/system/filterMgr";
}


@RequestMapping(value = "/admin/filter/create", method = RequestMethod.POST)
public String createFilter(MemberSessionVo loginUser,String targetIp){
    WeAccess weAccess = new WeAccess(targetIp, loginUser.getWeUserIdx());
    weAccess.setWe_use_yn("Y");
    entityService.insertEntity(weAccess);
    return "redirect:/admin/filter";
}


@RequestMapping(value = "/admin/menu", method = RequestMethod.GET)
public ModelAndView menuManage(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("###TODOLIST 메뉴 관리 추후 다운로드 버전에서 삭제 할 예정 (menuMgr.jsp");
    // 최상단 메뉴 목록을 조회한다.
    WeMenu menuEntity = new WeMenu();
    menuEntity.setWe_use_yn("Y");
    // 1뎁스
    menuEntity.setWe_menu_depth(1);
    // 시스템 메뉴만 조회
    menuEntity.setWe_menu_type("S");
    List<WeMenu> menuList = entityService.getListEntityOrdered(menuEntity, "we_menu_order_idx");
    logger.debug("menuList : " + menuList.toString());
    modelAndView.addObject("menu", "3");
    modelAndView.addObject("menuCode", "9");
    modelAndView.addObject("menuList", menuList);
    modelAndView.setViewName("admin/system/menuMgr");
    return modelAndView;
}


@RequestMapping(value = "/admin/mail", method = RequestMethod.GET)
public ModelAndView adminMail(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("### Mail properties 설정 ###");
    // 변경된 정보를 읽어오기 위해서 새 프로퍼티를 선언하여 호출한다.
    String svcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
    Properties props = PropertyUtil.getMailPropertyInfo(svcPath);
    String mailUserId = props.getProperty("mail.user.id");
    String mailUserPassword = props.getProperty("mail.user.password");
    String mailUserKey = props.getProperty("mail.user.key");
    String smtpHostName = props.getProperty("smtp.host.name");
    String smtpServerPort = props.getProperty("smtp.server.port");
    String mailCharset = props.getProperty("mail.charset");
    String siteDomain = props.getProperty("mail.site.domain");
    String siteOwner = props.getProperty("mail.site.owner");
    logger.debug("###mailUserPassword : " + mailUserPassword);
    logger.debug(" mailUserId : " + mailUserId);
    logger.debug(" mailUserPassword : " + mailUserPassword);
    logger.debug(" mailUserKey : " + mailUserKey);
    logger.debug(" smtpHostName : " + smtpHostName);
    logger.debug(" smtpServerPort : " + smtpServerPort);
    logger.debug(" mailCharset : " + mailCharset);
    logger.debug(" siteDomain : " + siteDomain);
    logger.debug(" siteOwner : " + siteOwner);
    modelAndView.addObject("mailUserId", mailUserId);
    modelAndView.addObject("mailUserPassword", mailUserPassword);
    modelAndView.addObject("mailUserKey", mailUserKey);
    modelAndView.addObject("smtpHostName", smtpHostName);
    modelAndView.addObject("smtpServerPort", smtpServerPort);
    modelAndView.addObject("mailCharset", mailCharset);
    modelAndView.addObject("siteDomain", siteDomain);
    modelAndView.addObject("siteOwner", siteOwner);
    modelAndView.addObject("menu", "3");
    modelAndView.addObject("menuCode", "7");
    modelAndView.setViewName("admin/system/mailMgr");
    return modelAndView;
}


}