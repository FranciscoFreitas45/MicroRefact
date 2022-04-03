package com.ec.survey.handler;
 import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.ec.survey.service.SettingsService;
import com.ec.survey.service.SurveyService;
import com.ec.survey.Interface.SurveyService;
public class ServerEnvironmentHandlerInterceptor extends HandlerInterceptorAdapter{

 protected  Logger logger;

@Value("${ui.enableresponsive}")
 public  String enableresponsive;

@Value("${server.prefix}")
 public  String serverPrefix;

 public  String APPLICATION_SERVER_ENVIRONMENT;

@Value("${app.server.env}")
 public  String serverEnv;

 public  String APPLICATION_CAPTCHA_BYPASS;

@Value("${captcha.bypass:@null}")
 public  String captchaBypass;

 public  String APPLICATION_CAPTCHA_KEY;

@Value("${captcha.key}")
 public  String captchakey;

 public  String APPLICATION_CAPTCHA_SERVERPREFIX;

@Value("${captcha.serverprefix}")
 public  String captchaserverprefix;

 public  String APPLICATION_ARCHIVING;

@Value("${ui.enablearchiving}")
 public  String enablearchiving;

 public  String APPLICATION_DELPHI;

@Value("${ui.enabledelphi}")
 public  String enabledelphi;

 public  String APPLICATION_FILEMANAGEMENT;

@Value("${ui.enablefilemanagement}")
 public  String enablefilemanagement;

 public  String APPLICATION_OPC;

@Value("${ui.enableopc}")
 public  String enableopc;

 public  String APPLICATION_ECF;

@Value("${ui.enableecf}")
 public  String enableecf;

 public  String APPLICATION_PUBLICSURVEYS;

@Value("${ui.enablepublicsurveys}")
 public  String enablepublicsurveys;

 public  String APPLICATION_OSS;

@Value("${oss}")
 public  String oss;

 public  String APPLICATION_PIWIK;

@Value("${piwik}")
 public  String piwik;

 public  String APPLICATION_SHOWPRIVACY;

@Value("${show.privacy}")
 public  String showPrivacy;

@Value("${monitoring.recipient}")
 private  String monitoringEmail;

@Value("${enablereportingdatabase}")
 private  String enablereportingdatabase;

@Value("${contextpath}")
 private  String contextpath;

@Resource(name = "settingsService")
 private  SettingsService settingsService;

@Resource(name = "surveyService")
 private  SurveyService surveyService;

public ServerEnvironmentHandlerInterceptor() {
}
@Override
public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView){
    if (modelAndView != null && modelAndView.hasView() && !modelAndView.getViewName().startsWith("redirect")) {
        modelAndView.getModelMap().addAttribute("serverprefix", serverPrefix);
        modelAndView.getModelMap().addAttribute(APPLICATION_SERVER_ENVIRONMENT, serverEnv);
        modelAndView.getModelMap().addAttribute(APPLICATION_CAPTCHA_BYPASS, isByPassCaptcha());
        modelAndView.getModelMap().addAttribute(APPLICATION_CAPTCHA_KEY, captchakey);
        modelAndView.getModelMap().addAttribute(APPLICATION_CAPTCHA_SERVERPREFIX, captchaserverprefix);
        modelAndView.getModelMap().addAttribute(APPLICATION_ARCHIVING, enablearchiving != null && enablearchiving.equalsIgnoreCase("true"));
        modelAndView.getModelMap().addAttribute(APPLICATION_DELPHI, enabledelphi != null && enabledelphi.equalsIgnoreCase("true"));
        modelAndView.getModelMap().addAttribute(APPLICATION_OPC, enableopc != null && enableopc.equalsIgnoreCase("true"));
        modelAndView.getModelMap().addAttribute(APPLICATION_ECF, enableecf != null && enableecf.equalsIgnoreCase("true"));
        modelAndView.getModelMap().addAttribute(APPLICATION_PUBLICSURVEYS, enablepublicsurveys != null && enablepublicsurveys.equalsIgnoreCase("true"));
        modelAndView.getModelMap().addAttribute(APPLICATION_FILEMANAGEMENT, enablefilemanagement != null && enablefilemanagement.equalsIgnoreCase("true"));
        modelAndView.getModelMap().addAttribute(APPLICATION_OSS, oss != null && oss.equalsIgnoreCase("true"));
        modelAndView.getModelMap().addAttribute(APPLICATION_PIWIK, piwik != null && piwik.equalsIgnoreCase("true"));
        modelAndView.getModelMap().addAttribute(APPLICATION_SHOWPRIVACY, showPrivacy != null && showPrivacy.equalsIgnoreCase("true"));
        modelAndView.getModelMap().addAttribute("contextpath", contextpath);
        modelAndView.getModelMap().addAttribute("monitoringEmail", monitoringEmail);
        modelAndView.getModelMap().addAttribute("enablereportingdatabase", enablereportingdatabase);
        Device device = DeviceUtils.getCurrentDevice(request);
        if (!request.getRequestURI().endsWith("management/edit") && (device.isMobile() || device.isTablet())) {
            modelAndView.getModelMap().addAttribute("responsive", true);
            if (device.isMobile()) {
                modelAndView.getModelMap().addAttribute("ismobile", true);
            }
        }
        String captcha = settingsService.get("captcha");
        modelAndView.getModelMap().addAttribute("captcha", captcha);
        String uisessiontimeout = settingsService.get("uisessiontimeout");
        modelAndView.getModelMap().addAttribute("uisessiontimeout", uisessiontimeout);
        modelAndView.getModelMap().addAttribute("languages", surveyService.getLanguages());
        modelAndView.getModelMap().addAttribute("origin", request.getRequestURI());
        if (request.getParameter("imported") != null && request.getParameter("imported").length() > 0) {
            modelAndView.getModelMap().addAttribute("imported", request.getParameter("imported"));
        }
        Object id = request.getSession().getAttribute("surveyeditorsaved");
        if (id != null) {
            modelAndView.getModelMap().addAttribute("surveyeditorsaved", id);
            request.getSession().removeAttribute("surveyeditorsaved");
        }
    }
}


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
    request.setAttribute(APPLICATION_CAPTCHA_BYPASS, isByPassCaptcha());
    request.setAttribute(APPLICATION_FILEMANAGEMENT, enablefilemanagement != null && enablefilemanagement.equalsIgnoreCase("true"));
    return true;
}


public boolean isByPassCaptcha(){
    return captchaBypass != null && captchaBypass.equalsIgnoreCase("true");
}


}