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


public long getSerialversionuid(){
    return serialVersionUID;
}


}