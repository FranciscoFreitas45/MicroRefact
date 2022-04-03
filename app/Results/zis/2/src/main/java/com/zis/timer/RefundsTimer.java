package com.zis.timer;
 import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import com.zis.common.util.ZisUtils;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.service.ShopService;
public class RefundsTimer extends CommntOrderTimer{

 private  ShopService doShopService;

 private  Logger logger;


public void setDoShopService(ShopService doShopService){
    this.doShopService = doShopService;
}


public ShopService getDoShopService(){
    return doShopService;
}


public void createRefunds(){
    Date endTime = new Date();
    Date startTime = new Date(endTime.getTime() - 600000);
    logger.info("退款查询开始时间" + ZisUtils.getDateString("yyyy-MM-dd HH:mm:ss", startTime));
    logger.info("退款查询结束时间" + ZisUtils.getDateString("yyyy-MM-dd HH:mm:ss", endTime));
    List<ShopInfo> shopList = this.doShopService.queryAllShop();
    for (ShopInfo shop : shopList) {
        try {
            this.doShopService.queryApplyRefundForShopIdAndDate(shop.getShopId(), startTime, endTime);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String[] email = { shop.getEmails() };
            String msg = String.format("%s %s %s %s \n %s %s", "开始时间", ZisUtils.getDateString("yyyy-MM-dd HH:mm:ss", startTime), "结束时间", ZisUtils.getDateString("yyyy-MM-dd HH:mm:ss", endTime), "错误原因", e.getMessage());
            sendFailEmail(email, shop.getShopName() + " 同步退款失败", msg, shop);
        }
    }
}


}