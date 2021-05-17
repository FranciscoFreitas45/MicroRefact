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
import com.hmm.employee.entity.Employee;
public class LeaveQueryDTO {

 private  String userId;

 private  String userName;

 private  String empNo;

 private  String empName;

 private  String deptName;

 private  Department department;

@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
 private  Date startTime;

@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
 private  Date endTime;


public Department getDepartment(){
    return department;
}


public void setDepartment(Department department){
    this.department = department;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


@SuppressWarnings({ "serial" })
public Specification<Leave> getWhereClause(LeaveQueryDTO leaveQueryDTO){
    return new Specification<Leave>() {

        @Override
        public Predicate toPredicate(Root<Leave> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicate = new ArrayList<>();
            if (StringUtils.isNotBlank(leaveQueryDTO.getUserId())) {
                predicate.add(criteriaBuilder.equal(root.get("userId").as(String.class), leaveQueryDTO.getUserId()));
            }
            if (null != leaveQueryDTO.getStartTime()) {
                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime").as(Date.class), leaveQueryDTO.getStartTime()));
            }
            if (null != leaveQueryDTO.getEndTime()) {
                predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("endTime").as(Date.class), leaveQueryDTO.getEndTime()));
            }
            if (StringUtils.isNotBlank(leaveQueryDTO.getEmpName())) {
                Join<Leave, Employee> join = root.join("employ", JoinType.LEFT);
                criteriaBuilder.like(join.get("empNo").as(String.class), "%" + leaveQueryDTO.getEmpName() + "%");
            }
            if (StringUtils.isNotBlank(leaveQueryDTO.getEmpNo())) {
                Join<Leave, Employee> join = root.join("employ", JoinType.LEFT);
                criteriaBuilder.equal(join.get("empNo").as(String.class), leaveQueryDTO.getEmpNo());
            }
            if (StringUtils.isNotBlank(leaveQueryDTO.getUserName())) {
                Join<Leave, Employee> join = root.join("employ", JoinType.LEFT);
                criteriaBuilder.equal(join.get("userName").as(String.class), leaveQueryDTO.getUserName());
            }
            if (leaveQueryDTO.getDepartment() != null) {
                Join<Leave, Employee> join = root.join("employ", JoinType.LEFT);
                criteriaBuilder.equal(join.get("departmentes").as(Department.class), leaveQueryDTO.getDepartment());
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
public Predicate toPredicate(Root<Leave> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicate = new ArrayList<>();
    if (StringUtils.isNotBlank(leaveQueryDTO.getUserId())) {
        predicate.add(criteriaBuilder.equal(root.get("userId").as(String.class), leaveQueryDTO.getUserId()));
    }
    if (null != leaveQueryDTO.getStartTime()) {
        predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime").as(Date.class), leaveQueryDTO.getStartTime()));
    }
    if (null != leaveQueryDTO.getEndTime()) {
        predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("endTime").as(Date.class), leaveQueryDTO.getEndTime()));
    }
    if (StringUtils.isNotBlank(leaveQueryDTO.getEmpName())) {
        Join<Leave, Employee> join = root.join("employ", JoinType.LEFT);
        criteriaBuilder.like(join.get("empNo").as(String.class), "%" + leaveQueryDTO.getEmpName() + "%");
    }
    if (StringUtils.isNotBlank(leaveQueryDTO.getEmpNo())) {
        Join<Leave, Employee> join = root.join("employ", JoinType.LEFT);
        criteriaBuilder.equal(join.get("empNo").as(String.class), leaveQueryDTO.getEmpNo());
    }
    if (StringUtils.isNotBlank(leaveQueryDTO.getUserName())) {
        Join<Leave, Employee> join = root.join("employ", JoinType.LEFT);
        criteriaBuilder.equal(join.get("userName").as(String.class), leaveQueryDTO.getUserName());
    }
    if (leaveQueryDTO.getDepartment() != null) {
        Join<Leave, Employee> join = root.join("employ", JoinType.LEFT);
        criteriaBuilder.equal(join.get("departmentes").as(Department.class), leaveQueryDTO.getDepartment());
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


public Date getEndTime(){
    return endTime;
}


public String getEmpNo(){
    return empNo;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public String getUserName(){
    return userName;
}


public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}