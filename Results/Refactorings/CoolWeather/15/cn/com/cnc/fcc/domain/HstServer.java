import java.io.Serializable;
public class HstServer implements Serializable{

 private  long serialVersionUID;

 private  HstServerInfo hstServerInfo;

 private  HstServerInfoDetails hstServerInfoDetails;


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