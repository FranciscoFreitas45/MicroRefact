package com.gbcom.system.servlet.init;
 import java.util.HashSet;
import java.util.Set;
import com.gbcom.common.template.res.BasicResManager;
public class InitServerInfo {

 private  InitServerInfo instance;

 private  Set<String> messages;

 private boolean result;

private InitServerInfo() {
    messages = new HashSet<String>();
}
public boolean getInitStatue(){
    return messages.isEmpty();
}


public String getMessage(){
    if (messages.isEmpty()) {
        return "" + BasicResManager.getString("Basic_application_load_success");
    }
    String msg = "";
    for (String m : messages) {
        msg += m + "\n";
    }
    return msg.substring(0, msg.lastIndexOf("\n"));
}


public InitServerInfo getInstance(){
    if (instance == null) {
        instance = new InitServerInfo();
    }
    return instance;
}


public void addMessage(String e){
    messages.add(e);
}


}