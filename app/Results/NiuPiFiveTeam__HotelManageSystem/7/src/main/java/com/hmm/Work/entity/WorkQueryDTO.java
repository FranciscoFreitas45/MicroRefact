package com.hmm.Work.entity;
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
import com.hmm.calendars.entity.SchedulEvent;
import com.hmm.department.entity.Department;
import com.hmm.employee.entity.Employee;
import com.hmm.Interface.Department;
public class WorkQueryDTO {

@DateTimeFormat(pattern = "yyyy-MM-dd")
 private  Date ontudytime;

@DateTimeFormat(pattern = "yyyy-MM-dd")
 private  Date offdutytime;

 private  Integer late;

 private  Integer lackCard;

 private  Integer leaveEarly;

 private  String empNo;

 private  String userName;

 private  String empName;

 private  String deptName;

 private  String calendar;

 private  String workDate;

 private  String requestType;

 private  Department department;


public Department getDepartment(){
    return department;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


public Integer getLackCard(){
    return lackCard;
}


@SuppressWarnings("serial")
public Specification<Work> getWhereClause(WorkQueryDTO workQueryDTO){
    return new Specification<Work>() {

        public Predicate toPredicate(Root<Work> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            if (workQueryDTO.getOntudytime() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("workDate").as(Date.class), workQueryDTO.getOntudytime()));
            }
            if (workQueryDTO.getOffdutytime() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("workDate").as(Date.class), workQueryDTO.getOffdutytime()));
            }
            if (StringUtils.isNotBlank(workQueryDTO.getWorkDate())) {
                predicates.add(criteriaBuilder.equal(root.get("workDate").as(String.class), workQueryDTO.getWorkDate()));
            }
            if (StringUtils.isNotBlank(workQueryDTO.getCalendar())) {
                predicates.add(criteriaBuilder.equal(root.get("calendar").as(String.class), workQueryDTO.getCalendar()));
            }
            if (null != workQueryDTO.getLate()) {
                predicates.add(criteriaBuilder.equal(root.get("late").as(Integer.class), workQueryDTO.getLate()));
            }
            if (workQueryDTO.getLackCard() != null) {
                predicates.add(criteriaBuilder.equal(root.get("lackCard").as(Integer.class), workQueryDTO.getLackCard()));
            }
            if (workQueryDTO.getLeaveEarly() != null) {
                predicates.add(criteriaBuilder.equal(root.get("leaveEarly").as(Integer.class), workQueryDTO.getLeaveEarly()));
            }
            if (StringUtils.isNotBlank(workQueryDTO.getEmpName())) {
                Join<Employee, Work> join = root.join("employ", JoinType.LEFT);
                predicates.add(criteriaBuilder.like(join.get("empName").as(String.class), "%" + workQueryDTO.getEmpName() + "%"));
            }
            if (StringUtils.isNotBlank(workQueryDTO.getEmpNo())) {
                Join<Employee, Work> join = root.join("employ", JoinType.LEFT);
                criteriaBuilder.like(join.get("empNo").as(String.class), workQueryDTO.getEmpNo());
            }
            if (StringUtils.isNotBlank(workQueryDTO.getUserName())) {
                Join<Employee, Work> join = root.join("employ", JoinType.LEFT);
                predicates.add(criteriaBuilder.like(join.get("userName").as(String.class), workQueryDTO.getUserName()));
            }
            if (workQueryDTO.getDepartment() != null) {
                Join<Employee, Work> join = root.join("employ", JoinType.LEFT);
                predicates.add(criteriaBuilder.equal(join.get("departmentes").as(Department.class), workQueryDTO.getDepartment()));
            }
            // if(workQueryDTO.getDeptName()!= null) {
            // Root<Employee> root2 = null;
            // @SuppressWarnings("null")
            // Join<Employee, Department> join = root2.join("departmentes",JoinType.INNER);
            // Join<Work, Employee> join2 = join.join("employ",JoinType.LEFT);
            // 
            // criteriaBuilder.equal(join2.get("deptName").as(Department.class), workQueryDTO.getDeptName());
            // }
            // 
            Predicate[] pre = new Predicate[predicates.size()];
            return query.where(predicates.toArray(pre)).getRestriction();
        }
    };
}


