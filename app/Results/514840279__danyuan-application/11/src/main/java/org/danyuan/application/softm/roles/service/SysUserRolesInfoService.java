package org.danyuan.application.softm.roles.service;
 import java.util.List;
import java.util.Optional;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.softm.roles.dao.SysUserRolesInfoDao;
import org.danyuan.application.softm.roles.po.SysUserRolesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysUserRolesInfoDao;
@Service("sysUserRolesService")
public class SysUserRolesInfoService extends BaseServiceImpl<SysUserRolesInfo>implements BaseService<SysUserRolesInfo>{

@Autowired
 private  SysUserRolesInfoDao sysUserRolesInfoDao;


@Override
public SysUserRolesInfo save(SysUserRolesInfo info){
    SysUserRolesInfo info2 = new SysUserRolesInfo(info.getUserId(), info.getRolesId());
    Optional<SysUserRolesInfo> optional = sysUserRolesInfoDao.findOne(Example.of(info2));
    if (optional.isPresent()) {
        info2 = optional.get();
        info.setUuid(info2.getUuid());
        sysUserRolesInfoDao.save(info);
    } else {
        super.save(info);
    }
    return info;
}


public Page<SysUserRolesInfo> findAllBySearchText(int pageNumber,int pageSize,SysUserRolesInfo info){
    Example<SysUserRolesInfo> example = Example.of(info);
    Sort sort = Sort.by(new Order(Direction.ASC, "createTime"));
    PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
    Page<SysUserRolesInfo> sourceCodes = sysUserRolesInfoDao.findAll(example, request);
    return sourceCodes;
}


public List<SysUserRolesInfo> findAll(){
    return sysUserRolesInfoDao.findAll();
}


}