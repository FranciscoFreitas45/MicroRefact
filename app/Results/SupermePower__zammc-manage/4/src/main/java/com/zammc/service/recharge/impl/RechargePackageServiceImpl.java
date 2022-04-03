package com.zammc.service.recharge.impl;
 import com.zammc.domain.recharge.RechargePackageEntity;
import com.zammc.idworker.IdWorker;
import com.zammc.page.PageBean;
import com.zammc.repository.RechargePackageRepository;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.recharge.RechargePackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.zammc.Interface.IdWorker;
import com.zammc.DTO.PageBean;
@Service
public class RechargePackageServiceImpl implements RechargePackageService{

@Autowired
 private  IdWorker idWorker;

@Autowired
 private  RechargePackageRepository rechargePackageRepository;


@Override
public void queryRechargePackagePage(RechargePackageEntity rechargePackageEntity,PageBean pageBean){
    int page = pageBean.getPageNum() - 1;
    int size = pageBean.getPageSize();
    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Sort.Direction.DESC, "updateTime"));
    Sort sort = new Sort(orders);
    Pageable pageable = new PageRequest(page, size, sort);
    Page<RechargePackageEntity> rechargePackagePage = rechargePackageRepository.findAll(RechargePackageSpecification.where(rechargePackageEntity), pageable);
    pageBean.setPageSize(rechargePackagePage.getSize());
    pageBean.setPageNum(rechargePackagePage.getNumber() + 1);
    pageBean.setTotalPage(rechargePackagePage.getTotalPages());
    pageBean.setTotalRecorder(rechargePackagePage.getTotalElements());
    pageBean.setContent(rechargePackagePage.getContent());
}


public Message editRechargePackage(RechargePackageEntity rechargePackageEntity){
    Message x = checkPackage(rechargePackageEntity);
    if (x != null)
        return x;
    rechargePackageRepository.saveAndFlush(rechargePackageEntity);
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "修改成功");
}


public RechargePackageEntity queryRechargePackageById(RechargePackageEntity rechargePackageEntity){
    return rechargePackageRepository.findOne(rechargePackageEntity.getPackageId());
}


public Message checkPackage(RechargePackageEntity rechargePackageEntity){
    if (null == rechargePackageEntity.getPackageName() || "".equals(rechargePackageEntity.getPackageName())) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "套餐名称不能为空");
    }
    if (null == rechargePackageEntity.getRechargeMoney()) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "充值金额不能为空");
    }
    if (rechargePackageEntity.getRechargeMoney().compareTo(new BigDecimal(0)) <= 0) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "充值金额必须大于0");
    }
    if (null == rechargePackageEntity.getPayMoney()) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "支付金额不能为空");
    }
    if (rechargePackageEntity.getPayMoney().compareTo(new BigDecimal(0)) <= 0) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "支付金额必须大于0");
    }
    return null;
}


public Message addRechargePackage(RechargePackageEntity rechargePackageEntity){
    Message x = checkPackage(rechargePackageEntity);
    if (x != null)
        return x;
    rechargePackageEntity.setPackageId(idWorker.nextId());
    rechargePackageRepository.saveAndFlush(rechargePackageEntity);
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "新增成功");
}


public void deletePackage(RechargePackageEntity rechargePackageEntity){
    RechargePackageEntity one = rechargePackageRepository.findOne(rechargePackageEntity.getPackageId());
    if (null != one && one.getDataStatus() == 0) {
        one.setDataStatus((byte) 1);
        rechargePackageRepository.saveAndFlush(one);
    }
}


}