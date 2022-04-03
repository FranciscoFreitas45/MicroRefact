package com.gs.common.mail;
 import com.gs.bean.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class MailConfig {

 private  Logger logger;

 private  Properties props;


public int getInt(String key){
    return Integer.valueOf(props.getProperty(key));
}


public Properties readProperties(File file){
    props = new Properties();
    try {
        props.load(new FileInputStream(file));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return props;
}


public void sendMail(File file,Mail mail){
    // 根据路径读到配置文件
    Properties props = MailConfig.readProperties(file);
    Session session = Session.getInstance(props, new MailAuth(MailConfig.getString("username"), MailConfig.getString("password")));
    try {
        Transport transport = session.getTransport();
        // 尝试发送邮件
        transport.connect();
        logger.info("尝试发送邮件");
        Message msg = new MimeMessage(session);
        // 把账号经过改编
        mail.setFrom(MailConfig.getString("username"));
        // 再调用get方法
        msg.setFrom(mail.getFrom());
        msg.setSubject(mail.getSubject());
        if (mail.getContent() != null) {
            msg.setContent(mail.getContent(), mail.getType());
        } else {
            msg.setContent(mail.getMultipart());
        }
        // 假如密送不为空
        if (mail.getBccRecipients() != null) {
            msg.setRecipients(RecipientType.BCC, mail.getBccRecipients());
        }
        // // 这个为空就报错
        // // 发送邮件的类型,
        // if (mail.getRecipients() != null) {
        // msg.setRecipients(RecipientType.TO, mail.getRecipients());
        // }
        // // 假如超送不为空
        // if (mail.getCcRecipients() != null){
        // msg.setRecipients(RecipientType.CC, mail.getCcRecipients());
        // }
        transport.send(msg);
        logger.info("发送邮件成功");
    } catch (NoSuchProviderException e) {
        e.printStackTrace();
    } catch (MessagingException e) {
        e.printStackTrace();
    }
}


public String getString(String key){
    return props.getProperty(key);
}


}