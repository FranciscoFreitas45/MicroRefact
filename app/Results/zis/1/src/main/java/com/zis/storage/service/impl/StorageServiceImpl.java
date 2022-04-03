package com.zis.storage.service.impl;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.zis.storage.dto.CreateOrderDTO;
import com.zis.storage.dto.CreateOrderDTO.CreateOrderDetail;
import com.zis.storage.dto.StockDTO;
import com.zis.storage.dto.StorageLacknessOpDTO;
import com.zis.storage.entity.StorageIoBatch;
import com.zis.storage.entity.StorageIoBatch.BizType;
import com.zis.storage.entity.StorageIoDetail;
import com.zis.storage.entity.StorageIoDetail.DetailStatus;
import com.zis.storage.entity.StorageIoDetail.IoType;
import com.zis.storage.entity.StorageOrder;
import com.zis.storage.entity.StorageOrder.OrderType;
import com.zis.storage.entity.StorageOrder.TradeStatus;
import com.zis.storage.entity.StoragePosStock;
import com.zis.storage.entity.StoragePosition;
import com.zis.storage.entity.StoragePosition.PosStatus;
import com.zis.storage.entity.StorageProduct;
import com.zis.storage.entity.StorageProductOccupy;
import com.zis.storage.entity.StorageRepoInfo;
import com.zis.storage.repository.StorageIoBatchDao;
import com.zis.storage.repository.StorageIoDetailDao;
import com.zis.storage.repository.StorageOrderDao;
import com.zis.storage.repository.StoragePosStockDao;
import com.zis.storage.repository.StoragePositionDao;
import com.zis.storage.repository.StorageProductDao;
import com.zis.storage.repository.StorageProductOccupyDao;
import com.zis.storage.repository.StorageRepoInfoDao;
import com.zis.storage.service.StorageService;
import com.zis.storage.util.StorageUtil;
import com.zis.DTO.CreateOrderDTO;
@Service
public class StorageServiceImpl implements StorageService{

@Autowired
 private  StorageIoBatchDao storageIoBatchDao;

@Autowired
 private  StorageIoDetailDao storageIoDetailDao;

@Autowired
 private  StorageOrderDao storageOrderDao;

@Autowired
 private  StoragePositionDao storagePositionDao;

@Autowired
 private  StoragePosStockDao storagePosStockDao;

@Autowired
 private  StorageProductDao storageProductDao;

@Autowired
 private  StorageProductOccupyDao storageProductOccupyDao;

@Autowired
 private  StorageRepoInfoDao storageRepoInfoDao;

