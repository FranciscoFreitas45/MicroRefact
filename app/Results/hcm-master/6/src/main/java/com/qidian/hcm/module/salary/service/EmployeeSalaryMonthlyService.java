package com.qidian.hcm.module.salary.service;
 import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.utils.DateUtil;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.center.service.FileService;
import com.qidian.hcm.module.employee.entity.Employee;
import com.qidian.hcm.module.employee.entity.EmployeePosition;
import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import com.qidian.hcm.module.employee.repository.EmployeeRepository;
import com.qidian.hcm.module.employee.service.EmployeePositionService;
import com.qidian.hcm.module.organization.entity.OrganizationEntity;
import com.qidian.hcm.module.organization.repository.OrganizationRepository;
import com.qidian.hcm.module.salary.dto.CycleTimeRange;
import com.qidian.hcm.module.salary.dto.SalaryItemAccountingDTO;
import com.qidian.hcm.module.salary.dto.SalaryItemMonthlyDTO;
import com.qidian.hcm.module.salary.entity.EmployeeSalaryMonthly;
import com.qidian.hcm.module.salary.entity.SalaryItem;
import com.qidian.hcm.module.salary.enums.SalarySettingEnum;
import com.qidian.hcm.module.salary.repository.EmployeeFinancialRepositoryImpl;
import com.qidian.hcm.module.salary.repository.EmployeeSalaryMonthlyRepository;
import com.qidian.hcm.module.salary.repository.SalaryItemRepository;
import com.qidian.hcm.module.salary.request.EmployeeListRequest;
import com.qidian.hcm.module.salary.request.FilterEmployeesSalaryRequest;
import com.qidian.hcm.module.salary.request.SalaryItemMonthlyRequest;
import com.qidian.hcm.module.salary.response.EmployeeSalaryPageResponse;
import com.qidian.hcm.module.salary.response.SalaryItemMonthlyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util;
import java.util.stream.Collectors;
import com.qidian.hcm.Interface.OrganizationRepository;
import com.qidian.hcm.Interface.EmployeePositionService;
import com.qidian.hcm.Interface.FileService;
import com.qidian.hcm.Interface.EmployeeRepository;
@Slf4j
@Service
public class EmployeeSalaryMonthlyService {

@Autowired
 private  OrganizationRepository organizationRepository;

@Autowired
 private  EmployeePositionService employeePositionService;

@Autowired
 private  FileService fileService;

@Autowired
 private  EmployeeRepository employeeRepository;

@Autowired
 private  SalaryItemRepository salaryItemRepository;

@Autowired
 private  EmployeeSalaryMonthlyRepository employeeSalaryMonthlyRepository;

@Autowired
 private  SalarySettingService salarySettingService;

@Autowired
 private  EmployeeFinancialRepositoryImpl employeeFinancialRepositoryImpl;

@Autowired
 private  CalculateService calculateService;


public List<SalaryItemAccountingDTO> getEmployeeAccounting(Long employeeId,SalaryItemAccountingDTO salaryItemMonthlyRequest){
    validateOrGetEmployee(employeeId);
    List<SalaryItemAccountingDTO> salaryItemAccountingDTOList = new ArrayList<>();
    // 查询当前薪资项
    SalaryItem salaryItemResult = salaryItemRepository.findByCode(salaryItemMonthlyRequest.getCode()).orElseThrow(() -> new BizException(ResultCode.SALARY_ITEM_CODE_NOT_EXISTS));
    // 计算薪资项
    calculateService.accountingCalculate(employeeId, salaryItemResult, salaryItemMonthlyRequest.getValue()).forEach(salaryItem -> {
        SalaryItemAccountingDTO salaryItemAccountingDTO = new SalaryItemAccountingDTO();
        BeanUtils.copyProperties(salaryItem, salaryItemAccountingDTO);
        salaryItemAccountingDTOList.add(salaryItemAccountingDTO);
    });
    return salaryItemAccountingDTOList;
}


public Employee validateOrGetEmployee(Long employeeId){
    return employeeRepository.findById(employeeId).orElseThrow(() -> new BizException(ResultCode.EMPLOYEE_NOT_EXISTS));
}


public Page<EmployeeSalaryPageResponse> getEmployeeSalaryPage(FilterEmployeesSalaryRequest filterEmployeesSalary){
    String currentCycleMonth = salarySettingService.getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    Page<Employee> pageBean = employeeFinancialRepositoryImpl.getEmployeeSalaryPage(filterEmployeesSalary, currentCycleMonth);
    List<EmployeeSalaryPageResponse> dtoList = Lists.newArrayList();
    if (!pageBean.getContent().isEmpty()) {
        List<OrganizationEntity> organizations = organizationRepository.findAll();
        Map<Long, String> orgMap = organizations.stream().collect(Collectors.toMap(OrganizationEntity::getId, OrganizationEntity::getName));
        for (Employee employee : pageBean.getContent()) {
            EmployeePosition currentEp = employeePositionService.getCurrentEmployeePosition(employee.getId());
            EmployeeSalaryPageResponse dto = new EmployeeSalaryPageResponse();
            BeanUtils.copyProperties(employee, dto);
            if (Objects.nonNull(currentEp)) {
                dto.setCompanyName(orgMap.get(currentEp.getCompanyId()));
                dto.setDepartmentName(orgMap.get(currentEp.getCompanyId()));
            }
            if (Objects.nonNull(employee.getEmployeeFinancial())) {
                dto.setSalary(employee.getEmployeeFinancial().getSalary());
            }
            dto.setAvatar(fileService.getAvatarImgUrl(employee.getAvatarFileId()));
            dtoList.add(dto);
        }
    }
    return new PageImpl<>(dtoList, pageBean.getPageable(), pageBean.getTotalElements());
}


public SalaryItemMonthlyDTO getSalaryItemMonthlyDTO(List<SalaryItemMonthlyDTO> salaryItemMonthlyDTOList,Long id){
    for (SalaryItemMonthlyDTO salaryItemMonthlyDTO : salaryItemMonthlyDTOList) {
        if (salaryItemMonthlyDTO.getId().equals(id)) {
            return salaryItemMonthlyDTO;
        }
    }
    SalaryItemMonthlyDTO salaryItemMonthlyDTO = new SalaryItemMonthlyDTO();
    salaryItemMonthlyDTOList.add(salaryItemMonthlyDTO);
    return salaryItemMonthlyDTO;
}


public void salaryExclude(EmployeeListRequest employeeListRequest){
    String currentCycleMonth = salarySettingService.getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    List<EmployeeSalaryMonthly> employeeSalaryMonthlyList = employeeSalaryMonthlyRepository.findByEmployeeIdInAndDate(employeeListRequest.getIds(), currentCycleMonth);
    employeeListRequest.getIds().forEach(employeeId -> {
        EmployeeSalaryMonthly salaryMonthly = getSalaryMonthlyByEmployeeId(employeeSalaryMonthlyList, employeeId);
        salaryMonthly.setInclude(Boolean.FALSE);
        salaryMonthly.setDate(currentCycleMonth);
    });
    employeeSalaryMonthlyRepository.saveAll(employeeSalaryMonthlyList);
}


public SalaryItemMonthlyResponse getSalaryItemMonthlyResponse(List<SalaryItemMonthlyResponse> salaryItemMonthlyResponseList,Long id){
    if (CollectionUtils.isEmpty(salaryItemMonthlyResponseList)) {
        return null;
    }
    for (SalaryItemMonthlyResponse salaryItemMonthlyResponse : salaryItemMonthlyResponseList) {
        if (salaryItemMonthlyResponse.getId().equals(id)) {
            return salaryItemMonthlyResponse;
        }
    }
    return null;
}


@Transactional
public void saveDetailInfo(Long employeeId,List<SalaryItemMonthlyRequest> salaryItemMonthlyRequestList){
    validateOrGetEmployee(employeeId);
    if (CollectionUtils.isEmpty(salaryItemMonthlyRequestList)) {
        throw new BizException(ResultCode.PARAM_ERROR);
    }
    EmployeeSalaryMonthly employeeSalaryMonthly = getEmployeeSalaryItemsMonthlyDetail(employeeId);
    List<SalaryItemMonthlyDTO> salaryItemMonthlyDTOList = JSON.parseArray(employeeSalaryMonthly.getItemsResult(), SalaryItemMonthlyDTO.class);
    if (salaryItemMonthlyDTOList == null) {
        salaryItemMonthlyDTOList = new ArrayList<>();
    }
    // 在保存之前校验数据
    List<SalaryItem> changedSalaryItem = getAttendanceSalaryItem(salaryItemMonthlyRequestList, salaryItemMonthlyDTOList);
    for (SalaryItemMonthlyRequest salaryItemMonthlyRequest : salaryItemMonthlyRequestList) {
        SalaryItemMonthlyDTO salaryItemMonthlyDTO = getSalaryItemMonthlyDTO(salaryItemMonthlyDTOList, salaryItemMonthlyRequest.getId());
        BeanUtils.copyProperties(salaryItemMonthlyRequest, salaryItemMonthlyDTO);
    }
    employeeSalaryMonthly.setItemsResult(JSON.toJSONString(salaryItemMonthlyDTOList));
    // 保存薪资项数据
    employeeSalaryMonthlyRepository.save(employeeSalaryMonthly);
    calculateService.formulaCalculate(employeeId, changedSalaryItem, Boolean.FALSE);
}


public boolean isEmployeeSalaryInclude(Long employeeId){
    // 获取本月薪资项
    String currentCycleMonth = salarySettingService.getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    Optional<EmployeeSalaryMonthly> employeeSalaryMonthlyOptional = employeeSalaryMonthlyRepository.findByEmployeeIdAndDate(employeeId, currentCycleMonth);
    if (employeeSalaryMonthlyOptional.isPresent()) {
        return employeeSalaryMonthlyOptional.get().getInclude();
    } else {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            if (employee.getStatus() == EmployeeStatus.former) {
                Date resignationDate = employee.getResignationDate();
                CycleTimeRange currentSalaryCycleTime = salarySettingService.getCurrentSalaryCycleTime();
                Date startDate = DateUtil.parseDate(currentSalaryCycleTime.getStartTime(), DateUtil.DATE_FORMAT_YMD);
                if (resignationDate.before(startDate)) {
                    return false;
                }
            } else {
                return true;
            }
        }
    }
    return false;
}


