import javax.persistence;
public class Advance {

 private  int id;

 private  int cmpId;

 private  int empId;

 private  String voucherNo;

 private  String advDate;

 private  double advAmount;

 private  double advRemainingAmount;

 private  String advRemarks;

 private  int dedMonth;

 private  int isDed;

 private  int isUsed;

 private  String loginName;

 private  String loginTime;

 private  int skipId;

 private  String skipLoginName;

 private  String skipLoginTime;

 private  int dedYear;

 private  String skipRemarks;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;

 private  int delStatus;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://21";


public String getExVar2(){
    return exVar2;
}


public String getSkipLoginName(){
    return skipLoginName;
}


public int getExInt2(){
    return exInt2;
}


public String getSkipLoginTime(){
    return skipLoginTime;
}


public int getExInt1(){
    return exInt1;
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


public int getDedMonth(){
    return dedMonth;
}


public int getId(){
    return id;
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


public int getSkipId(){
    return skipId;
}


public String getSkipRemarks(){
    return skipRemarks;
}


public int getDedYear(){
    return dedYear;
}


public int getEmpId(){
    return empId;
}


public String getLoginName(){
    return loginName;
}


public int getDelStatus(){
    return delStatus;
}


public String getVoucherNo(){
    return voucherNo;
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


public void setAdvAmount(double advAmount){
    this.advAmount = advAmount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAdvAmount"));

.queryParam("advAmount",advAmount);
restTemplate.put(builder.toUriString(),null);
}


public void setAdvDate(String advDate){
    this.advDate = advDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAdvDate"));

.queryParam("advDate",advDate);
restTemplate.put(builder.toUriString(),null);
}


public void setAdvRemainingAmount(double advRemainingAmount){
    this.advRemainingAmount = advRemainingAmount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAdvRemainingAmount"));

.queryParam("advRemainingAmount",advRemainingAmount);
restTemplate.put(builder.toUriString(),null);
}


public void setAdvRemarks(String advRemarks){
    this.advRemarks = advRemarks;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAdvRemarks"));

.queryParam("advRemarks",advRemarks);
restTemplate.put(builder.toUriString(),null);
}


public void setCmpId(int cmpId){
    this.cmpId = cmpId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCmpId"));

.queryParam("cmpId",cmpId);
restTemplate.put(builder.toUriString(),null);
}


public void setEmpId(int empId){
    this.empId = empId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmpId"));

.queryParam("empId",empId);
restTemplate.put(builder.toUriString(),null);
}


public void setDedMonth(int dedMonth){
    this.dedMonth = dedMonth;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDedMonth"));

.queryParam("dedMonth",dedMonth);
restTemplate.put(builder.toUriString(),null);
}


public void setDedYear(int dedYear){
    this.dedYear = dedYear;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDedYear"));

.queryParam("dedYear",dedYear);
restTemplate.put(builder.toUriString(),null);
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setExInt1"));

.queryParam("exInt1",exInt1);
restTemplate.put(builder.toUriString(),null);
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setExVar1"));

.queryParam("exVar1",exVar1);
restTemplate.put(builder.toUriString(),null);
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setExVar2"));

.queryParam("exVar2",exVar2);
restTemplate.put(builder.toUriString(),null);
}


public void setVoucherNo(String voucherNo){
    this.voucherNo = voucherNo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setVoucherNo"));

.queryParam("voucherNo",voucherNo);
restTemplate.put(builder.toUriString(),null);
}


public void setIsDed(int isDed){
    this.isDed = isDed;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setIsDed"));

.queryParam("isDed",isDed);
restTemplate.put(builder.toUriString(),null);
}


public void setIsUsed(int isUsed){
    this.isUsed = isUsed;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setIsUsed"));

.queryParam("isUsed",isUsed);
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


public void setSkipId(int skipId){
    this.skipId = skipId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setSkipId"));

.queryParam("skipId",skipId);
restTemplate.put(builder.toUriString(),null);
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDelStatus"));

.queryParam("delStatus",delStatus);
restTemplate.put(builder.toUriString(),null);
}


}