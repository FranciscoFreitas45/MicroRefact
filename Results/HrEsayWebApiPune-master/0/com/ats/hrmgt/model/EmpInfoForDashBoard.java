import javax.persistence;
@Entity
public class EmpInfoForDashBoard {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "emp_name")
 private  String empName;

@Column(name = "designation_name")
 private  String designationName;

@Column(name = "department_name")
 private  String departmentName;

@Column(name = "contact_no")
 private  String contactNo;

@Column(name = "profile_pic")
 private  String profilePic;


public void setContactNo(String contactNo){
    this.contactNo = contactNo;
}


public String getDepartmentName(){
    return departmentName;
}


public String getContactNo(){
    return contactNo;
}


public String getDesignationName(){
    return designationName;
}


public void setProfilePic(String profilePic){
    this.profilePic = profilePic;
}


public int getEmpId(){
    return empId;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpName(){
    return empName;
}


public String getProfilePic(){
    return profilePic;
}


public String getEmpCode(){
    return empCode;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public void setDepartmentName(String departmentName){
    this.departmentName = departmentName;
}


@Override
public String toString(){
    return "EmpInfoForDashBoard [empId=" + empId + ", empCode=" + empCode + ", empName=" + empName + ", designationName=" + designationName + ", departmentName=" + departmentName + ", contactNo=" + contactNo + ", profilePic=" + profilePic + "]";
}


public void setDesignationName(String designationName){
    this.designationName = designationName;
}


}