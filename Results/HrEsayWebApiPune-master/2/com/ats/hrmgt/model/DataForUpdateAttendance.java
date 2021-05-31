import java.util.List;
public class DataForUpdateAttendance {

 private  String fromDate;

 private  String toDate;

 private  int month;

 private  int year;

 private  int userId;

 private  int empId;

 private  List<FileUploadedData> fileUploadedDataList;


public void setFromDate(String fromDate){
    this.fromDate = fromDate;
}


public void setMonth(int month){
    this.month = month;
}


public List<FileUploadedData> getFileUploadedDataList(){
    return fileUploadedDataList;
}


public void setFileUploadedDataList(List<FileUploadedData> fileUploadedDataList){
    this.fileUploadedDataList = fileUploadedDataList;
}


public String getFromDate(){
    return fromDate;
}


public void setYear(int year){
    this.year = year;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setToDate(String toDate){
    this.toDate = toDate;
}


public int getYear(){
    return year;
}


public String getToDate(){
    return toDate;
}


@Override
public String toString(){
    return "DataForUpdateAttendance [fromDate=" + fromDate + ", toDate=" + toDate + ", month=" + month + ", year=" + year + ", userId=" + userId + ", empId=" + empId + ", fileUploadedDataList=" + fileUploadedDataList + "]";
}


public int getMonth(){
    return month;
}


public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


}