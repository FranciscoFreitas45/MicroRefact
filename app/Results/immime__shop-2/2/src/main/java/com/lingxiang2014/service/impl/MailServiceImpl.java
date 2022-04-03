package com.lingxiang2014.service.impl;
 import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import com.lingxiang2014.Setting;
import com.lingxiang2014.entity.SafeKey;
import com.lingxiang2014.service.MailService;
import com.lingxiang2014.service.TemplateService;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.util.SpringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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


public void sendBackupMail(String smtpFromMail,String smtpHost,Integer smtpPort,String smtpUsername,String smtpPassword,String toMail,List<String> attachments){
    Setting setting = SettingUtils.get();
    String subject = SpringUtils.getMessage("admin.setting.testMailSubject", setting.getSiteName());
    com.lingxiang2014.Template testMailTemplate = templateService.get("backup");
    send(smtpFromMail, smtpHost, smtpPort, smtpUsername, smtpPassword, toMail, subject, testMailTemplate.getTemplatePath(), null, false, attachments);
}


public void sendFindPasswordMail(String toMail,String username,SafeKey safeKey){
    Setting setting = SettingUtils.get();
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("username", username);
    model.put("safeKey", safeKey);
    String subject = SpringUtils.getMessage("shop.password.mailSubject", setting.getSiteName());
    com.lingxiang2014.Template findPasswordMailTemplate = templateService.get("findPasswordMail");
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
    send(setting.getSmtpFromMail(), setting.getSmtpHost(), setting.getSmtpPort(), setting.getSmtpUsername(), setting.getSmtpPassword(), toMail, subject, templatePath, null, true, null);
}


public void sendTestMail(String smtpFromMail,String smtpHost,Integer smtpPort,String smtpUsername,String smtpPassword,String toMail){
    Setting setting = SettingUtils.get();
    String subject = SpringUtils.getMessage("admin.setting.testMailSubject", setting.getSiteName());
    com.lingxiang2014.Template testMailTemplate = templateService.get("testMail");
    send(smtpFromMail, smtpHost, smtpPort, smtpUsername, smtpPassword, toMail, subject, testMailTemplate.getTemplatePath(), null, false, null);
}


}