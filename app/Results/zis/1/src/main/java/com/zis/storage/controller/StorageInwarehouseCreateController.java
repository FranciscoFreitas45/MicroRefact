package com.zis.storage.controller;
 import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.common.controllertemplate.ViewTips;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.InwarehousePosition;
import com.zis.purchase.bean.PurchaseDetail;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.InwarehouseCreateResult;
import com.zis.purchase.repository.InwarehousePositionDao;
import com.zis.shop.util.ShopUtil;
import com.zis.storage.dto.InwarehouseCreateDto;
import com.zis.storage.dto.InwarehouseViewDTO;
import com.zis.storage.entity.StorageIoBatch;
import com.zis.storage.entity.StoragePosition;
import com.zis.storage.repository.StorageIoBatchDao;
import com.zis.storage.repository.StoragePositionDao;
import com.zis.storage.service.StorageService;
import com.zis.storage.util.StorageUtil;
import com.zis.Interface.InwarehousePositionDao;
import com.zis.Interface.DoPurchaseService;
import com.zis.DTO.InwarehouseCreateResult;
import com.zis.DTO.InwarehousePositionDao;
@Controller
@RequestMapping(value = "/storage")
public class StorageInwarehouseCreateController implements ViewTips{

@Autowired
 private  StorageService storageService;

@Autowired
 private  InwarehousePositionDao inwarehousePositionDao;

@Autowired
 private  StoragePositionDao storagePositionDao;

@Autowired
 private  StorageIoBatchDao storageIoBatchDao;

@Autowired
 private  DoPurchaseService doPurchaseService;

 private  Logger logger;

 private  String PURCHASE_TYPE;

