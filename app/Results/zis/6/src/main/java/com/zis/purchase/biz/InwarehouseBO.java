package com.zis.purchase.biz;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.Inwarehouse;
import com.zis.purchase.bean.InwarehouseDetail;
import com.zis.purchase.bean.InwarehousePosition;
import com.zis.purchase.bean.InwarehouseStatus;
import com.zis.purchase.dto.InwarehouseCreateDTO;
import com.zis.purchase.dto.InwarehouseCreateResult;
import com.zis.purchase.dto.InwarehouseDealtResult;
import com.zis.purchase.repository.InwarehouseDao;
import com.zis.purchase.repository.InwarehouseDetailDao;
import com.zis.purchase.repository.InwarehousePositionDao;
import com.zis.purchase.repository.PurchasePlanDao;
@Component
public class InwarehouseBO {

@Autowired
 private  InwarehouseDao inwarehouseDao;

@Autowired
 private  InwarehouseDetailDao inwarehouseDetailDao;

@Autowired
 private  InwarehousePositionDao inwarehousePositionDao;

@Autowired
 protected  PurchasePlanDao purchasePlanDao;

 protected  Logger logger;


public InwarehousePosition findAvailablePosition(Integer inwarehouseId,Integer amount){
    // 按照ID排序，由于是同一时间创建的，如果按照时间排序会导致库位顺序错乱的bug
    List<InwarehousePosition> posList = this.inwarehousePositionDao.findAvailablePosition(inwarehouseId);
    if (posList == null || posList.isEmpty()) {
        return null;
    }
    List<InwarehousePosition> updatePos = new ArrayList<InwarehousePosition>();
    InwarehousePosition availablePos = null;
    // 遍历所有库位
    for (InwarehousePosition curPos : posList) {
        // 如果当前库位可用量不足存放，为了保证顺次存放，需将当前库位isFull置为true
        if (curPos.getCapacity() - curPos.getCurrentAmount() < amount) {
            updatePos.add(curPos);
        // this.inwarehousePositionDao.save(curPos);
        } else {
            availablePos = curPos;
            break;
        }
    }
    // 如果没有可用库位，则不更新当前库位的isFull标记
    if (availablePos != null) {
        for (InwarehousePosition pos : updatePos) {
            pos.setIsFull(true);
            pos.setGmtModify(ZisUtils.getTS());
            this.inwarehousePositionDao.save(pos);
        }
    }
    return availablePos;
}


public void deleteInwarehouse(Integer inwarehouseId){
    if (inwarehouseId == null) {
        throw new RuntimeException("inwarehouseId could not be null");
    }
    Inwarehouse in = this.inwarehouseDao.findOne(inwarehouseId);
    if (in == null) {
        return;
    }
    if (!InwarehouseStatus.PROCESSING.equals(in.getStatus())) {
        throw new RuntimeException("待删除的入库单状态必须是“处理中”");
    }
    in.setStatus(InwarehouseStatus.CANCEL);
    in.setGmtModify(ZisUtils.getTS());
    // FIXME 入库单置为无效，但是没有修改库存
    this.inwarehouseDao.save(in);
}


public Integer createInwarehousePosition(Integer inwarehouseId,String positionLabel,Integer capacity){
    InwarehousePosition pos = new InwarehousePosition();
    pos.setInwarehouseId(inwarehouseId);
    pos.setPositionLabel(positionLabel);
    pos.setCapacity(capacity);
    pos.setCurrentAmount(0);
    pos.setIsFull(false);
    pos.setGmtCreate(ZisUtils.getTS());
    pos.setGmtModify(ZisUtils.getTS());
    this.inwarehousePositionDao.save(pos);
    return pos.getId();
}


public void afterPut(Inwarehouse in,Integer bookId,Integer amount){
}


public InwarehouseDealtResult doInwarehouse(Inwarehouse in,String posLabel,Integer bookId,Integer amount){
    // 基本参数检查
    if (in == null) {
        throw new IllegalArgumentException("inwarehouse could not be null");
    }
    if (StringUtils.isBlank(posLabel)) {
        throw new IllegalArgumentException("posLabel could not be null");
    }
    if (bookId == null) {
        throw new IllegalArgumentException("bookId could not be null");
    }
    if (amount == null || amount < 1) {
        throw new IllegalArgumentException("入库数量必须大于等于1");
    }
    // 其他检查，由子类进行扩展
    String error = checkForDoInwarehouse(in, bookId, amount);
    if (StringUtils.isNotBlank(error)) {
        throw new RuntimeException(error);
    }
    InwarehouseDealtResult result = new InwarehouseDealtResult();
    // 自动选择可用库位
    InwarehousePosition pos = findAvailablePosition(in.getId(), amount);
    if (pos == null) {
        // 没有可用库位，直接返回
        result.setSuccess(false);
        result.setFailReason("所有可用库位已存满，操作结束");
        result.setTotalAmount(in.getAmount());
        return result;
    }
    result.setPrePosLabel(posLabel);
    result.setCurPosLabel(pos.getPositionLabel());
    if (!posLabel.equals(pos.getPositionLabel())) {
        // 库位发生变化，提示客户端
        result.setPositionChange(true);
    }
    // 执行入库操作
    putIntoPosition(in, pos, bookId, amount);
    // 更新采购计划
    // updatePurchasePlanForStock(bookId, amount);
    // 入库完成后的后续操作，由子类进行扩展
    afterPut(in, bookId, amount);
    result.setSuccess(true);
    result.setTotalAmount(in.getAmount());
    return result;
}


public String checkForDoInwarehouse(Inwarehouse in,Integer bookId,Integer amount){
    return StringUtils.EMPTY;
}


public void deleteInwarehouseDetail(Integer detailId){
    InwarehouseDetail detail = this.inwarehouseDetailDao.findOne(detailId);
    if (detail == null) {
        return;
    }
    this.inwarehouseDetailDao.delete(detail);
    Inwarehouse in = this.inwarehouseDao.findOne(detail.getInwarehouseId());
    in.setAmount(in.getAmount() - detail.getAmount());
    in.setGmtModify(ZisUtils.getTS());
    this.inwarehouseDao.save(in);
}


public InwarehouseCreateResult createInwarehouse(InwarehouseCreateDTO inwarehouse){
    // 输入有效性检查
    if (inwarehouse == null) {
        throw new RuntimeException("illegal argument, for input null");
    }
    String[] labels = inwarehouse.getStockPosLabel();
    Integer[] capacities = inwarehouse.getStockPosCapacity();
    if (labels == null || capacities == null) {
        throw new RuntimeException("库位名称和库位容量必须设定");
    }
    if (labels.length != capacities.length) {
        throw new RuntimeException("库位名称和库位容量不匹配");
    }
    if (!StringUtils.isNoneBlank(labels)) {
        throw new RuntimeException("库位名称不能为空");
    }
    for (int i = 0; i < labels.length; i++) {
        if (StringUtils.isBlank(labels[i])) {
            throw new RuntimeException("库位名称不能为空");
        }
        if (capacities[i] <= 0) {
            throw new RuntimeException("库位容量必须大于0");
        }
    }
    String error = checkForCreateInwarehouse(inwarehouse);
    if (StringUtils.isNotBlank(error)) {
        InwarehouseCreateResult result = new InwarehouseCreateResult();
        result.setFailReason(error);
        result.setSuccess(false);
        return result;
    }
    // 创建采购入库单
    Inwarehouse record = new Inwarehouse();
    record.setBizType(inwarehouse.getBizType());
    record.setInwarehouseOperator(inwarehouse.getInwarehouseOperator());
    // 来源设置成采购员
    record.setSource(inwarehouse.getPurchaseOperator());
    record.setMemo(inwarehouse.getMemo());
    record.setStatus(InwarehouseStatus.PROCESSING);
    record.setAmount(0);
    record.setGmtCreate(ZisUtils.getTS());
    record.setGmtModify(ZisUtils.getTS());
    this.inwarehouseDao.save(record);
    // 创建入库辅助表InwarehousePosition
    for (int i = 0; i < labels.length; i++) {
        createInwarehousePosition(record.getId(), labels[i], capacities[i]);
    }
    InwarehouseCreateResult result = new InwarehouseCreateResult();
    result.setSuccess(true);
    result.setInwarehouseId(record.getId());
    if (labels != null && labels.length > 0) {
        result.setCurrentPosition(labels[0]);
    }
    return result;
}


public void terminateInwarehouse(Integer inwarehouseId){
    Inwarehouse in = this.inwarehouseDao.findOne(inwarehouseId);
    if (in == null) {
        throw new IllegalArgumentException("不存在的入库单，id=" + inwarehouseId);
    }
    if (!InwarehouseStatus.PROCESSING.equals(in.getStatus())) {
        throw new RuntimeException("入库单的状态必须是处理中, id=" + inwarehouseId);
    }
    in.setStatus(InwarehouseStatus.SUCCESS);
    in.setGmtModify(ZisUtils.getTS());
    this.inwarehouseDao.save(in);
}


public String checkForCreateInwarehouse(InwarehouseCreateDTO inwarehouse){
    return StringUtils.EMPTY;
}


public void putIntoPosition(Inwarehouse in,InwarehousePosition pos,Integer bookId,Integer amount){
    int amountAfterThisIn = pos.getCapacity() - pos.getCurrentAmount() - amount;
    // 更新入库单
    in.setAmount(in.getAmount() + amount);
    in.setGmtModify(ZisUtils.getTS());
    this.inwarehouseDao.save(in);
    // 更新库位
    pos.setCurrentAmount(pos.getCurrentAmount() + amount);
    if (amountAfterThisIn <= 0) {
        pos.setIsFull(true);
    }
    pos.setGmtModify(ZisUtils.getTS());
    this.inwarehousePositionDao.save(pos);
    // 新增入库明细记录
    InwarehouseDetail detail = new InwarehouseDetail();
    detail.setAmount(amount);
    detail.setBizType(in.getBizType());
    detail.setBookId(bookId);
    detail.setInwarehouseId(in.getId());
    detail.setPositionLabel(pos.getPositionLabel());
    detail.setGmtCreate(ZisUtils.getTS());
    detail.setGmtModify(ZisUtils.getTS());
    this.inwarehouseDetailDao.save(detail);
}


}