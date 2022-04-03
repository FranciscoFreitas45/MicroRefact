package org.danyuan.application.softm.dic.service;
 import java.util.ArrayList;
import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.softm.dic.dao.SysDicKeyListDao;
import org.danyuan.application.softm.dic.po.SysDicKeyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
@Service
public class SysDicKeyListService extends BaseServiceImpl<SysDicKeyList>implements BaseService<SysDicKeyList>{

@Autowired
 private  SysDicKeyListDao sysDicKeyListDao;


@Override
public Page<SysDicKeyList> page(Pagination<SysDicKeyList> vo){
    List<Order> orders = new ArrayList<>();
    orders.add(new Order(Direction.ASC, "keyOrder"));
    if (vo.getSortName() != null) {
        Order order;
        if (vo.getSortOrder().equals("desc")) {
            order = Order.desc(vo.getSortName());
        } else {
            order = Order.asc(vo.getSortName());
        }
        orders.add(order);
    }
    if (vo.getInfo() == null) {
        vo.setInfo(new SysDicKeyList());
    }
    Example<SysDicKeyList> example = Example.of(vo.getInfo());
    Sort sort = Sort.by(orders);
    PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
    Page<SysDicKeyList> page = sysDicKeyListDao.findAll(example, request);
    return page;
}


}