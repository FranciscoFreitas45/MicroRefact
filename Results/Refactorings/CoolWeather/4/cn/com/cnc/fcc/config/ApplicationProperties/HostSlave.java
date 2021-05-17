import org.springframework.boot.context.properties.ConfigurationProperties;
public class HostSlave {

 private  String hostOrSlave;

 private  String hostUrl;

 private  String hostNode;

 private  String slaveUrl;

 private  String slaveNode;


public String getHostOrSlave(){
    return hostOrSlave;
}


public void setHostOrSlave(String hostOrSlave){
    this.hostOrSlave = hostOrSlave;
}


public void setSlaveUrl(String slaveUrl){
    this.slaveUrl = slaveUrl;
}


public String getSlaveNode(){
    return slaveNode;
}


public String getHostUrl(){
    return hostUrl;
}


public String getSlaveUrl(){
    return slaveUrl;
}


public void setHostNode(String hostNode){
    this.hostNode = hostNode;
}


public String getHostNode(){
    return hostNode;
}


public void setSlaveNode(String slaveNode){
    this.slaveNode = slaveNode;
}


public void setHostUrl(String hostUrl){
    this.hostUrl = hostUrl;
}


}