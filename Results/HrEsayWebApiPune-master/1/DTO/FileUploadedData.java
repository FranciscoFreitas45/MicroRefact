public class FileUploadedData {

 private  String empCode;

 private  String empName;

 private  String logDate;

 private  String inTime;

 private  String outTime;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public String getInTime(){
    return inTime;
}


public String getEmpCode(){
    return empCode;
}


public String getOutTime(){
    return outTime;
}


public String getLogDate(){
    return logDate;
}


public String getEmpName(){
    return empName;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEmpCode"));

.queryParam("empCode",empCode);
restTemplate.put(builder.toUriString(),null);
}


public void setEmpName(String empName){
    this.empName = empName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEmpName"));

.queryParam("empName",empName);
restTemplate.put(builder.toUriString(),null);
}


public void setLogDate(String logDate){
    this.logDate = logDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLogDate"));

.queryParam("logDate",logDate);
restTemplate.put(builder.toUriString(),null);
}


public void setInTime(String inTime){
    this.inTime = inTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInTime"));

.queryParam("inTime",inTime);
restTemplate.put(builder.toUriString(),null);
}


public void setOutTime(String outTime){
    this.outTime = outTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOutTime"));

.queryParam("outTime",outTime);
restTemplate.put(builder.toUriString(),null);
}


}