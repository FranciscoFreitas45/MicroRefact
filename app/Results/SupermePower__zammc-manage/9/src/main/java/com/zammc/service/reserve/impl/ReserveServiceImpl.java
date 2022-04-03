package com.zammc.service.reserve.impl;
 import com.zammc.domain.reserve.ReserveEntity;
import com.zammc.page.PageBean;
import com.zammc.repository.ReserveRepository;
import com.zammc.service.reserve.ReserveService;
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
public class ReserveServiceImpl implements ReserveService{

@Autowired
 private  ReserveRepository reserveRepository;


public void queryReservePage(ReserveEntity reserveEntity,PageBean pageBean){
    int page = pageBean.getPageNum() - 1;
    int size = pageBean.getPageSize();
    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Sort.Direction.DESC, "updateTime"));
    Sort sort = new Sort(orders);
    Pageable pageable = new PageRequest(page, size, sort);
    Page<ReserveEntity> reservePage = reserveRepository.findAll(ReserveSpecification.where(reserveEntity), pageable);
    pageBean.setPageSize(reservePage.getSize());
    pageBean.setPageNum(reservePage.getNumber() + 1);
    pageBean.setTotalPage(reservePage.getTotalPages());
    pageBean.setTotalRecorder(reservePage.getTotalElements());
    pageBean.setContent(reservePage.getContent());
}


public void deleteReserve(ReserveEntity reserveEntity){
    ReserveEntity one = reserveRepository.findOne(reserveEntity.getReserveId());
    if (null != one && one.getDataStatus() == 0) {
        one.setDataStatus((byte) 1);
        reserveRepository.saveAndFlush(one);
    }
}


}