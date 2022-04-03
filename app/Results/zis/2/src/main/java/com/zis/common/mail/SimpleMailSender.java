package com.zis.common.mail;
 import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.lang3.StringUtils;
public class SimpleMailSender {

 private  Properties props;

 private  MailAuthenticator authenticator;

 private  Session session;

/**
 * 初始化邮件发送器
 *
 * @param smtpHostName
 *            SMTP邮件服务器地址
 * @param username
 *            发送邮件的用户名(地址)
 * @param password
 *            发送邮件的密码
 */
public SimpleMailSender(final String smtpHostName, final String username, final String password) {
    init(username, password, smtpHostName);
}/**
 * 初始化邮件发送器
 *
 * @param username
 *            发送邮件的用户名(地址)，并以此解析SMTP服务器地址
 * @param password
 *            发送邮件的密码
 */
public SimpleMailSender(final String username, final String password) {
    // 通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
    final String smtpHostName = "smtp." + username.split("@")[1];
    init(username, password, smtpHostName);
}
public void init(String username,String password,String smtpHostName){
    // 初始化props
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", smtpHostName);
    // 验证
    authenticator = new MailAuthenticator(username, password);
    // 创建session
    session = Session.getInstance(props, authenticator);
}


public void send(String[] recipients,String subject,Object content,String filePath){
    // 创建mime类型邮件
    final MimeMessage message = new MimeMessage(session);
    // 设置发信人
    message.setFrom(new InternetAddress(authenticator.getUsername()));
    // 设置收件人们
    final int num = recipients.length;
    InternetAddress[] addresses = new InternetAddress[num];
    for (int i = 0; i < num; i++) {
        addresses[i] = new InternetAddress(recipients[i]);
    }
    message.setRecipients(RecipientType.TO, addresses);
    // 设置主题
    message.setSubject(subject);
    // 设置邮件内容
    if (StringUtils.isBlank(filePath)) {
        message.setContent(content.toString(), "text/html;charset=utf-8");
    } else {
        // 添加附件
        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();
        // Fill the message
        messageBodyPart.setText(content.toString());
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filePath);
        messageBodyPart.setDataHandler(new DataHandler(source));
        System.out.println(filePath);
        messageBodyPart.setFileName(getFileName(filePath));
        multipart.addBodyPart(messageBodyPart);
        // Put parts in message
        message.setContent(multipart);
    }
    // 发送
    Transport.send(message);
}


public String getFileName(String filePath){
    int idx = filePath.lastIndexOf("/");
    if (idx == -1) {
        idx = 0;
    }
    return filePath.substring(idx);
}


}