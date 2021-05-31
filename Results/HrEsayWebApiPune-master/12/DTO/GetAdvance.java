import javax.persistence.Entity;
import javax.persistence.Id;
public class GetAdvance {

 private  int id;

 private  int cmpId;

 private  int empId;

 private  String voucherNo;

 private  String advDate;

 private  double advAmount;

 private  double advRemainingAmount;

 private  String advRemarks;

 private  String dedMonth;

 private  int isDed;

 private  int isUsed;

 private  String loginName;

 private  String loginTime;

 private  int skipId;

 private  String skipLoginName;

 private  String skipLoginTime;

 private  String dedYear;

 private  String skipRemarks;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;

 private  int delStatus;

 private  String empCode;

 private  String firstName;

 private  String middleName;

 private  String surname;

 private  String designation;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://21";


public String getExVar2(){
    return exVar2;
}


public String getSkipLoginTime(){
    return skipLoginTime;
}


public String getAdvDate(){
    return advDate;
}


public String getExVar1(){
    return exVar1;
}


public int getCmpId(){
    return cmpId;
}


public String getDedMonth(){
    return dedMonth;
}


public int getSkipId(){
    return skipId;
}


public String getSkipRemarks(){
    return skipRemarks;
}


public int getEmpId(){
    return empId;
}


public String getLoginName(){
    return loginName;
}


public double getAdvAmount(){
    return advAmount;
}


public double getAdvRemainingAmount(){
    return advRemainingAmount;
}


public int getIsUsed(){
    return isUsed;
}


public String getSkipLoginName(){
    return skipLoginName;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public int getId(){
    return id;
}


public String getMiddleName(){
    return middleName;
}


public String getLoginTime(){
    return loginTime;
}


public String getAdvRemarks(){
    return advRemarks;
}


public int getIsDed(){
    return isDed;
}


public String getDesignation(){
    return designation;
}


public String getDedYear(){
    return dedYear;
}


public String getEmpCode(){
    return empCode;
}


public String getVoucherNo(){
    return voucherNo;
}


public int getDelStatus(){
    return delStatus;
}


public String getFirstName(){
    return firstName;
}


public String getSurname(){
    return surname;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setExVar1"));

.queryParam("exVar1",exVar1);
restTemplate.put(builder.toUriString(),null);
}


public void setAdvDate(String advDate){
    this.advDate = advDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAdvDate"));

.queryParam("advDate",advDate);
restTemplate.put(builder.toUriString(),null);
}


}