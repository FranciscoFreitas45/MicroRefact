package com.lingxiang2014.controller.admin;
 import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.CommonAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.Setting;
import com.lingxiang2014.Setting.AccountLockType;
import com.lingxiang2014.Setting.CaptchaType;
import com.lingxiang2014.Setting.RoundType;
import com.lingxiang2014.service.CacheService;
import com.lingxiang2014.service.FileService;
import com.lingxiang2014.service.MailService;
import com.lingxiang2014.service.StaticService;
import com.lingxiang2014.util.SettingUtils;
import com.sun.mail.smtp.SMTPSendFailedException;
import com.sun.mail.smtp.SMTPSenderFailedException;
import com.lingxiang2014.Interface.FileService;
@Controller("adminstingController")
@RequestMapping("/admin/setting")
public class SettingController extends BaseController{

@Resource(name = "fileServiceImpl")
 private  FileService fileService;

@Resource(name = "mailServiceImpl")
 private  MailService mailService;

@Resource(name = "cacheServiceImpl")
 private  CacheService cacheService;

@Resource(name = "staticServiceImpl")
 private  StaticService staticService;


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(ModelMap model){
    model.addAttribute("roundTypes", RoundType.values());
    model.addAttribute("captchaTypes", CaptchaType.values());
    model.addAttribute("accountLockTypes", AccountLockType.values());
    return "/admin/setting/edit";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Setting setting,MultipartFile watermarkImageFile,RedirectAttributes redirectAttributes){
    if (!isValid(setting)) {
        return ERROR_VIEW;
    }
    if (setting.getUsernameMinLength() > setting.getUsernameMaxLength() || setting.getPasswordMinLength() > setting.getPasswordMinLength()) {
        return ERROR_VIEW;
    }
    Setting srcSetting = SettingUtils.get();
    if (StringUtils.isEmpty(setting.getSmtpPassword())) {
        setting.setSmtpPassword(srcSetting.getSmtpPassword());
    }
    setting.setCnzzSiteId(srcSetting.getCnzzSiteId());
    setting.setCnzzPassword(srcSetting.getCnzzPassword());
    SettingUtils.set(setting);
    cacheService.clear();
    staticService.buildIndex();
    staticService.buildOther();
    OutputStream outputStream = null;
    try {
        org.springframework.core.io.Resource resource = new ClassPathResource(CommonAttributes.SHOPXX_PROPERTIES_PATH);
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        String templateUpdateDelay = properties.getProperty("template.update_delay");
        String messageCacheSeconds = properties.getProperty("message.cache_seconds");
        if (setting.getIsDevelopmentEnabled()) {
            if (!templateUpdateDelay.equals("0") || !messageCacheSeconds.equals("0")) {
                outputStream = new FileOutputStream(resource.getFile());
                properties.setProperty("template.update_delay", "0");
                properties.setProperty("message.cache_seconds", "0");
                properties.store(outputStream, "SHOP++ PROPERTIES");
            }
        } else {
            if (templateUpdateDelay.equals("0") || messageCacheSeconds.equals("0")) {
                outputStream = new FileOutputStream(resource.getFile());
                properties.setProperty("template.update_delay", "3600");
                properties.setProperty("message.cache_seconds", "3600");
                properties.store(outputStream, "SHOP++ PROPERTIES");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        IOUtils.closeQuietly(outputStream);
    }
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:edit.jhtml";
}


@RequestMapping(value = "/mail_test", method = RequestMethod.POST)
@ResponseBody
public Message mailTest(String smtpFromMail,String smtpHost,Integer smtpPort,String smtpUsername,String smtpPassword,String toMail){
    if (StringUtils.isEmpty(toMail)) {
        return ERROR_MESSAGE;
    }
    Setting setting = SettingUtils.get();
    if (StringUtils.isEmpty(smtpPassword)) {
        smtpPassword = setting.getSmtpPassword();
    }
    try {
        if (!isValid(Setting.class, "smtpFromMail", smtpFromMail) || !isValid(Setting.class, "smtpHost", smtpHost) || !isValid(Setting.class, "smtpPort", smtpPort) || !isValid(Setting.class, "smtpUsername", smtpUsername)) {
            return ERROR_MESSAGE;
        }
        mailService.sendTestMail(smtpFromMail, smtpHost, smtpPort, smtpUsername, smtpPassword, toMail);
    } catch (MailSendException e) {
        Exception[] messageExceptions = e.getMessageExceptions();
        if (messageExceptions != null) {
            for (Exception exception : messageExceptions) {
                if (exception instanceof SMTPSendFailedException) {
                    SMTPSendFailedException smtpSendFailedException = (SMTPSendFailedException) exception;
                    Exception nextException = smtpSendFailedException.getNextException();
                    if (nextException instanceof SMTPSenderFailedException) {
                        return Message.error("admin.setting.mailTestSenderFailed");
                    }
                } else if (exception instanceof MessagingException) {
                    MessagingException messagingException = (MessagingException) exception;
                    Exception nextException = messagingException.getNextException();
                    if (nextException instanceof UnknownHostException) {
                        return Message.error("admin.setting.mailTestUnknownHost");
                    } else if (nextException instanceof ConnectException) {
                        return Message.error("admin.setting.mailTestConnect");
                    }
                }
            }
        }
        return Message.error("admin.setting.mailTestError");
    } catch (MailAuthenticationException e) {
        return Message.error("admin.setting.mailTestAuthentication");
    } catch (Exception e) {
        return Message.error("admin.setting.mailTestError");
    }
    return Message.success("admin.setting.mailTestSuccess");
}


}