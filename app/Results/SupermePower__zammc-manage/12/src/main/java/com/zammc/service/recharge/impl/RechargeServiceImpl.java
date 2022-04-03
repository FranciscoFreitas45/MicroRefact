package com.zammc.service.recharge.impl;
 import com.zammc.domain.recharge.RechargeOrderEntity;
import com.zammc.page.PageBean;
import com.zammc.repository.RechargeRepository;
import com.zammc.service.recharge.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.zammc.DTO.PageBean;
@Service
public class RechargeServiceImpl implements RechargeService{

@Autowired
 private  RechargeRepository rechargeRepository;


public void queryRechargeOrderPage(RechargeOrderEntity rechargeOrderEntity,PageBean pageBean){
    int page = pageBean.getPageNum() - 1;
    int size = pageBean.getPageSize();
    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Sort.Direction.DESC, "updateTime"));
    Sort sort = new Sort(orders);
    Pageable pageable = new PageRequest(page, size, sort);
    Page<RechargeOrderEntity> orderInfoPage = rechargeRepository.findAll(RechargeSpecification.where(rechargeOrderEntity), pageable);
    pageBean.setPageSize(orderInfoPage.getSize());
    pageBean.setPageNum(orderInfoPage.getNumber() + 1);
    pageBean.setTotalPage(orderInfoPage.getTotalPages());
    pageBean.setTotalRecorder(orderInfoPage.getTotalElements());
    pageBean.setContent(orderInfoPage.getContent());
}


}