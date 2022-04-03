package org.jeecgframework.web.system.sms.util.msg.domain;
 import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
public class MsgConnectResp extends MsgHead{

 private  Logger logger;

 private  int status;

 private  String statusStr;

 private  byte[] authenticatorISMG;

 private  byte version;

/**
 * .
 *
 * @param data
 *            byte[]
 */
public MsgConnectResp(byte[] data) {
    if (data.length == 8 + 4 + 16 + 1) {
        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);
        try {
            this.setTotalLength(data.length + 4);
            this.setCommandId(dins.readInt());
            this.setSequenceId(dins.readInt());
            this.setStatus(dins.readInt());
            byte[] aiByte = new byte[16];
            dins.read(aiByte);
            this.authenticatorISMG = aiByte;
            this.version = dins.readByte();
            dins.close();
            bins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        logger.info("链接至IMSP,解析数据包出错，包长度不一致。长度为:" + data.length);
    }
}
public byte getVersion(){
    return version;
}


public String getStatusStr(){
    return statusStr;
}


public void setVersion(byte version){
    this.version = version;
}


public byte[] getAuthenticatorISMG(){
    return authenticatorISMG;
}


public void setAuthenticatorISMG(byte[] authenticatorISMG){
    this.authenticatorISMG = authenticatorISMG;
}


public int getStatus(){
    return status;
}


public void setStatus(int status){
    this.status = status;
    switch(status) {
        case 0:
            statusStr = "正确";
            break;
        case 1:
            statusStr = "消息结构错";
            break;
        case 2:
            statusStr = "非法源地址";
            break;
        case 3:
            statusStr = "认证错";
            break;
        case 4:
            statusStr = "版本太高";
            break;
        case 5:
            statusStr = "其他错误";
            break;
        default:
            statusStr = status + ":未知";
            break;
    }
}


}