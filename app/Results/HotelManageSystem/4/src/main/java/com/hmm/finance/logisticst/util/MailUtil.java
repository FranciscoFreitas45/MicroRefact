package com.hmm.finance.logisticst.util;
 import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;
public class MailUtil {

 private  String email;

 private  String code;

public MailUtil(String email, String code) {
    super();
    this.email = email;
    this.code = code;
}
public void sentEMail(){
    String from = "751613511@qq.com";
    // 指定发送邮件的主机
    String host = "smtp.qq.com";
    Properties properties = System.getProperties();
    // 设置邮件服务器
    properties.setProperty("mail.smtp.host", host);
    // 打开认证
    properties.setProperty("mail.smtp.auth", "true");
    try {
        // QQ邮箱需要下面这段代码，163邮箱不需要
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 1.获取默认session对象
        Session session = Session.getInstance(properties, new Authenticator() {

            public PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱账号、授权码
                return new PasswordAuthentication("751613511@qq.com", "dvflvplurbvdbdha");
            }
        });
        // 2.创建邮件对象
        Message message = new MimeMessage(session);
        // 2.1设置发件人
        message.setFrom(new InternetAddress(from));
        // 2.2设置接收人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        // 2.3设置邮件主题
        message.setSubject("出纳付款");
        // 2.4设置邮件内容
        String content = "<html><head></head><body><h1>这是一封[出纳付款]的信，请点击以下链接进行付款</h1><h3><a href='http://localhost:8080/inStorage/complete/" + code + "'>http://localhost:8080/inStorage/complete/" + code + "</href></h3></body></html>";
        message.setContent(content, "text/html;charset=UTF-8");
        // 3.发送邮件
        Transport.send(message);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public PasswordAuthentication getPasswordAuthentication(){
    // 发件人邮箱账号、授权码
    return new PasswordAuthentication("751613511@qq.com", "dvflvplurbvdbdha");
}


}