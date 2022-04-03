package org.danyuan.application.softm.organization.service;
 import java.util.List;
import java.util.Optional;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.softm.organization.dao.SysRolesJurisdictionInfoDao;
import org.danyuan.application.softm.organization.po.SysRolesJurisdictionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysRolesJurisdictionInfoDao;
@Service("sysRolesJurisdictionService")
public class SysRolesJurisdictionInfoService extends BaseServiceImpl<SysRolesJurisdictionInfo>implements BaseService<SysRolesJurisdictionInfo>{

@Autowired
 private  SysRolesJurisdictionInfoDao sysRolesJurisdictionIfoDao;


@Override
public void saveAll(List<SysRolesJurisdictionInfo> entities){
    for (SysRolesJurisdictionInfo sysRolesJurisdictionInfo : entities) {
        SysRolesJurisdictionInfo sysRolesJurisdictionInfo2 = new SysRolesJurisdictionInfo(sysRolesJurisdictionInfo.getMenuId(), sysRolesJurisdictionInfo.getRoleId());
        Optional<SysRolesJurisdictionInfo> optional = sysRolesJurisdictionIfoDao.findOne(Example.of(sysRolesJurisdictionInfo2));
        if (optional.isPresent()) {
            sysRolesJurisdictionInfo2 = optional.get();
            sysRolesJurisdictionInfo2.setChecked(sysRolesJurisdictionInfo.getChecked());
            sysRolesJurisdictionIfoDao.save(sysRolesJurisdictionInfo2);
        } else {
            super.save(sysRolesJurisdictionInfo);
        }
    }
}


public Page<SysRolesJurisdictionInfo> findAllBySearchText(int pageNumber,int pageSize,SysRolesJurisdictionInfo info){
    Example<SysRolesJurisdictionInfo> example = Example.of(info);
    Sort sort = Sort.by(new Order(Direction.DESC, "createTime"));
    PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
    Page<SysRolesJurisdictionInfo> sourceCodes = sysRolesJurisdictionIfoDao.findAll(example, request);
    return sourceCodes;
}


public List<SysRolesJurisdictionInfo> findAll(){
    return sysRolesJurisdictionIfoDao.findAll();
}


}