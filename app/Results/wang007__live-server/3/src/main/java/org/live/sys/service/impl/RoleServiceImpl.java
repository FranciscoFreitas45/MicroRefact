package org.live.sys.service.impl;
 import org.live.common.base.BaseRepository;
import org.live.common.base.BaseServiceImpl;
import org.live.common.exception.ServiceException;
import org.live.sys.entity.Role;
import org.live.sys.repository.RoleRepository;
import org.live.sys.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
@Service("roleService")
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseServiceImpl<Role, String>implements RoleService{

@Resource
 private  RoleRepository roleRepo;


@Override
public Integer createSerialNo(){
    Integer csn;
    // 获得serialNo最大值
    csn = this.roleRepo.getMaxSerialNo();
    if (csn != null) {
        // 非空则最大值+1,空则为1
        csn = csn + 1;
    } else {
        csn = 1;
    }
    return csn;
}


@Override
public BaseRepository<Role,String> getRepository(){
    return this.roleRepo;
}


@Override
public List<Role> findRoleBySerialNo(int serialNo){
    List<Role> roles = null;
    try {
        roles = this.roleRepo.findRoleBySerialNo(serialNo);
    } catch (Exception e) {
        throw new ServiceException(e);
    }
    return roles;
}


@Override
public boolean isExistSerialNo(int serialNo){
    List<Role> roles = this.findRoleBySerialNo(serialNo);
    return roles != null && roles.size() > 0;
}


@Override
public Page<Role> findRoles(Pageable pageable,Role role){
    return this.roleRepo.findRoles(pageable, role);
}


}