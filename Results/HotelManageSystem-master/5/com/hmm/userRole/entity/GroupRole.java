import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.hmm.department.entity.Department;
import com.hmm.employee.entity.Employee;
@Entity
@Table(name = "t_groupRole")
public class GroupRole {

 private  Integer groupTable_id;

 private  String groupName;

 private  String groupId;

 private  Department department;

 private  Float money;

 private  List<Employee> employs;


@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "groupRoles")
public List<Employee> getEmploys(){
    return employs;
}


public String getGroupName(){
    return groupName;
}


public void setGroupName(String groupName){
    this.groupName = groupName;
}


public void setMoney(Float money){
    this.money = money;
}


public void setEmploys(List<Employee> employs){
    this.employs = employs;
}


public void setGroupId(String groupId){
    this.groupId = groupId;
}


@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
@JoinColumn(name = "dept_id")
public Department getDepartment(){
    return department;
}


public void setGroupTable_id(Integer groupTable_id){
    this.groupTable_id = groupTable_id;
}


public void setDepartment(Department department){
    this.department = department;
}


public Float getMoney(){
    return money;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Integer getGroupTable_id(){
    return groupTable_id;
}


public String getGroupId(){
    return groupId;
}


}