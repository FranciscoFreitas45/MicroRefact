package org.danyuan.application.softm.organization.service;
 import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.softm.organization.dao.SysDepartmentInfoDao;
import org.danyuan.application.softm.organization.po.SysDepartmentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysDepartmentInfoDao;
@Service("sysDepartmentService")
public class SysDepartmentInfoService extends BaseServiceImpl<SysDepartmentInfo>implements BaseService<SysDepartmentInfo>{

@Autowired
 private  SysDepartmentInfoDao sysDepartmentInfoDao;


public Page<SysDepartmentInfo> findAllBySearchText(int pageNumber,int pageSize,SysDepartmentInfo info){
    Example<SysDepartmentInfo> example = Example.of(info);
    Sort sort = Sort.by(new Order(Direction.DESC, "createTime"));
    PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
    Page<SysDepartmentInfo> sourceCodes = sysDepartmentInfoDao.findAll(example, request);
    return sourceCodes;
}


public List<SysDepartmentInfo> findAll(){
    return sysDepartmentInfoDao.findAll();
}


}