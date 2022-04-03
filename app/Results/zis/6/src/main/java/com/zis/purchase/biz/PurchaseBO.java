package com.zis.purchase.biz;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoStatus;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.ConstantString;
import com.zis.common.cache.SysVarCache;
import com.zis.common.cache.SysVarConstant;
import com.zis.common.util.AlterEditionPurchaseStrategyEnum;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.PurchaseDetail;
import com.zis.purchase.bean.PurchaseDetailStatus;
import com.zis.purchase.bean.PurchasePlan;
import com.zis.purchase.bean.PurchasePlanFlag;
import com.zis.purchase.bean.PurchasePlanStatus;
import com.zis.purchase.calcMode.CalcModeFactory;
import com.zis.purchase.calcMode.CalculateModeInterface;
import com.zis.purchase.repository.PurchaseDetailDao;
import com.zis.purchase.repository.PurchasePlanDao;
import com.zis.Interface.SysVarCache;
import com.zis.Interface.BookService;
import com.zis.DTO.CalculateModeInterface;
@Component
public class PurchaseBO {

@Autowired
 private  SysVarCache sysVarCache;

@Autowired
 private  PurchaseDetailDao purchaseDetailDao;

@Autowired
 private  PurchasePlanDao purchasePlanDao;

@Autowired
 private  BookService bookService;

@Autowired
 private  ThreadPoolTaskExecutor taskExecutor;

