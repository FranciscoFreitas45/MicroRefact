package org.danyuan.application.dbms.tabs.service;
 import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.dbms.tabs.dao.SysDbmsUserIndexInfoDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsUserIndexInfo;
import org.danyuan.application.dbms.tabs.vo.SysDbmsUserIndexInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
@Service
public class SysDbmsUserIndexInfoService extends BaseServiceImpl<SysDbmsUserIndexInfo>implements BaseService<SysDbmsUserIndexInfo>{

@Autowired
 private  SysDbmsUserIndexInfoDao sysDbmsUserIndexInfoDao;


public List<SysDbmsUserIndexInfo> chartList(){
    return sysDbmsUserIndexInfoDao.findAllByChart();
}


public Page<SysDbmsUserIndexInfo> page(SysDbmsUserIndexInfoVo vo){
    Example<SysDbmsUserIndexInfo> example = Example.of(vo.getInfo());
    Sort sort = Sort.by(new Order(Direction.ASC, "userOrder"), new Order(Direction.DESC, "createTime"));
    if (vo.getSortName() != null && !"".equals(vo.getSortName())) {
        if (vo.getSortOrder().equals("asc")) {
            sort = Sort.by(new Order(Direction.ASC, vo.getSortName()));
        } else {
            sort = Sort.by(new Order(Direction.DESC, vo.getSortName()));
        }
    }
    PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
    Page<SysDbmsUserIndexInfo> sourceCodes = sysDbmsUserIndexInfoDao.findAll(example, request);
    return sourceCodes;
}


public List<SysDbmsUserIndexInfo> findAll(){
    return sysDbmsUserIndexInfoDao.findAllByDeleteFlag();
}


}