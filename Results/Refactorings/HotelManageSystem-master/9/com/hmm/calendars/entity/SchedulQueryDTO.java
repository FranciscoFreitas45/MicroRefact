import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import com.hmm.department.entity.Department;
import com.hmm.employee.entity.Employee;
public class SchedulQueryDTO {

@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date traStartTime;

@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date traEndTime;

 private  String eventDate;

 private  String empNo;

 private  String empName;

 private  String userName;

 private  String calendar;

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


public Date getTraStartTime(){
    return traStartTime;
}


public Department getDepartment(){
    return department;
}


public void setDepartment(Department department){
    this.department = department;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


@SuppressWarnings("serial")
public Specification<SchedulEvent> getWhereClause(SchedulQueryDTO schedulQueryDTO){
    return new Specification<SchedulEvent>() {

        public Predicate toPredicate(Root<SchedulEvent> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            if (null != schedulQueryDTO.getTraStartTime()) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate").as(Date.class), schedulQueryDTO.getTraStartTime()));
            }
            if (null != schedulQueryDTO.getTraEndTime()) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("startDate").as(Date.class), schedulQueryDTO.getTraEndTime()));
            }
            if (null != schedulQueryDTO.getEventDate()) {
                predicates.add(criteriaBuilder.equal(root.get("eventDate").as(String.class), schedulQueryDTO.getEventDate()));
            }
            if (null != schedulQueryDTO.getEmpName()) {
                Join<Employee, SchedulEvent> join = root.join("employ", JoinType.LEFT);
                predicates.add(criteriaBuilder.like(join.get("empName").as(String.class), "%" + schedulQueryDTO.getEmpName() + "%"));
            }
            if (null != schedulQueryDTO.getUserName()) {
                Join<Employee, SchedulEvent> join = root.join("employ", JoinType.LEFT);
                predicates.add(criteriaBuilder.like(join.get("userName").as(String.class), "%" + schedulQueryDTO.getUserName() + "%"));
            }
            if (null != schedulQueryDTO.getEmpNo()) {
                Join<Employee, SchedulEvent> join = root.join("employ", JoinType.LEFT);
                predicates.add(criteriaBuilder.equal(join.get("empNo").as(String.class), schedulQueryDTO.getEmpNo()));
            }
            if (null != schedulQueryDTO.getCalendar()) {
                Join<Employee, SchedulEvent> join = root.join("calendar", JoinType.LEFT);
                predicates.add(criteriaBuilder.like(join.get("title").as(String.class), "%" + schedulQueryDTO.getCalendar() + "%"));
            }
            if (null != schedulQueryDTO.getDepartment()) {
                Join<Employee, SchedulEvent> join = root.join("employ", JoinType.LEFT);
                predicates.add(criteriaBuilder.equal(join.get("departmentes").as(Department.class), schedulQueryDTO.getDepartment()));
            }
            Predicate[] pre = new Predicate[predicates.size()];
            return query.where(predicates.toArray(pre)).getRestriction();
        }
    };
}


public String getCalendar(){
    return calendar;
}


public Predicate toPredicate(Root<SchedulEvent> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<>();
    if (null != schedulQueryDTO.getTraStartTime()) {
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate").as(Date.class), schedulQueryDTO.getTraStartTime()));
    }
    if (null != schedulQueryDTO.getTraEndTime()) {
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("startDate").as(Date.class), schedulQueryDTO.getTraEndTime()));
    }
    if (null != schedulQueryDTO.getEventDate()) {
        predicates.add(criteriaBuilder.equal(root.get("eventDate").as(String.class), schedulQueryDTO.getEventDate()));
    }
    if (null != schedulQueryDTO.getEmpName()) {
        Join<Employee, SchedulEvent> join = root.join("employ", JoinType.LEFT);
        predicates.add(criteriaBuilder.like(join.get("empName").as(String.class), "%" + schedulQueryDTO.getEmpName() + "%"));
    }
    if (null != schedulQueryDTO.getUserName()) {
        Join<Employee, SchedulEvent> join = root.join("employ", JoinType.LEFT);
        predicates.add(criteriaBuilder.like(join.get("userName").as(String.class), "%" + schedulQueryDTO.getUserName() + "%"));
    }
    if (null != schedulQueryDTO.getEmpNo()) {
        Join<Employee, SchedulEvent> join = root.join("employ", JoinType.LEFT);
        predicates.add(criteriaBuilder.equal(join.get("empNo").as(String.class), schedulQueryDTO.getEmpNo()));
    }
    if (null != schedulQueryDTO.getCalendar()) {
        Join<Employee, SchedulEvent> join = root.join("calendar", JoinType.LEFT);
        predicates.add(criteriaBuilder.like(join.get("title").as(String.class), "%" + schedulQueryDTO.getCalendar() + "%"));
    }
    if (null != schedulQueryDTO.getDepartment()) {
        Join<Employee, SchedulEvent> join = root.join("employ", JoinType.LEFT);
        predicates.add(criteriaBuilder.equal(join.get("departmentes").as(Department.class), schedulQueryDTO.getDepartment()));
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


public void setCalendar(String calendar){
    this.calendar = calendar;
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


public String getEventDate(){
    return eventDate;
}


public String getUserName(){
    return userName;
}


public void setEventDate(String eventDate){
    this.eventDate = eventDate;
}


}