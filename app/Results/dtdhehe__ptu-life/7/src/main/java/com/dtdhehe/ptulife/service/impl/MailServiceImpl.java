package com.dtdhehe.ptulife.service.impl;
 import com.dtdhehe.ptulife.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
@Service
public class MailServiceImpl implements MailService{

@Value("${spring.mail.username}")
 private  String from;

@Autowired
 private  JavaMailSender mailSender;


@Override
public void sendHtmlMail(String to,String subject,String content){
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(to);
    helper.setSubject(subject);
    // 发送html邮件时需设置为true
    helper.setText(content, true);
    helper.setFrom(from);
    mailSender.send(message);
}


@Override
public void sendInlineResourceMail(String to,String subject,String content,String rscPath,String rscId){
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(content, true);
    helper.setFrom(from);
    // 构造文件对象
    FileSystemResource res = new FileSystemResource(new File(rscPath));
    helper.addInline(rscId, res);
    mailSender.send(message);
}


@Override
public void sendSimpleMail(String to,String subject,String content){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(content);
    // 设置发送者
    message.setFrom(from);
    mailSender.send(message);
}


@Override
public void sendAttachmentMail(String to,String subject,String content,String filePath){
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(content, true);
    helper.setFrom(from);
    // 构造文件对象
    FileSystemResource file = new FileSystemResource(new File(filePath));
    String fileName = file.getFilename();
    helper.addAttachment(fileName, file);
    mailSender.send(message);
}


}