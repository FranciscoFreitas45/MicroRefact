package com.qidian.hcm.module.employee.service;
 import com.qidian.hcm.module.employee.dto.EmployeeHistoryDTO;
import com.qidian.hcm.module.employee.entity.EmployeeHistory;
import com.qidian.hcm.module.employee.repository.EmployeeHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors.toList;
@Service
@Slf4j
public class EmployeeHistoryService {

@Autowired
 private  EmployeeHistoryRepository employeeHistoryRepository;


public void saveEmployeeHistory(Long employeeId,String content,Date date,String remark){
    EmployeeHistory employeeHistory = new EmployeeHistory(employeeId, content, date, remark);
    employeeHistoryRepository.save(employeeHistory);
}


public List<EmployeeHistoryDTO> findEmployeeHistory(Long id){
    List<EmployeeHistory> employeeHistories = employeeHistoryRepository.findAllByEmployeeIdOrderByCreatedDateDesc(id);
    return employeeHistories.stream().map(employeeHistory -> new EmployeeHistoryDTO(employeeHistory.getId(), employeeHistory.getContent(), employeeHistory.getRemark(), employeeHistory.getCreatedDate())).collect(toList());
}


}