public class GetYearlyAdvanceNew {

 private  int empId;

 private  String empName;

 private  String empCode;

 private  String janCount;

 private  String febCount;

 private  String marchCount;

 private  String aprCount;

 private  String mayCount;

 private  String junCount;

 private  String julCount;

 private  String augCount;

 private  String sepCount;

 private  String octCount;

 private  String novCount;

 private  String decCount;

 private  double total;


public void setTotal(double total){
    this.total = total;
}


public void setSepCount(String sepCount){
    this.sepCount = sepCount;
}


public String getJanCount(){
    return janCount;
}


public String getFebCount(){
    return febCount;
}


public void setOctCount(String octCount){
    this.octCount = octCount;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setMarchCount(String marchCount){
    this.marchCount = marchCount;
}


public String getEmpName(){
    return empName;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getOctCount(){
    return octCount;
}


public String getDecCount(){
    return decCount;
}


public void setAugCount(String augCount){
    this.augCount = augCount;
}


public void setNovCount(String novCount){
    this.novCount = novCount;
}


public String getMarchCount(){
    return marchCount;
}


public String getAugCount(){
    return augCount;
}


public String getSepCount(){
    return sepCount;
}


public void setDecCount(String decCount){
    this.decCount = decCount;
}


public void setJanCount(String janCount){
    this.janCount = janCount;
}


public String getNovCount(){
    return novCount;
}


public void setMayCount(String mayCount){
    this.mayCount = mayCount;
}


public void setAprCount(String aprCount){
    this.aprCount = aprCount;
}


public void setJulCount(String julCount){
    this.julCount = julCount;
}


public String getJunCount(){
    return junCount;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setJunCount(String junCount){
    this.junCount = junCount;
}


public void setFebCount(String febCount){
    this.febCount = febCount;
}


public String getAprCount(){
    return aprCount;
}


public String getEmpCode(){
    return empCode;
}


public String getMayCount(){
    return mayCount;
}


public double getTotal(){
    return total;
}


@Override
public String toString(){
    return "GetYearlyAdvanceNew [empId=" + empId + ", empName=" + empName + ", empCode=" + empCode + ", janCount=" + janCount + ", febCount=" + febCount + ", marchCount=" + marchCount + ", aprCount=" + aprCount + ", mayCount=" + mayCount + ", junCount=" + junCount + ", julCount=" + julCount + ", augCount=" + augCount + ", sepCount=" + sepCount + ", octCount=" + octCount + ", novCount=" + novCount + ", decCount=" + decCount + ", total=" + total + "]";
}


public String getJulCount(){
    return julCount;
}


}