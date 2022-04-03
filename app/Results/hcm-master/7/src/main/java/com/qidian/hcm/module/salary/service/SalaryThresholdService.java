package com.qidian.hcm.module.salary.service;
 import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.salary.entity.EmployeeFinancial;
import com.qidian.hcm.module.salary.entity.SalaryThreshold;
import com.qidian.hcm.module.salary.repository.EmployeeFinancialRepository;
import com.qidian.hcm.module.salary.repository.SalaryThresholdRepository;
import com.qidian.hcm.module.salary.request.SalaryThresholdRequest;
import com.qidian.hcm.module.salary.response.SalaryThresholdResponse;
import com.qidian.hcm.module.salary.utils.SalaryFormulaIKeyword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.qidian.hcm.Interface.CalculateService;
@Slf4j
@Service
public class SalaryThresholdService {

@Autowired
 private  SalaryThresholdRepository salaryThresholdRepository;

@Autowired
 private  EmployeeFinancialRepository employeeFinancialRepository;

@Autowired
 private  CalculateService calculateService;


@Transactional
public void updateThreshold(Long id,SalaryThresholdRequest salaryThresholdRequest){
    Optional<SalaryThreshold> salaryThresholdOptional = salaryThresholdRepository.findById(id);
    SalaryThreshold salaryThreshold = salaryThresholdOptional.orElseThrow(() -> new BizException(ResultCode.SALARY_THRESHOLD_NOT_EXISTS));
    salaryThreshold.setPoint(salaryThresholdRequest.getPoint());
    salaryThresholdRepository.save(salaryThreshold);
    // 计算起征点变化的员工薪资项
    List<EmployeeFinancial> employeeFinancialList = employeeFinancialRepository.findByThresholdId(id);
    if (!CollectionUtils.isEmpty(employeeFinancialList)) {
        calculateService.formulaCalculate(employeeFinancialList.stream().map(EmployeeFinancial::getEmployeeId).collect(Collectors.toList()), SalaryFormulaIKeyword.THRESHOLD);
    }
}


public List<SalaryThresholdResponse> getSalaryThreshold(){
    List<SalaryThreshold> allSalaryThreshold = salaryThresholdRepository.findAll();
    return allSalaryThreshold.stream().map(e -> {
        SalaryThresholdResponse salaryThresholdResponse = new SalaryThresholdResponse();
        BeanUtils.copyProperties(e, salaryThresholdResponse);
        return salaryThresholdResponse;
    }).collect(Collectors.toList());
}


}