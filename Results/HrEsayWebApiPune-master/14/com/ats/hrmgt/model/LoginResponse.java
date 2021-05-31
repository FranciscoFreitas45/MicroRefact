import javax.persistence;
@Entity
public class LoginResponse {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "first_name ")
 private  String firstName;

@Column(name = "middle_name")
 private  String middleName;

@Column(name = "surname")
 private  String surname;

@Column(name = "mother_name")
 private  String motherName;

@Column(name = "email_id")
 private  String emailId;

@Column(name = "user_id")
 private  int userId;

@Column(name = "loc_id")
 private  String locationIds;

@Column(name = "user_pwd")
 private  String userPwd;

@Column(name = "design_type")
 private  int designType;

@Column(name = "hod_dept_ids")
 private  String hodDeptIds;

@Column(name = "is_visit")
 private  int isVisit;

@Column(name = "emp_photo")
 private  String empPhoto;

@Column(name = "access_role_id")
 private  int accessRoleId;

@Transient
 private  boolean isError;


public int getAccessRoleId(){
    return accessRoleId;
}


public void setHodDeptIds(String hodDeptIds){
    this.hodDeptIds = hodDeptIds;
}


public void setAccessRoleId(int accessRoleId){
    this.accessRoleId = accessRoleId;
}


public void setUserPwd(String userPwd){
    this.userPwd = userPwd;
}


public void setEmailId(String emailId){
    this.emailId = emailId;
}


public String getEmailId(){
    return emailId;
}


public String getLocationIds(){
    return locationIds;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getMiddleName(){
    return middleName;
}


public void setIsError(boolean isError){
    this.isError = isError;
}


public void setSurname(String surname){
    this.surname = surname;
}


public void setMotherName(String motherName){
    this.motherName = motherName;
}


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


public String getMotherName(){
    return motherName;
}


public void setLocationIds(String locationIds){
    this.locationIds = locationIds;
}


public void setIsVisit(int isVisit){
    this.isVisit = isVisit;
}


public void setEmpPhoto(String empPhoto){
    this.empPhoto = empPhoto;
}


public int getEmpId(){
    return empId;
}


public int getIsVisit(){
    return isVisit;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpPhoto(){
    return empPhoto;
}


public int getDesignType(){
    return designType;
}


public String getEmpCode(){
    return empCode;
}


public boolean getIsError(){
    return isError;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public String getHodDeptIds(){
    return hodDeptIds;
}


public void setError(boolean isError){
    this.isError = isError;
}


public void setDesignType(int designType){
    this.designType = designType;
}


@Override
public String toString(){
    return "LoginResponse [empId=" + empId + ", empCode=" + empCode + ", firstName=" + firstName + ", middleName=" + middleName + ", surname=" + surname + ", motherName=" + motherName + ", emailId=" + emailId + ", userId=" + userId + ", locationIds=" + locationIds + ", userPwd=" + userPwd + ", designType=" + designType + ", hodDeptIds=" + hodDeptIds + ", isVisit=" + isVisit + ", empPhoto=" + empPhoto + ", accessRoleId=" + accessRoleId + ", isError=" + isError + "]";
}


public String getUserPwd(){
    return userPwd;
}


public String getFirstName(){
    return firstName;
}


public int getUserId(){
    return userId;
}


public String getSurname(){
    return surname;
}


public void setUserId(int userId){
    this.userId = userId;
}


}