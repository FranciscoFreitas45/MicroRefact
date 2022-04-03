package org.danyuan.application.softm.dic.service;
 import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.softm.dic.dao.SysDicKeyListDao;
import org.danyuan.application.softm.dic.dao.SysDicNameDao;
import org.danyuan.application.softm.dic.po.SysDicKeyList;
import org.danyuan.application.softm.dic.po.SysDicName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysDicNameDao;
import org.danyuan.application.DTO.Pagination;
@Service
public class SysDicNameService extends BaseServiceImpl<SysDicName>implements BaseService<SysDicName>{

@Autowired
 private  SysDicNameDao sysDicNameDao;

@Autowired
 private  SysDicKeyListDao sysDicKeyListDao;


public List<SysDicKeyList> findkeyList(SysDicName info){
    Example<SysDicName> example = Example.of(info);
    Optional<SysDicName> reinfo = sysDicNameDao.findOne(example);
    if (reinfo.isPresent()) {
        info = reinfo.get();
        SysDicKeyList key = new SysDicKeyList();
        key.setNameUuid(info.getUuid());
        Example<SysDicKeyList> ke = Example.of(key);
        Order[] order = { new Order(Direction.ASC, "keyOrder"), new Order(Direction.ASC, "createTime") };
        Sort sort = Sort.by(order);
        return sysDicKeyListDao.findAll(ke, sort);
    } else {
        return null;
    }
}


@Override
public Page<SysDicName> page(Pagination<SysDicName> vo){
    List<Order> orders = new ArrayList<>();
    if (vo.getSortName() != null) {
        Order order;
        if (vo.getSortOrder().equals("desc")) {
            order = Order.desc(vo.getSortName());
        } else {
            order = Order.asc(vo.getSortName());
        }
        orders.add(order);
    } else {
        Order order = new Order(Direction.DESC, "createTime");
        orders.add(order);
    }
    if (vo.getInfo() == null) {
        vo.setInfo(new SysDicName());
    }
    Sort sort = Sort.by(orders);
    PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
    return sysDicNameDao.findAll((Specification<SysDicName>) (root, query, cb) -> {
        if (vo.getInfo().getName() != null) {
            return cb.like(root.get("name").as(String.class), "%" + vo.getInfo().getName() + "%");
        } else {
            return null;
        }
    }, request);
}


public boolean checkCode(String code){
    SysDicName info = new SysDicName();
    info.setCode(code);
    Example<SysDicName> example = Example.of(info);
    List<SysDicName> list = sysDicNameDao.findAll(example);
    if (list.size() == 0) {
        return true;
    } else {
        return false;
    }
}


}