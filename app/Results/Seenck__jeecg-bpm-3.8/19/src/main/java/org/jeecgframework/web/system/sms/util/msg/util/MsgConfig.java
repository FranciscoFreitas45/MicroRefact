package org.jeecgframework.web.system.sms.util.msg.util;
 import java.util.ResourceBundle;
public class MsgConfig {

 private  ResourceBundle resourceBundle;


public int getConnectCount(){
    return Integer.parseInt(MsgConfig.get("connectCount"));
}


public String get(String key){
    return resourceBundle.getString(key);
}


public int getTimeOut(){
    return Integer.parseInt(MsgConfig.get("timeOut"));
}


public String getSpSharedSecret(){
    return MsgConfig.get("sharedSecret");
}


public String getIsmgIp(){
    return MsgConfig.get("ismgIp");
}


public String getSpId(){
    return MsgConfig.get("spId");
}


public String getSpCode(){
    return MsgConfig.get("spCode");
}


public int getIsmgPort(){
    return Integer.parseInt(MsgConfig.get("ismgPort"));
}


}