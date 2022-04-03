package com.ukefu.core;
 public class ClusterContext {

 private  long serialVersionUID;

 private  ClusterContext instance;

 private  boolean master;

 private  String host;

 private  int port;

 private  String id;

 private  long start;


public void setHost(String host){
    this.host = host;
}


public int getPort(){
    return port;
}


public long getStart(){
    return start;
}


public boolean isMaster(){
    return master;
}


public void setStart(long start){
    this.start = start;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public ClusterContext getInstance(){
    return instance;
}


public void setMaster(boolean master){
    this.master = master;
}


public void setPort(int port){
    this.port = port;
}


public String getHost(){
    return host;
}


}