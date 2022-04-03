package org.jeecgframework.web.system.sms.util.msg.domain;
 import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
public class MsgDeliverResp extends MsgHead{

 private  Logger logger;

 private  long msgId;

 private  int result;


public long getMsgId(){
    return msgId;
}


public void setResult(int result){
    this.result = result;
}


public int getResult(){
    return result;
}


public byte[] toByteArry(){
    ByteArrayOutputStream bous = new ByteArrayOutputStream();
    DataOutputStream dous = new DataOutputStream(bous);
    try {
        dous.writeInt(this.getTotalLength());
        dous.writeInt(this.getCommandId());
        dous.writeInt(this.getSequenceId());
        dous.writeLong(this.msgId);
        dous.writeInt(this.result);
        dous.close();
    } catch (IOException e) {
        logger.error("封装链接二进制数组失败。");
    }
    return bous.toByteArray();
}


public void setMsgId(long msgId){
    this.msgId = msgId;
}


}