public List<SalaryItem> getAttendanceSalaryItem(List<SalaryItemMonthlyRequest> salaryItemMonthlyRequestList,List<SalaryItemMonthlyDTO> salaryItemMonthlyDTOList){
    Map<String, SalaryItemMonthlyRequest> salaryItemMonthlyRequestMap = salaryItemMonthlyRequestList.stream().collect(Collectors.toMap(SalaryItemMonthlyRequest::getCode, salaryItemMonthly -> salaryItemMonthly));
    Map<String, SalaryItemMonthlyDTO> salaryItemMonthlyDTOListMap = salaryItemMonthlyDTOList.stream().collect(Collectors.toMap(SalaryItemMonthlyDTO::getCode, salaryItemMonthlyDTO -> salaryItemMonthlyDTO));
    // 查询考勤字典公式
    List<SalaryItem> salaryItemList = salaryItemRepository.findAllByInflow(Boolean.TRUE);
    List<SalaryItem> changedSalaryItemList = new ArrayList<>();
    salaryItemList.forEach(salaryItem -> {
        String salaryItemCode = salaryItem.getCode();
        SalaryItemMonthlyRequest salaryItemMonthlyRequest = salaryItemMonthlyRequestMap.get(salaryItemCode);
        if (Objects.nonNull(salaryItemMonthlyRequest) && Objects.nonNull(salaryItemMonthlyRequest.getValue())) {
            SalaryItemMonthlyDTO salaryItemMonthlyDTO = salaryItemMonthlyDTOListMap.get(salaryItemCode);
            if (Objects.isNull(salaryItemMonthlyDTO) || salaryItemMonthlyRequest.getValue().equals(salaryItemMonthlyDTO.getValue())) {
                changedSalaryItemList.add(salaryItem);
            }
        }
    });
    return changedSalaryItemList;
}


