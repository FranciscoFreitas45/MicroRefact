package com.lingxiang2014.service;
 import java.util.List;
import java.util.Map;
import com.lingxiang2014.entity.SafeKey;
public interface MailService {


public void sendBackupMail(String smtpFromMail,String smtpHost,Integer smtpPort,String smtpUsername,String smtpPassword,String toMail,List<String> attachments)
;

public void sendFindPasswordMail(String toMail,String username,SafeKey safeKey)
;

public void send(String toMail,String subject,String templatePath)
;

public void sendTestMail(String smtpFromMail,String smtpHost,Integer smtpPort,String smtpUsername,String smtpPassword,String toMail)
;

}