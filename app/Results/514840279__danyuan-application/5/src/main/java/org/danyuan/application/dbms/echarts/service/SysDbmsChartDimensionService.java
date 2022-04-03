package org.danyuan.application.dbms.echarts.service;
 import java.util.ArrayList;
import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.dbms.echarts.dao.SysDbmsChartDimensionDao;
import org.danyuan.application.dbms.echarts.po.SysDbmsChartDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.danyuan.application.DTO.Pagination;
@Service
public class SysDbmsChartDimensionService extends BaseServiceImpl<SysDbmsChartDimension>implements BaseService<SysDbmsChartDimension>{

@Autowired
 private SysDbmsChartDimensionDao sysDbmsChartDimensionDao;


public void changeGroup(SysDbmsChartDimension info){
    sysDbmsChartDimensionDao.changeGroup(info.getUuid(), info.getGroupUuid());
}


public SysDbmsChartDimension updBefor(SysDbmsChartDimension task){
    return null;
}


@Override
public Page<SysDbmsChartDimension> page(Pagination<SysDbmsChartDimension> vo){
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
        Order order = new Order(Direction.ASC, "createTime");
        orders.add(order);
    }
    if (vo.getInfo() == null) {
        vo.setInfo(new SysDbmsChartDimension());
    }
    Example<SysDbmsChartDimension> example = Example.of(vo.getInfo());
    Sort sort = Sort.by(orders);
    PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
    return sysDbmsChartDimensionDao.findAll(example, request);
}


public List<SysDbmsChartDimension> findAllDime(){
    // Example<SysPlantChartDimension> example = Example.of(info);
    // Order order = new Order(Direction.ASC, "dimeOrder");
    // Sort sort = new Sort(order);
    return sysDbmsChartDimensionDao.findAllDime();
}


@Override
public List<SysDbmsChartDimension> findAll(SysDbmsChartDimension info){
    Example<SysDbmsChartDimension> example = Example.of(info);
    Order order = new Order(Direction.ASC, "dimeOrder");
    Sort sort = Sort.by(order);
    return sysDbmsChartDimensionDao.findAll(example, sort);
}


}