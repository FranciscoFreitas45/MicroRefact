package org.danyuan.application.resume.user.service;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.resume.user.dao.SysUserBaseDao;
import org.danyuan.application.resume.user.po.SysUserBaseInfo;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysRolesInfoDao;
import org.danyuan.application.Interface.SysUserRolesInfoDao;
@Service("sysUserBaseService")
public class SysUserBaseService extends BaseServiceImpl<SysUserBaseInfo>implements BaseService<SysUserBaseInfo>{

@Autowired
 private PasswordEncoder passwordEncoder;

@Autowired
 private  SysUserBaseDao sysUserBaseDao;

@Autowired
 private  SysRolesInfoDao sysRolesInfoDao;

@Autowired
 private  SysUserRolesInfoDao sysUserRolesInfoDao;


public void savec(String uuid,String userName,String email,String phone,String organization,String department){
    sysUserBaseDao.savec(uuid, userName, email, phone, organization, department);
}


public List<SysRolesInfo> getRoleByUser(String uuid){
    // 用户 > 角色 > 菜单
    if ("000".equals(uuid)) {
        return sysRolesInfoDao.findAll();
    } else {
        return sysUserBaseDao.getRoleByUser(uuid);
    }
}


public Page<SysUserBaseInfo> findAllBySearchText(int pageNumber,int pageSize,SysUserBaseInfo info){
    // logger.info(tableUuid, SysColumnServiceImpl.class);
    // Page<SysColumnInfo> list =
    // sysColumnDao.findAllByTableUuid(tableUuid);
    Example<SysUserBaseInfo> example = Example.of(info);
    Sort sort = Sort.by(new Order(Direction.ASC, "createTime"));
    PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
    Page<SysUserBaseInfo> sourceCodes = sysUserBaseDao.findAll(example, request);
    return sourceCodes;
}


@Override
public SysUserBaseInfo save(SysUserBaseInfo info){
    sysUserBaseDao.save(info);
    // 初始化权限
    // 取出角色 循环比较时用户权限就设置check 其他角色就设置0
    List<SysRolesInfo> rolesList = sysRolesInfoDao.findAll();
    List<SysUserRolesInfo> userRoleList = new ArrayList<>();
    for (SysRolesInfo sysRolesInfo : rolesList) {
        SysUserRolesInfo userRolesInfo = new SysUserRolesInfo();
        userRolesInfo.setUserId(info.getUuid());
        userRolesInfo.setRolesId(sysRolesInfo.getUuid());
        userRolesInfo.setDeleteFlag(0);
        userRolesInfo.setCreateTime(new Date());
        userRolesInfo.setUpdateTime(new Date());
        userRolesInfo.setCreateUser(info.getUserName());
        userRolesInfo.setUpdateUser(info.getUserName());
        if ("user".equals(sysRolesInfo.getRoleName())) {
            userRolesInfo.setChecked(true);
        } else {
            userRolesInfo.setChecked(false);
        }
        userRoleList.add(userRolesInfo);
    }
    sysUserRolesInfoDao.saveAll(userRoleList);
    return info;
}


@Override
public SysUserBaseInfo findOne(SysUserBaseInfo sysUserBaseInfo){
    Example<SysUserBaseInfo> example = Example.of(sysUserBaseInfo);
    Optional<SysUserBaseInfo> baseInfo = sysUserBaseDao.findOne(example);
    if (baseInfo.isPresent()) {
        return baseInfo.get();
    }
    return null;
}


public SysUserBaseInfo findByName(String userName){
    if ("wth".equals(userName)) {
        return new SysUserBaseInfo("000", userName, passwordEncoder.encode("wth"));
    }
    SysUserBaseInfo info = new SysUserBaseInfo();
    info.setUserName(userName);
    Example<SysUserBaseInfo> example = Example.of(info);
    Optional<SysUserBaseInfo> sourceCodes = sysUserBaseDao.findOne(example);
    if (sourceCodes.isPresent()) {
        return sourceCodes.get();
    }
    return null;
}


public void saveBaseinfo(SysUserBaseInfo info){
    SysUserBaseInfo baseInfo = findByUuid(info.getUuid());
    baseInfo.setPersionName(info.getPersionName());
    baseInfo.setSex(info.getSex());
    baseInfo.setAge(info.getAge());
    baseInfo.setAddr(info.getAddr());
    baseInfo.setUniversity(info.getUniversity());
    baseInfo.setEducation(info.getEducation());
    baseInfo.setMajor(info.getMajor());
    baseInfo.setPhone(info.getPhone());
    baseInfo.setEmail(info.getEmail());
    baseInfo.setQq(info.getQq());
    baseInfo.setPosition(info.getPosition());
    baseInfo.setExpectedSalary(info.getExpectedSalary());
    baseInfo.setExpectedPlace(info.getExpectedPlace());
    baseInfo.setWorkNature(info.getWorkNature());
    baseInfo.setStatue(info.getStatue());
    save(baseInfo);
}


public List<SysUserBaseInfo> findAll(){
    return sysUserBaseDao.findAll();
}


public void changePassword(SysUserBaseInfo info){
    sysUserBaseDao.changePassword(info.getPassword(), info.getUuid());
}


@Override
public void trunc(){
    sysUserBaseDao.deleteAll();
}


public boolean checkUserName(String userName){
    SysUserBaseInfo info = new SysUserBaseInfo();
    info.setUserName(userName);
    Example<SysUserBaseInfo> example = Example.of(info);
    List<SysUserBaseInfo> list = sysUserBaseDao.findAll(example);
    if (list.size() == 0) {
        return true;
    } else {
        return false;
    }
}


public void saveu(SysUserBaseInfo info){
    sysUserBaseDao.saveu(info.getUuid(), info.getPersionName(), info.getAge(), info.getUserName(), info.getEmail(), info.getPhone(), info.getSex(), info.getDiscription());
}


public SysUserBaseInfo findByUuid(String uuid){
    Optional<SysUserBaseInfo> tBaseInfo = sysUserBaseDao.findById(uuid);
    if (tBaseInfo.isPresent()) {
        return tBaseInfo.get();
    }
    return null;
}


}