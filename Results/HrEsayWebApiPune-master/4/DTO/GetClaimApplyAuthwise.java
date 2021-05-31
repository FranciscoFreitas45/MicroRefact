import javax.persistence.Entity;
import javax.persistence.Id;
public class GetClaimApplyAuthwise {

 private  int caHeadId;

 private  int empId;

 private  String empCode;

 private  String empName;

 private  int projId;

 private  String claimTitle;

 private  String projectTitle;

 private  String caFromDt;

 private  String caToDt;

 private  int claimStatus;

 private  float claimAmount;

 private  String circulatedTo;

 private  String exVar1;

 private  int caIniAuthEmpId;

 private  int caFinAuthEmpId;

 private  String empPhoto;


public String getExVar1(){
    return exVar1;
}


public int getCaIniAuthEmpId(){
    return caIniAuthEmpId;
}


public int getCaHeadId(){
    return caHeadId;
}


public float getClaimAmount(){
    return claimAmount;
}


public String getEmpName(){
    return empName;
}


public String getCirculatedTo(){
    return circulatedTo;
}


public String getCaFromDt(){
    return caFromDt;
}


public String getCaToDt(){
    return caToDt;
}


public int getClaimStatus(){
    return claimStatus;
}


public int getCaFinAuthEmpId(){
    return caFinAuthEmpId;
}


public String getProjectTitle(){
    return projectTitle;
}


public String getClaimTitle(){
    return claimTitle;
}


public int getEmpId(){
    return empId;
}


public String getEmpPhoto(){
    return empPhoto;
}


public String getEmpCode(){
    return empCode;
}


public int getProjId(){
    return projId;
}


}