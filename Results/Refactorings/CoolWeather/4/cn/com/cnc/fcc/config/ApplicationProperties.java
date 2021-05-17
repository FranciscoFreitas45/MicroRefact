import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

 private  Security security;

 private  Web web;

 private  HostSlave hostSlave;

 private  Jwt jwt;

 private  int tokenCountDay;

 private  String fileShare;

 private  String hostOrSlave;

 private  String hostUrl;

 private  String hostNode;

 private  String slaveUrl;

 private  String slaveNode;


public String getHostOrSlave(){
    return hostOrSlave;
}


public String getFileShare(){
    return fileShare;
}


public void setTokenCountDay(int tokenCountDay){
    this.tokenCountDay = tokenCountDay;
}


public HostSlave getHostSlave(){
    return hostSlave;
}


public void setHostNode(String hostNode){
    this.hostNode = hostNode;
}


public String getHostNode(){
    return hostNode;
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


public Web getWeb(){
    return web;
}


public String getHostUrl(){
    return hostUrl;
}


public String getSlaveUrl(){
    return slaveUrl;
}


public Jwt getJwt(){
    return jwt;
}


public Security getSecurity(){
    return security;
}


public int getTokenCountDay(){
    return tokenCountDay;
}


public void setFileShare(String fileShare){
    this.fileShare = fileShare;
}


public void setSlaveNode(String slaveNode){
    this.slaveNode = slaveNode;
}


public void setHostUrl(String hostUrl){
    this.hostUrl = hostUrl;
}


}