 private  Logger logger;


public boolean isAllowManualDecisionForPurchasePlan(){
    return sysVarCache.getSystemVar(SysVarConstant.PURCHASE_STRATEGY_MANUAL_FIRST.getKeyName()) > 0;
}


public void addWhiteList(int bookId,Integer repoId){
    PurchasePlan plan = this.findPurchasePlanByBookId(bookId, repoId);
    if (plan == null) {
        throw new RuntimeException("采购计划不存在，bookId=" + bookId);
    }
    // 如果已经在黑名单里，直接返回
    if (isBookInWhiteList(plan)) {
        return;
    }
    // 设置为白名单记录，重新计算计划采购量(系统)
    Bookinfo bi = this.bookService.findBookById(bookId);
    if (bi == null) {
        throw new RuntimeException("图书信息不存在，bookId=" + bookId);
    }
    plan.setFlag(PurchasePlanFlag.WHITE);
    plan.setRequireAmount(calculateRequireAmount(bi, plan, repoId));
    plan.setGmtModify(ZisUtils.getTS());
    purchasePlanDao.save(plan);
    logger.info("add bookInfo to whiteList, bookId=" + bookId);
}


public Integer calculateStillRequireAmount(PurchasePlan plan,Integer stockAmount){
    Integer requireAmount = plan.getRequireAmount();
    boolean manualFirst = sysVarCache.getSystemVar(SysVarConstant.PURCHASE_STRATEGY_MANUAL_FIRST.getKeyName()) > 0;
    if (manualFirst && plan.getManualDecision() > 0) {
        requireAmount = plan.getManualDecision();
    }
    if (PurchasePlanFlag.BLACK.equals(plan.getFlag())) {
        requireAmount = 0;
    }
    // TODO 查询库存量，通过repoId 以及 skuId
    Integer still = requireAmount - stockAmount - plan.getPurchasedAmount();
    return still > 0 ? still : 0;
}


public String updateBookStock(Integer bookId,Integer amount,Integer repoId){
    PurchasePlan plan = this.findPurchasePlanByBookId(bookId, repoId);
    if (plan == null) {
        return "没有找可用的采购计划,bookId=" + bookId;
    }
    // TODO 新版本采购计划不允许采购计划中更新库存，库存更改只在出入库做操作
    // plan.setStockAmount(amount);
    plan.setGmtModify(ZisUtils.getTS());
    this.purchasePlanDao.save(plan);
    logger.info("update stock, bookId=" + bookId + ",stockBalance=" + amount);
    return StringUtils.EMPTY;
}


public void dealPurchasePlanForNormal(Bookinfo bi,Integer repoId){
    // 计算需求量
    PurchasePlan plan = this.findPurchasePlanByBookId(bi.getId(), repoId);
    Integer requireAmount = calculateRequireAmount(bi, plan, repoId);
    // 如果没有图书对应的采购计划，则新增
    if (plan == null) {
        plan = new PurchasePlan();
        BeanUtils.copyProperties(bi, plan);
        plan.setBookId(bi.getId());
        plan.setRequireAmount(requireAmount);
        plan.setManualDecision(0);
        // plan.setStockAmount(0);
        plan.setRepoId(repoId);
        plan.setPurchasedAmount(0);
        plan.setStatus(PurchasePlanStatus.NORMAL);
        plan.setGmtCreate(ZisUtils.getTS());
        plan.setGmtModify(ZisUtils.getTS());
        plan.setId(null);
        plan.setVersion(0);
        this.purchasePlanDao.save(plan);
        logger.info("add new purchasePlan, bookId=" + bi.getId());
    } else // 如果存在采购计划，则仅更新需求量
    {
        plan.setBookName(bi.getBookName());
        plan.setBookAuthor(bi.getBookAuthor());
        plan.setBookEdition(bi.getBookEdition());
        plan.setBookPublisher(bi.getBookPublisher());
        plan.setRequireAmount(requireAmount);
        // if (isBookInBlackList(bi.getId())) {
        // plan.setManualDecision(0); // 黑名单中的记录，人工定义需求量也设置为0(补偿处理之前的老数据)
        // }
        plan.setGmtModify(ZisUtils.getTS());
        purchasePlanDao.save(plan);
        logger.info("update exist purchasePlan, bookId=" + bi.getId());
    }
}


public int sumPurchaseAmount(int bookId){
    // DetachedCriteria detailDc = DetachedCriteria
    // .forClass(PurchaseDetail.class);
    // detailDc.add(Restrictions.eq("status",
    // PurchaseDetailStatus.PURCHASED));
    // detailDc.add(Restrictions.eq("bookId", bookId));
    // List<PurchaseDetail> details = this.purchaseDetailDao
    // .findByCriteria(detailDc);
    // if (details == null || details.isEmpty()) {
    // return 0;
    // }
    // int sum = 0;
    // for (PurchaseDetail detail : details) {
    // sum += (detail.getPurchasedAmount() - detail.getInwarehouseAmount());
    // }
    // return sum;
    // select sum(purchasedAmount - inwarehouseAmount) from PurchaseDetail
    // where status = '' and bookId =
    return this.purchaseDetailDao.sumPurchasedAmount(bookId);
}


public String addPurchaseDetail(int bookId,int purchasedAmount,String operator,String position,String memo,Integer repoId,Integer stockAmount){
    if (purchasedAmount <= 0) {
        throw new IllegalArgumentException("illegal purchasedAmount, for input:" + purchasedAmount);
    }
    // 查询采购计划
    PurchasePlan plan = this.findPurchasePlanByBookId(bookId, repoId);
    if (plan == null) {
        return "没有可用的采购计划，bookId=" + bookId;
    }
    // 判断本次采购量是否超出计划
    Integer count = calculateStillRequireAmount(plan, stockAmount);
    if (purchasedAmount > count) {
        return "已采购量超出需求量:" + (purchasedAmount - count);
    }
    // 更新采购计划
    plan.setPurchasedAmount(purchasedAmount + plan.getPurchasedAmount());
    plan.setGmtModify(ZisUtils.getTS());
    purchasePlanDao.save(plan);
    // 新增采购明细
    PurchaseDetail detail = buildPurchaseDetail(bookId, purchasedAmount, operator, position, memo);
    purchaseDetailDao.save(detail);
    logger.info("successfully add purchaseDetail, bookId=" + bookId + ",purchasedAmount=" + purchasedAmount);
    return StringUtils.EMPTY;
}


public void run(){
    if (bookList == null || bookList.isEmpty()) {
        return;
    }
    for (Bookinfo book : bookList) {
        try {
            addPurchasePlan(book, repoId);
        } catch (Exception e) {
            logger.error("采购计划初始化失败, bookId=" + book.getId(), e);
        }
    }
}


public Integer calculateRequireAmount(Bookinfo bi,PurchasePlan plan,Integer repoId){
    // 定性：参考黑名单、白名单、过期等多方面因素，判断图书是否需要采购
    if (StringUtils.isNotBlank(isUsefulBook(bi, plan))) {
        return 0;
    }
    // 计算采购计划量：根据系统预设的策略
    String keyName = SysVarConstant.PURCHASE_STRATEGY_AMT_CALCULATE.getKeyName();
    Integer strategy = sysVarCache.getSystemVar(keyName);
    CalculateModeInterface mode = CalcModeFactory.getInstance(strategy);
    if (mode == null) {
        throw new RuntimeException("没有此种类型的采购计划量计算策略，stragegy=" + strategy);
    }
    return mode.doCalculate(bi.getId(), repoId);
}


public String addManualDecision(Integer bookId,Integer amount,Integer repoId){
    if (bookId == null) {
        return "参数非法，bookId不能为空";
    }
    if (amount == null || amount <= 0) {
        return "参数非法，数量必须大于0";
    }
    // 检查系统是否打开人工定量开关
    if (!isAllowManualDecisionForPurchasePlan()) {
        return "不允许手动设置采购量";
    }
    // 检查图书是否有用
    PurchasePlan plan = this.findPurchasePlanByBookId(bookId, repoId);
    String uselessReason = isUsefulBook(this.bookService.findBookById(bookId), plan);
    if (StringUtils.isNotBlank(uselessReason)) {
        return uselessReason;
    }
    // 检查采购计划是否存在
    if (plan == null) {
        return "没有可用的采购计划,bookId=" + bookId;
    }
    // 设置需求量
    plan.setManualDecision(amount);
    plan.setGmtModify(ZisUtils.getTS());
    this.purchasePlanDao.save(plan);
    logger.info("update PurchasePlan by manual, bookId=" + bookId + ",manualDecision=" + amount);
    return StringUtils.EMPTY;
}


@Deprecated
public void batchUpdatePurchasePlanForPurchaseAmount(Integer repoId){
    List<PurchasePlan> list = purchasePlanDao.findForRecalcOnwayStock(repoId);
    for (PurchasePlan plan : list) {
        plan.setPurchasedAmount(sumPurchaseAmount(plan.getBookId()));
        plan.setGmtModify(ZisUtils.getTS());
        this.purchasePlanDao.save(plan);
    }
}


public String isUsefulBook(Bookinfo bi,PurchasePlan plan){
    if (bi == null) {
        return "参数图书为空";
    }
    // 状态不是“正式”，false
    if (!bi.getBookStatus().equals(ConstantString.USEFUL)) {
        return "图书" + bi.getId() + "状态不是“正式”";
    }
    // 黑名单，false
    if (isBookInBlackList(plan)) {
        return "图书" + bi.getId() + "已加入黑名单";
    }
    // 如果是最新版，true
    if (bi.getIsNewEdition()) {
        return StringUtils.EMPTY;
    }
    // 如果不是最新版，则根据过期策略来定
    Integer strategy = sysVarCache.getSystemVar(SysVarConstant.PURCHASE_STRATEGY_ALTER_EDITION_ALLOW.getKeyName());
    AlterEditionPurchaseStrategyEnum st = AlterEditionPurchaseStrategyEnum.getEnumByValue(strategy);
    switch(st) {
        case // 全要
        GET_ALL:
            return StringUtils.EMPTY;
        case // 全不要
        GET_NONE:
            return "系统不允许使用过期图书" + bi.getId();
        case // 只要白名单里的
        GET_WHITE_LIST:
            return isBookInWhiteList(plan) ? StringUtils.EMPTY : "系统不允许使用此图书" + bi.getId();
        default:
            return "系统错误";
    }
}


public void addPurchasePlanForBatch(List<Bookinfo> bookList,Integer repoId){
    Thread task = new Thread(new Runnable() {

        public void run() {
            if (bookList == null || bookList.isEmpty()) {
                return;
            }
            for (Bookinfo book : bookList) {
                try {
                    addPurchasePlan(book, repoId);
                } catch (Exception e) {
                    logger.error("采购计划初始化失败, bookId=" + book.getId(), e);
                }
            }
        }
    });
    // 添加任务，如果遇到新的任务被拒绝，则稍后重试
    while (true) {
        try {
            taskExecutor.execute(task);
            break;
        } catch (TaskRejectedException e) {
            logger.info("new task reject, wait a moment...");
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e1) {
            }
        }
    }
}


