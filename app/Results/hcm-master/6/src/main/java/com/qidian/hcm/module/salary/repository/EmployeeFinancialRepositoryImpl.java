package com.qidian.hcm.module.salary.repository;
 import com.qidian.hcm.common.framework.CommonRepositoryUtil;
import com.qidian.hcm.module.employee.entity.Employee;
import com.qidian.hcm.module.salary.enums.EmployeesFilterType;
import com.qidian.hcm.module.salary.request.FilterEmployeesSalaryRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import com.qidian.hcm.Interface.CommonRepositoryUtil;
@Repository
@Slf4j
public class EmployeeFinancialRepositoryImpl {

@Autowired
 private  CommonRepositoryUtil commonRepositoryUtil;


public Page<Employee> getEmployeeSalaryPage(FilterEmployeesSalaryRequest filter,String date){
    log.info(filter.toString());
    StringBuilder sb = new StringBuilder();
    sb.append(" select e.* from employee as e").append(" left join employee_financial as ef on e.id=ef.employee_id").append(" left join employee_salary_monthly as eam on e.id=eam.employee_id and eam.date=:date  where");
    // 如果记薪
    if (filter.getInclude()) {
        sb.append(" eam.include=true or eam.include is null");
    } else {
        sb.append(" eam.include=false");
    }
    if (filter.getStatus() != EmployeesFilterType.all) {
        sb.append(" and e.").append(filter.getStatus().getDataBaseCode()).append("=:date");
    }
    Map<String, Object> map = new HashMap<>();
    map.put("date", date);
    if (StringUtils.isNotBlank(filter.getKeyword())) {
        sb.append(" and (e.employee_no like :keyword or e.name like :keyword or e.mobile like :keyword )");
        map.put("keyword", filter.getKeyword());
    }
    return commonRepositoryUtil.pageByNative(Employee.class, sb.toString(), null, map, filter.getPageNo(), filter.getPageSize());
}


}