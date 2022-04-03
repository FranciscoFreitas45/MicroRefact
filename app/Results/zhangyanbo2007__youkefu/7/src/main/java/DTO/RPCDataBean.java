package DTO;
 import java.io.Serializable;
public class RPCDataBean implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String event;

 private  Object data;

 private  long start;

 private  String host;

 private  int port;

public RPCDataBean(String id, String event, Object data) {
    this.id = id;
    this.event = event;
    this.data = data;
}public RPCDataBean(String id, String host, int port, long start) {
    this.host = host;
    this.port = port;
    this.start = start;
    this.id = id;
}
public String getEvent(){
    return event;
}


public int getPort(){
    return port;
}


public long getStart(){
    return start;
}


public String getId(){
    return id;
}


public Object getData(){
    return data;
}


public String getHost(){
    return host;
}


}