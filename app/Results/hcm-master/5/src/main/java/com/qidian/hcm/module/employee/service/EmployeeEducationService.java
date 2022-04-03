package com.qidian.hcm.module.employee.service;
 import com.alibaba.fastjson.JSONObject;
import com.qidian.hcm.module.employee.dto.EmployeeEducationDTO;
import com.qidian.hcm.module.employee.entity.EmployeeEducation;
import com.qidian.hcm.module.employee.repository.EmployeeEducationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
@Service
@Slf4j
public class EmployeeEducationService {

@Autowired
 private  EmployeeEducationRepository employeeEducationRepository;


@Transactional
public void saveEmployeeEducation(Long employeeId,List<EmployeeEducationDTO> employeeEducationDTOList){
    employeeEducationRepository.deleteAllByEmployeeId(employeeId);
    // education表操作
    List<EmployeeEducation> employeeEducationList = newArrayListWithExpectedSize(employeeEducationDTOList.size());
    for (EmployeeEducationDTO education : employeeEducationDTOList) {
        EmployeeEducation employeeEducation = new EmployeeEducation();
        employeeEducation.setEmployeeId(employeeId);
        BeanUtils.copyProperties(education, employeeEducation);
        employeeEducation.setCustomizedField(JSONObject.toJSONString(education.getCustomizedFields()));
        employeeEducationList.add(employeeEducation);
    }
    employeeEducationRepository.saveAll(employeeEducationList);
}


public List<EmployeeEducationDTO> getEducationDTOList(Long employeeId){
    List<EmployeeEducation> employeeEducationList = employeeEducationRepository.findAllByEmployeeId(employeeId);
    List<EmployeeEducationDTO> educations = newArrayListWithExpectedSize(employeeEducationList.size());
    for (EmployeeEducation employeeEducation : employeeEducationList) {
        EmployeeEducationDTO employeeEducationDTO = new EmployeeEducationDTO();
        BeanUtils.copyProperties(employeeEducation, employeeEducationDTO);
        employeeEducationDTO.setCustomizedFields(JSONObject.parseObject(employeeEducation.getCustomizedField()));
        educations.add(employeeEducationDTO);
    }
    return educations;
}


}