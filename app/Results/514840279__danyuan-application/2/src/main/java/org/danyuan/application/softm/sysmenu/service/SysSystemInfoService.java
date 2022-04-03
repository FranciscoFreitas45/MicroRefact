package org.danyuan.application.softm.sysmenu.service;
 import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.softm.sysmenu.dao.SysSystemDao;
import org.danyuan.application.softm.sysmenu.po.SysSystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysSystemDao;
@Service("sysSystemService")
public class SysSystemInfoService extends BaseServiceImpl<SysSystemInfo>implements BaseService<SysSystemInfo>{

@Autowired
 private  SysSystemDao sysSystemDao;


public Page<SysSystemInfo> findAllBySearchText(int pageNumber,int pageSize,SysSystemInfo info){
    Example<SysSystemInfo> example = Example.of(info);
    Sort sort = Sort.by(new Order(Direction.DESC, "insertDatetime"));
    PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
    Page<SysSystemInfo> sourceCodes = sysSystemDao.findAll(example, request);
    return sourceCodes;
}


public List<SysSystemInfo> findAll(){
    return sysSystemDao.findAll();
}


}