package org.sdrc.childinfo.service;
 import org.sdrc.childinfo.model.Mail;
public interface UserService {


public String sendMail(String fromUserName,String toUserName,String toEmailId,StringBuffer subject,StringBuffer msg)
;

public String sendAdaptionMail(Mail mail)
;

}