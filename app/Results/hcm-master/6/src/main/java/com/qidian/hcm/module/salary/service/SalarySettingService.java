package com.qidian.hcm.module.salary.service;
 import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.utils.DateUtil;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.salary.dto.CycleTimeRange;
import com.qidian.hcm.module.salary.entity.SalarySetting;
import com.qidian.hcm.module.salary.enums.SalarySettingEnum;
import com.qidian.hcm.module.salary.repository.SalarySettingRepository;
import com.qidian.hcm.module.salary.request.SalaryCycleDateRequest;
import com.qidian.hcm.module.salary.request.SalaryDateRequest;
import com.qidian.hcm.module.salary.response.SalarySettingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
@Slf4j
@Service
public class SalarySettingService {

@Autowired
 private  SalarySettingRepository salarySettingRepository;


public void setSalaryPayDate(SalaryDateRequest request){
    saveOrUpdateSalarySetting(SalarySettingEnum.PAY_DATE, request.getSalaryPayDate());
    saveOrUpdateSalarySetting(SalarySettingEnum.PAY_MONTH, request.getSalaryPayMonth());
}


public CycleTimeRange getCurrentSalaryCycleTime(){
    // 获取当前计薪月份
    String currentCycleMonth = getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    if (StringUtils.isEmpty(currentCycleMonth)) {
        throw new BizException(ResultCode.SALARY_CURRENT_CYCLE_NOT_EXISTS);
    }
    // 获取计薪开始日期
    String startCycleDayStr = getSalarySettingValue(SalarySettingEnum.START_CYCLE_DAY);
    if (StringUtils.isEmpty(startCycleDayStr)) {
        throw new BizException(ResultCode.SALARY_START_CYCLE_DATE_NOT_EXISTS);
    }
    Date date = DateUtil.parseDate(currentCycleMonth, DateUtil.DATE_FORMAT_YM);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(startCycleDayStr));
    // 获取开始日期
    String startDate = DateUtil.convertDateToStr(calendar.getTime(), DateUtil.DATE_FORMAT_YMD);
    // 结束日期
    int endDay;
    int startDay = Integer.parseInt(startCycleDayStr);
    if (startDay - 1 == 0) {
        endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    } else {
        // 月份+1
        calendar.add(Calendar.MONTH, 1);
        endDay = startDay - 1;
    }
    calendar.set(Calendar.DAY_OF_MONTH, endDay);
    // 获取结束日期
    String endDate = DateUtil.convertDateToStr(calendar.getTime(), DateUtil.DATE_FORMAT_YMD);
    return new CycleTimeRange(startDate, endDate);
}


public String saveOrUpdateCurrentCycleMonth(){
    String nextMonthStr;
    Optional<SalarySetting> salarySettingOptional = salarySettingRepository.findByKey(SalarySettingEnum.CURRENT_CYCLE_MONTH.getKey());
    if (salarySettingOptional.isPresent()) {
        SalarySetting salarySetting = salarySettingOptional.get();
        Date date = DateUtil.parseDate(salarySetting.getValue(), DateUtil.DATE_FORMAT_YM);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        Date nextMonth = calendar.getTime();
        nextMonthStr = DateUtil.convertDateToStr(nextMonth, DateUtil.DATE_FORMAT_YM);
        salarySetting.setValue(nextMonthStr);
        salarySettingRepository.save(salarySetting);
    } else {
        nextMonthStr = DateUtil.convertDateToStr(new Date(), DateUtil.DATE_FORMAT_YM);
        SalarySetting setting = new SalarySetting(SalarySettingEnum.CURRENT_CYCLE_MONTH.getName(), SalarySettingEnum.CURRENT_CYCLE_MONTH.getKey(), nextMonthStr);
        salarySettingRepository.save(setting);
    }
    return nextMonthStr;
}


public String getSalarySettingValue(SalarySettingEnum salarySettingEnum){
    Optional<SalarySetting> salarySettingOptional = salarySettingRepository.findByKey(salarySettingEnum.getKey());
    if (salarySettingOptional.isPresent()) {
        return salarySettingOptional.get().getValue();
    }
    return null;
}


public SalarySetting saveOrUpdateSalarySetting(SalarySettingEnum salarySettingEnum,Object value){
    SalarySetting setting;
    String valStr = String.valueOf(value);
    Optional<SalarySetting> salarySettingOptional = salarySettingRepository.findByKey(salarySettingEnum.getKey());
    if (salarySettingOptional.isPresent()) {
        setting = salarySettingOptional.get();
        setting.setValue(valStr);
        salarySettingRepository.save(setting);
    } else {
        setting = new SalarySetting(salarySettingEnum.getName(), salarySettingEnum.getKey(), valStr);
        setting = salarySettingRepository.save(setting);
    }
    return setting;
}


public Integer getSalarySettingIntegerValue(SalarySettingEnum salarySettingEnum){
    Optional<SalarySetting> salarySettingOptional = salarySettingRepository.findByKey(salarySettingEnum.getKey());
    if (salarySettingOptional.isPresent()) {
        return Integer.valueOf(salarySettingOptional.get().getValue());
    }
    return 0;
}


public void setSalaryCycleDate(SalaryCycleDateRequest request){
    saveOrUpdateSalarySetting(SalarySettingEnum.FIRST_CYCLE_MONTH, request.getFirstCycleMonth());
    saveOrUpdateSalarySetting(SalarySettingEnum.START_CYCLE_DAY, request.getStartCycleDay());
    // 设置计薪开始时间的时候判断当前周期是否为空，如果为空，则为两者设置同样的值
    String currentCycleMonth = getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    if (StringUtils.isEmpty(currentCycleMonth)) {
        saveOrUpdateSalarySetting(SalarySettingEnum.CURRENT_CYCLE_MONTH, request.getFirstCycleMonth());
    }
}


public SalarySettingResponse getSalaryConfigs(){
    SalarySettingResponse response = new SalarySettingResponse();
    response.setFirstCycleMonth(getSalarySettingValue(SalarySettingEnum.FIRST_CYCLE_MONTH));
    response.setStartCycleDay(getSalarySettingIntegerValue(SalarySettingEnum.START_CYCLE_DAY));
    response.setSalaryPayDate(getSalarySettingIntegerValue(SalarySettingEnum.PAY_DATE));
    response.setSalaryPayMonth(getSalarySettingValue(SalarySettingEnum.PAY_MONTH));
    return response;
}


}