public void removeManualDecision(Integer bookId,Integer repoId){
    PurchasePlan plan = this.findPurchasePlanByBookId(bookId, repoId);
    if (plan == null) {
        throw new RuntimeException("采购计划不存在，bookId=" + bookId);
    }
    plan.setManualDecision(0);
    plan.setGmtModify(ZisUtils.getTS());
    this.purchasePlanDao.save(plan);
    logger.info("remove manual decision successfully, bookId=" + bookId);
}


public void addBlackList(int bookId,Integer repoId){
    PurchasePlan plan = this.findPurchasePlanByBookId(bookId, repoId);
    if (plan == null) {
        throw new RuntimeException("采购计划不存在，bookId=" + bookId);
    }
    // 如果已经在黑名单里，直接返回
    if (isBookInBlackList(plan)) {
        return;
    }
    // 设置为黑名单记录，清空计划采购量(系统和人工)
    plan.setFlag(PurchasePlanFlag.BLACK);
    plan.setRequireAmount(0);
    plan.setManualDecision(0);
    plan.setGmtModify(ZisUtils.getTS());
    purchasePlanDao.save(plan);
    logger.info("update purchasePlan, for clear requireAmount, bookId=" + bookId);
}


public void dealPurchasePlanForUseless(Bookinfo bi,Integer repoId){
    this.purchasePlanDao.updateToUselessByBookId(bi.getId(), repoId);
    logger.info("make purchasePLan useless, for bookId=" + bi.getId());
}


