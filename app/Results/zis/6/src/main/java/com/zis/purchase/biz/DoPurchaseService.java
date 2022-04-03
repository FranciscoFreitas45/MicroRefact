package com.zis.purchase.biz;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.purchase.bean.Inwarehouse;
import com.zis.purchase.bean.InwarehouseBizType;
import com.zis.purchase.bean.InwarehouseDetail;
import com.zis.purchase.bean.InwarehousePosition;
import com.zis.purchase.bean.InwarehouseStatus;
import com.zis.purchase.bean.PurchaseDetail;
import com.zis.purchase.bean.PurchasePlan;
import com.zis.purchase.bean.TempImportDetail;
import com.zis.purchase.bean.TempImportTask;
import com.zis.purchase.bean.TempImportTaskBizTypeEnum;
import com.zis.purchase.dto.InwarehouseCreateDTO;
import com.zis.purchase.dto.InwarehouseCreateResult;
import com.zis.purchase.dto.InwarehouseDealtResult;
import com.zis.purchase.dto.InwarehouseView;
import com.zis.purchase.dto.StockDTO;
import com.zis.purchase.dto.TempImportDTO;
import com.zis.purchase.dto.TempImportDetailView;
import com.zis.purchase.repository.InwarehouseDao;
import com.zis.purchase.repository.InwarehouseDetailDao;
import com.zis.purchase.repository.InwarehousePositionDao;
import com.zis.purchase.repository.PurchaseDetailDao;
import com.zis.purchase.repository.PurchasePlanDao;
import com.zis.purchase.repository.TempImportDetailDao;
import com.zis.Interface.TempImportBO;
import com.zis.Interface.TempImportDetailDao;
import com.zis.DTO.StockDTO;
@Service
public class DoPurchaseService {

@Autowired
 private  InwarehouseDao inwarehouseDao;

@Autowired
 private  InwarehousePositionDao inwarehousePositionDao;

@Autowired
 private  InwarehouseDetailDao inwarehouseDetailDao;

@Autowired
 private  PurchasePlanDao purchasePlanDao;

@Autowired
 private  PurchaseBO purchaseBO;

@Autowired
 private  TempImportBO tempImportBO;

@Autowired
 private  InwarehouseBO inwarehouseBO;

@Autowired
 private  PurchaseInwarehouseBOV2 purchaseInwarehouseBO;

@Autowired
 private  PurchaseDetailDao purchaseDetailDao;

@Autowired
 private  TempImportDetailDao tempImportDetailDao;

