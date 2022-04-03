package com.zis.shop.bo.impl;
 import java.util.Date;
import javax.mail.internet.AddressException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.common.mail.MailSenderFactory;
import com.zis.common.mail.SimpleMailSender;
import com.zis.common.util.ZisUtils;
import com.zis.shop.bean.DownloadItemLog;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bo.ShopDownloadBo;
import com.zis.shop.repository.DownloadItemLogDao;
public class AbstractShopDownloadBo implements ShopDownloadBo{

 private  Logger logger;

 private  SimpleMailSender mailSender;

@Autowired
 private  DownloadItemLogDao downloadItemLogDao;

 private  String[] shaoweiEmail;


public DownloadItemLog addDownLoadLog(Integer shopId,String desc){
    DownloadItemLog log = new DownloadItemLog();
    log.setCreateTime(new Date());
    log.setUpdateTime(new Date());
    log.setDescription(desc);
    log.setShopId(shopId);
    return log;
}


public void addFailDownLoadLog(Integer shopId,String desc){
    DownloadItemLog log = addDownLoadLog(shopId, desc);
    log.setStatus(DownloadItemLog.DownloadItemLogStatus.FAIL.getValue());
    this.downloadItemLogDao.save(log);
}


public void addErrorDownLoadLog(Integer shopId,String desc){
    DownloadItemLog log = addDownLoadLog(shopId, desc);
    log.setStatus(DownloadItemLog.DownloadItemLogStatus.ERROR.getValue());
    this.downloadItemLogDao.save(log);
}


public void sendFailEmail(String[] mail,String msg,ShopInfo shop){
    try {
        mailSender.send(mail, "店铺下载时异常数据" + ZisUtils.getDateString("yyyy年MM月dd天HH时mm分"), msg);
    } catch (AddressException e) {
        addErrorDownLoadLog(shop.getShopId(), e.getMessage());
        logger.error(e.getMessage(), e);
        try {
            mailSender.send(shaoweiEmail, shop.getShopId() + "店铺下载时异常数据" + ZisUtils.getDateString("yyyy年MM月dd天HH时mm分"), msg);
        } catch (Exception e2) {
            addErrorDownLoadLog(shop.getShopId(), e2.getMessage());
            logger.error(e2.getMessage(), e2);
        }
    } catch (Exception e1) {
        addErrorDownLoadLog(shop.getShopId(), e1.getMessage());
        logger.error(e1.getMessage(), e1);
    }
}


}