package com.qidian.hcm.module.employee.service;
 import com.alibaba.fastjson.JSONObject;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.employee.dto.EmployeeContractDTO;
import com.qidian.hcm.module.employee.entity.EmployeeContract;
import com.qidian.hcm.module.employee.repository.EmployeeContractRepository;
import com.qidian.hcm.module.employee.request.ResignEmployeeRequest;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
@Service
@Slf4j
public class EmployeeContractService {

@Autowired
 private  EmployeeContractRepository employeeContractRepository;


public List<EmployeeContract> findAllByEmployeeId(Long employeeId){
    return employeeContractRepository.findAllByEmployeeId(employeeId);
}


public void updateContractProbationEndDate(Long employeeId,Date qualifyDate){
    // 更新合同中的转正日期
    List<EmployeeContract> contracts = employeeContractRepository.findAllByEmployeeId(employeeId);
    EmployeeContract employeeContract = contracts.stream().filter(ec -> qualifyDate.compareTo(ec.getStartDate()) >= 0 && qualifyDate.compareTo(ec.getEndDate()) <= 0).findFirst().orElseThrow(() -> new BizException(ResultCode.NOT_MATCH_QUALIFY_CONTRACT));
    DateTime date = new DateTime(qualifyDate);
    employeeContract.setProbationEndDate(date.minusDays(1).toDate());
    employeeContractRepository.save(employeeContract);
}


@Transactional
public void saveEmployeeContract(Long employeeId,List<EmployeeContractDTO> employeeContractDTOList){
    employeeContractRepository.deleteAllByEmployeeId(employeeId);
    // contract表操作
    List<EmployeeContract> employeeContractList = newArrayListWithExpectedSize(employeeContractDTOList.size());
    for (EmployeeContractDTO contract : employeeContractDTOList) {
        EmployeeContract employeeContract = new EmployeeContract();
        employeeContract.setEmployeeId(employeeId);
        employeeContract.setCustomizedField(JSONObject.toJSONString(contract.getCustomizedFields()));
        BeanUtils.copyProperties(contract, employeeContract);
        employeeContractList.add(employeeContract);
    }
    employeeContractRepository.saveAll(employeeContractList);
}


public void saveEmployeeContractForResignation(ResignEmployeeRequest resignEmployeeRequest,Long id){
    Optional<EmployeeContract> employeeContractOptional = employeeContractRepository.findById(id);
    if (employeeContractOptional.isPresent()) {
        EmployeeContract employeeContract = employeeContractOptional.get();
        employeeContract.setEndDate(resignEmployeeRequest.getDate());
        employeeContractRepository.save(employeeContract);
    }
}


public List<EmployeeContractDTO> getEmployeeContractDTOList(Long employeeId){
    List<EmployeeContract> employeeContractList = findAllByEmployeeId(employeeId);
    List<EmployeeContractDTO> employeeContractDTOList = newArrayListWithExpectedSize(employeeContractList.size());
    for (EmployeeContract employeeContract : employeeContractList) {
        EmployeeContractDTO employeeContractDTO = new EmployeeContractDTO();
        employeeContractDTO.setCustomizedFields(JSONObject.parseObject(employeeContract.getCustomizedField()));
        BeanUtils.copyProperties(employeeContract, employeeContractDTO);
        employeeContractDTOList.add(employeeContractDTO);
    }
    return employeeContractDTOList;
}


}