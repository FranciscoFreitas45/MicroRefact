package com.zis.timer;
 import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.common.util.Page;
import com.zis.common.util.PaginationQueryUtil;
import com.zis.purchase.biz.DoPurchaseService;
public class PurchasePlanBuildTimer {

 private  Logger logger;

 private  DoPurchaseService purchaseService;

 private  Integer ZIS_STORAGE_REPO_ID;


public void buildAll(){
    // 考虑到服务器压力，分批处理采购计划
    final int batchSize = 1000;
    DetachedCriteria dc = DetachedCriteria.forClass(Bookinfo.class);
    Integer totalCount = PaginationQueryUtil.getTotalCount(dc);
    Page page = Page.createPage(1, batchSize, totalCount);
    for (int i = 0; i < page.getTotalPageCount(); i++) {
        page = Page.createPage(i + 1, batchSize, totalCount);
        logger.info("采购计划开始，from=" + page.getBeginIndex() + ", to=" + (page.getBeginIndex() + batchSize));
        try {
            @SuppressWarnings("unchecked")
            List<Bookinfo> list = PaginationQueryUtil.paginationQuery(dc, page);
            purchaseService.addPurchasePlanForBatch(list, ZIS_STORAGE_REPO_ID);
        } catch (Exception e) {
            logger.error("执行采购计划过程中出错" + e);
        }
    }
}


public void setPurchaseService(DoPurchaseService purchaseService){
    this.purchaseService = purchaseService;
}


}