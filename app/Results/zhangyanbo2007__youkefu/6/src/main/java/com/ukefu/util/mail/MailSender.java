package com.ukefu.util.mail;
 import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.apache.commons.lang.StringUtils;
public class MailSender {

 private  Properties props;

 private  MailAuthenticator authenticator;

 private  String fromEmail;

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
public MailSender(final String smtpHostName, final String username, final String password, final String seclev, String sslport) {
    init(username, password, smtpHostName, seclev, sslport);
}/**
 * 初始化邮件发送器
 *
 * @param smtpHostName
 *            SMTP邮件服务器地址
 * @param fromEmail
 * 			  邮件发送地址
 * @param username
 *            发送邮件的用户名(地址)
 * @param password
 *            发送邮件的密码
 * @param seclev
 *            是否开启ssl
 */
public MailSender(final String smtpHostName, final String fromEmail, final String username, final String password, final String seclev, String sslport) {
    this.fromEmail = fromEmail;
    init(username, password, smtpHostName, seclev, sslport);
}/**
 * 初始化邮件发送器
 *
 * @param username
 *            发送邮件的用户名(地址)，并以此解析SMTP服务器地址
 * @param password
 *            发送邮件的密码
 */
public MailSender(final String username, final String password, final String seclev, String sslport) {
    // 通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
    final String smtpHostName = "smtp." + username.split("@")[1];
    init(username, password, smtpHostName, seclev, sslport);
}
@SuppressWarnings("restriction")
public void init(String username,String password,String smtpHostName,String seclev,String sslport){
    // 初始化props
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", smtpHostName);
    // ssl
    if (!StringUtils.isBlank(seclev) && seclev.equals("true")) {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.port", sslport);
        props.put("mail.smtp.socketFactory.port", sslport);
    }
    // 验证
    authenticator = new MailAuthenticator(username, password);
    // 创建session
    session = Session.getInstance(props, authenticator);
}


public void send(List<String> recipients,MailInfo mail){
    send(recipients, mail.getSubject(), mail.getContent());
}


}