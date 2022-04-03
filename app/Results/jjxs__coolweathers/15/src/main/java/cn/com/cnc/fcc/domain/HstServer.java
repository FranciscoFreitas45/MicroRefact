package cn.com.cnc.fcc.domain;
 import java.io.Serializable;
public class HstServer implements Serializable{

 private  long serialVersionUID;

 private  HstServerInfo hstServerInfo;

 private  HstServerInfoDetails hstServerInfoDetails;

public HstServer() {
}public HstServer(HstServerInfo hstServerInfo) {
    HstServerInfoDetails hstServerInfoDetails = new HstServerInfoDetails();
    this.hstServerInfo = hstServerInfo;
    this.hstServerInfoDetails = hstServerInfoDetails;
}public HstServer(HstServerInfoDetails hstServerInfoDetails) {
    HstServerInfo hstServerInfo = new HstServerInfo();
    this.hstServerInfo = hstServerInfo;
    this.hstServerInfoDetails = hstServerInfoDetails;
}public HstServer(HstServerInfo hstServerInfo, HstServerInfoDetails hstServerInfoDetails) {
    this.hstServerInfo = hstServerInfo;
    this.hstServerInfoDetails = hstServerInfoDetails;
}
public HstServerInfo getHstServerInfo(){
    return hstServerInfo;
}


public HstServerInfoDetails getHstServerInfoDetails(){
    return hstServerInfoDetails;
}


public void setHstServerInfoDetails(HstServerInfoDetails hstServerInfoDetails){
    this.hstServerInfoDetails = hstServerInfoDetails;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public void setHstServerInfo(HstServerInfo hstServerInfo){
    this.hstServerInfo = hstServerInfo;
}


}