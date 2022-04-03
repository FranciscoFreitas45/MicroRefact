package com.hmm.travel.entity;
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
import com.hmm.department.entity.Department;
import com.hmm.employee.entity.Employee;
public class TravelQueryDTO {

 private  Date traStartTime;

 private  Date traEndTime;

 private  String empNo;

 private  String approval;

 private  String userName;

 private  String empName;

 private  String deptName;

 private  Department department;


public void setTraStartTime(Date traStartTime){
    this.traStartTime = traStartTime;
}


public Date getTraEndTime(){
    return traEndTime;
}


public void setTraEndTime(Date traEndTime){
    this.traEndTime = traEndTime;
}


public Department getDepartment(){
    return department;
}


public Date getTraStartTime(){
    return traStartTime;
}


public String getApproval(){
    return approval;
}


public void setDepartment(Department department){
    this.department = department;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


@SuppressWarnings("serial")
public Specification<Travel> getWhereClause(TravelQueryDTO travalQueryDTO){
    return new Specification<Travel>() {

        @Override
        public Predicate toPredicate(Root<Travel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicate = new ArrayList<>();
            if (StringUtils.isNotBlank(travalQueryDTO.getApproval())) {
                predicate.add(criteriaBuilder.equal(root.get("approval").as(String.class), travalQueryDTO.getApproval()));
            }
            if (StringUtils.isNotBlank(travalQueryDTO.getEmpNo())) {
                predicate.add(criteriaBuilder.equal(root.get("empNo").as(String.class), travalQueryDTO.getEmpNo()));
            }
            if (null != travalQueryDTO.getTraStartTime()) {
                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("traStartTime").as(Date.class), travalQueryDTO.getTraStartTime()));
            }
            if (null != travalQueryDTO.getTraEndTime()) {
                predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("traEndTime").as(Date.class), travalQueryDTO.getTraEndTime()));
            }
            if (StringUtils.isNotBlank(travalQueryDTO.getEmpName())) {
                Join<Travel, Employee> join = root.join("employ", JoinType.LEFT);
                criteriaBuilder.like(join.get("empNo").as(String.class), "%" + travalQueryDTO.getEmpName() + "%");
            }
            if (StringUtils.isNotBlank(travalQueryDTO.getEmpNo())) {
                Join<Travel, Employee> join = root.join("employ", JoinType.LEFT);
                criteriaBuilder.equal(join.get("empNo").as(String.class), travalQueryDTO.getEmpNo());
            }
            if (StringUtils.isNotBlank(travalQueryDTO.getUserName())) {
                Join<Travel, Employee> join = root.join("employ", JoinType.LEFT);
                criteriaBuilder.equal(join.get("userName").as(String.class), travalQueryDTO.getUserName());
            }
            if (travalQueryDTO.getDepartment() != null) {
                Join<Travel, Employee> join = root.join("employ", JoinType.LEFT);
                criteriaBuilder.equal(join.get("departmentes").as(Department.class), travalQueryDTO.getDepartment());
            }
            Predicate[] pre = new Predicate[predicate.size()];
            return query.where(predicate.toArray(pre)).getRestriction();
        }
    };
}


public String getDeptName(){
    return deptName;
}


@Override
public Predicate toPredicate(Root<Travel> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicate = new ArrayList<>();
    if (StringUtils.isNotBlank(travalQueryDTO.getApproval())) {
        predicate.add(criteriaBuilder.equal(root.get("approval").as(String.class), travalQueryDTO.getApproval()));
    }
    if (StringUtils.isNotBlank(travalQueryDTO.getEmpNo())) {
        predicate.add(criteriaBuilder.equal(root.get("empNo").as(String.class), travalQueryDTO.getEmpNo()));
    }
    if (null != travalQueryDTO.getTraStartTime()) {
        predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("traStartTime").as(Date.class), travalQueryDTO.getTraStartTime()));
    }
    if (null != travalQueryDTO.getTraEndTime()) {
        predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("traEndTime").as(Date.class), travalQueryDTO.getTraEndTime()));
    }
    if (StringUtils.isNotBlank(travalQueryDTO.getEmpName())) {
        Join<Travel, Employee> join = root.join("employ", JoinType.LEFT);
        criteriaBuilder.like(join.get("empNo").as(String.class), "%" + travalQueryDTO.getEmpName() + "%");
    }
    if (StringUtils.isNotBlank(travalQueryDTO.getEmpNo())) {
        Join<Travel, Employee> join = root.join("employ", JoinType.LEFT);
        criteriaBuilder.equal(join.get("empNo").as(String.class), travalQueryDTO.getEmpNo());
    }
    if (StringUtils.isNotBlank(travalQueryDTO.getUserName())) {
        Join<Travel, Employee> join = root.join("employ", JoinType.LEFT);
        criteriaBuilder.equal(join.get("userName").as(String.class), travalQueryDTO.getUserName());
    }
    if (travalQueryDTO.getDepartment() != null) {
        Join<Travel, Employee> join = root.join("employ", JoinType.LEFT);
        criteriaBuilder.equal(join.get("departmentes").as(Department.class), travalQueryDTO.getDepartment());
    }
    Predicate[] pre = new Predicate[predicate.size()];
    return query.where(predicate.toArray(pre)).getRestriction();
}


public String getEmpName(){
    return empName;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setEmpName(String empName){
    this.empName = empName;
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


public void setApproval(String approval){
    this.approval = approval;
}


}