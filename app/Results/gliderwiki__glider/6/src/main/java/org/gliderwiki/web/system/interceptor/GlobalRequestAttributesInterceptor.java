package org.gliderwiki.web.system.interceptor;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.Base64Coder;
import org.gliderwiki.framework.util.SecretKeyPBECipher;
import org.gliderwiki.framework.util.SimpleAesStringCipherManager;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.web.system.SessionService;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.gliderwiki.Interface.CommonService;
public class GlobalRequestAttributesInterceptor extends HandlerInterceptorAdapter{

 private Logger logger;

 private  String IMG_TEMP_UPLOAD_ROOT_PATH_ATTR_NAME;

 private  String IMG_REAL_UPLOAD_ROOT_PATH_ATTR_NAME;

 private  String LOGIN_USER_ATTR_NAME;

 private  String LOGIN_USER_CHANNEL_ATTR_NAME;

 private  String LOGIN_USER_VIEWALARM_ATTR_NAME;

 private  SessionService sessionService;

 private  CommonService commonService;

 private  SimpleAesStringCipherManager simpleAesStringCipherManager;


public void setNotiCipherManager(SimpleAesStringCipherManager simpleAesStringCipherManager){
    this.simpleAesStringCipherManager = simpleAesStringCipherManager;
}


public void setSessionService(SessionService sessionService){
    this.sessionService = sessionService;
}


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
    setUploadImgPath(request);
    addLoginUser(request);
    return true;
}


public void setUploadImgPath(HttpServletRequest request){
    String tempImgRootPath = request.getSession().getServletContext().getRealPath("/resource/temp");
    String realImgRootPath = request.getSession().getServletContext().getRealPath("/resource/real");
    request.setAttribute(IMG_TEMP_UPLOAD_ROOT_PATH_ATTR_NAME, tempImgRootPath);
    request.setAttribute(IMG_REAL_UPLOAD_ROOT_PATH_ATTR_NAME, realImgRootPath);
}


public void addLoginUser(HttpServletRequest request){
    MemberSessionVo loginUser = sessionService.getLoginUser();
    // logger.debug("loginUser : {}", loginUser);
    request.setAttribute(LOGIN_USER_ATTR_NAME, loginUser);
    logger.debug("request.getRequestURI() :  " + request.getRequestURI());
    if (!loginUser.isGuest()) {
        if (!request.getRequestURI().startsWith("/admin") && !request.getRequestURI().startsWith("/dwr")) {
            logger.debug("alarmChannel : {}", simpleAesStringCipherManager.encrypt(String.format("A_%d", loginUser.getWeUserIdx())));
            request.setAttribute(LOGIN_USER_CHANNEL_ATTR_NAME, simpleAesStringCipherManager.encrypt(String.format("A_%d", loginUser.getWeUserIdx())));
            logger.debug("viewAlarm : {}", commonService.realNotiView(loginUser.getWeUserIdx()));
            request.setAttribute(LOGIN_USER_VIEWALARM_ATTR_NAME, commonService.realNotiView(loginUser.getWeUserIdx()));
        }
    }
}


public void setCommonService(CommonService commonService){
    this.commonService = commonService;
}


}