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
            // ??????????????????????????????????????????????????????????????????
            positionLabels = new String[] { "???????????????" };
        }
        // ??????????????????????????????????????????
        context.put("inwarehouse", view);
        context.put("ioBatchId", view.getBatchId());
        context.put("stockPosLabel", positionLabels);
        context.put("inwarehouseOperator", StorageUtil.getUserName());
        if (view.getMemo().contains(ZH_CN_PURCHASE)) {
            // ??????????????????????????????????????????????????????
            List<PurchaseDetail> list = this.doPurchaseService.findPurchaseDetailByBatchId(ioBatchId);
            if (list.isEmpty()) {
                // ????????????????????????????????????????????????????????????????????????
                this.storageService.cancelInStorage(ioBatchId, StorageUtil.getUserId());
                throw new RuntimeException("??????????????????????????????????????????????????? " + ioBatchId);
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
            context.put("actionError", stockPosLabel + "????????????????????????????????????????????????");
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
        map.put("actionMessage", "??????:" + ioBatchId + " ????????????");
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
        map.put("actionMessage", "??????:" + ioBatchId + " ?????????");
        return "storage/inwarehouse/inwarehouse";
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        return "error";
    }
}


public InwarehouseCreateResult createInwarehouse(InwarehouseCreateDto inwarehouse){
    // ?????????????????????
    if (inwarehouse == null) {
        throw new RuntimeException("illegal argument, for input null");
    }
    String[] labels = inwarehouse.getStockPosLabel();
    Integer[] capacities = inwarehouse.getStockPosCapacity();
    if (labels == null || capacities == null) {
        throw new RuntimeException("???????????????????????????????????????");
    }
    if (labels.length != capacities.length) {
        throw new RuntimeException("????????????????????????????????????");
    }
    if (!StringUtils.isNoneBlank(labels)) {
        throw new RuntimeException("????????????????????????");
    }
    for (int i = 0; i < labels.length; i++) {
        if (StringUtils.isBlank(labels[i])) {
            throw new RuntimeException("????????????????????????");
        }
        if (capacities[i] <= 0) {
            throw new RuntimeException("????????????????????????0");
        }
    }
    // ??????????????????
    StorageIoBatch ioBacth = this.storageService.createInStorage(StorageUtil.getRepoId(), inwarehouse.getMemo(), StorageUtil.getUserId());
    // ?????????????????????InwarehousePosition
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
        // ????????????
        String[] stockPosLabel = inwarehouseCreateDto.getStockPosLabel();
        for (String s : stockPosLabel) {
            StoragePosition position = this.storagePositionDao.findByLabelAndRepoId(s, StorageUtil.getRepoId());
            if (position == null) {
                context.put("actionError", s + "????????????????????????????????????????????????");
                return "storage/inwarehouse/inwarehouse";
            }
        }
        // ???????????????
        String inwarehouseOperator = ShopUtil.getUserName();
        // ??????
        String memo = inwarehouseCreateDto.getMemo();
        String purchaseOperator = inwarehouseCreateDto.getPurchaseOperator();
        String bizType = inwarehouseCreateDto.getBizType();
        boolean purchaseInwarehouse = StringUtils.isNotBlank(purchaseOperator) && PURCHASE_TYPE.equals(bizType);
        if (purchaseInwarehouse) {
            inwarehouseCreateDto.setMemo(ZH_CN_PURCHASE + " " + memo);
        }
        // ??????????????????
        InwarehouseCreateResult result = this.createInwarehouse(inwarehouseCreateDto);
        // ??????????????????????????????????????????
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
        logger.error("???????????????????????????", e);
        return "storage/inwarehouse/inwarehouse";
    }
}


public InwarehouseViewDTO findInwarehouseViewById(Integer ioBatchId){
    // ???????????????
    StorageIoBatch in = this.storageIoBatchDao.findOne(ioBatchId);
    if (in == null) {
        return null;
    }
    InwarehouseViewDTO inView = new InwarehouseViewDTO();
    BeanUtils.copyProperties(in, inView);
    inView.setBizTypeDisplay(in.getBizType());
    inView.setStatusDisplay(in.getStatus());
    if (StorageIoBatch.Status.CREATED.getValue().equals(in.getStatus())) {
        // ?????????????????????????????????
        // DetachedCriteria criteria = DetachedCriteria
        // .forClass(InwarehousePosition.class);
        // criteria.add(Restrictions.eq("inwarehouseId", inwarehouseId));
        // criteria.add(Restrictions.eq("isFull", false));
        // criteria.addOrder(Order.asc("gmtCreate"));
        // List<InwarehousePosition> list = this.inwarehousePositionDao
        // .findByCriteria(criteria);
        // ??????ID????????????????????????????????????????????????????????????????????????????????????????????????bug
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