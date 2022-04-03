package com.gbcom.common.template.xml.mail;
 import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;
import com.gbcom.op.util.xml.XStreamUtil;
public class MailSender {

 private  Logger LOGGER;

 private  MailContext context;

 private  MailSender INSTANCE;

private MailSender() {
    final Class<?>[] classContext = { MailContext.class, MailReceiver.class };
    final URL url = Thread.currentThread().getContextClassLoader().getResource("config/mail/mail.xml");
    context = XStreamUtil.fromXML(MailContext.class, url.getFile(), classContext);
    LOGGER.info("MailSender init success , ----- from file ： config/mail/mail.xml");
}
public MailContext getMailContext(){
    return context;
}


public void sendMail(String subject,String content,Set<String> files){
    final Message msg = buildDefaultMsg();
    // 设定信中的主题
    msg.setSubject(subject);
    // 设定送信的时间
    msg.setSentDate(new Date());
    final Multipart mlp = new MimeMultipart();
    final MimeBodyPart mbpContent = new MimeBodyPart();
    mbpContent.setText(content);
    mlp.addBodyPart(mbpContent);
    /* 往邮件中添加附件 */
    MimeBodyPart mbpFile;
    for (String fileName : files) {
        mbpFile = new MimeBodyPart();
        final FileDataSource fds = new FileDataSource(fileName);
        mbpFile.setDataHandler(new DataHandler(fds));
        mbpFile.setFileName((fds.getName()));
        mlp.addBodyPart(mbpFile);
    }
    msg.setContent(mlp);
    msg.saveChanges();
    // 4.0发送
    Transport.send(msg);
    LOGGER.info("Send mail success  ---  subject=" + subject + " : content=" + content + " :: file=" + files);
}


public Message buildDefaultMsg(){
    // 1.0 封装参数,从模板获取
    final Properties props = new Properties();
    // // 服务器地址
    props.put("mail.host", context.getHost());
    // 端口号
    props.put("mail.smtp.port", context.getPort());
    // 暂时使用SMTP协议,可去掉
    props.put("mail.transport.protocol", context.getProtocol());
    props.put("mail.smtp.auth", context.getAuth());
    // 2.0 产生新的邮件Session 服务
    final SmtpAuthentic auth = new SmtpAuthentic(context.getSenderName(), context.getSenderPwd());
    // 带简单鉴权
    final Session mailSession = Session.getInstance(props, auth);
    // //邮件打印
    mailSession.setDebug(true);
    // 3.0 封装邮件Msg
    final Message msg = new MimeMessage(mailSession);
    // 设定传送邮件的发信人
    msg.setFrom(new InternetAddress(context.getSenderAddr()));
    for (MailReceiver rev : context.getReceivers()) {
        if (rev.getReceiverType().equalsIgnoreCase("BCC")) {
            msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(rev.getReceiverAddr()));
        } else if (rev.getReceiverType().equalsIgnoreCase("CC")) {
            msg.addRecipient(Message.RecipientType.CC, new InternetAddress(rev.getReceiverAddr()));
        } else {
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(rev.getReceiverAddr()));
        }
    }
    msg.setDataHandler(new javax.activation.DataHandler("alarm", "text/html"));
    return msg;
}


public void setContext(MailContext context){
    this.context = context;
}


public void main(String[] args){
    Set<String> files = new HashSet<String>();
    try {
        for (int i = 0; i < 10; i++) {
            files.add("D:\\example.xml");
            MailSender.getInstance().sendMail("hellow", "chinese room", files);
        }
    } catch (MessagingException e) {
        LOGGER.error(e.getMessage());
    }
}


public MailSender getInstance(){
    return INSTANCE;
}


public MailContext getContext(){
    return context;
}


}