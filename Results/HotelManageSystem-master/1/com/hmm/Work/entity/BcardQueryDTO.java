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
import jodd.util.StringUtil;
public class BcardQueryDTO {

@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date ontudytime;

@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date offdutytime;

 private  String empNo;

 private  String userName;

 private  String empName;

 private  String deptName;

 private  String calendar;

@DateTimeFormat(pattern = "yyyy-MM-dd")
 private  Date workDate;

 private  Department department;


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
public Specification<Bcard> getWhereClause(BcardQueryDTO workQueryDTO){
    return new Specification<Bcard>() {

        public Predicate toPredicate(Root<Bcard> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            if (workQueryDTO.getOntudytime() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("ontudytime").as(Date.class), workQueryDTO.getOntudytime()));
            }
            if (workQueryDTO.getOffdutytime() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("ontudytime").as(Date.class), workQueryDTO.getOffdutytime()));
            }
            if (workQueryDTO.getWorkDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("workDate").as(Date.class), workQueryDTO.getWorkDate()));
            }
            if (StringUtil.isNotBlank(workQueryDTO.getCalendar())) {
                predicates.add(criteriaBuilder.equal(root.get("calendar").as(String.class), workQueryDTO.getCalendar()));
            }
            if (StringUtils.isNotBlank(workQueryDTO.getEmpName())) {
                Join<Employee, Bcard> join = root.join("employ", JoinType.LEFT);
                predicates.add(criteriaBuilder.like(join.get("empName").as(String.class), "%" + workQueryDTO.getEmpName() + "%"));
            }
            if (StringUtils.isNotBlank(workQueryDTO.getEmpNo())) {
                Join<Employee, Bcard> join = root.join("employ", JoinType.LEFT);
                criteriaBuilder.like(join.get("empNo").as(String.class), workQueryDTO.getEmpNo());
            }
            if (StringUtils.isNotBlank(workQueryDTO.getUserName())) {
                Join<Employee, Bcard> join = root.join("employ", JoinType.LEFT);
                predicates.add(criteriaBuilder.like(join.get("userName").as(String.class), workQueryDTO.getUserName()));
            }
            if (workQueryDTO.getDepartment() != null) {
                Join<Employee, Bcard> join = root.join("employ", JoinType.LEFT);
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


public String getDeptName(){
    return deptName;
}


public String getCalendar(){
    return calendar;
}


public Predicate toPredicate(Root<Bcard> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<>();
    if (workQueryDTO.getOntudytime() != null) {
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("ontudytime").as(Date.class), workQueryDTO.getOntudytime()));
    }
    if (workQueryDTO.getOffdutytime() != null) {
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("ontudytime").as(Date.class), workQueryDTO.getOffdutytime()));
    }
    if (workQueryDTO.getWorkDate() != null) {
        predicates.add(criteriaBuilder.equal(root.get("workDate").as(Date.class), workQueryDTO.getWorkDate()));
    }
    if (StringUtil.isNotBlank(workQueryDTO.getCalendar())) {
        predicates.add(criteriaBuilder.equal(root.get("calendar").as(String.class), workQueryDTO.getCalendar()));
    }
    if (StringUtils.isNotBlank(workQueryDTO.getEmpName())) {
        Join<Employee, Bcard> join = root.join("employ", JoinType.LEFT);
        predicates.add(criteriaBuilder.like(join.get("empName").as(String.class), "%" + workQueryDTO.getEmpName() + "%"));
    }
    if (StringUtils.isNotBlank(workQueryDTO.getEmpNo())) {
        Join<Employee, Bcard> join = root.join("employ", JoinType.LEFT);
        criteriaBuilder.like(join.get("empNo").as(String.class), workQueryDTO.getEmpNo());
    }
    if (StringUtils.isNotBlank(workQueryDTO.getUserName())) {
        Join<Employee, Bcard> join = root.join("employ", JoinType.LEFT);
        predicates.add(criteriaBuilder.like(join.get("userName").as(String.class), workQueryDTO.getUserName()));
    }
    if (workQueryDTO.getDepartment() != null) {
        Join<Employee, Bcard> join = root.join("employ", JoinType.LEFT);
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


public String getEmpName(){
    return empName;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setCalendar(String calendar){
    this.calendar = calendar;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getEmpNo(){
    return empNo;
}


public Date getWorkDate(){
    return workDate;
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


public void setOffdutytime(Date offdutytime){
    this.offdutytime = offdutytime;
}


public Date getOntudytime(){
    return ontudytime;
}


public void setWorkDate(Date workDate){
    this.workDate = workDate;
}


}