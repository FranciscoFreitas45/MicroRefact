package com.uec.imonitor.user.service.impl;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.user.bean.OrgEntity;
import com.uec.imonitor.user.bean.OrgUserEntity;
import com.uec.imonitor.user.dao.IOrgJPARepository;
import com.uec.imonitor.user.dao.IOrgUserJPARepository;
import com.uec.imonitor.user.service.IOrgService;
@Service("orgService")
@Transactional(value = "transactionManager")
public class OrgServiceImpl implements IOrgService{

@Autowired
 private  IOrgJPARepository orgRepository;

@Autowired
 private  IOrgUserJPARepository orgUserRepository;


@Override
public OrgUserEntity addOrgUser(OrgUserEntity orgUser){
    OrgUserEntity entity = orgUserRepository.save(orgUser);
    return entity;
}


@Override
public OrgUserEntity updateOrgUser(OrgUserEntity orgUser){
    OrgUserEntity entity = orgUserRepository.save(orgUser);
    return entity;
}


@Override
public OrgEntity addOrg(OrgEntity org){
    OrgEntity entity = orgRepository.save(org);
    return entity;
}


@Override
public OrgEntity findOrgById(Integer id){
    OrgEntity entity = orgRepository.findOne(id);
    return entity;
}


@Override
public void deleteOrg(Integer id){
    orgRepository.delete(id);
}


@Override
public OrgEntity updateOrg(OrgEntity org){
    OrgEntity entity = orgRepository.save(org);
    return entity;
}


@Override
public List<OrgUserEntity> findOrgUserByOrgId(Integer orgId){
    List<OrgUserEntity> list = orgUserRepository.findByOrgId(orgId);
    return list;
}


@Override
public OrgUserEntity findOrgUserById(Integer id){
    OrgUserEntity entity = orgUserRepository.findOne(id);
    return entity;
}


@Override
public void deleteOrgUser(Integer id){
    orgUserRepository.delete(id);
}


@Override
public List<OrgUserEntity> findOrgUserByUserId(Integer userId){
    List<OrgUserEntity> list = orgUserRepository.findByUserId(userId);
    return list;
}


}