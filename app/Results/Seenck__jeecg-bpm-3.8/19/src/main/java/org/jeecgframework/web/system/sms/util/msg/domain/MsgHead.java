package org.jeecgframework.web.system.sms.util.msg.domain;
 import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
public class MsgHead {

 private  Logger logger;

 private  int totalLength;

 private  int commandId;

 private  int sequenceId;

/**
 * .
 *
 * @param data
 *            byte[]
 */
public MsgHead(byte[] data) {
    ByteArrayInputStream bins = new ByteArrayInputStream(data);
    DataInputStream dins = new DataInputStream(bins);
    try {
        this.setTotalLength(data.length + 4);
        this.setCommandId(dins.readInt());
        this.setSequenceId(dins.readInt());
        dins.close();
        bins.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}/**
 * .
 */
public MsgHead() {
    super();
}
public int getSequenceId(){
    return sequenceId;
}


public int getTotalLength(){
    return totalLength;
}


public void setTotalLength(int totalLength){
    this.totalLength = totalLength;
}


public byte[] toByteArry(){
    ByteArrayOutputStream bous = new ByteArrayOutputStream();
    DataOutputStream dous = new DataOutputStream(bous);
    try {
        dous.writeInt(this.getTotalLength());
        dous.writeInt(this.getCommandId());
        dous.writeInt(this.getSequenceId());
        dous.close();
    } catch (IOException e) {
        logger.error("封装CMPP消息头二进制数组失败。");
    }
    return bous.toByteArray();
}


public int getCommandId(){
    return commandId;
}


public void setCommandId(int commandId){
    this.commandId = commandId;
}


public void setSequenceId(int sequenceId){
    this.sequenceId = sequenceId;
}


}