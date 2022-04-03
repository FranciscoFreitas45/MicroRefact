package com.qidian.hcm.module.salary.dto;
 import com.qidian.hcm.common.utils.DateUtil;
import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import com.qidian.hcm.module.employee.enums.EmployeeType;
import com.qidian.hcm.module.employee.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
@Getter
@Setter
public class ExternalEmployeeDTO implements Serializable{

 private  long serialVersionUID;

 public  String employeeNo;

 public  String gender;

 public  String birthday;

 public  Date hireDate;

 public  Date workDate;

 public  Date resignationDate;

 public  String hireDateStr;

 public  String workDateStr;

 public  String resignationDateStr;

 public  String type;

 public  String status;

 public  Double salary;

 public  Double socialSecurityBase;

 public  Double housingFundBase;

 public  String currentCycleMonth;

 public  ExternalHousingFundPlanDTO housingFund;

 public  ExternalSocialSecurityPlanDTO socialSecurity;

 public  ExternalThresholdDTO threshold;


public Boolean isMatchSocialFundPoint(){
    Date limitDate = getLimitPointDate(socialSecurity.getLimitPoint());
    return isMatchPoint(limitDate);
}


public Date getLimitPointDate(Integer limitPoint){
    String housingPoint = this.currentCycleMonth + "-" + limitPoint;
    return DateUtil.parseDate(housingPoint, DateUtil.DATE_FORMAT_Y_M_D);
}


public Boolean isMatchHousingFundPoint(){
    Date limitDate = getLimitPointDate(housingFund.getLimitPoint());
    return isMatchPoint(limitDate);
}


public void setHireDate(Date hireDate){
    this.hireDate = hireDate;
    this.hireDateStr = DateUtil.convertDateToStr(hireDate, DateUtil.DATE_FORMAT_YM);
}


public void setResignationDate(Date resignationDate){
    this.resignationDate = resignationDate;
    this.resignationDateStr = DateUtil.convertDateToStr(resignationDate, DateUtil.DATE_FORMAT_YM);
}


public Boolean isMatchPoint(Date pointDate){
    if (Objects.isNull(hireDate)) {
        return Boolean.FALSE;
    }
    // 入职时间是否在临界点之前
    Boolean isBeforeHirePoint = compareDay(pointDate, hireDate);
    // 不是离职状态并且
    if (!EmployeeStatus.former.name().equalsIgnoreCase(status)) {
        return isBeforeHirePoint;
    }
    // 离职到比较
    return isBeforeHirePoint && compareDay(resignationDate, pointDate);
}


public void setWorkDate(Date workDate){
    this.workDate = workDate;
    this.workDateStr = DateUtil.convertDateToStr(workDate, DateUtil.DATE_FORMAT_YM);
}


public Boolean compareDay(Date start,Date end){
    return new DateTime(start).withTimeAtStartOfDay().compareTo(new DateTime(end).withTimeAtStartOfDay()) > 0;
}


}