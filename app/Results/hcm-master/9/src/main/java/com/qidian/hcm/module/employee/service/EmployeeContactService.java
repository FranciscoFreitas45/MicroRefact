package com.qidian.hcm.module.employee.service;
 import com.qidian.hcm.module.employee.dto.EmployeeContactDTO;
import com.qidian.hcm.module.employee.entity.EmployeeContact;
import com.qidian.hcm.module.employee.repository.EmployeeContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
@Service
@Slf4j
public class EmployeeContactService {

@Autowired
 private  EmployeeContactRepository employeeContactRepository;


@Transactional
public void saveEmployeeContact(Long employeeId,List<EmployeeContactDTO> employeeContactDTOS){
    employeeContactRepository.deleteAllByEmployeeId(employeeId);
    // contacts表操作
    List<EmployeeContact> employeeContactList = newArrayListWithExpectedSize(employeeContactDTOS.size());
    for (EmployeeContactDTO contact : employeeContactDTOS) {
        EmployeeContact employeeContact = new EmployeeContact();
        employeeContact.setEmployeeId(employeeId);
        BeanUtils.copyProperties(contact, employeeContact);
        employeeContactList.add(employeeContact);
    }
    employeeContactRepository.saveAll(employeeContactList);
}


public List<EmployeeContactDTO> getEmployeeContactDTOList(Long employeeId){
    List<EmployeeContact> employeeContactList = employeeContactRepository.findAllByEmployeeId(employeeId);
    List<EmployeeContactDTO> employeeContactDTOList = newArrayListWithExpectedSize(employeeContactList.size());
    for (EmployeeContact employeeContact : employeeContactList) {
        EmployeeContactDTO employeeContactDTO = new EmployeeContactDTO();
        BeanUtils.copyProperties(employeeContact, employeeContactDTO);
        employeeContactDTOList.add(employeeContactDTO);
    }
    return employeeContactDTOList;
}


}