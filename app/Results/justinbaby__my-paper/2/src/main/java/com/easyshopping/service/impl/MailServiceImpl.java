package com.easyshopping.service.impl;
 import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import com.easyshopping.Setting;
import com.easyshopping.entity.ProductNotify;
import com.easyshopping.entity.SafeKey;
import com.easyshopping.service.MailService;
import com.easyshopping.service.TemplateService;
import com.easyshopping.util.SettingUtils;
import com.easyshopping.util.SpringUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import com.easyshopping.Interface.TemplateService;
import com.easyshopping.DTO.Template;
@Service("mailServiceImpl")
public class MailServiceImpl implements MailService{

@Resource(name = "freeMarkerConfigurer")
 private  FreeMarkerConfigurer freeMarkerConfigurer;

@Resource(name = "javaMailSender")
 private  JavaMailSenderImpl javaMailSender;

@Resource(name = "taskExecutor")
 private  TaskExecutor taskExecutor;

@Resource(name = "templateServiceImpl")
 private  TemplateService templateService;


public void sendProductNotifyMail(ProductNotify productNotify){
    Setting setting = SettingUtils.get();
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("productNotify", productNotify);
    String subject = SpringUtils.getMessage("admin.productNotify.mailSubject", setting.getSiteName());
    com.easyshopping.Template productNotifyMailTemplate = templateService.get("productNotifyMail");
    send(productNotify.getEmail(), subject, productNotifyMailTemplate.getTemplatePath(), model);
}


public void sendFindPasswordMail(String toMail,String username,SafeKey safeKey){
    Setting setting = SettingUtils.get();
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("username", username);
    model.put("safeKey", safeKey);
    String subject = SpringUtils.getMessage("shop.password.mailSubject", setting.getSiteName());
    com.easyshopping.Template findPasswordMailTemplate = templateService.get("findPasswordMail");
    send(toMail, subject, findPasswordMailTemplate.getTemplatePath(), model);
}


public void addSendTask(MimeMessage mimeMessage){
    try {
        taskExecutor.execute(new Runnable() {

            public void run() {
                javaMailSender.send(mimeMessage);
            }
        });
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public void run(){
    javaMailSender.send(mimeMessage);
}


public void send(String toMail,String subject,String templatePath){
    Setting setting = SettingUtils.get();
    send(setting.getSmtpFromMail(), setting.getSmtpHost(), setting.getSmtpPort(), setting.getSmtpUsername(), setting.getSmtpPassword(), toMail, subject, templatePath, null, true);
}


public void sendTestMail(String smtpFromMail,String smtpHost,Integer smtpPort,String smtpUsername,String smtpPassword,String toMail){
    Setting setting = SettingUtils.get();
    String subject = SpringUtils.getMessage("admin.setting.testMailSubject", setting.getSiteName());
    com.easyshopping.Template testMailTemplate = templateService.get("testMail");
    send(smtpFromMail, smtpHost, smtpPort, smtpUsername, smtpPassword, toMail, subject, testMailTemplate.getTemplatePath(), null, false);
}


}