import java.util.List;
public class DataForUpdateAttendance {

 private  String fromDate;

 private  String toDate;

 private  int month;

 private  int year;

 private  int userId;

 private  int empId;

 private  List<FileUploadedData> fileUploadedDataList;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public List<FileUploadedData> getFileUploadedDataList(){
    return fileUploadedDataList;
}


public String getFromDate(){
    return fromDate;
}


public int getEmpId(){
    return empId;
}


public int getYear(){
    return year;
}


public String getToDate(){
    return toDate;
}


public int getMonth(){
    return month;
}


public int getUserId(){
    return userId;
}


public void setFromDate(String fromDate){
    this.fromDate = fromDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFromDate"));

.queryParam("fromDate",fromDate);
restTemplate.put(builder.toUriString(),null);
}


public void setToDate(String toDate){
    this.toDate = toDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setToDate"));

.queryParam("toDate",toDate);
restTemplate.put(builder.toUriString(),null);
}


public void setMonth(int month){
    this.month = month;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMonth"));

.queryParam("month",month);
restTemplate.put(builder.toUriString(),null);
}


public void setYear(int year){
    this.year = year;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setYear"));

.queryParam("year",year);
restTemplate.put(builder.toUriString(),null);
}


public void setUserId(int userId){
    this.userId = userId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserId"));

.queryParam("userId",userId);
restTemplate.put(builder.toUriString(),null);
}


public void setFileUploadedDataList(List<FileUploadedData> fileUploadedDataList){
    this.fileUploadedDataList = fileUploadedDataList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFileUploadedDataList"));

.queryParam("fileUploadedDataList",fileUploadedDataList);
restTemplate.put(builder.toUriString(),null);
}


public void setEmpId(int empId){
    this.empId = empId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEmpId"));

.queryParam("empId",empId);
restTemplate.put(builder.toUriString(),null);
}


}