 private  String ZH_CN_PURCHASE;


@RequestMapping(value = "/recoverScan")
public String recoverScan(Integer ioBatchId,ModelMap context){
    try {
        InwarehouseViewDTO view = this.findInwarehouseViewById(ioBatchId);
        String[] positionLabels = view.getPositionLabel();
        if (positionLabels == null || positionLabels.length == 0) {
            // 如果没有可用库位，进行如下设置，方便页面展示
            positionLabels = new String[] { "无可用库位" };
        }
        // 参数传递到下一个页面，展示用
        context.put("inwarehouse", view);
        context.put("ioBatchId", view.getBatchId());
        context.put("stockPosLabel", positionLabels);
        context.put("inwarehouseOperator", StorageUtil.getUserName());
        if (view.getMemo().contains(ZH_CN_PURCHASE)) {
            // 如果备注中包含采购入库，执行下面逻辑
            List<PurchaseDetail> list = this.doPurchaseService.findPurchaseDetailByBatchId(ioBatchId);
            if (list.isEmpty()) {
                // 如果没有采购明细，查询不到采购员，则取消此次入库
                this.storageService.cancelInStorage(ioBatchId, StorageUtil.getUserId());
                throw new RuntimeException("查询不到采购员，此批次取消，批次号 " + ioBatchId);
            }
            context.put("purchaseOperator", list.get(0).getOperator());
            context.put("bizType", PURCHASE_TYPE);
        }
        context.put("curPosition", positionLabels[0]);
        context.put("memo", view.getMemo());
        return "storage/inwarehouse/inwarehouseScanner";
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        context.put("actionError", e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "/fastInWarehouse")
public String createFastBatch(String stockPosLabel,ModelMap context){
    try {
        StoragePosition position = this.storagePositionDao.findByLabelAndRepoId(stockPosLabel, StorageUtil.getRepoId());
        if (position == null) {
            context.put("actionError", stockPosLabel + "库位不存在请新建库位后创建入库单");
            return "storage/inwarehouse/fast-inwarehouse";
        }
        context.put("stockPosLabel", stockPosLabel);
        context.put("oldAmount", 0);
        context.put("curPosition", stockPosLabel);
        return "storage/inwarehouse/fast-inwarehouseScanner";
    } catch (Exception e) {
        context.put(ACTION_ERROR, e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "/confirmInStorage")
public String confirmInStorage(ModelMap map,Integer ioBatchId){
    try {
        this.storageService.confirmInStorage(ioBatchId, StorageUtil.getUserId());
        map.put("actionMessage", "批次:" + ioBatchId + " 入库完成");
        return "storage/inwarehouse/inwarehouse";
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        return "error";
    }
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


@RequestMapping(value = "/cancelInStorage")
public String cancelInStorage(ModelMap map,Integer ioBatchId){
    try {
        this.storageService.cancelInStorage(ioBatchId, StorageUtil.getUserId());
        map.put("actionMessage", "批次:" + ioBatchId + " 已取消");
        return "storage/inwarehouse/inwarehouse";
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        return "error";
    }
}


public InwarehouseCreateResult createInwarehouse(InwarehouseCreateDto inwarehouse){
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
    // 创建入库批次
    StorageIoBatch ioBacth = this.storageService.createInStorage(StorageUtil.getRepoId(), inwarehouse.getMemo(), StorageUtil.getUserId());
    // 创建入库辅助表InwarehousePosition
    for (int i = 0; i < labels.length; i++) {
        createInwarehousePosition(ioBacth.getBatchId(), labels[i], capacities[i]);
    }
    InwarehouseCreateResult result = new InwarehouseCreateResult();
    result.setSuccess(true);
    result.setInwarehouseId(ioBacth.getBatchId());
    if (labels != null && labels.length > 0) {
        result.setCurrentPosition(labels[0]);
    }
    return result;
}


@RequestMapping(value = "/inWarehouse")
public String createBatch(InwarehouseCreateDto inwarehouseCreateDto,BindingResult br,ModelMap context){
    if (br.hasErrors()) {
        return "storage/inwarehouse/inwarehouse";
    }
    try {
        // 库位名称
        String[] stockPosLabel = inwarehouseCreateDto.getStockPosLabel();
        for (String s : stockPosLabel) {
            StoragePosition position = this.storagePositionDao.findByLabelAndRepoId(s, StorageUtil.getRepoId());
            if (position == null) {
                context.put("actionError", s + "库位不存在请新建库位后创建入库单");
                return "storage/inwarehouse/inwarehouse";
            }
        }
        // 入库操作员
        String inwarehouseOperator = ShopUtil.getUserName();
        // 备注
        String memo = inwarehouseCreateDto.getMemo();
        String purchaseOperator = inwarehouseCreateDto.getPurchaseOperator();
        String bizType = inwarehouseCreateDto.getBizType();
        boolean purchaseInwarehouse = StringUtils.isNotBlank(purchaseOperator) && PURCHASE_TYPE.equals(bizType);
        if (purchaseInwarehouse) {
            inwarehouseCreateDto.setMemo(ZH_CN_PURCHASE + " " + memo);
        }
        // 新建入库批次
        InwarehouseCreateResult result = this.createInwarehouse(inwarehouseCreateDto);
        // 参数传递到下一个页面，展示用
        if (purchaseInwarehouse) {
            context.put("purchaseOperator", purchaseOperator);
            context.put("bizType", bizType);
            context.put("memo", ZH_CN_PURCHASE + " " + memo);
        } else {
            context.put("memo", memo);
        }
        context.put("ioBatchId", result.getInwarehouseId());
        context.put("stockPosLabel", stockPosLabel);
        context.put("inwarehouseOperator", inwarehouseOperator);
        context.put("curPosition", stockPosLabel[0]);
        return "storage/inwarehouse/inwarehouseScanner";
    } catch (Exception e) {
        context.put("actionError", e.getMessage());
        logger.error("创建采购入库单失败", e);
        return "storage/inwarehouse/inwarehouse";
    }
}


public InwarehouseViewDTO findInwarehouseViewById(Integer ioBatchId){
    // 查询入库单
    StorageIoBatch in = this.storageIoBatchDao.findOne(ioBatchId);
    if (in == null) {
        return null;
    }
    InwarehouseViewDTO inView = new InwarehouseViewDTO();
    BeanUtils.copyProperties(in, inView);
    inView.setBizTypeDisplay(in.getBizType());
    inView.setStatusDisplay(in.getStatus());
    if (StorageIoBatch.Status.CREATED.getValue().equals(in.getStatus())) {
        // 查询入库单下的可用库位
        // DetachedCriteria criteria = DetachedCriteria
        // .forClass(InwarehousePosition.class);
        // criteria.add(Restrictions.eq("inwarehouseId", inwarehouseId));
        // criteria.add(Restrictions.eq("isFull", false));
        // criteria.addOrder(Order.asc("gmtCreate"));
        // List<InwarehousePosition> list = this.inwarehousePositionDao
        // .findByCriteria(criteria);
        // 按照ID排序，由于是同一时间创建的，如果按照时间排序会导致库位顺序错乱的bug
        List<InwarehousePosition> list = this.inwarehousePositionDao.findAvailablePosition(ioBatchId);
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


}