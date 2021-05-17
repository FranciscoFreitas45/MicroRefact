public class QmsProductRelationTabTwoDTO {

 private  Long id;

 private  String organizationName;

 private  String technologyCd;

 private  String processCd;

 private  String prjName;

 private  String orderNo;

 private  String workGroupCd;

 private  String technologyName;

 private  String processName;

 private  String isControlPoint;

 private  String isTest;


public void setTechnologyName(String technologyName){
    this.technologyName = technologyName;
}


public String getProcessName(){
    return processName;
}


public String getOrderNo(){
    return orderNo;
}


public void setIsControlPoint(String isControlPoint){
    this.isControlPoint = isControlPoint;
}


public String getOrganizationName(){
    return organizationName;
}


public void setOrderNo(String orderNo){
    this.orderNo = orderNo;
}


public Long getId(){
    return id;
}


public void setPrjName(String prjName){
    this.prjName = prjName;
}


public void setIsTest(String isTest){
    this.isTest = isTest;
}


public String getTechnologyCd(){
    return technologyCd;
}


public void setProcessCd(String processCd){
    this.processCd = processCd;
}


public void setOrganizationName(String organizationName){
    this.organizationName = organizationName;
}


public String getTechnologyName(){
    return technologyName;
}


public void setProcessName(String processName){
    this.processName = processName;
}


public String getIsTest(){
    return isTest;
}


public String getWorkGroupCd(){
    return workGroupCd;
}


public String getProcessCd(){
    return processCd;
}


public String getPrjName(){
    return prjName;
}


public String getIsControlPoint(){
    return isControlPoint;
}


public void setId(Long id){
    this.id = id;
}


public void setTechnologyCd(String technologyCd){
    this.technologyCd = technologyCd;
}


public void setWorkGroupCd(String workGroupCd){
    this.workGroupCd = workGroupCd;
}


}