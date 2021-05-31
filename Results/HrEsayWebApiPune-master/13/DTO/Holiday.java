import javax.persistence;
public class Holiday {

 private  int holidayId;

 private  int calYrId;

 private  int companyId;

 private  String locId;

 private  String holidayFromdt;

 private  String holidayTodt;

 private  String holidayRemark;

 private  int delStatus;

 private  int isActive;

 private  int makerUserId;

 private  String makerEnterDatetime;

 private  int exInt1;

 private  int exInt2;

 private  int exInt3;

 private  String exVar1;

 private  String exVar2;

 private  String exVar3;

 private  boolean isError;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt3(){
    return exInt3;
}


public int getHolidayId(){
    return holidayId;
}


public int getExInt1(){
    return exInt1;
}


public String getLocId(){
    return locId;
}


public String getExVar1(){
    return exVar1;
}


public String getHolidayRemark(){
    return holidayRemark;
}


public String getHolidayTodt(){
    return holidayTodt;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getMakerUserId(){
    return makerUserId;
}


public int getIsActive(){
    return isActive;
}


public int getCalYrId(){
    return calYrId;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


public String getHolidayFromdt(){
    return holidayFromdt;
}


public void setError(boolean isError){
    this.isError = isError;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ holidayId).concat("/setError"));

.queryParam("isError",isError);
restTemplate.put(builder.toUriString(),null);
}


}