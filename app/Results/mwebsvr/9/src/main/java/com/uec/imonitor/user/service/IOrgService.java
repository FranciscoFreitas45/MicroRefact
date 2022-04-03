package com.uec.imonitor.user.service;
 import java.util.List;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.user.bean.OrgEntity;
import com.uec.imonitor.user.bean.OrgUserEntity;
public interface IOrgService {


public OrgUserEntity addOrgUser(OrgUserEntity orgUser)
;

public OrgUserEntity updateOrgUser(OrgUserEntity orgUser)
;

public OrgEntity addOrg(OrgEntity org)
;

public OrgEntity findOrgById(Integer id)
;

public void deleteOrg(Integer id)
;

public OrgEntity updateOrg(OrgEntity org)
;

public List<OrgUserEntity> findOrgUserByOrgId(Integer orgId)
;

public OrgUserEntity findOrgUserById(Integer id)
;

public void deleteOrgUser(Integer id)
;

public List<OrgUserEntity> findOrgUserByUserId(Integer userId)
;

}