package com.gp.cricket.service;
 import java.util.Properties;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.gp.cricket.configuration.EmailConfiguration;
import com.gp.cricket.mapobject.PaymentEmailBody;
@Service
public class EmailService {

@Autowired
 private  EmailConfiguration emailConfiguration;


public String setClubPaymentHtmlBody(PaymentEmailBody message){
    String htmlMsg = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "	<title>CrickDom Annual Payment</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "	<h1 align=\"center\"><u>CickDom - Sri Lankan Domestic Cricket Scorecard</u></h1>\r\n" + "	<hr>\r\n" + "	<p></p>\r\n" + "	<pre>Club	:" + message.getClub() + "</pre>\r\n" + "	<pre>Amount	:(Rs)" + message.getAmount() + "</pre>\r\n" + "	<pre>Year	:" + message.getYear() + "</pre>\r\n" + "	<pre>Date	:" + message.getDate() + "</pre>\r\n" + "<hr>\r\n" + "	<small align=\\\"center\\\">Payment Successfully Completed.\nThank You.</small>" + "</body>\r\n" + "</html>";
    return htmlMsg;
}


public String setUpEmailInstance(String from,String to,String subject,PaymentEmailBody message){
    JavaMailSenderImpl mailSender = setUpEmailSender();
    // Add HTML for EmailBody
    Boolean flag = false;
    try {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        String htmlMsg = setClubPaymentHtmlBody(message);
        mailMessage.setSubject(subject);
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(mailMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText(htmlMsg, true);
        mailSender.send(mailMessage);
        flag = true;
    } catch (Exception e) {
        flag = false;
        System.out.println("Mail Service Error");
    }
    if (flag) {
        return "success";
    }
    return "notsuccess";
}


public JavaMailSenderImpl setUpEmailSender(){
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(emailConfiguration.getHost());
    mailSender.setPort(emailConfiguration.getPort());
    mailSender.setUsername(emailConfiguration.getUsername());
    mailSender.setPassword(emailConfiguration.getPassword());
    // Gmail Mail Config
    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");
    return mailSender;
}


}