public void recalculateRequireAmount(Integer bookId,Integer repoId){
    Bookinfo bi = this.bookService.findBookById(bookId);
    if (bi == null) {
        throw new RuntimeException("图书信息不存在，bookId=" + bookId);
    }
    PurchasePlan plan = this.findPurchasePlanByBookId(bookId, repoId);
    if (plan == null) {
        throw new RuntimeException("采购计划不存在，bookId=" + bookId);
    }
    int planAmt = this.calculateRequireAmount(bi, plan, repoId);
    plan.setRequireAmount(planAmt);
    plan.setGmtModify(ZisUtils.getTS());
    this.purchasePlanDao.save(plan);
    logger.info("recalculate purchasePlan successfully, bookId=" + bookId);
}


public void deleteOnwayStock(String purchaseOperator,Integer repoId){
    if (StringUtils.isBlank(purchaseOperator)) {
        return;
    }
    List<PurchaseDetail> details = this.purchaseDetailDao.findByOperatorAndStatus(purchaseOperator, PurchaseDetailStatus.PURCHASED);
    if (details == null || details.isEmpty()) {
        return;
    }
    for (PurchaseDetail detail : details) {
        // 更新采购明细状态
        detail.setStatus(PurchaseDetailStatus.USELESS);
        detail.setGmtModify(ZisUtils.getTS());
        this.purchaseDetailDao.save(detail);
        // 更新采购计划表
        PurchasePlan plan = findPurchasePlanByBookId(detail.getBookId(), repoId);
        plan.setPurchasedAmount(plan.getPurchasedAmount() - (detail.getPurchasedAmount() - detail.getInwarehouseAmount()));
        plan.setGmtModify(ZisUtils.getTS());
        this.purchasePlanDao.save(plan);
    }
}


public PurchasePlan findPurchasePlanByBookId(int bookId,Integer repoId){
    return this.purchasePlanDao.findByBookId(bookId, repoId);
}


public void addPurchasePlan(Bookinfo bi,Integer repoId){
    if (bi == null) {
        return;
    }
    // 判断图书状态
    // 1. 待审核记录不录入采购计划
    if (BookinfoStatus.WAITCHECK.equals(bi.getBookStatus())) {
        return;
    } else // 2. 废弃记录不处理/或作废已存在的采购计划
    if (BookinfoStatus.DISCARD.equals(bi.getBookStatus())) {
        dealPurchasePlanForUseless(bi, repoId);
        return;
    } else // 3. 状态为“正式”的记录，新增/更新采购计划
    if (BookinfoStatus.NORMAL.equals(bi.getBookStatus())) {
        dealPurchasePlanForNormal(bi, repoId);
    } else {
        throw new RuntimeException("图书状态不合法，bookId=" + bi.getId() + "bookStatus=" + bi.getBookStatus());
    }
}


public boolean isBookInWhiteList(PurchasePlan plan){
    return plan != null && PurchasePlanFlag.WHITE.equals(plan.getFlag());
}


public void deleteBlackOrWhiteList(Integer bookId,Integer repoId){
    PurchasePlan plan = this.findPurchasePlanByBookId(bookId, repoId);
    if (plan == null) {
        throw new RuntimeException("采购计划不存在，bookId=" + bookId);
    }
    // 没有黑白名单标记的记录不处理
    if (PurchasePlanFlag.NORMAL.equals(plan.getFlag())) {
        return;
    }
    // 取消标记，重新计算计划采购量
    Bookinfo bi = this.bookService.findBookById(bookId);
    if (bi == null) {
        throw new RuntimeException("图书信息不存在，bookId=" + bookId);
    }
    plan.setFlag(PurchasePlanFlag.NORMAL);
    plan.setRequireAmount(calculateRequireAmount(bi, plan, repoId));
    plan.setGmtModify(ZisUtils.getTS());
    purchasePlanDao.save(plan);
    logger.info("cancel while or black flag for purchasePlan, bookId=" + bookId);
}


public boolean isBookInBlackList(PurchasePlan plan){
    return plan != null && PurchasePlanFlag.BLACK.equals(plan.getFlag());
}


public PurchaseDetail buildPurchaseDetail(int bookId,int purchasedAmount,String operator,String position,String memo){
    PurchaseDetail po = new PurchaseDetail();
    po.setBookId(bookId);
    po.setMemo(memo);
    po.setStatus(PurchaseDetailStatus.PURCHASED);
    po.setOperator(operator);
    po.setPosition(position);
    po.setPurchasedAmount(purchasedAmount);
    po.setInwarehouseAmount(0);
    po.setGmtCreate(ZisUtils.getTS());
    po.setGmtModify(ZisUtils.getTS());
    return po;
}


}