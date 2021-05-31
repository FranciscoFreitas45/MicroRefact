import javax.persistence;
public class LoanDetails {

 private  int id;

 private  int loanMainId;

 private  int months;

 private  int years;

 private  String payType;

 private  int amountEmi;

 private  String remarks;

 private  String loginTime;

 private  String loginName;

 private  String skippMonthYear;

 private  double skippAmoount;

 private  String skippRemark;

 private  int delStatus;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://18";


public String getSkippMonthYear(){
    return skippMonthYear;
}


public int getId(){
    return id;
}


public int getMonths(){
    return months;
}


public String getPayType(){
    return payType;
}


public String getLoginTime(){
    return loginTime;
}


public int getYears(){
    return years;
}


public String getRemarks(){
    return remarks;
}


public int getAmountEmi(){
    return amountEmi;
}


public String getLoginName(){
    return loginName;
}


public int getLoanMainId(){
    return loanMainId;
}


public int getDelStatus(){
    return delStatus;
}


public String getSkippRemark(){
    return skippRemark;
}


public double getSkippAmoount(){
    return skippAmoount;
}


public void setLoanMainId(int loanMainId){
    this.loanMainId = loanMainId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLoanMainId"));

.queryParam("loanMainId",loanMainId);
restTemplate.put(builder.toUriString(),null);
}


public void setMonths(int months){
    this.months = months;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMonths"));

.queryParam("months",months);
restTemplate.put(builder.toUriString(),null);
}


public void setYears(int years){
    this.years = years;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setYears"));

.queryParam("years",years);
restTemplate.put(builder.toUriString(),null);
}


public void setPayType(String payType){
    this.payType = payType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPayType"));

.queryParam("payType",payType);
restTemplate.put(builder.toUriString(),null);
}


public void setLoginName(String loginName){
    this.loginName = loginName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLoginName"));

.queryParam("loginName",loginName);
restTemplate.put(builder.toUriString(),null);
}


public void setLoginTime(String loginTime){
    this.loginTime = loginTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLoginTime"));

.queryParam("loginTime",loginTime);
restTemplate.put(builder.toUriString(),null);
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDelStatus"));

.queryParam("delStatus",delStatus);
restTemplate.put(builder.toUriString(),null);
}


public void setAmountEmi(int amountEmi){
    this.amountEmi = amountEmi;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAmountEmi"));

.queryParam("amountEmi",amountEmi);
restTemplate.put(builder.toUriString(),null);
}


public void setSkippAmoount(double skippAmoount){
    this.skippAmoount = skippAmoount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setSkippAmoount"));

.queryParam("skippAmoount",skippAmoount);
restTemplate.put(builder.toUriString(),null);
}


public void setSkippMonthYear(String skippMonthYear){
    this.skippMonthYear = skippMonthYear;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setSkippMonthYear"));

.queryParam("skippMonthYear",skippMonthYear);
restTemplate.put(builder.toUriString(),null);
}


public void setRemarks(String remarks){
    this.remarks = remarks;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRemarks"));

.queryParam("remarks",remarks);
restTemplate.put(builder.toUriString(),null);
}


}