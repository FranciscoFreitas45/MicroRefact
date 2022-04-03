package org.jeecgframework.web.system.sms.util.msg.domain;
 import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.jeecgframework.web.system.sms.util.msg.util.MsgUtils;
public class MsgConnect extends MsgHead{

 private  Logger logger;

 private  String sourceAddr;

 private  byte[] authenticatorSource;

 private  byte version;

 private  int timestamp;


public byte getVersion(){
    return version;
}


public void setSourceAddr(String sourceAddr){
    this.sourceAddr = sourceAddr;
}


public void setAuthenticatorSource(byte[] authenticatorSource){
    this.authenticatorSource = authenticatorSource;
}


public void setVersion(byte version){
    this.version = version;
}


public int getTimestamp(){
    return timestamp;
}


public byte[] toByteArry(){
    ByteArrayOutputStream bous = new ByteArrayOutputStream();
    DataOutputStream dous = new DataOutputStream(bous);
    try {
        dous.writeInt(this.getTotalLength());
        dous.writeInt(this.getCommandId());
        dous.writeInt(this.getSequenceId());
        MsgUtils.writeString(dous, this.sourceAddr, 6);
        dous.write(authenticatorSource);
        dous.writeByte(0x30);
        dous.writeInt(Integer.parseInt(MsgUtils.getTimestamp()));
        dous.close();
    } catch (IOException e) {
        logger.error("封装链接二进制数组失败。");
    }
    return bous.toByteArray();
}


public String getSourceAddr(){
    return sourceAddr;
}


public byte[] getAuthenticatorSource(){
    return authenticatorSource;
}


public void setTimestamp(int timestamp){
    this.timestamp = timestamp;
}


}