public void setLeaveEarly(Integer leaveEarly){
    this.leaveEarly = leaveEarly;
}


public String getCalendar(){
    return calendar;
}


public void setLackCard(Integer lackCard){
    this.lackCard = lackCard;
}


public String getEmpName(){
    return empName;
}


public String getRequestType(){
    return requestType;
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


public Date getOffdutytime(){
    return offdutytime;
}


public Integer getLate(){
    return late;
}


public Integer getLeaveEarly(){
    return leaveEarly;
}


public void setLate(Integer late){
    this.late = late;
}


public void setDepartment(Department department){
    this.department = department;
}


public String getDeptName(){
    return deptName;
}


public Predicate toPredicate(Root<Work> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<>();
    if (workQueryDTO.getOntudytime() != null) {
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("workDate").as(Date.class), workQueryDTO.getOntudytime()));
    }
    if (workQueryDTO.getOffdutytime() != null) {
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("workDate").as(Date.class), workQueryDTO.getOffdutytime()));
    }
    if (StringUtils.isNotBlank(workQueryDTO.getWorkDate())) {
        predicates.add(criteriaBuilder.equal(root.get("workDate").as(String.class), workQueryDTO.getWorkDate()));
    }
    if (StringUtils.isNotBlank(workQueryDTO.getCalendar())) {
        predicates.add(criteriaBuilder.equal(root.get("calendar").as(String.class), workQueryDTO.getCalendar()));
    }
    if (null != workQueryDTO.getLate()) {
        predicates.add(criteriaBuilder.equal(root.get("late").as(Integer.class), workQueryDTO.getLate()));
    }
    if (workQueryDTO.getLackCard() != null) {
        predicates.add(criteriaBuilder.equal(root.get("lackCard").as(Integer.class), workQueryDTO.getLackCard()));
    }
    if (workQueryDTO.getLeaveEarly() != null) {
        predicates.add(criteriaBuilder.equal(root.get("leaveEarly").as(Integer.class), workQueryDTO.getLeaveEarly()));
    }
    if (StringUtils.isNotBlank(workQueryDTO.getEmpName())) {
        Join<Employee, Work> join = root.join("employ", JoinType.LEFT);
        predicates.add(criteriaBuilder.like(join.get("empName").as(String.class), "%" + workQueryDTO.getEmpName() + "%"));
    }
    if (StringUtils.isNotBlank(workQueryDTO.getEmpNo())) {
        Join<Employee, Work> join = root.join("employ", JoinType.LEFT);
        criteriaBuilder.like(join.get("empNo").as(String.class), workQueryDTO.getEmpNo());
    }
    if (StringUtils.isNotBlank(workQueryDTO.getUserName())) {
        Join<Employee, Work> join = root.join("employ", JoinType.LEFT);
        predicates.add(criteriaBuilder.like(join.get("userName").as(String.class), workQueryDTO.getUserName()));
    }
    if (workQueryDTO.getDepartment() != null) {
        Join<Employee, Work> join = root.join("employ", JoinType.LEFT);
        predicates.add(criteriaBuilder.equal(join.get("departmentes").as(Department.class), workQueryDTO.getDepartment()));
    }
    // if(workQueryDTO.getDeptName()!= null) {
    // Root<Employee> root2 = null;
    // @SuppressWarnings("null")
    // Join<Employee, Department> join = root2.join("departmentes",JoinType.INNER);
    // Join<Work, Employee> join2 = join.join("employ",JoinType.LEFT);
    // 
    // criteriaBuilder.equal(join2.get("deptName").as(Department.class), workQueryDTO.getDeptName());
    // }
    // 
    Predicate[] pre = new Predicate[predicates.size()];
    return query.where(predicates.toArray(pre)).getRestriction();
}


public void setOntudytime(Date ontudytime){
    this.ontudytime = ontudytime;
}


public void setRequestType(String requestType){
    this.requestType = requestType;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setCalendar(String calendar){
    this.calendar = calendar;
}


public String getWorkDate(){
    return workDate;
}


public void setOffdutytime(Date offdutytime){
    this.offdutytime = offdutytime;
}


public Date getOntudytime(){
    return ontudytime;
}


public void setWorkDate(String workDate){
    this.workDate = workDate;
}


}