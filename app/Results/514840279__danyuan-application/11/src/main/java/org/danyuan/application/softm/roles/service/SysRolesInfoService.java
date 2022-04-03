package org.danyuan.application.softm.roles.service;
 import java.util.List;
import java.util.Optional;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.softm.roles.dao.SysRolesInfoDao;
import org.danyuan.application.softm.roles.dao.SysUserRolesInfoDao;
import org.danyuan.application.softm.roles.po.SysRolesInfo;
import org.danyuan.application.softm.roles.po.SysUserRolesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysRolesInfoDao;
import org.danyuan.application.Interface.SysUserRolesInfoDao;
@Service("sysRolesService")
public class SysRolesInfoService extends BaseServiceImpl<SysRolesInfo>implements BaseService<SysRolesInfo>{

@Autowired
 private  SysRolesInfoDao sysRolesInfoDao;

@Autowired
 private  SysUserRolesInfoDao sysUserRolesInfoDao;


public Page<SysRolesInfo> findAllBySearchText(int pageNumber,int pageSize,SysRolesInfo info){
    Example<SysRolesInfo> example = Example.of(info);
    Sort sort = Sort.by(new Order(Direction.DESC, "createTime"));
    PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
    Page<SysRolesInfo> sourceCodes = sysRolesInfoDao.findAll(example, request);
    return sourceCodes;
}


public List<SysRolesInfo> findAllRoleBySearchText(String userId){
    List<SysRolesInfo> list = sysRolesInfoDao.findAll();
    for (SysRolesInfo sysRolesInfo : list) {
        SysUserRolesInfo info = new SysUserRolesInfo();
        info.setUserId(userId);
        info.setRolesId(sysRolesInfo.getUuid());
        Example<SysUserRolesInfo> e = Example.of(info);
        Optional<SysUserRolesInfo> t = sysUserRolesInfoDao.findOne(e);
        if (t.isPresent()) {
            sysRolesInfo.setChecked(t.get().getChecked());
        } else {
            sysRolesInfo.setChecked(false);
        }
    }
    return list;
}


public List<SysRolesInfo> findAll(){
    return sysRolesInfoDao.findAll();
}


}