 private  Logger logger;


public boolean isAllowManualDecisionForPurchasePlan(){
    return purchaseBO.isAllowManualDecisionForPurchasePlan();
}


public String updateAssociatePurchaseTempImportWithBookInfo(TempImportDetail detail,Bookinfo book){
    return tempImportBO.updateAssociatePurchaseTempImportWithBookInfo(detail, book);
}


public void addBookStock(List<StockDTO> importList,Integer repoId){
    if (importList == null || importList.isEmpty()) {
        return;
    }
    // 批量保存
    for (StockDTO record : importList) {
        String errMsg = updateBookStock(record.getBookId(), record.getStockBalance(), repoId);
        if (StringUtils.isNotBlank(errMsg)) {
            logger.error("更新采购计划中的库存失败:" + errMsg);
        }
    }
}


public Integer calculateStillRequireAmount(PurchasePlan plan,Integer stockAmount){
    return purchaseBO.calculateStillRequireAmount(plan, stockAmount);
}


public String updateBookStock(Integer bookId,Integer amount,Integer repoId){
    return purchaseBO.updateBookStock(bookId, amount, repoId);
}


public String checkForDoInwarehouse(String purchaseOperator,Integer bookId,Integer amount){
    return this.purchaseInwarehouseBO.checkForDoInwarehouseNew(purchaseOperator, bookId, amount);
}


public void addTempImportTask(List<TempImportDTO> list,TempImportTaskBizTypeEnum bizType,String memo){
    tempImportBO.addTempImportTask(list, bizType, memo);
}


public String addManualDecision(Integer bookId,Integer amount,Integer repoId){
    return purchaseBO.addManualDecision(bookId, amount, repoId);
}


public InwarehouseCreateResult createInwarehouse(InwarehouseCreateDTO inwarehouse){
    // 输入有效性检查
    if (inwarehouse == null) {
        throw new RuntimeException("illegal argument, for input null");
    }
    // 采购入库
    if (InwarehouseBizType.PURCHASE.equals(inwarehouse.getBizType())) {
        // return this.purchaseInwarehouseBO.createInwarehouse(inwarehouse);
        return null;
    } else // 其他入库
    {
        return this.inwarehouseBO.createInwarehouse(inwarehouse);
    }
}


@Deprecated
public void batchUpdatePurchasePlanForPurchaseAmount(Integer repoId){
    this.purchaseBO.batchUpdatePurchasePlanForPurchaseAmount(repoId);
}


public Page<TempImportDetail> findTempImportDetailBySpecPage(Specification<TempImportDetail> spec,Pageable page){
    return this.tempImportDetailDao.findAll(spec, page);
}


public void addPurchasePlanForBatch(List<Bookinfo> bookList,Integer repoId){
    purchaseBO.addPurchasePlanForBatch(bookList, repoId);
}


public InwarehouseDealtResult applyInwarehouse(Integer inwarehouseId,String posLabel,Integer bookId,Integer amount){
    // 参数基本检查
    if (inwarehouseId == null) {
        throw new IllegalArgumentException("inwarehouseId could not be null");
    }
    // 入库单检查，必须存在且状态是处理中
    Inwarehouse in = this.inwarehouseDao.findOne(inwarehouseId);
    if (in == null) {
        throw new IllegalArgumentException("不存在的入库单，id=" + inwarehouseId);
    }
    if (!InwarehouseStatus.PROCESSING.equals(in.getStatus())) {
        throw new RuntimeException("入库单的状态必须是处理中, id=" + inwarehouseId);
    }
    // 采购入库
    if (InwarehouseBizType.PURCHASE.equals(in.getBizType())) {
        // return this.purchaseInwarehouseBO.doInwarehouse(in, posLabel,
        // bookId, amount);
        return null;
    } else // 退货入库或直接入库
    {
        return this.inwarehouseBO.doInwarehouse(in, posLabel, bookId, amount);
    }
}


public Page<PurchasePlan> findAllPurchasePlan(Pageable page){
    return this.purchasePlanDao.findAll(page);
}


public Page<PurchaseDetail> findPurchaseDetailBySpecPage(Specification<PurchaseDetail> spec,Pageable page){
    return this.purchaseDetailDao.findAll(spec, page);
}


public String updateAssociateTempImportDetailWithBookInfo(Integer tempImportDetailId,Integer bookId){
    return tempImportBO.updateAssociateTempImportDetailWithBookInfo(tempImportDetailId, bookId);
}


public void deleteOnwayStock(String purchaseOperator,Integer repoId){
    if (StringUtils.isBlank(purchaseOperator)) {
        return;
    }
    this.purchaseBO.deleteOnwayStock(purchaseOperator, repoId);
}


public PurchasePlan findPurchasePlanByBookId(int bookId,Integer repoId){
    return purchaseBO.findPurchasePlanByBookId(bookId, repoId);
}


public Page<PurchasePlan> findPurchasePlanBySpecPage(Specification<PurchasePlan> spec,Pageable page){
    return this.purchasePlanDao.findAll(spec, page);
}


public void afterPut(String purchaseOperator,Integer bookId,Integer amount,Integer repoId,Integer batchId){
    this.purchaseInwarehouseBO.afterPutNew(purchaseOperator, bookId, amount, repoId, batchId);
}


public Page<InwarehouseDetail> findInwarehouseDetailBySpecPage(Specification<InwarehouseDetail> spec,Pageable page){
    return this.inwarehouseDetailDao.findAll(spec, page);
}


public List<PurchasePlan> findPurchasePlanByIsbn(String isbn,Integer repoId){
    return purchasePlanDao.findByIsbn(isbn, repoId);
}


public Page<Inwarehouse> findAllInwarehouse(Pageable page){
    return this.inwarehouseDao.findAll(page);
}


public TempImportTask findTempImportTaskByTaskId(Integer taskId){
    return tempImportBO.findTempImportTaskByTaskId(taskId);
}


public InwarehouseView findInwarehouseViewById(Integer inwarehouseId){
    // 查询入库单
    Inwarehouse in = this.inwarehouseDao.findOne(inwarehouseId);
    if (in == null) {
        return null;
    }
    InwarehouseView inView = new InwarehouseView();
    BeanUtils.copyProperties(in, inView);
    inView.setBizTypeDisplay(InwarehouseBizType.getDisplay(in.getBizType()));
    inView.setStatusDisplay(InwarehouseStatus.getDisplay(in.getStatus()));
    if (InwarehouseStatus.PROCESSING.equals(in.getStatus())) {
        // 查询入库单下的可用库位
        // DetachedCriteria criteria = DetachedCriteria
        // .forClass(InwarehousePosition.class);
        // criteria.add(Restrictions.eq("inwarehouseId", inwarehouseId));
        // criteria.add(Restrictions.eq("isFull", false));
        // criteria.addOrder(Order.asc("gmtCreate"));
        // List<InwarehousePosition> list = this.inwarehousePositionDao
        // .findByCriteria(criteria);
        // 按照ID排序，由于是同一时间创建的，如果按照时间排序会导致库位顺序错乱的bug
        List<InwarehousePosition> list = this.inwarehousePositionDao.findAvailablePosition(inwarehouseId);
        if (list == null || list.isEmpty()) {
            return inView;
        }
        String[] positionLabel = new String[list.size()];
        Integer[] capacity = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            positionLabel[i] = list.get(i).getPositionLabel();
            capacity[i] = list.get(i).getCapacity();
        }
        inView.setPositionLabel(positionLabel);
        inView.setCapacity(capacity);
    }
    return inView;
}


