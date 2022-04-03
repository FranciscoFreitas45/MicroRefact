package org.danyuan.application.softm.organization.service;
 import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.softm.organization.dao.SysOrganizationInfoDao;
import org.danyuan.application.softm.organization.po.SysOrganizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysOrganizationInfoDao;
@Service("sysOrganizationService")
public class SysOrganizationInfoService extends BaseServiceImpl<SysOrganizationInfo>implements BaseService<SysOrganizationInfo>{

@Autowired
 private  SysOrganizationInfoDao sysOrganizationInfoDao;


public Page<SysOrganizationInfo> findAllBySearchText(int pageNumber,int pageSize,SysOrganizationInfo info){
    Example<SysOrganizationInfo> example = Example.of(info);
    Sort sort = Sort.by(new Order(Direction.DESC, "createTime"));
    PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
    Page<SysOrganizationInfo> sourceCodes = sysOrganizationInfoDao.findAll(example, request);
    return sourceCodes;
}


public List<SysOrganizationInfo> findAll(){
    return sysOrganizationInfoDao.findAll();
}


}