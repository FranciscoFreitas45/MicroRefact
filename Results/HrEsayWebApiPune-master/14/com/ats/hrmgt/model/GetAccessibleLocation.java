import javax.persistence;
@Entity
public class GetAccessibleLocation {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "accessible_loc")
 private  String accessibleLoc;

@Column(name = "present_loc ")
 private  int presentLoc;

@Transient
 private  boolean isError;


public void setAccessibleLoc(String accessibleLoc){
    this.accessibleLoc = accessibleLoc;
}


public boolean isError(){
    return isError;
}


public String getAccessibleLoc(){
    return accessibleLoc;
}


public void setError(boolean isError){
    this.isError = isError;
}


public int getPresentLoc(){
    return presentLoc;
}


public void setPresentLoc(int presentLoc){
    this.presentLoc = presentLoc;
}


@Override
public String toString(){
    return "GetAccessibleLocation [empId=" + empId + ", accessibleLoc=" + accessibleLoc + ", presentLoc=" + presentLoc + ", isError=" + isError + "]";
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


}