package com.zammc.service.coupon.impl;
 import com.zammc.domain.coupon.CouponEntity;
import com.zammc.idworker.IdWorker;
import com.zammc.page.PageBean;
import com.zammc.repository.CouponRepository;
import com.zammc.service.coupon.CouponService;
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
public class CouponServiceImpl implements CouponService{

@Autowired
 private  CouponRepository couponRepository;

@Autowired
 private  IdWorker idWorker;


@Override
public void deleteCoupon(CouponEntity couponEntity){
    CouponEntity coupon = couponRepository.findOne(couponEntity.getId());
    if (null == coupon || coupon.getDataStatus() == 1) {
        return;
    }
    coupon.setDataStatus((byte) 1);
    couponRepository.saveAndFlush(coupon);
}


@Override
public void addCoupon(CouponEntity couponEntity){
    couponEntity.setId(idWorker.nextId());
    couponRepository.saveAndFlush(couponEntity);
}


@Override
public void queryCouponPage(PageBean pageBean){
    int page = pageBean.getPageNum() - 1;
    int size = pageBean.getPageSize();
    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Sort.Direction.ASC, "id"));
    Sort sort = new Sort(orders);
    Pageable pageable = new PageRequest(page, size, sort);
    Page<CouponEntity> couponPage = couponRepository.findAll(CouponSpecification.where(), pageable);
    pageBean.setPageSize(couponPage.getSize());
    pageBean.setPageNum(couponPage.getNumber() + 1);
    pageBean.setTotalPage(couponPage.getTotalPages());
    pageBean.setTotalRecorder(couponPage.getTotalElements());
    pageBean.setContent(couponPage.getContent());
}


}