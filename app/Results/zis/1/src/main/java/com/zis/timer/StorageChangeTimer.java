package com.zis.timer;
 import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import com.zis.common.util.ZisUtils;
import com.zis.shop.bean.Company;
import com.zis.shop.service.ShopService;
import com.zis.storage.entity.StorageProduct;
import com.zis.storage.entity.StorageRepoInfo;
import com.zis.storage.service.StorageService;
import com.zis.Interface.ShopService;
import com.zis.DTO.ShopService;
import com.zis.DTO.ShopService;
public class StorageChangeTimer extends CommntOrderTimer{

 private  ShopService doShopService;

 private  StorageService storageService;

 private  Logger logger;


public void setStorageService(StorageService storageService){
    this.storageService = storageService;
}


public void setDoShopService(ShopService doShopService){
    this.doShopService = doShopService;
}


public ShopService getDoShopService(){
    return doShopService;
}


public StorageService getStorageService(){
    return storageService;
}


public void stockChangeToCompany(){
    Date endTime = new Date();
    Date startTime = new Date(endTime.getTime() - 600000);
    logger.info("查询订单开始时间" + ZisUtils.getDateString("yyyy-MM-dd HH:mm:ss", startTime));
    logger.info("查询订单结束时间" + ZisUtils.getDateString("yyyy-MM-dd HH:mm:ss", endTime));
    List<Company> comList = this.doShopService.queryAllCompany();
    for (Company c : comList) {
        try {
            List<StorageRepoInfo> sList = this.storageService.findStorageRepoInfoByCompanyId(c.getCompanyId());
            if (sList.isEmpty()) {
                throw new RuntimeException(c.getCompanyName() + "公司没有没有仓库");
            }
            List<StorageProduct> pList = this.storageService.findByUpdateTimeBetweenStartTimeAndEndTimeAndRepoId(startTime, endTime, sList.get(0).getRepoId());
            if (pList.isEmpty()) {
                continue;
            }
            for (StorageProduct s : pList) {
                this.doShopService.stockChangeToShopUPdateItem(c.getCompanyId(), s.getSkuId(), s.getStockAvailable());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}


}