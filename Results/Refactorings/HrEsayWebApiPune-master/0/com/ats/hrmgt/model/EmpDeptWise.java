import javax.persistence;
@Entity
public class EmpDeptWise {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  String id;

@Column(name = "dept_count")
 private  int deptCount;

@Column(name = "dept_name")
 private  String deptName;

@Column(name = "depart_id")
 private  int departId;


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public int getDepartId(){
    return departId;
}


public void setId(String id){
    this.id = id;
}


public void setDepartId(int departId){
    this.departId = departId;
}


public String getId(){
    return id;
}


@Override
public String toString(){
    return "EmpDeptWise [id=" + id + ", deptCount=" + deptCount + ", deptName=" + deptName + ", departId=" + departId + "]";
}


public int getDeptCount(){
    return deptCount;
}


public void setDeptCount(int deptCount){
    this.deptCount = deptCount;
}


public String getDeptName(){
    return deptName;
}


}