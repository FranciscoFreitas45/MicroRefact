package com.qidian.hcm.module.salary.service;
 import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.utils.ExcelUtil;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.center.request.UploadFileRequest;
import com.qidian.hcm.module.employee.entity.Employee;
import com.qidian.hcm.module.employee.repository.EmployeeRepository;
import com.qidian.hcm.module.salary.dto.CycleTimeRange;
import com.qidian.hcm.module.salary.dto.EmployeeSalaryMonthlyDTO;
import com.qidian.hcm.module.salary.dto.SalaryItemDTO;
import com.qidian.hcm.module.salary.dto.SalaryReportItemDTO;
import com.qidian.hcm.module.salary.entity.EmployeeSalaryMonthly;
import com.qidian.hcm.module.salary.entity.SalaryItem;
import com.qidian.hcm.module.salary.entity.SalaryRecordMonthly;
import com.qidian.hcm.module.salary.enums.SalarySettingEnum;
import com.qidian.hcm.module.salary.repository.EmployeeSalaryMonthlyRepository;
import com.qidian.hcm.module.salary.repository.SalaryItemRepository;
import com.qidian.hcm.module.salary.repository.SalaryRecordMonthlyRepository;
import com.qidian.hcm.module.salary.request.FilterSalaryRecordRequest;
import com.qidian.hcm.module.salary.response.SalaryRecordMonthlyResponse;
import com.qidian.hcm.module.salary.response.SalaryReportAllResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.io.ByteArrayInputStream;
import java.util;
import java.util.stream.Collectors;
import com.qidian.hcm.Interface.EmployeeRepository;
@Slf4j
@Service
public class BasicSalaryService {

@Autowired
 private  SalaryRecordMonthlyRepository salaryRecordMonthlyRepository;

@Autowired
 private  SalarySettingService salarySettingService;

@Autowired
 private  SalaryItemRepository salaryItemRepository;

@Autowired
 private  EmployeeRepository employeeRepository;

@Autowired
 private  EmployeeSalaryMonthlyRepository employeeSalaryMonthlyRepository;

@Autowired
 private  EmployeeSalaryMonthlyService employeeSalaryMonthlyService;

@Autowired
 private  CalculateService calculateService;


public Page<SalaryRecordMonthlyResponse> getSalaryRecords(FilterSalaryRecordRequest request){
    Pageable pageable = PageRequest.of(request.getPageNo() - 1, request.getPageSize(), Sort.by(Sort.Order.desc("id")));
    Page<SalaryRecordMonthly> pageBean = salaryRecordMonthlyRepository.findAll(pageable);
    List<SalaryRecordMonthlyResponse> salaryRecordMonthlyResponses = Lists.newArrayListWithExpectedSize(pageBean.getContent().size());
    if (!pageBean.getContent().isEmpty()) {
        for (SalaryRecordMonthly position : pageBean.getContent()) {
            SalaryRecordMonthlyResponse salaryRecordMonthlyResponse = new SalaryRecordMonthlyResponse();
            BeanUtils.copyProperties(position, salaryRecordMonthlyResponse);
            salaryRecordMonthlyResponses.add(salaryRecordMonthlyResponse);
        }
    }
    return new PageImpl<>(salaryRecordMonthlyResponses, pageable, pageBean.getTotalElements());
}


public EmployeeSalaryMonthly handlerImportData(JSONObject dto,String date){
    EmployeeSalaryMonthly monthly = null;
    Optional<Employee> employeeOptional = employeeRepository.findByEmployeeNo(dto.getString("employeeNo"));
    if (employeeOptional.isPresent()) {
        Employee employee = employeeOptional.get();
        // 只有当前周期内为计薪状态的员工才录入，其余放弃
        if (employeeSalaryMonthlyService.isEmployeeSalaryInclude(employee.getId())) {
            monthly = employeeSalaryMonthlyService.getEmployeeSalaryItemsMonthlyDetail(employee.getId());
            monthly.setEmployeeId(employee.getId());
            monthly.setDate(date);
            monthly.setItemsResult(convertToJSONString(monthly.getItemsResult(), dto));
        }
    }
    return monthly;
}


public YesNo getAccountStatus(){
    String cycle = salarySettingService.getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    Optional<SalaryRecordMonthly> byCycle = salaryRecordMonthlyRepository.findByCycle(cycle);
    if (byCycle.isPresent()) {
        return byCycle.get().getAccounted();
    } else {
        return YesNo.NO;
    }
}


public HSSFWorkbook exportMonthlyReport(String month){
    List<String> titleList = getColumnTitleList();
    List<String> columnTitleFieldsList = getColumnTitleFieldsList();
    List<Object[]> dataList = new ArrayList<>();
    List<EmployeeSalaryMonthlyDTO> list = employeeSalaryMonthlyRepository.findAllByDate(month);
    if (!CollectionUtils.isEmpty(list)) {
        for (EmployeeSalaryMonthlyDTO salaryMonthly : list) {
            SalaryReportAllResponse salaryReportAllResponse = new SalaryReportAllResponse();
            BeanUtils.copyProperties(salaryMonthly, salaryReportAllResponse);
            Optional<Employee> byEmployeeNo = employeeRepository.findById(salaryMonthly.getEmployeeId());
            if (byEmployeeNo.isPresent()) {
                Object[] objArr = new Object[titleList.size()];
                JSONObject jsonObject = new JSONObject();
                List<SalaryReportItemDTO> salaryReportItemDTOS = JSONObject.parseArray(salaryMonthly.getItemsResult(), SalaryReportItemDTO.class);
                salaryReportItemDTOS.forEach(e -> jsonObject.put(e.getCode(), e.getValue()));
                Employee employee = byEmployeeNo.get();
                objArr[0] = employee.getName();
                objArr[1] = employee.getEmployeeNo();
                for (int i = 0; i < columnTitleFieldsList.size(); i++) {
                    objArr[i + 2] = jsonObject.getString(columnTitleFieldsList.get(i));
                }
                dataList.add(objArr);
            }
        }
    }
    return ExcelUtil.createWorkbook("", titleList.toArray(new String[0]), dataList);
}


public List<String> getColumnTitleList(){
    List<SalaryItem> itemList = salaryItemRepository.findAll();
    List<String> titleList = itemList.stream().map(this::generateColumnTitle).collect(Collectors.toList());
    titleList.add(0, "工号(employeeNo)");
    titleList.add(0, "姓名(employeeName)");
    return titleList;
}


public List<String> getColumnTitleFieldsList(){
    List<SalaryItem> itemList = salaryItemRepository.findAll();
    return itemList.stream().map(SalaryItem::getCode).collect(Collectors.toList());
}


public Page<SalaryReportAllResponse> getSalaryMonthReport(String month,FilterSalaryRecordRequest request){
    Pageable pageable = PageRequest.of(request.getPageNo() - 1, request.getPageSize(), Sort.by(Sort.Order.desc("id")));
    Page<EmployeeSalaryMonthly> pageBean = employeeSalaryMonthlyRepository.findAllByDate(month, pageable);
    List<SalaryReportAllResponse> salaryRecordMonthlyResponses = Lists.newArrayListWithExpectedSize(pageBean.getContent().size());
    if (!pageBean.getContent().isEmpty()) {
        for (EmployeeSalaryMonthly salaryMonthly : pageBean.getContent()) {
            SalaryReportAllResponse salaryReportAllResponse = new SalaryReportAllResponse();
            BeanUtils.copyProperties(salaryMonthly, salaryReportAllResponse);
            Optional<Employee> byEmployeeNo = employeeRepository.findById(salaryMonthly.getEmployeeId());
            if (byEmployeeNo.isPresent()) {
                String name = byEmployeeNo.get().getName();
                salaryReportAllResponse.setEmployeeName(name);
                salaryReportAllResponse.setEmployeeNo(salaryMonthly.getEmployee().getEmployeeNo());
                salaryReportAllResponse.setSalaryItemDTO(JSONObject.parseArray(salaryMonthly.getItemsResult(), SalaryReportItemDTO.class));
                salaryRecordMonthlyResponses.add(salaryReportAllResponse);
            }
        }
    }
    return new PageImpl<>(salaryRecordMonthlyResponses, pageable, pageBean.getTotalElements());
}


public void importSalaryFromExcel(UploadFileRequest uploadFileRequest){
    byte[] bytes = Base64.decodeBase64(uploadFileRequest.getFileBase64Str());
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
    List<LinkedHashMap> list = ExcelUtil.parseExcelToJson(byteArrayInputStream);
    JSONArray array = JSONObject.parseArray(JSONObject.toJSONString(list));
    String currentCycleMonth = salarySettingService.getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    List<EmployeeSalaryMonthly> collect = array.stream().map(e -> handlerImportData((JSONObject) e, currentCycleMonth)).collect(Collectors.toList()).stream().filter(n -> !Objects.isNull(n)).collect(Collectors.toList());
    employeeSalaryMonthlyRepository.saveAll(collect);
}


public String convertToJSONString(String itemResult,JSONObject dto){
    dto.remove("employeeNo");
    dto.remove("employeeName");
    List<SalaryReportItemDTO> salaryReportItemDTOS = JSONObject.parseArray(itemResult, SalaryReportItemDTO.class);
    salaryReportItemDTOS.forEach(e -> {
        // 如果导入项已经存在,更新原来的值
        if (dto.containsKey(e.getCode())) {
            e.setValue(Double.valueOf(dto.getString(e.getCode())));
            dto.remove(e.getCode());
        }
    });
    dto.forEach((key, value) -> {
        SalaryReportItemDTO tempItem = new SalaryReportItemDTO();
        tempItem.setCode(key);
        tempItem.setValue(Double.valueOf((String) value));
        salaryReportItemDTOS.add(tempItem);
    });
    return JSONObject.toJSONString(salaryReportItemDTOS);
}


public void accountingCancel(){
    String cycle = salarySettingService.getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    Optional<SalaryRecordMonthly> byCycle = salaryRecordMonthlyRepository.findByCycle(cycle);
    SalaryRecordMonthly salaryRecordMonthly;
    if (byCycle.isPresent()) {
        salaryRecordMonthly = byCycle.get();
        salaryRecordMonthly.setAccounted(YesNo.NO);
        salaryRecordMonthlyRepository.save(salaryRecordMonthly);
    } else {
        throw new BizException(ResultCode.SALARY_CURRENT_MONTH_RECORD_NOT_EXISTS);
    }
}


public HSSFWorkbook exportTemplet(){
    List<String> titleList = getColumnTitleList();
    return ExcelUtil.createWorkbook("", titleList.toArray(new String[0]), null);
}


public void accountingSalary(){
    String cycle = salarySettingService.getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    Optional<SalaryRecordMonthly> byCycle = salaryRecordMonthlyRepository.findByCycle(cycle);
    SalaryRecordMonthly salaryRecordMonthly;
    if (byCycle.isPresent()) {
        salaryRecordMonthly = byCycle.get();
        salaryRecordMonthly.setAccounted(YesNo.YES);
    } else {
        salaryRecordMonthly = new SalaryRecordMonthly();
        salaryRecordMonthly.setAccounted(YesNo.YES);
        salaryRecordMonthly.setCycle(cycle);
        CycleTimeRange currentSalaryCycleTime = salarySettingService.getCurrentSalaryCycleTime();
        salaryRecordMonthly.setCycleStartDate(currentSalaryCycleTime.getStartTime());
        salaryRecordMonthly.setCycleEndDate(currentSalaryCycleTime.getEndTime());
    }
    salaryRecordMonthlyRepository.save(salaryRecordMonthly);
}


public String generateColumnTitle(SalaryItem salaryItem){
    return salaryItem.getName() + '(' + salaryItem.getCode() + ')';
}


public List<SalaryItemDTO> getSalaryItemList(){
    List<SalaryItem> itemList = salaryItemRepository.findAll();
    return itemList.stream().map(e -> new SalaryItemDTO(e.getCode(), e.getName())).collect(Collectors.toList());
}


public void saveSalaryRecord(){
    String cycle = salarySettingService.getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    SalaryRecordMonthly salaryRecordMonthly = salaryRecordMonthlyRepository.findByCycle(cycle).orElseThrow(() -> new BizException(ResultCode.SALARY_NOT_ACCOUNTED));
    if (salaryRecordMonthly.getAccounted() == YesNo.NO) {
        throw new BizException(ResultCode.SALARY_NOT_ACCOUNTED);
    }
    salaryRecordMonthly.setRecorded(YesNo.YES);
    salaryRecordMonthly.setRecordTime(new Date());
    salaryRecordMonthlyRepository.save(salaryRecordMonthly);
    // 更新setting中的周期
    salarySettingService.saveOrUpdateCurrentCycleMonth();
    // 计算下一周期薪资
    calculateService.formulaCalculate();
}


}