 private  Logger logger;


@Override
@Transactional
public StorageOrder createOrder(CreateOrderDTO request){
    // param check
    checkForCreateOrder(request);
    // 创建StorageOrder, status=created
    // 修改StorageProduct，增加占用库存
    // 新增StorageProductOccupy，关联StorageOrder, StorageProduct
    List<CreateOrderDetail> detailList = request.getDetailList();
    Integer totalAmount = 0;
    for (CreateOrderDetail detail : detailList) {
        totalAmount += detail.getAmount();
    }
    Integer repoId = request.getRepoId();
    StorageOrder order = new StorageOrder();
    Date now = new Date();
    order.setOrderType(OrderType.SELF.getValue());
    // order.setOutTradeNo(request.getOutTradeNo());
    order.setOutOrderId(request.getOutOrderId());
    order.setBuyerName(request.getBuyerName());
    order.setAmount(totalAmount);
    order.setRepoId(request.getRepoId());
    order.setShopId(request.getShopId());
    order.setTradeStatus(TradeStatus.CREATED.getValue());
    String orderDetail = JSONObject.toJSONString(detailList);
    order.setOrderDetail(orderDetail);
    order.setGmtCreate(now);
    order.setGmtModify(now);
    this.storageOrderDao.save(order);
    logger.info("[创建订单] orderId={}, outOrderId={}, orderDetail={}", order.getOrderId(), order.getOutOrderId(), orderDetail);
    for (CreateOrderDetail detail : detailList) {
        StorageProduct prod = this.storageProductDao.findBySkuIdAndRepoId(detail.getSkuId(), repoId);
        if (prod == null) {
            throw new RuntimeException(String.format("库存商品不存在,skuId=%s,repoId=%s.", detail.getSkuId(), repoId));
        }
        if (prod.getStockAvailable() < detail.getAmount()) {
            throw new RuntimeException(String.format("库存不足,productId=%s,skuId=%s,订单量%s,可用量%s", prod.getProductId(), detail.getSkuId(), detail.getAmount(), prod.getStockAvailable()));
        }
        doOccupy(prod, order.getOrderId(), detail.getAmount());
    }
    return order;
}


@Override
public StorageProduct findBySkuIdAndRepoId(Integer skuId,Integer repoId){
    if (repoId == null) {
        throw new IllegalArgumentException("repoId不能为空");
    }
    if (skuId == null) {
        throw new IllegalArgumentException("skuId不能为空");
    }
    return this.storageProductDao.findBySkuIdAndRepoId(skuId, repoId);
}


@Override
@Transactional
public void confirmInStorage(Integer batchId,Integer operator){
    if (batchId == null) {
        throw new IllegalArgumentException("batchId不能为空");
    }
    if (operator == null) {
        throw new IllegalArgumentException("operator不能为空");
    }
    // 批次存在，且必须是入库类型，CREATED状态
    StorageIoBatch batch = this.storageIoBatchDao.findOne(batchId);
    checkStorageIoBatchForInStorage(batchId, batch);
    List<StorageIoDetail> list = this.storageIoDetailDao.findByBatchId(batchId);
    if (list == null || list.isEmpty()) {
        throw new RuntimeException("该批次没有记录，无法进行确认入库操作，batchId=" + batchId);
    }
    // 针对每一个入库明细：更新状态为已完成，增加库位库存，增加总库存
    for (StorageIoDetail detail : list) {
        inStorageForOne(detail);
    }
    // 更新入库批次，st=已完成
    batch.setStatus(StorageIoBatch.Status.FINISH.getValue());
    batch.setGmtModify(new Date());
    this.storageIoBatchDao.save(batch);
    logger.info("[确认批量入库] batchId={}, operator={}", batchId, operator);
}


@Override
public List<StorageIoDetail> findStorageIoDetailByRepoIdAndBatchIdInAndIoDetailTypeAndDetailStatusIn(Integer repoId,List<Integer> batchIds,String ioDetailType,List<String> DetailStatusList){
    return this.storageIoDetailDao.findByRepoIdAndIoDetailTypeAndDetailStatusInAndBatchIdIn(repoId, ioDetailType, DetailStatusList, batchIds);
}


public void doOccupy(StorageProduct prod,Integer orderId,Integer amount){
    Date now = new Date();
    prod.setStockOccupy(prod.getStockOccupy() + amount);
    prod.setStockAvailable(prod.getStockAvailable() - amount);
    prod.setGmtModify(now);
    this.storageProductDao.save(prod);
    StorageProductOccupy occupy = new StorageProductOccupy();
    occupy.setOrigAmt(amount);
    occupy.setCurAmt(amount);
    occupy.setOrderId(orderId);
    occupy.setProductId(prod.getProductId());
    occupy.setStatus(StorageProductOccupy.Status.OCCUPY.getValue());
    occupy.setCreateTime(now);
    occupy.setUpdateTime(now);
    this.storageProductOccupyDao.save(occupy);
    logger.info("[商品库存占用] productId={}, orderId={}, amount={}", prod.getProductId(), orderId, amount);
}


@Override
@Transactional
public StorageIoDetail pickupDoneAndLockNext(Integer ioDetailId,Integer operator){
    checkForPickup(ioDetailId, operator);
    // 更新出库明细状态，status:processing->success
    StorageIoDetail current = findAndCheckDetailForPickupOrLackness(ioDetailId, operator);
    // 结束库存占用，减少库位库存、商品库存和占用量、更新出库明细
    finishOccupy(current, DetailStatus.SUCCESS);
    // 返回下一条记录，status:waiting->processing（防止多个操作员同时操作过程中看到同一个记录）
    return doPickupLock(current.getBatchId(), operator);
}


@Override
@Transactional
public StorageLacknessOpDTO lackPart(Integer ioDetailId,Integer operator,Integer actualAmt){
    logger.info("[部分缺货] ioDetailId={}, operator={}, 实际发货量={}", ioDetailId, operator, actualAmt);
    checkForPickup(ioDetailId, operator);
    if (actualAmt == null || actualAmt < 0) {
        throw new IllegalArgumentException("actualAmt不合法，actualAmt=" + actualAmt);
    }
    logger.info("[部分缺货] 作废原有出入库明细, ioDetailId={}", ioDetailId);
    // 根据actualAmt，把detail拆分成有货和缺货两条记录
    StorageIoDetail detail = findAndCheckDetailForPickupOrLackness(ioDetailId, operator);
    if (detail.getAmount() <= actualAmt) {
        throw new IllegalArgumentException("实际配货量错误，actualAmt必须小于detail.amount");
    }
    updateDetailStatusAndBalance(detail, 0, DetailStatus.USELESS);
    // 对于有货部分，更新库存量，结束占用，更新出库明细
    Date now = new Date();
    StorageIoDetail actualSend = new StorageIoDetail();
    BeanUtils.copyProperties(detail, actualSend);
    actualSend.setDetailId(null);
    actualSend.setAmount(actualAmt);
    actualSend.setGmtCreate(now);
    actualSend.setGmtModify(now);
    // actualSend.setDetailStatus(DetailStatus.PROCESSING.getValue());
    actualSend = this.storageIoDetailDao.save(actualSend);
    logger.info("[部分缺货] 出库部分, ioDetailId={}, orderId={}, 实际发货量={}", actualSend.getDetailId(), actualSend.getOrderId(), actualAmt);
    finishOccupy(actualSend, DetailStatus.SUCCESS);
    // 对于缺货部分，执行全部缺货逻辑，并返回下一条待取件的记录
    Integer lackness = detail.getAmount() - actualAmt;
    StorageIoDetail lackDetail = new StorageIoDetail();
    BeanUtils.copyProperties(detail, lackDetail);
    lackDetail.setDetailId(null);
    lackDetail.setAmount(lackness);
    lackDetail.setGmtModify(new Date());
    lackDetail.setDetailStatus(DetailStatus.PROCESSING.getValue());
    lackDetail.setGmtCreate(now);
    lackDetail.setGmtModify(now);
    lackDetail = this.storageIoDetailDao.save(lackDetail);
    logger.info("[部分缺货] 缺货部分, ioDetailId={}, orderId={}, 实际缺货量={}", actualSend.getDetailId(), actualSend.getOrderId(), lackness);
    return lackAll(lackDetail.getDetailId(), operator);
}


@Override
@Transactional
public void cancelOrder(Integer repoId,Integer outOrderId){
    logger.info("[取消订单] repoId={}, outOrderId={}", repoId, outOrderId);
    StorageOrder order = this.storageOrderDao.findByOutOrderId(outOrderId);
    Assert.notNull(order, "订单不存在outOrderId=" + outOrderId);
    cancelOrder(order.getOrderId(), order);
}


@Override
@Transactional
public StoragePosition createStoragePosition(Integer repoId,String posLabel){
    if (repoId == null) {
        throw new IllegalArgumentException("repoId不能为空");
    }
    if (StringUtils.isBlank(posLabel)) {
        throw new IllegalArgumentException("posLabel不能为空");
    }
    StoragePosition pos = new StoragePosition();
    pos.setLabel(posLabel);
    pos.setRepoId(repoId);
    pos.setPosStatus(PosStatus.AVAILABLE.getValue());
    this.storagePositionDao.save(pos);
    logger.info("[新增库位] repoId={}, posLabel={}", repoId, posLabel);
    return pos;
}


public StorageIoDetail findAndCheckDetailForPickupOrLackness(Integer ioDetailId,Integer operator){
    StorageIoDetail detail = this.storageIoDetailDao.findOne(ioDetailId);
    if (!DetailStatus.PROCESSING.getValue().equals(detail.getDetailStatus())) {
        throw new RuntimeException("不能执行取件，detailStatus=" + detail.getDetailStatus());
    }
    if (!detail.getOperator().equals(operator)) {
        throw new RuntimeException("不能操作别人锁定的记录");
    }
    return detail;
}


public boolean haveAvailableAmount(Integer productId,Integer amount){
    List<StockDTO> stocks = this.storagePosStockDao.findAvailableStock(productId);
    int availableAmt = 0;
    for (StockDTO stock : stocks) {
        availableAmt += (stock.getTotalAmt() - stock.getOccupyAmt());
    }
    return availableAmt >= amount;
}


@Transactional
@Override
public void directSend(Integer repoId,Integer skuId,Integer amount,String posLabel,Integer operator){
    // 自动关联当日批次
    StorageIoBatch batch = this.storageIoBatchDao.findDailyBatchByBizTypeAndRepoId(BizType.OUT_DAILY.getValue(), repoId);
    if (batch == null) {
        batch = saveStorageIoBatch(repoId, null, BizType.OUT_DAILY, "当日自动批次");
    }
    // 减少商品库存
    Date now = new Date();
    StorageProduct prod = this.storageProductDao.findBySkuIdAndRepoId(skuId, repoId);
    if (prod == null) {
        throw new RuntimeException(String.format("prod不存在, skuId=%s, repoId=%s", skuId, repoId));
    }
    prod.setStockAmt(prod.getStockAmt() - amount);
    prod.setStockAvailable(prod.getStockAvailable() - amount);
    prod.setGmtModify(now);
    this.storageProductDao.save(prod);
    logger.info("[商品库存变更] productId={0}, 总库存-{1}, 可用库存-{1}", prod.getProductId(), amount);
    // 更新库位库存:直接减少总量。
    StoragePosStock stock = this.storagePosStockDao.findByLabelAndProductId(repoId, posLabel, prod.getProductId());
    if (stock == null) {
        throw new RuntimeException(String.format("posStock不存在, repoId=%s, label=%s, productId=%s", repoId, posLabel, prod.getProductId()));
    }
    Integer available = stock.getTotalAmt() - stock.getOccupyAmt();
    if (available < amount) {
        throw new RuntimeException(String.format("库位库存不足，期望%s, 实际%s", amount, available));
    }
    stock.setTotalAmt(stock.getTotalAmt() - amount);
    stock.setGmtModify(new Date());
    logger.info("[库位库存变更] stockId={0}, 总库存-{1}", stock.getStockId(), amount);
    this.storagePosStockDao.save(stock);
    // 生成出库明细
    StorageIoDetail detail = saveStorageIoDetail(batch, stock.getPosId(), posLabel, prod.getProductId(), skuId, amount, operator, null);
    updateDetailStatusAndBalance(detail, stock.getTotalAmt(), DetailStatus.SUCCESS);
    // 更新批次数量
    batch.setAmount(batch.getAmount() + amount);
    batch.setGmtModify(new Date());
    this.storageIoBatchDao.save(batch);
}


public void checkStorageIoBatchForInStorage(Integer batchId,StorageIoBatch batch){
    if (batch == null) {
        throw new IllegalArgumentException("入库批次不存在，batchId=" + batchId);
    }
    if (!BizType.isInStorage(batch.getBizType())) {
        throw new RuntimeException("入库批次类型错误, bizType=" + batch.getBizType());
    }
    if (!StorageIoBatch.Status.CREATED.getValue().equals(batch.getStatus())) {
        throw new RuntimeException("入库批次状态错误, status=" + batch.getStatus());
    }
}


@Override
public List<StorageProduct> findByPosStockList(List<StoragePosStock> posStockList){
    List<Integer> productIds = new ArrayList<Integer>();
    for (StoragePosStock s : posStockList) {
        productIds.add(s.getProductId());
    }
    List<StorageProduct> pList = this.storageProductDao.findByRepoIdAndProductIdInOrderBySkuIdAsc(StorageUtil.getRepoId(), productIds);
    return pList;
}


@Override
public StoragePosition findByLabelAndRepoId(String label,Integer repoId){
    return this.storagePositionDao.findByLabelAndRepoId(label, repoId);
}


@Override
@Transactional
public StorageLacknessOpDTO lackAll(Integer ioDetailId,Integer operator){
    logger.info("[全部缺货] ioDetailId={}, operator={}", ioDetailId, operator);
    checkForPickup(ioDetailId, operator);
    // 更新出库明细为缺货
    StorageIoDetail detail = findAndCheckDetailForPickupOrLackness(ioDetailId, operator);
    // 结束占用，更新商品库存（减少总量、可用量，占用量不变），更新库位库存（减少总量、占用量），更新出库明细
    finishOccupy(detail, DetailStatus.LACKNESS);
    // 重新生成等待取件的记录，并关联到当前批次中
    StorageIoBatch batch = this.storageIoBatchDao.findOne(detail.getBatchId());
    boolean available = haveAvailableAmount(detail.getProductId(), detail.getAmount());
    Integer lackOutOrderId = null;
    if (available) {
        arrangeOrder(operator, batch, detail.getProductId(), detail.getAmount(), detail.getOrderId());
    } else {
        // 没有足够库存，标记为缺货
        StorageOrder order = storageOrderDao.findOne(detail.getOrderId());
        order.setTradeStatus(TradeStatus.LACKNESS.getValue());
        order.setGmtModify(new Date());
        this.storageOrderDao.save(order);
        lackOutOrderId = order.getOutOrderId();
        logger.info("[缺货]没有足够的库存，productId={}, amount={}, orderId={}", detail.getProductId(), detail.getAmount(), detail.getOrderId());
    }
    // 返回下一条等待取件的记录
    StorageIoDetail rs = doPickupLock(batch.getBatchId(), operator);
    StorageLacknessOpDTO lackDTO = new StorageLacknessOpDTO();
    if (rs != null) {
        BeanUtils.copyProperties(rs, lackDTO);
        lackDTO.setHasNext(true);
    }
    lackDTO.setLackOutOrderId(lackOutOrderId);
    lackDTO.setLacknessMatchNewPos(available);
    return lackDTO;
}


@Override
public Page<StorageIoDetail> findStorageIoDetailByProductIdAndPosId(Integer productId,Integer posId,Pageable page){
    if (productId == null) {
        throw new IllegalArgumentException("productId不能为空");
    }
    if (page == null) {
        throw new IllegalArgumentException("page不能为空");
    }
    // 设置排序方式：按照库位、创建时间降序
    Pageable searchPage = new PageRequest(page.getPageNumber(), page.getPageSize(), Direction.ASC, StorageIoDetail.SORT_POS_ID, StorageIoDetail.SORT_CREATE_TIME);
    List<String> sts = new ArrayList<String>();
    sts.add(DetailStatus.SUCCESS.getValue());
    sts.add(DetailStatus.LACKNESS.getValue());
    if (posId == null) {
        return storageIoDetailDao.findByProductIdAndDetailStatusIn(productId, sts, searchPage);
    } else {
        return storageIoDetailDao.findByProductIdAndPosIdAndDetailStatusIn(productId, posId, sts, searchPage);
    }
}


public void arrangeOrder(Integer operator,StorageIoBatch batch,Integer productId,Integer amount,Integer orderId){
    List<StockDTO> stocks = this.storagePosStockDao.findAvailableStock(productId);
    int amountNotDivide = amount;
    for (StockDTO stock : stocks) {
        // 当前库位可用数量
        int amountAvailable = stock.getTotalAmt() - stock.getOccupyAmt();
        // 本次分配数量
        int amountDivide = Math.min(amountAvailable, amountNotDivide);
        // 生成出库明细
        StorageIoDetail detail = saveStorageIoDetail(batch, stock.getPosId(), stock.getPosLabel(), productId, stock.getSkuId(), amountDivide, operator, orderId);
        // 更新库位库存
        // 1)批量出库操作，则增加占用量。如果取件成功/缺货，则减少总量和占用量。
        // 2)直接出库操作，则直接减少总量。
        String message = null;
        StoragePosStock posStock = this.storagePosStockDao.findOne(stock.getStockId());
        if (BizType.OUT_BATCH.getValue().equals(batch.getBizType())) {
            posStock.setOccupyAmt(posStock.getOccupyAmt() + amountDivide);
            message = "批量出库，增加占用量" + amountDivide;
        } else if (BizType.OUT_DAILY.getValue().equals(batch.getBizType())) {
            posStock.setTotalAmt(posStock.getTotalAmt() - amountDivide);
            updateDetailStatusAndBalance(detail, posStock.getTotalAmt(), DetailStatus.SUCCESS);
            message = "日常出库，减少可用量" + amountDivide;
        } else {
            throw new RuntimeException("分配库位失败，业务类型bizType=" + batch.getBizType());
        }
        this.storagePosStockDao.save(posStock);
        logger.info("[更新库位库存] stockId={}, {}", stock.getStockId(), message);
        // 剩余未分配数量
        amountNotDivide -= amountDivide;
        if (amountNotDivide == 0) {
            break;
        }
        if (amountNotDivide < 0) {
            throw new RuntimeException("分配库位错误，未分配数量小于0");
        }
    }
    if (amountNotDivide > 0) {
        throw new RuntimeException("分配库位错误，库存量不足");
    }
}


public void finishOccupy(StorageIoDetail detail,DetailStatus detailStatus){
    // 减少商品库存和占用
    Date now = new Date();
    final Integer productId = detail.getProductId();
    final Integer amount = detail.getAmount();
    StorageProduct prod = this.storageProductDao.findOne(productId);
    if (prod == null) {
        throw new RuntimeException("prod不存在, productId=" + productId);
    }
    prod.setStockAmt(prod.getStockAmt() - amount);
    // 如果是缺货，库存占用依然需要保持，需要减少可用库存（在下单阶段扣减的库存基础上额外扣减）
    if (detailStatus.equals(DetailStatus.LACKNESS)) {
        prod.setStockAvailable(prod.getStockAmt() - amount);
        logger.info("[商品缺货] 减少总库存、可用库存，保留占用库存，productId={}, detailId={}, amount={}", productId, detail.getDetailId(), amount);
    } else // 如果是出库，只需减少占用库存，可用库存在下单阶段已扣减
    if (detailStatus.equals(DetailStatus.SUCCESS)) {
        final Integer orderId = detail.getOrderId();
        prod.setStockOccupy(prod.getStockOccupy() - amount);
        // FIXME　如果同样skuId下了2次这边就会报错
        StorageProductOccupy occupy = this.storageProductOccupyDao.findByOrderIdAndProductId(orderId, productId);
        if (occupy == null) {
            throw new RuntimeException(String.format("occupy不存在, orderId=%s, productId=%s", orderId, productId));
        }
        occupy.setCurAmt(occupy.getCurAmt() - amount);
        if (occupy.getCurAmt() == 0) {
            occupy.setStatus(StorageProductOccupy.Status.SENT.getValue());
        }
        occupy.setUpdateTime(now);
        this.storageProductOccupyDao.save(occupy);
        logger.info("[商品出库] 减少总库存、占用库存，productId={}, detailId={}, amount={}", productId, detail.getDetailId(), amount);
    } else // 其他状态，异常
    {
        throw new RuntimeException("结束库存占用时，detailStatus必须是SUCCESS或LACKNESS，detailStatus=" + detailStatus.getValue());
    }
    prod.setGmtModify(now);
    this.storageProductDao.save(prod);
    // 减少库位库存和占用
    StoragePosStock stock = this.storagePosStockDao.findByPosIdAndProductId(detail.getPosId(), productId);
    if (stock == null) {
        throw new RuntimeException(String.format("stock不存在, posId=%s, productId=%s", detail.getPosId(), productId));
    }
    stock.setOccupyAmt(stock.getOccupyAmt() - amount);
    stock.setTotalAmt(stock.getTotalAmt() - amount);
    stock.setGmtModify(now);
    this.storagePosStockDao.save(stock);
    logger.info("[减少库位库存] 减少总库存和占用库存，stockId={}, amount={}", stock.getStockId(), amount);
    // 更新出库明细为SUCCESS，更新库位库存余量
    updateDetailStatusAndBalance(detail, stock.getTotalAmt(), detailStatus);
}


public void inStorageForOne(StorageIoDetail detail){
    // 增加总库存（如果没有，则报错）
    StorageProduct prod = this.storageProductDao.findOne(detail.getProductId());
    if (prod == null) {
        throw new RuntimeException("商品不存在, productId=" + detail.getProductId());
    }
    prod.setStockAmt(prod.getStockAmt() + detail.getAmount());
    prod.setStockAvailable(prod.getStockAvailable() + detail.getAmount());
    this.storageProductDao.save(prod);
    // 增加库位库存（如果没有，则新增）
    StoragePosStock posStock = this.storagePosStockDao.findByPosIdAndProductId(detail.getPosId(), prod.getProductId());
    if (posStock == null) {
        posStock = buildNewStoragePosStock(detail.getPosId(), prod.getProductId());
    }
    posStock.setTotalAmt(posStock.getTotalAmt() + detail.getAmount());
    this.storagePosStockDao.save(posStock);
    // 更新入库状态为success，记录剩余库位库存量
    updateDetailStatusAndBalance(detail, posStock.getTotalAmt(), DetailStatus.SUCCESS);
}


@Override
public Page<StorageIoDetail> findStorageIoDetailByProductId(Integer productId,Pageable page){
    return findStorageIoDetailByProductIdAndPosId(productId, null, page);
}


public void undoOccupyPosStock(Integer orderId){
    Date now = new Date();
    List<StorageIoDetail> list = this.storageIoDetailDao.findByOrderId(orderId);
    for (StorageIoDetail detail : list) {
        detail.setDetailStatus(DetailStatus.CANCEL.getValue());
        detail.setGmtModify(now);
        this.storageIoDetailDao.save(detail);
        StoragePosStock stock = this.storagePosStockDao.findByPosIdAndProductId(detail.getPosId(), detail.getProductId());
        if (stock == null) {
            throw new RuntimeException(String.format("StoragePosStock不存在, posId=%s,productId=%s", detail.getPosId(), detail.getProductId()));
        }
        stock.setOccupyAmt(stock.getOccupyAmt() - detail.getAmount());
        stock.setGmtModify(now);
        this.storagePosStockDao.save(stock);
        logger.info("[取消库位库存占用] orderId={}, prodId={}, skuId={}, amount={}", orderId, detail.getProductId(), detail.getSkuId(), detail.getAmount());
    }
}


@Override
public StorageIoDetail findByIoDetailId(Integer ioDetailId){
    return storageIoDetailDao.findOne(ioDetailId);
}


@Override
public List<StockDTO> findAllStockByProductId(Integer productId){
    if (productId == null) {
        throw new IllegalArgumentException("productId不能为空");
    }
    return storagePosStockDao.findAllStock(productId);
}


@Override
@Transactional
public StorageIoDetail addInStorageDetail(Integer batchId,Integer skuId,Integer amount,String posLabel,Integer operator){
    if (batchId == null) {
        throw new IllegalArgumentException("batchId不能为空");
    }
    if (skuId == null) {
        throw new IllegalArgumentException("skuId不能为空");
    }
    if (amount == null || amount <= 0) {
        throw new IllegalArgumentException("入库数量错误，amount=" + amount);
    }
    if (StringUtils.isBlank(posLabel)) {
        throw new IllegalArgumentException("库位标签posLabel不能为空");
    }
    if (operator == null) {
        throw new IllegalArgumentException("operator不能为空");
    }
    // 批次存在，且必须是入库类型，CREATED状态
    StorageIoBatch batch = this.storageIoBatchDao.findOne(batchId);
    checkStorageIoBatchForInStorage(batchId, batch);
    batch.setAmount(batch.getAmount() + amount);
    this.storageIoBatchDao.save(batch);
    // 查找商品表（如果没有，则新增）
    StorageProduct prod = this.storageProductDao.findBySkuIdAndRepoId(skuId, batch.getRepoId());
    if (prod == null) {
        prod = buildNewStorageProduct(skuId, batch.getRepoId());
        this.storageProductDao.save(prod);
        logger.info("[新增库存商品] skuId={}, repoId={}", skuId, batch.getRepoId());
    }
    // 库位必须存在且可用
    StoragePosition pos = this.storagePositionDao.findByLabelAndRepoId(posLabel, batch.getRepoId());
    if (pos == null) {
        throw new IllegalArgumentException(String.format("库位不存在，repoId=%s, posLabel=%s", batch.getRepoId(), posLabel));
    }
    if (!StoragePosition.PosStatus.AVAILABLE.getValue().equals(pos.getPosStatus())) {
        throw new RuntimeException(String.format("库位状态不允许入库操作, posId=%s, status=%s", pos.getPosId(), pos.getPosStatus()));
    }
    return saveStorageIoDetail(batch, pos, prod.getProductId(), skuId, amount, operator, null);
}


@Override
public StorageProduct findStorageProductBySkuIdAndRepoId(Integer skuId,Integer repoId){
    if (repoId == null) {
        throw new IllegalArgumentException("repoId不能为空");
    }
    if (skuId == null) {
        throw new IllegalArgumentException("skuId不能为空");
    }
    return storageProductDao.findBySkuIdAndRepoId(skuId, repoId);
}


@Override
@Transactional
public StorageIoBatch createInStorage(Integer repoId,String memo,Integer operator){
    if (repoId == null) {
        throw new IllegalArgumentException("repoId不能为空");
    }
    if (operator == null) {
        throw new IllegalArgumentException("operator不能为空");
    }
    return saveStorageIoBatch(repoId, operator, BizType.IN_BATCH, memo);
}


public StoragePosStock buildNewStoragePosStock(Integer posId,Integer productId){
    StoragePosStock stock = new StoragePosStock();
    stock.setProductId(productId);
    stock.setPosId(posId);
    stock.setTotalAmt(0);
    stock.setOccupyAmt(0);
    Date now = new Date();
    stock.setGmtCreate(now);
    stock.setGmtModify(now);
    return stock;
}


public void undoOccupy(Integer orderId){
    Date now = new Date();
    List<StorageProductOccupy> occupys = this.storageProductOccupyDao.findByOrderId(orderId);
    for (StorageProductOccupy occupy : occupys) {
        occupy.setStatus(StorageProductOccupy.Status.CANCEL.getValue());
        occupy.setUpdateTime(now);
        this.storageProductOccupyDao.save(occupy);
        StorageProduct prod = this.storageProductDao.findOne(occupy.getProductId());
        prod.setStockOccupy(prod.getStockOccupy() - occupy.getCurAmt());
        prod.setStockAvailable(prod.getStockAvailable() + occupy.getCurAmt());
        prod.setGmtModify(now);
        this.storageProductDao.save(prod);
        logger.info("[取消库存占用] productId={}, orderId={}", prod.getProductId(), orderId);
    }
}


@Override
public void updatePosition(Integer posId,String label,Integer repoId){
    StoragePosition s = this.storagePositionDao.findByPosIdAndRepoId(posId, repoId);
    if (s == null) {
        throw new RuntimeException("请勿非法操作");
    }
    s.setLabel(label);
    s.setGmtModify(new Date());
    this.storagePositionDao.save(s);
}


public StorageIoDetail doPickupLock(Integer batchId,Integer operator){
    // 查找意外退出、关机丢失的数据（锁定但未取货）
    StorageIoDetail lost = this.storageIoDetailDao.findProcessingRecordByBatchIdAndOperator(batchId, operator);
    if (lost != null) {
        logger.info("[返回已锁定记录] ioDetailId={}, batchId={}, operator={}", lost.getDetailId(), batchId, operator);
        return lost;
    }
    // 返回下一条记录
    StorageIoDetail next = this.storageIoDetailDao.findNextRecordForPickup(batchId);
    if (next == null) {
        logger.info("[本批次已配完] batchId={}, operator={}", batchId, operator);
        return null;
    }
    // status:waiting->processing（防止多个操作员同时操作过程中看到同一个记录）
    next.setDetailStatus(DetailStatus.PROCESSING.getValue());
    next.setOperator(operator);
    this.storageIoDetailDao.save(next);
    logger.info("[锁定配货记录] ioDetailId={}, batchId={}, operator={}", next.getDetailId(), batchId, operator);
    return next;
}


public StorageProduct buildNewStorageProduct(Integer skuId,Integer repoId){
    // TODO lvbin 商品信息，需要查询bookService
    StorageProduct prod = new StorageProduct();
    prod.setLockFlag(false);
    prod.setRepoId(repoId);
    prod.setSkuId(skuId);
    prod.setSkuName("skuName");
    prod.setSubjectId(skuId);
    prod.setSubjectName("subjectName");
    prod.setStockAmt(0);
    prod.setStockAvailable(0);
    prod.setStockOccupy(0);
    Date now = new Date();
    prod.setGmtCreate(now);
    prod.setGmtModify(now);
    return prod;
}


public void checkForCreateOrder(CreateOrderDTO request){
    if (request == null) {
        throw new IllegalArgumentException("request不能为空");
    }
    if (request.getOutOrderId() == null) {
        throw new IllegalArgumentException("OutOrderId不能为空");
    }
    if (StringUtils.isBlank(request.getBuyerName())) {
        throw new IllegalArgumentException("buyerName不能为空");
    }
    if (request.getRepoId() == null) {
        throw new IllegalArgumentException("RepoId不能为空");
    }
    if (request.getShopId() == null) {
        throw new IllegalArgumentException("ShopId不能为空");
    }
    if (request.getOrderType() == null) {
        throw new IllegalArgumentException("OrderType不能为空");
    }
}


@Override
public List<StorageProduct> findStorageProductBySkuIdsAndRepoId(List<Integer> skuIds,Integer repoId){
    if (repoId == null) {
        throw new IllegalArgumentException("repoId不能为空");
    }
    if (CollectionUtils.isEmpty(skuIds)) {
        throw new IllegalArgumentException("skuIds不能为空");
    }
    return storageProductDao.findBySkuIdsAndRepoId(skuIds, repoId);
}


@Override
public StoragePosition findByPosIdAndRepoId(Integer posId,Integer repoId){
    return this.storagePositionDao.findByPosIdAndRepoId(posId, repoId);
}


@Override
public List<StorageProduct> findByUpdateTimeBetweenStartTimeAndEndTimeAndRepoId(Date startTime,Date endTime,Integer repoId){
    return this.storageProductDao.findByUpdateTimeBetweenStartTimeAndEndTimeAndRepoId(startTime, endTime, repoId);
}


public void checkForPickup(Integer ioDetailId,Integer operator){
    if (ioDetailId == null) {
        throw new IllegalArgumentException("ioDetailId不能为空");
    }
    if (operator == null) {
        throw new IllegalArgumentException("operator不能为空");
    }
}


@Override
@Transactional
public void directInStorage(Integer repoId,Integer skuId,Integer amount,String posLabel,Integer operator){
    if (repoId == null) {
        throw new IllegalArgumentException("repoId不能为空");
    }
    // 自动关联当日批次
    StorageIoBatch batch = this.storageIoBatchDao.findDailyBatchByBizTypeAndRepoId(BizType.IN_DAILY.getValue(), repoId);
    if (batch == null) {
        batch = saveStorageIoBatch(repoId, null, BizType.IN_DAILY, "当日自动批次");
    }
    logger.info("[直接入库] 自动关联当日批次 batchId={}, skuId={}, amount={}, posLabel={}", batch.getBatchId(), skuId, amount, posLabel);
    // 增加入库明细
    StorageIoDetail detail = addInStorageDetail(batch.getBatchId(), skuId, amount, posLabel, operator);
    // 增加商品库存、库位库存
    inStorageForOne(detail);
}


@Override
@Transactional
public StorageIoDetail pickupLock(Integer batchId,Integer operator){
    if (batchId == null) {
        throw new IllegalArgumentException("batchId不能为空");
    }
    if (operator == null) {
        throw new IllegalArgumentException("operator不能为空");
    }
    return doPickupLock(batchId, operator);
}


public StorageIoDetail saveStorageIoDetail(StorageIoBatch batch,StoragePosition pos,Integer productId,Integer skuId,Integer amount,Integer operator,Integer orderId){
    StorageIoDetail detail = new StorageIoDetail();
    detail.setSkuId(skuId);
    detail.setAmount(amount);
    detail.setProductId(productId);
    detail.setBatchId(batch.getBatchId());
    detail.setDetailStatus(DetailStatus.WAITING.getValue());
    if (BizType.isInStorage(batch.getBizType())) {
        detail.setIoDetailType(IoType.IN.getValue());
    } else {
        detail.setIoDetailType(IoType.OUT.getValue());
    }
    detail.setOperator(operator);
    detail.setPosLabel(pos.getLabel());
    detail.setPosId(pos.getPosId());
    detail.setRepoId(batch.getRepoId());
    detail.setOrderId(orderId);
    Date now = new Date();
    detail.setGmtCreate(now);
    detail.setGmtModify(now);
    this.storageIoDetailDao.save(detail);
    logger.info("[保存出入库明细] detailId={}, skuId={}, amount={}, productId={}, bizType={}, operator={}", detail.getDetailId(), skuId, amount, productId, batch.getBizType(), operator);
    return detail;
}


@Override
public Page<StoragePosStock> findByPosId(Integer posId,Pageable page){
    return this.storagePosStockDao.findByPosId(posId, page);
}


@Override
public List<StorageRepoInfo> findStorageRepoInfoByCompanyId(Integer companyId){
    return this.storageRepoInfoDao.findByOwnerIdOrderByGmtCreateAsc(companyId);
}


@Override
@Transactional
public List<Integer> finishBatchSend(Integer batchId){
    if (batchId == null) {
        throw new IllegalArgumentException("batchId不能为空");
    }
    StorageIoBatch batch = this.storageIoBatchDao.findOne(batchId);
    if (batch == null) {
        throw new RuntimeException("batch不存在, batchId=" + batchId);
    }
    if (!BizType.OUT_BATCH.getValue().equals(batch.getBizType())) {
        throw new RuntimeException("批次类型必须是out_batch");
    }
    if (!StorageIoBatch.Status.CREATED.getValue().equals(batch.getStatus())) {
        throw new RuntimeException("批次状态必须是created");
    }
    List<StorageIoDetail> list = this.storageIoDetailDao.findByBatchIdAndDetailStatus(batchId, DetailStatus.WAITING.getValue());
    if (!CollectionUtils.isEmpty(list)) {
        throw new RuntimeException("还有未完成的配货明细");
    }
    Date now = new Date();
    batch.setStatus(StorageIoBatch.Status.FINISH.getValue());
    batch.setGmtModify(now);
    this.storageIoBatchDao.save(batch);
    this.storageOrderDao.updateToSentByBatchId(batchId);
    logger.info("[批量出库完成] batchId={}", batchId);
    return this.storageOrderDao.findOutOrderIdsByBatchId(batchId);
}


public void updateDetailStatusAndBalance(StorageIoDetail detail,Integer balance,DetailStatus status){
    if (!DetailStatus.isFinalStatus(status)) {
        throw new RuntimeException("DetailStatus必须是SUCCESS或LACKNESS, 实际值为:" + status.getValue());
    }
    detail.setBalance(balance);
    detail.setDetailStatus(status.getValue());
    detail.setGmtModify(new Date());
    logger.info("[更新出入库明细] detailId={}, balance={}, status={}", detail.getDetailId(), balance, status.getValue());
    this.storageIoDetailDao.save(detail);
}


public StorageIoBatch saveStorageIoBatch(Integer repoId,Integer operator,BizType bizType,String memo){
    StorageIoBatch batch = new StorageIoBatch();
    batch.setRepoId(repoId);
    batch.setMemo(memo);
    batch.setOperator(operator);
    batch.setAmount(0);
    batch.setBizType(bizType.getValue());
    batch.setStatus(StorageIoBatch.Status.CREATED.getValue());
    Date now = new Date();
    batch.setGmtCreate(now);
    batch.setGmtModify(now);
    this.storageIoBatchDao.save(batch);
    logger.info("[保存出入库批次], batchId={}, repoId={}, operator={}, bizType={}", batch.getBatchId(), repoId, operator, bizType.getValue());
    return batch;
}


@Override
@Transactional
public void cancelInStorage(Integer batchId,Integer operator){
    if (batchId == null) {
        throw new IllegalArgumentException("batchId不能为空");
    }
    if (operator == null) {
        throw new IllegalArgumentException("operator不能为空");
    }
    // 批次存在，且必须是入库类型，CREATED状态
    StorageIoBatch batch = this.storageIoBatchDao.findOne(batchId);
    checkStorageIoBatchForInStorage(batchId, batch);
    if (!BizType.IN_BATCH.getValue().equals(batch.getBizType())) {
        throw new RuntimeException("只有批量入库可以被取消");
    }
    batch.setStatus(StorageIoBatch.Status.CANCEL.getValue());
    // 更新入库明细，st=已取消
    // 更新入库批次，st=已取消
    this.storageIoBatchDao.save(batch);
    this.storageIoDetailDao.batchCancel(batchId);
    logger.info("[取消入库] batchId={}, operator={}", batchId, operator);
}


@Override
public void savePosition(String label,Integer repoId){
    StoragePosition storagePosition = new StoragePosition();
    storagePosition.setRepoId(repoId);
    storagePosition.setLabel(label);
    storagePosition.setPosStatus(StoragePosition.PosStatus.AVAILABLE.getValue());
    storagePosition.setGmtCreate(new Date());
    storagePosition.setGmtModify(new Date());
    this.storagePositionDao.save(storagePosition);
}


}