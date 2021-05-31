import javax.persistence;
public class CalenderYear {

 private  int calYrId;

 private  String calYrFromDate;

 private  String calYrToDate;

 private  int isCurrent;

 private  int exInt1;

 private  int exInt2;

 private  int exInt3;

 private  String exVar1;

 private  String exVar2;

 private  String exVar3;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


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


public int getExInt1(){
    return exInt1;
}


public String getCalYrToDate(){
    return calYrToDate;
}


public String getExVar1(){
    return exVar1;
}


public String getCalYrFromDate(){
    return calYrFromDate;
}


public int getIsCurrent(){
    return isCurrent;
}


public int getCalYrId(){
    return calYrId;
}


public void setCalYrFromDate(String calYrFromDate){
    this.calYrFromDate = calYrFromDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ calYrId).concat("/setCalYrFromDate"));

.queryParam("calYrFromDate",calYrFromDate);
restTemplate.put(builder.toUriString(),null);
}


public void setCalYrToDate(String calYrToDate){
    this.calYrToDate = calYrToDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ calYrId).concat("/setCalYrToDate"));

.queryParam("calYrToDate",calYrToDate);
restTemplate.put(builder.toUriString(),null);
}


}