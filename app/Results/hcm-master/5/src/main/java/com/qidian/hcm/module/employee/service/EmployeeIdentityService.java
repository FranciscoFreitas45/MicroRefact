package com.qidian.hcm.module.employee.service;
 import com.alibaba.fastjson.JSONObject;
import com.qidian.hcm.module.employee.dto.EmployeeIdentityDTO;
import com.qidian.hcm.module.employee.entity.EmployeeIdentity;
import com.qidian.hcm.module.employee.repository.EmployeeIdentityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
@Service
@Slf4j
public class EmployeeIdentityService {

@Autowired
 private  EmployeeIdentityRepository employeeIdentityRepository;


public List<EmployeeIdentity> findAllByEmployeeId(Long employeeId){
    return employeeIdentityRepository.findAllByEmployeeId(employeeId);
}


@Transactional
public void saveEmployeeIdentity(Long employeeId,List<EmployeeIdentityDTO> employeeIdentityDTOList){
    employeeIdentityRepository.deleteAllByEmployeeId(employeeId);
    // identity表操作
    List<EmployeeIdentity> employeeIdentityList = newArrayListWithExpectedSize(employeeIdentityDTOList.size());
    for (EmployeeIdentityDTO identity : employeeIdentityDTOList) {
        EmployeeIdentity employeeIdentity = new EmployeeIdentity();
        employeeIdentity.setEmployeeId(employeeId);
        BeanUtils.copyProperties(identity, employeeIdentity);
        employeeIdentity.setCustomizedField(JSONObject.toJSONString(identity.getCustomizedFields()));
        employeeIdentityList.add(employeeIdentity);
    }
    employeeIdentityRepository.saveAll(employeeIdentityList);
}


public List<EmployeeIdentityDTO> getEmployeeIdentityDTOList(Long employeeId){
    List<EmployeeIdentity> employeeIdentityList = findAllByEmployeeId(employeeId);
    List<EmployeeIdentityDTO> employeeIdentityDTOList = newArrayListWithExpectedSize(employeeIdentityList.size());
    for (EmployeeIdentity identity : employeeIdentityList) {
        EmployeeIdentityDTO employeeIdentityDTO = new EmployeeIdentityDTO();
        BeanUtils.copyProperties(identity, employeeIdentityDTO);
        employeeIdentityDTO.setCustomizedFields(JSONObject.parseObject(identity.getCustomizedField()));
        employeeIdentityDTOList.add(employeeIdentityDTO);
    }
    return employeeIdentityDTOList;
}


}