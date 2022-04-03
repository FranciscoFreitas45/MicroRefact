package com.gbcom.common.template.xml.msg;
 import java.util.Date;
import org.apache.log4j.Logger;
public class MsgSender {

 private  Logger LOG;

 private  MsgSender instance;

 private  MessageDispatcher msgDispatcher;

private MsgSender() {
    init();
}
public void init(){
    msgDispatcher = new MessageDispatcher();
    msgDispatcher.start();
    LOG.info("build and start message dispatcher  success!  time  =" + new Date());
}


public MsgSender getInstance(){
    return instance;
}


public void send(MessageInfo message){
    if (!msgDispatcher.isRunning()) {
        LOG.error("msgDispatcher is not running,so the message will not be process !!! (Thread problem or other)");
        // off checkstyle
        synchronized (instance) {
            if (!msgDispatcher.isRunning()) {
                if (msgDispatcher != null) {
                    LOG.warn("MSG:: stop msgDispatcher");
                    msgDispatcher.stop();
                    msgDispatcher = null;
                }
                init();
                LOG.warn("MsgSender :: reBuild msgDispatcher object again   .....");
            }
        }
    }
    msgDispatcher.addMessage(message);
}


}