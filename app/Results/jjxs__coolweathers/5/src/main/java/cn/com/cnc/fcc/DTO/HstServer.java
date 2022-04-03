package cn.com.cnc.fcc.DTO;
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


public long getSerialversionuid(){
    return serialVersionUID;
}


}