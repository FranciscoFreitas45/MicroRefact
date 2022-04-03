package com.qidian.hcm.module.employee.service;
 import com.qidian.hcm.module.employee.dto.EmployeeEmergencyContactDTO;
import com.qidian.hcm.module.employee.entity.EmployeeEmergencyContact;
import com.qidian.hcm.module.employee.repository.EmployeeEmergencyContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
@Service
@Slf4j
public class EmployeeEmergencyContactService {

@Autowired
 private  EmployeeEmergencyContactRepository employeeEmergencyContactRepository;


public List<EmployeeEmergencyContactDTO> getEmergencyContactsDTOList(Long employeeId){
    List<EmployeeEmergencyContact> employeeEmergencyContactList = employeeEmergencyContactRepository.findAllByEmployeeId(employeeId);
    List<EmployeeEmergencyContactDTO> emergencyContacts = newArrayListWithExpectedSize(employeeEmergencyContactList.size());
    for (EmployeeEmergencyContact employeeEmergencyContact : employeeEmergencyContactList) {
        EmployeeEmergencyContactDTO employeeEmergencyContactDTO = new EmployeeEmergencyContactDTO();
        BeanUtils.copyProperties(employeeEmergencyContact, employeeEmergencyContactDTO);
        emergencyContacts.add(employeeEmergencyContactDTO);
    }
    return emergencyContacts;
}


@Transactional
public void saveEmployeeEmergencyContact(Long employeeId,List<EmployeeEmergencyContactDTO> employeeEmergencyContactDTOList){
    employeeEmergencyContactRepository.deleteAllByEmployeeId(employeeId);
    // emergencyContact表操作
    List<EmployeeEmergencyContact> employeeEmergencyContactList = newArrayListWithExpectedSize(employeeEmergencyContactDTOList.size());
    for (EmployeeEmergencyContactDTO emergencyContact : employeeEmergencyContactDTOList) {
        EmployeeEmergencyContact employeeEmergencyContact = new EmployeeEmergencyContact();
        employeeEmergencyContact.setEmployeeId(employeeId);
        BeanUtils.copyProperties(emergencyContact, employeeEmergencyContact);
        employeeEmergencyContactList.add(employeeEmergencyContact);
    }
    employeeEmergencyContactRepository.saveAll(employeeEmergencyContactList);
}


}