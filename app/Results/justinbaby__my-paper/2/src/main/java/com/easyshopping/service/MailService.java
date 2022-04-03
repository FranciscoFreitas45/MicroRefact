package com.easyshopping.service;
 import java.util.Map;
import com.easyshopping.entity.ProductNotify;
import com.easyshopping.entity.SafeKey;
public interface MailService {


public void sendProductNotifyMail(ProductNotify productNotify)
;

public void sendFindPasswordMail(String toMail,String username,SafeKey safeKey)
;

public void send(String toMail,String subject,String templatePath)
;

public void sendTestMail(String smtpFromMail,String smtpHost,Integer smtpPort,String smtpUsername,String smtpPassword,String toMail)
;

}