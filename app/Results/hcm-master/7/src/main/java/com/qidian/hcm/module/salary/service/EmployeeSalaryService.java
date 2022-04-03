package com.qidian.hcm.module.salary.service;
 import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.center.service.FileService;
import com.qidian.hcm.module.employee.entity.Employee;
import com.qidian.hcm.module.employee.entity.EmployeePosition;
import com.qidian.hcm.module.employee.repository.EmployeeRepository;
import com.qidian.hcm.module.employee.service.EmployeePositionService;
import com.qidian.hcm.module.organization.entity.GradeEntity;
import com.qidian.hcm.module.organization.entity.OrganizationEntity;
import com.qidian.hcm.module.organization.entity.PositionEntity;
import com.qidian.hcm.module.salary.dto.BankInfoDTO;
import com.qidian.hcm.module.salary.entity;
import com.qidian.hcm.module.salary.enums.SalaryAjustType;
import com.qidian.hcm.module.salary.repository;
import com.qidian.hcm.module.salary.request;
import com.qidian.hcm.module.salary.response;
import com.qidian.hcm.module.salary.utils.SalaryFormulaIKeyword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.google.common.collect.Lists.newArrayList;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
import com.qidian.hcm.Interface.SalaryItemRepository;
import com.qidian.hcm.Interface.SocialSecurityPlanRepository;
import com.qidian.hcm.Interface.EmployeeRepository;
import com.qidian.hcm.Interface.FileService;
import com.qidian.hcm.Interface.CalculateService;
import com.qidian.hcm.Interface.EmployeePositionService;
@Slf4j
@Service
public class EmployeeSalaryService {

@Autowired
 private  SalaryHistoryRepository salaryHistoryRepository;

@Autowired
 private  EmployeeBankInfoRepository employeeBankInfoRepository;

@Autowired
 private  SalaryThresholdRepository salaryThresholdRepository;

@Autowired
 private  SalaryItemRepository salaryItemRepository;

@Autowired
 private  EmployeeFinancialRepository employeeFinancialRepository;

@Autowired
 private  SocialSecurityPlanRepository socialSecurityPlanRepository;

@Autowired
 private  HousingFundPlanRepository housingFundPlanRepository;

@Autowired
 private  EmployeeRepository employeeRepository;

@Autowired
 private  FileService fileService;

@Autowired
 private  CalculateService calculateService;

@Autowired
 private  EmployeePositionService employeePositionService;


@Transactional
public void adjustSalary(Long employeeId,SalaryAdjustRequest salaryAdjustRequest){
    validateOrGetEmployee(employeeId);
    EmployeeFinancial employeeFinancial = employeeFinancialRepository.findByEmployeeId(employeeId).orElseThrow(() -> new BizException(ResultCode.SOCIAL_SECURITY_NOT_EXISTS));
    double beforeAdjust = employeeFinancial.getSalary();
    double afterAdjust = salaryAdjustRequest.getSalary();
    employeeFinancial.setSalary(afterAdjust);
    employeeFinancial.setSalaryAdjustDate(new Date());
    employeeFinancialRepository.save(employeeFinancial);
    // 保存薪酬历史记录
    SalaryHistory salaryHistory = new SalaryHistory();
    salaryHistory.setBeforeAdjust(beforeAdjust);
    salaryHistory.setAfterAdjust(afterAdjust);
    salaryHistory.setEmployeeId(employeeId);
    salaryHistory.setRemark(salaryAdjustRequest.getRemark());
    salaryHistory.setType(SalaryAjustType.basic);
    salaryHistory.setIncreased(getPercent(afterAdjust - beforeAdjust) / beforeAdjust);
    salaryHistoryRepository.save(salaryHistory);
    salaryCalculate(employeeId);
}


@Transactional
public void editEmployeeThreshold(Long employeeId,EmployeeThresholdRequest employeeThresholdRequest){
    validateOrGetEmployee(employeeId);
    Optional<EmployeeFinancial> financialOptional = employeeFinancialRepository.findByEmployeeId(employeeId);
    EmployeeFinancial employeeFinancial;
    if (financialOptional.isPresent()) {
        employeeFinancial = financialOptional.get();
    } else {
        employeeFinancial = new EmployeeFinancial();
        employeeFinancial.setEmployeeId(employeeId);
    }
    employeeFinancial.setThresholdId(employeeThresholdRequest.getId());
    employeeFinancialRepository.save(employeeFinancial);
    calculateService.formulaCalculate(newArrayList(employeeId), SalaryFormulaIKeyword.THRESHOLD);
}


public Employee validateOrGetEmployee(Long employeeId){
    return employeeRepository.findById(employeeId).orElseThrow(() -> new BizException(ResultCode.EMPLOYEE_NOT_EXISTS));
}


public EmployeeSalaryDetailResponse getEmployeeSalaryInfo(Long employeeId){
    validateOrGetEmployee(employeeId);
    Optional<EmployeeFinancial> financialOptional = employeeFinancialRepository.findByEmployeeId(employeeId);
    EmployeeSalaryDetailResponse employeeSalaryDetailResponse = new EmployeeSalaryDetailResponse();
    SalaryThresholdResponse salaryThresholdResponse = new SalaryThresholdResponse();
    employeeSalaryDetailResponse.setThreshold(salaryThresholdResponse);
    BankInfoDTO bankInfoDTO = new BankInfoDTO();
    employeeSalaryDetailResponse.setBankInfo(bankInfoDTO);
    if (!financialOptional.isPresent()) {
        return employeeSalaryDetailResponse;
    }
    // 起征点信息设置
    EmployeeFinancial employeefinancial = financialOptional.get();
    if (Objects.nonNull(employeefinancial.getThresholdId())) {
        Optional<SalaryThreshold> salaryThresholdOptional = salaryThresholdRepository.findById(employeefinancial.getThresholdId());
        salaryThresholdOptional.ifPresent(salaryThreshold -> {
            BeanUtils.copyProperties(salaryThreshold, salaryThresholdResponse);
        });
    }
    // 银行卡信息设置
    Optional<EmployeeBankInfo> employeeBankInfoOptional = employeeBankInfoRepository.findByEmployeeId(employeeId);
    employeeBankInfoOptional.ifPresent(employeeBankInfo -> {
        BeanUtils.copyProperties(employeeBankInfo, bankInfoDTO);
    });
    return employeeSalaryDetailResponse;
}


public List<EmployeeSalaryHistoryResponse> getSalaryHistory(Long employeeId){
    validateOrGetEmployee(employeeId);
    List<SalaryHistory> salaryHistoryList = salaryHistoryRepository.findByEmployeeId(employeeId);
    List<EmployeeSalaryHistoryResponse> salaryHistoryResponses = newArrayListWithExpectedSize(salaryHistoryList.size());
    salaryHistoryList.forEach(salaryHistory -> {
        EmployeeSalaryHistoryResponse employeeSalaryHistoryResponse = new EmployeeSalaryHistoryResponse();
        BeanUtils.copyProperties(salaryHistory, employeeSalaryHistoryResponse);
        salaryHistoryResponses.add(employeeSalaryHistoryResponse);
    });
    return salaryHistoryResponses;
}


public void salaryCalculate(Long employeeId){
    SalaryItem salaryItem = salaryItemRepository.findByCode(SalaryFormulaIKeyword.SALARY_CODE).orElseThrow(() -> new BizException(ResultCode.SALARY_ITEM_CODE_NOT_EXISTS));
    calculateService.formulaCalculate(employeeId, newArrayList(salaryItem), Boolean.TRUE);
}


@Transactional
public void editEmployeeFundPlanInfo(Long employeeId,EmployeeHousingFundPlanRequest employeeHousingFundPlan){
    validateOrGetEmployee(employeeId);
    housingFundPlanRepository.findById(employeeHousingFundPlan.getHousingFundPlanId()).orElseThrow(() -> new BizException(ResultCode.HOUSING_FUND_NOT_EXISTS));
    Optional<EmployeeFinancial> financialOptional = employeeFinancialRepository.findByEmployeeId(employeeId);
    EmployeeFinancial employeeFinancial;
    if (financialOptional.isPresent()) {
        employeeFinancial = financialOptional.get();
    } else {
        employeeFinancial = new EmployeeFinancial();
        employeeFinancial.setEmployeeId(employeeId);
    }
    employeeFinancial.setHousingFundAccount(employeeHousingFundPlan.getAccount());
    employeeFinancial.setHousingFundBase(employeeHousingFundPlan.getHousingFundBase());
    employeeFinancial.setHousingFundPlanId(employeeHousingFundPlan.getHousingFundPlanId());
    employeeFinancialRepository.save(employeeFinancial);
    calculateService.formulaCalculate(newArrayList(employeeId), SalaryFormulaIKeyword.HOUSING_FUND);
}


@Transactional
public void editSalary(Long employeeId,SalaryEditRequest salaryAdjustRequest){
    validateOrGetEmployee(employeeId);
    Optional<EmployeeFinancial> financialOptional = employeeFinancialRepository.findByEmployeeId(employeeId);
    EmployeeFinancial employeeFinancial;
    if (financialOptional.isPresent()) {
        employeeFinancial = financialOptional.get();
    } else {
        employeeFinancial = new EmployeeFinancial();
        employeeFinancial.setEmployeeId(employeeId);
    }
    employeeFinancial.setSalary(salaryAdjustRequest.getSalary());
    employeeFinancialRepository.save(employeeFinancial);
    salaryCalculate(employeeId);
}


public Double getPercent(Double value){
    return value * 100;
}


public void updateBankInfo(Long employeeId,BankInfoDTO bankInfoDTO){
    validateOrGetEmployee(employeeId);
    Optional<EmployeeBankInfo> employeeBankInfoOptional = employeeBankInfoRepository.findByEmployeeId(employeeId);
    EmployeeBankInfo bankInfo;
    if (employeeBankInfoOptional.isPresent()) {
        bankInfo = employeeBankInfoOptional.get();
    } else {
        bankInfo = new EmployeeBankInfo();
        bankInfo.setEmployeeId(employeeId);
    }
    BeanUtils.copyProperties(bankInfoDTO, bankInfo);
    employeeBankInfoRepository.save(bankInfo);
}


public EmployeeBasicInfoResponse getEmployeeBasicInfo(Long employeeId){
    Employee employee = validateOrGetEmployee(employeeId);
    EmployeeBasicInfoResponse employeeBasicInfoResponse = new EmployeeBasicInfoResponse();
    BeanUtils.copyProperties(employee, employeeBasicInfoResponse);
    EmployeePosition employeePosition = employeePositionService.getCurrentEmployeePosition(employeeId);
    if (Objects.nonNull(employeePosition)) {
        OrganizationEntity company = employeePosition.getCompany();
        if (Objects.nonNull(company)) {
            employeeBasicInfoResponse.setCompany(company.getName());
        }
        OrganizationEntity department = employeePosition.getDepartment();
        if (Objects.nonNull(department)) {
            employeeBasicInfoResponse.setDepartment(department.getName());
        }
        PositionEntity position = employeePosition.getPosition();
        if (Objects.nonNull(position)) {
            employeeBasicInfoResponse.setPosition(position.getName());
        }
        GradeEntity grade = employeePosition.getGrade();
        if (Objects.nonNull(grade)) {
            employeeBasicInfoResponse.setGrade(grade.getName());
        }
    }
    // 添加头像地址
    if (Objects.nonNull(employee.getAvatarFileId())) {
        employeeBasicInfoResponse.setAvatar(fileService.getAvatarImgUrl(employee.getAvatarFileId()));
    }
    Optional<EmployeeFinancial> financialOptional = employeeFinancialRepository.findByEmployeeId(employeeId);
    if (financialOptional.isPresent()) {
        employeeBasicInfoResponse.setSalary(financialOptional.get().getSalary());
    }
    return employeeBasicInfoResponse;
}


public EmployeeSecurityDetailResponse getEmployeeSecurityDetail(Long employeeId){
    validateOrGetEmployee(employeeId);
    Optional<EmployeeFinancial> financialOptional = employeeFinancialRepository.findByEmployeeId(employeeId);
    EmployeeSecurityDetailResponse employeeSecurityDetailResponse = new EmployeeSecurityDetailResponse();
    EmployeeSecurityPlanResponse employeeSecurityPlanResponse = new EmployeeSecurityPlanResponse();
    employeeSecurityDetailResponse.setEmployeeSecurityPlan(employeeSecurityPlanResponse);
    EmployeeHousingFundPlanResponse employeeHousingFundPlanResponse = new EmployeeHousingFundPlanResponse();
    employeeSecurityDetailResponse.setEmployeeHousingFundPlan(employeeHousingFundPlanResponse);
    if (!financialOptional.isPresent()) {
        return employeeSecurityDetailResponse;
    }
    EmployeeFinancial employeeFinancial = financialOptional.get();
    // 社保信息
    if (!StringUtils.isEmpty(employeeFinancial.getSocialSecurityPlanId())) {
        Optional<SocialSecurityPlan> socialSecurityPlanOptional = socialSecurityPlanRepository.findById(employeeFinancial.getSocialSecurityPlanId());
        socialSecurityPlanOptional.ifPresent(socialSecurityPlan -> {
            employeeSecurityPlanResponse.setSocialSecurityPlanId(socialSecurityPlan.getId());
            // 另外设置基数
            employeeSecurityPlanResponse.setSocialSecurityBase(employeeFinancial.getSocialSecurityBase());
        });
    }
    // 公积金
    if (!StringUtils.isEmpty(employeeFinancial.getHousingFundPlanId())) {
        Optional<HousingFundPlan> housingFundPlanOptional = housingFundPlanRepository.findById(employeeFinancial.getHousingFundPlanId());
        housingFundPlanOptional.ifPresent(housingFundPlan -> {
            // 另外设置账号和基数
            employeeHousingFundPlanResponse.setAccount(employeeFinancial.getHousingFundAccount());
            employeeHousingFundPlanResponse.setHousingFundBase(employeeFinancial.getHousingFundBase());
            employeeHousingFundPlanResponse.setHousingFundPlanId(housingFundPlan.getId());
        });
    }
    return employeeSecurityDetailResponse;
}


@Transactional
public void editEmployeeSecurity(Long employeeId,EmployeeSecurityPlanRequest employeeSecurityPlanRequest){
    validateOrGetEmployee(employeeId);
    socialSecurityPlanRepository.findById(employeeSecurityPlanRequest.getSocialSecurityPlanId()).orElseThrow(() -> new BizException(ResultCode.SOCIAL_SECURITY_NOT_EXISTS));
    Optional<EmployeeFinancial> financialOptional = employeeFinancialRepository.findByEmployeeId(employeeId);
    EmployeeFinancial employeeFinancial;
    if (financialOptional.isPresent()) {
        employeeFinancial = financialOptional.get();
    } else {
        employeeFinancial = new EmployeeFinancial();
        employeeFinancial.setEmployeeId(employeeId);
    }
    employeeFinancial.setSocialSecurityPlanId(employeeSecurityPlanRequest.getSocialSecurityPlanId());
    employeeFinancial.setSocialSecurityBase(employeeSecurityPlanRequest.getSocialSecurityBase());
    employeeFinancialRepository.save(employeeFinancial);
    calculateService.formulaCalculate(newArrayList(employeeId), SalaryFormulaIKeyword.SOCIAL_SECURITY);
}


}