package com.zis.timer;
 import javax.mail.internet.AddressException;
import org.apache.log4j.Logger;
import com.zis.common.mail.MailSenderFactory;
import com.zis.common.mail.SimpleMailSender;
import com.zis.common.util.ZisUtils;
import com.zis.shop.bean.ShopInfo;
import com.zis.Interface.SimpleMailSender;
public class CommntOrderTimer {

 private  String[] shaoweiEmail;

 private  SimpleMailSender mailSender;

 private  Logger logger;


public void sendFailEmail(String[] mail,String title,String msg,ShopInfo shop){
    try {
        mailSender.send(mail, title + ZisUtils.getDateString("yyyy年MM月dd天HH时mm分"), msg);
    } catch (AddressException e) {
        logger.error(e.getMessage(), e);
        try {
            mailSender.send(shaoweiEmail, shop.getShopId() + title + ZisUtils.getDateString("yyyy年MM月dd天HH时mm分"), msg);
        } catch (Exception e2) {
            logger.error(e2.getMessage(), e2);
        }
    } catch (Exception e1) {
        logger.error(e1.getMessage(), e1);
    }
}


}