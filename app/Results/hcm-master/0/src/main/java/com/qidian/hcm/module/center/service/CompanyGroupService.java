package com.qidian.hcm.module.center.service;
 import com.qidian.hcm.module.center.entity.CompanyGroup;
import com.qidian.hcm.module.center.repository.CompanyGroupRepository;
import com.qidian.hcm.module.center.request.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Slf4j
public class CompanyGroupService {

@Autowired
 private  CompanyGroupRepository companyGroupRepository;

@Autowired
 private  GroupConfigService groupConfigService;


@Transactional
public CompanyGroup createGroup(RegisterRequest request,String tenantName){
    CompanyGroup companyGroup = new CompanyGroup();
    companyGroup.setName(request.getGroupName());
    companyGroup = companyGroupRepository.save(companyGroup);
    groupConfigService.createGroupConfig(companyGroup.getId(), tenantName);
    return companyGroup;
}


}