package com.gbcom.DTO;
 import java.util.Date;
import org.apache.log4j.Logger;
public class MsgSender {

 private  Logger LOG;

 private  MsgSender instance;

 private  MessageDispatcher msgDispatcher;

private MsgSender() {
    init();
}
public MsgSender getInstance(){
    return instance;
}


}