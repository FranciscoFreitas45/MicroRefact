package com.qidian.hcm.module.employee.service;
 import com.qidian.hcm.module.employee.dto.EmployeeWorkExperienceDTO;
import com.qidian.hcm.module.employee.entity.EmployeeWorkExperience;
import com.qidian.hcm.module.employee.repository.EmployeeWorkExperienceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
@Service
@Slf4j
public class EmployeeWorkExperienceService {

@Autowired
 private  EmployeeWorkExperienceRepository employeeWorkExperienceRepository;


@Transactional
public void saveEmployeeWorkExperience(Long employeeId,List<EmployeeWorkExperienceDTO> employeeWorkExperienceDTOList){
    employeeWorkExperienceRepository.deleteAllByEmployeeId(employeeId);
    // workExperience表操作
    List<EmployeeWorkExperience> employeeWorkExperienceList = newArrayListWithExpectedSize(employeeWorkExperienceDTOList.size());
    for (EmployeeWorkExperienceDTO workExperience : employeeWorkExperienceDTOList) {
        EmployeeWorkExperience employeeWorkExperience = new EmployeeWorkExperience();
        employeeWorkExperience.setEmployeeId(employeeId);
        BeanUtils.copyProperties(workExperience, employeeWorkExperience);
        employeeWorkExperienceList.add(employeeWorkExperience);
    }
    employeeWorkExperienceRepository.saveAll(employeeWorkExperienceList);
}


public List<EmployeeWorkExperienceDTO> getEmployeeWorkExperienceDTOList(Long employeeId){
    List<EmployeeWorkExperience> employeeWorkExperienceList = employeeWorkExperienceRepository.findAllByEmployeeId(employeeId);
    List<EmployeeWorkExperienceDTO> employeeWorkExperienceDTOList = newArrayListWithExpectedSize(employeeWorkExperienceList.size());
    for (EmployeeWorkExperience workExperience : employeeWorkExperienceList) {
        EmployeeWorkExperienceDTO employeeWorkExperienceDTO = new EmployeeWorkExperienceDTO();
        BeanUtils.copyProperties(workExperience, employeeWorkExperienceDTO);
        employeeWorkExperienceDTOList.add(employeeWorkExperienceDTO);
    }
    return employeeWorkExperienceDTOList;
}


}