public EmployeeSalaryMonthly getEmployeeSalaryItemsMonthlyDetail(Long employeeId){
    // 获取本月薪资项
    String currentCycleMonth = salarySettingService.getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    Optional<EmployeeSalaryMonthly> employeeSalaryMonthlyOptional = employeeSalaryMonthlyRepository.findByEmployeeIdAndDate(employeeId, currentCycleMonth);
    return employeeSalaryMonthlyOptional.orElseGet(() -> {
        EmployeeSalaryMonthly employeeSalaryMonthly = new EmployeeSalaryMonthly();
        employeeSalaryMonthly.setEmployeeId(employeeId);
        employeeSalaryMonthly.setDate(currentCycleMonth);
        employeeSalaryMonthly.setInclude(isEmployeeSalaryInclude(employeeId));
        return employeeSalaryMonthly;
    });
}


public void salaryInclude(EmployeeListRequest employeeListRequest){
    String currentCycleMonth = salarySettingService.getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    List<EmployeeSalaryMonthly> employeeSalaryMonthlyList = employeeSalaryMonthlyRepository.findByEmployeeIdInAndDate(employeeListRequest.getIds(), currentCycleMonth);
    employeeListRequest.getIds().forEach(employeeId -> {
        EmployeeSalaryMonthly salaryMonthly = getSalaryMonthlyByEmployeeId(employeeSalaryMonthlyList, employeeId);
        salaryMonthly.setInclude(Boolean.TRUE);
        salaryMonthly.setDate(currentCycleMonth);
    });
    calculateService.formulaCalculateByEmployee(employeeListRequest.getIds());
    employeeSalaryMonthlyRepository.saveAll(employeeSalaryMonthlyList);
}


