package com.dtdhehe.ptulife.service;
 import javax.mail.MessagingException;
public interface MailService {


public void sendHtmlMail(String to,String subject,String content)
;

public void sendInlineResourceMail(String to,String subject,String content,String rscPath,String rscId)
;

public void sendSimpleMail(String to,String subject,String content)
;

public void sendAttachmentMail(String to,String subject,String content,String filePath)
;

}