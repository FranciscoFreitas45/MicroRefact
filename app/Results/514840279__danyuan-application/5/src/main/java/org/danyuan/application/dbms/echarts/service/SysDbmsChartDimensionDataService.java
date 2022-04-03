package org.danyuan.application.dbms.echarts.service;
 import java.util.ArrayList;
import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.dbms.echarts.dao.SysDbmsChartDimensionDataDao;
import org.danyuan.application.dbms.echarts.po.SysDbmsChartDimensionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysDbmsChartDimensionDataDao;
@Service
public class SysDbmsChartDimensionDataService extends BaseServiceImpl<SysDbmsChartDimensionData>implements BaseService<SysDbmsChartDimensionData>{

@Autowired
 private SysDbmsChartDimensionDataDao sysDbmsChartDimensionDataDao;


@Override
public Page<SysDbmsChartDimensionData> page(Pagination<SysDbmsChartDimensionData> vo){
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
        vo.setInfo(new SysDbmsChartDimensionData());
    }
    Example<SysDbmsChartDimensionData> example = Example.of(vo.getInfo());
    Sort sort = Sort.by(orders);
    PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
    return sysDbmsChartDimensionDataDao.findAll(example, request);
}


}