import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
public class GetEmployeeInfo {

 private  int empId;

 private  String empCode;

 private  String companyName;

 private  int companyId;

 private  int empCatId;

 private  String empCategory;

 private  int empTypeId;

 private  String empType;

 private  int empDeptId;

 private  String empDept;

 private  String empDeptShortName;

 private  String empTypeShortName;

 private  String empCatShortName;

 private  String empFname;

 private  String empMname;

 private  String empSname;

 private  String empMobile1;

 private  String empEmail;

 private  int empPrevExpYrs;

 private  float empRatePerhr;

 private  String exVar1;


public String getEmpMname(){
    return empMname;
}


public String getExVar1(){
    return exVar1;
}


public String getEmpCatShortName(){
    return empCatShortName;
}


public String getEmpDeptShortName(){
    return empDeptShortName;
}


public String getEmpSname(){
    return empSname;
}


public int getEmpPrevExpYrs(){
    return empPrevExpYrs;
}


public String getEmpCategory(){
    return empCategory;
}


public String getEmpType(){
    return empType;
}


public String getEmpDept(){
    return empDept;
}


public String getEmpFname(){
    return empFname;
}


public int getEmpTypeId(){
    return empTypeId;
}


public int getEmpDeptId(){
    return empDeptId;
}


public int getEmpCatId(){
    return empCatId;
}


public int getEmpId(){
    return empId;
}


public String getEmpCode(){
    return empCode;
}


public int getCompanyId(){
    return companyId;
}


public String getCompanyName(){
    return companyName;
}


public String getEmpMobile1(){
    return empMobile1;
}


public String getEmpEmail(){
    return empEmail;
}


public float getEmpRatePerhr(){
    return empRatePerhr;
}


public String getEmpTypeShortName(){
    return empTypeShortName;
}


}