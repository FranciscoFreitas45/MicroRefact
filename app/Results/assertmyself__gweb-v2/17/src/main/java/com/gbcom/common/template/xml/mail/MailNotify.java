package com.gbcom.common.template.xml.mail;
 import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class MailNotify {


public void sendSmtpEmail(String sendAddress,String name,String password,String emailServer,String[] revAddresses,String title,String content,Vector<String> file){
    if (revAddresses == null || revAddresses.length == 0) {
        return;
    }
    // 设定所要用的Mail 服务器和所使用的传输协议
    Properties props = new Properties();
    props.put("mail.host", emailServer);
    props.put("mail.smtp.auth", "true");
    SmtpAuthentic auth = new SmtpAuthentic(name, password);
    Session mailSession = Session.getInstance(props, auth);
    boolean sessionDebug = false;
    mailSession.setDebug(sessionDebug);
    Message msg = new MimeMessage(mailSession);
    msg.setFrom(new InternetAddress(sendAddress));
    InternetAddress address = null;
    for (String revAddress : revAddresses) {
        address = new InternetAddress(revAddress);
        msg.addRecipient(Message.RecipientType.TO, address);
    }
    msg.setSubject(title);
    msg.setSentDate(new Date());
    // msg.setDataHandler(new
    // javax.activation.DataHandler("alarm","text/html"));
    // multipart
    Multipart mp = new MimeMultipart();
    MimeBodyPart mbpContent = new MimeBodyPart();
    mbpContent.setText(content);
    mp.addBodyPart(mbpContent);
    /* 往邮件中添加附件 */
    Enumeration<String> efile = file.elements();
    String fileName;
    while (efile.hasMoreElements()) {
        MimeBodyPart mbpFile = new MimeBodyPart();
        fileName = efile.nextElement().toString();
        FileDataSource fds = new FileDataSource(fileName);
        mbpFile.setDataHandler(new DataHandler(fds));
        mbpFile.setFileName((fds.getName()));
        mp.addBodyPart(mbpFile);
    }
    msg.setContent(mp);
    msg.saveChanges();
    Transport.send(msg);
}


public void main(String[] args){
    try {
        MailNotify.sendSmtpEmail("gbcomcms@163.com", "gbcomcms", "Welcome123", "smtp.163.com", new String[] { "sunyanzheng@gbcom.com.cn" }, "test", "content+who are you");
    } catch (AddressException e) {
        e.printStackTrace();
    } catch (MessagingException e) {
        e.printStackTrace();
    }
}


}