public EmployeeSalaryMonthly getSalaryMonthlyByEmployeeId(List<EmployeeSalaryMonthly> employeeSalaryMonthlyList,Long employeeId){
    for (EmployeeSalaryMonthly employeeSalaryMonthly : employeeSalaryMonthlyList) {
        if (employeeId.equals(employeeSalaryMonthly.getEmployeeId())) {
            return employeeSalaryMonthly;
        }
    }
    EmployeeSalaryMonthly employeeSalaryMonthly = new EmployeeSalaryMonthly();
    employeeSalaryMonthly.setEmployeeId(employeeId);
    employeeSalaryMonthlyList.add(employeeSalaryMonthly);
    return employeeSalaryMonthly;
}


public List<SalaryItemMonthlyResponse> getSalaryMonthly(Long employeeId){
    // 获取薪资项
    List<SalaryItem> salaryItemList = salaryItemRepository.findByInListOrderByIdAsc(Boolean.TRUE);
    EmployeeSalaryMonthly employeeSalaryMonthly = getEmployeeSalaryItemsMonthlyDetail(employeeId);
    List<SalaryItemMonthlyResponse> salaryItemMonthlyResponseList = JSON.parseArray(employeeSalaryMonthly.getItemsResult(), SalaryItemMonthlyResponse.class);
    if (salaryItemMonthlyResponseList == null) {
        salaryItemMonthlyResponseList = new ArrayList<>();
    }
    for (SalaryItem salaryItem : salaryItemList) {
        SalaryItemMonthlyResponse salaryItemMonthlyResponse = getSalaryItemMonthlyResponse(salaryItemMonthlyResponseList, salaryItem.getId());
        if (Objects.nonNull(salaryItemMonthlyResponse)) {
            salaryItemMonthlyResponse.setName(salaryItem.getName());
        } else {
            salaryItemMonthlyResponse = new SalaryItemMonthlyResponse();
            BeanUtils.copyProperties(salaryItem, salaryItemMonthlyResponse);
            salaryItemMonthlyResponseList.add(salaryItemMonthlyResponse);
        }
    }
    return salaryItemMonthlyResponseList;
}


}