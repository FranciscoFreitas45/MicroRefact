package com.hmm.employee.entity;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import com.hmm.department.entity.Department;
public class EmployeeQueryDTO {

 private  String deptName;

 private  String empName;

 private  String userName;

 private  String idcard;

 private  String empNo;

@DateTimeFormat(pattern = "yyyy-MM-dd")
 private  Date createTimeStart;

@DateTimeFormat(pattern = "yyyy-MM-dd")
 private  Date createTimeEnd;


public void setIdcard(String idcard){
    this.idcard = idcard;
}


public Date getCreateTimeEnd(){
    return createTimeEnd;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


@SuppressWarnings("serial")
public Specification<Employee> getWhereClause(EmployeeQueryDTO employQueryDTO){
    return new Specification<Employee>() {

        @Override
        public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(employQueryDTO.getDeptName())) {
                Join<Department, Employee> join = root.join("departmentes", JoinType.LEFT);
                predicates.add(criteriaBuilder.like(join.get("deptName").as(String.class), "%" + employQueryDTO.getDeptName() + "%"));
            // predicates.add(criteriaBuilder.equal(join.get("deptName").as(String.class), "%"+employQueryDTO.getDeptName()+"%"));
            }
            if (StringUtils.isNotBlank(employQueryDTO.getEmpName())) {
                predicates.add(criteriaBuilder.like(root.get("empName").as(String.class), "%" + employQueryDTO.getEmpName() + "%"));
            }
            if (StringUtils.isNotBlank(employQueryDTO.getEmpNo())) {
                predicates.add(criteriaBuilder.equal(root.get("empNo").as(String.class), "%" + employQueryDTO.getEmpNo() + "%"));
            }
            if (StringUtils.isNotBlank(employQueryDTO.getIdcard())) {
                predicates.add(criteriaBuilder.equal(root.get("idcard").as(String.class), "%" + employQueryDTO.getIdcard() + "%"));
            }
            if (StringUtils.isNotBlank(employQueryDTO.getUserName())) {
                predicates.add(criteriaBuilder.like(root.get("userName").as(String.class), "%" + employQueryDTO.getUserName() + "%"));
            }
            if (null != employQueryDTO.getCreateTimeStart()) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("entryDate").as(Date.class), employQueryDTO.getCreateTimeStart()));
            }
            if (null != employQueryDTO.getCreateTimeEnd()) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("entryDate").as(Date.class), employQueryDTO.getCreateTimeEnd()));
            }
            Predicate[] pre = new Predicate[predicates.size()];
            return query.where(predicates.toArray(pre)).getRestriction();
        }
    };
}


@Override
public Predicate toPredicate(Root<Employee> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<>();
    if (StringUtils.isNotBlank(employQueryDTO.getDeptName())) {
        Join<Department, Employee> join = root.join("departmentes", JoinType.LEFT);
        predicates.add(criteriaBuilder.like(join.get("deptName").as(String.class), "%" + employQueryDTO.getDeptName() + "%"));
    // predicates.add(criteriaBuilder.equal(join.get("deptName").as(String.class), "%"+employQueryDTO.getDeptName()+"%"));
    }
    if (StringUtils.isNotBlank(employQueryDTO.getEmpName())) {
        predicates.add(criteriaBuilder.like(root.get("empName").as(String.class), "%" + employQueryDTO.getEmpName() + "%"));
    }
    if (StringUtils.isNotBlank(employQueryDTO.getEmpNo())) {
        predicates.add(criteriaBuilder.equal(root.get("empNo").as(String.class), "%" + employQueryDTO.getEmpNo() + "%"));
    }
    if (StringUtils.isNotBlank(employQueryDTO.getIdcard())) {
        predicates.add(criteriaBuilder.equal(root.get("idcard").as(String.class), "%" + employQueryDTO.getIdcard() + "%"));
    }
    if (StringUtils.isNotBlank(employQueryDTO.getUserName())) {
        predicates.add(criteriaBuilder.like(root.get("userName").as(String.class), "%" + employQueryDTO.getUserName() + "%"));
    }
    if (null != employQueryDTO.getCreateTimeStart()) {
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("entryDate").as(Date.class), employQueryDTO.getCreateTimeStart()));
    }
    if (null != employQueryDTO.getCreateTimeEnd()) {
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("entryDate").as(Date.class), employQueryDTO.getCreateTimeEnd()));
    }
    Predicate[] pre = new Predicate[predicates.size()];
    return query.where(predicates.toArray(pre)).getRestriction();
}


public String getDeptName(){
    return deptName;
}


public String getEmpName(){
    return empName;
}


public Date getCreateTimeStart(){
    return createTimeStart;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getIdcard(){
    return idcard;
}


public String getEmpNo(){
    return empNo;
}


public void setUserName(String userName){
    this.userName = userName;
}


public String getUserName(){
    return userName;
}


public void setCreateTimeEnd(Date createTimeEnd){
    this.createTimeEnd = createTimeEnd;
}


public void setCreateTimeStart(Date createTimeStart){
    this.createTimeStart = createTimeStart;
}


}