public Page<TempImportDetail> findAllTempImportDetail(Pageable page){
    return this.tempImportDetailDao.findAll(page);
}


public String addWhiteList(Integer bookId,Integer repoId){
    try {
        purchaseBO.addWhiteList(bookId, repoId);
        return "";
    } catch (Exception e) {
        String msg = "加入白名单失败," + e.getMessage();
        logger.error(msg, e);
        return msg;
    }
}


public String addPurchaseDetail(int bookId,int purchasedAmount,String operator,String position,String memo,Integer repoId,Integer stockAmount){
    return purchaseBO.addPurchaseDetail(bookId, purchasedAmount, operator, position, memo, repoId, stockAmount);
}


public void deleteInwarehouseDetail(Integer detailId){
    this.inwarehouseBO.deleteInwarehouseDetail(detailId);
}


public Page<Inwarehouse> findInwarehouseBySpecPage(Specification<Inwarehouse> spec,Pageable page){
    return this.inwarehouseDao.findAll(spec, page);
}


public List<InwarehouseDetail> findInwarehouseDetailByInwarehouseIds(Integer[] inwarehouseIds){
    return this.inwarehouseDetailDao.findByInwarehouseIds(inwarehouseIds);
}


public List<PurchaseDetail> findPurchaseDetailByBatchId(Integer batchId){
    return this.purchaseDetailDao.findByBatchId(batchId);
}


public Page<TempImportTask> findAllTempImportTask(Pageable page){
    return tempImportBO.findAllTempImportTask(page);
}


public void updateTempImportDetail(Integer taskId){
    tempImportBO.updateTempImportDetail(taskId);
}


public String removeManualDecision(Integer bookId,Integer repoId){
    try {
        purchaseBO.removeManualDecision(bookId, repoId);
        return "";
    } catch (Exception e) {
        String msg = "去除人工定量失败," + e.getMessage();
        logger.error(msg, e);
        return msg;
    }
}


public String addBlackList(Integer bookId,Integer repoId){
    try {
        purchaseBO.addBlackList(bookId, repoId);
        return "";
    } catch (Exception e) {
        String msg = "加入黑名单失败," + e.getMessage();
        logger.error(msg, e);
        return msg;
    }
}


public void deleteInwarehouse(Integer inwarehouseId){
    inwarehouseBO.deleteInwarehouse(inwarehouseId);
}


public Page<PurchaseDetail> findAllPurchaseDetail(Pageable page){
    return this.purchaseDetailDao.findAll(page);
}


public String recalculateRequireAmount(Integer bookId,Integer repoId){
    try {
        purchaseBO.recalculateRequireAmount(bookId, repoId);
        return "";
    } catch (Exception e) {
        String msg = "重新计算计划采购量失败," + e.getMessage();
        logger.error(msg, e);
        return msg;
    }
}


public List<TempImportDetailView> findTempImportDetail(Integer taskId,String tempImportDetailStatus){
    return tempImportBO.findTempImportDetail(taskId, tempImportDetailStatus);
}


public String updateTempImportTaskStatus(Integer taskId,Integer tempImportTaskStatus){
    try {
        tempImportBO.updateTempImportTaskStatus(taskId, tempImportTaskStatus);
        return null;
    } catch (Exception e) {
        logger.error("更新TempImportTask出错", e);
        return "更新出错，原因为" + e.getMessage();
    }
}


public void applyTerminateInwarehouse(Integer inwarehouseId){
    this.inwarehouseBO.terminateInwarehouse(inwarehouseId);
}


public String deleteBlackOrWhiteList(Integer bookId,Integer repoId){
    try {
        purchaseBO.deleteBlackOrWhiteList(bookId, repoId);
        return "";
    } catch (Exception e) {
        String msg = "删除黑名单或白名单失败," + e.getMessage();
        logger.error(msg, e);
        return msg;
    }
}


public Page<InwarehouseDetail> findAllInwarehouseDetail(Pageable page){
    return this.inwarehouseDetailDao.findAll(page);
}


}