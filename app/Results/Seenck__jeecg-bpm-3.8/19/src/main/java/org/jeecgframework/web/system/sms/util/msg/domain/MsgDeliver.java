package org.jeecgframework.web.system.sms.util.msg.domain;
 import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
public class MsgDeliver extends MsgHead{

 private  Logger logger;

 private  long msgId;

 private  String destId;

 private  String serviceId;

 private  byte tPPid;

 private  byte tPUdhi;

 private  byte msgFmt;

 private  String srcTerminalId;

 private  byte srcTerminalType;

 private  byte registeredDelivery;

 private  byte msgLength;

 private  String msgContent;

 private  String linkID;

 private  long msgIdReport;

 private  String stat;

 private  String submitTime;

 private  String doneTime;

 private  String destTerminalId;

 private  int sMSCSequence;

 private  int result;

/**
 * .
 *
 * @param data
 *            byte[]
 */
public MsgDeliver(byte[] data) {
    if (data.length > 8 + 8 + 21 + 10 + 1 + 1 + 1 + 32 + 1 + 1 + 1 + 20) {
        // +Msg_length+
        String fmtStr = "gb2312";
        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);
        try {
            this.setTotalLength(data.length + 4);
            this.setCommandId(dins.readInt());
            this.setSequenceId(dins.readInt());
            // Msg_Id
            this.msgId = dins.readLong();
            byte[] destIdByte = new byte[21];
            dins.read(destIdByte);
            // 21 目的号码 String
            this.destId = new String(destIdByte);
            byte[] service_IdByte = new byte[10];
            dins.read(service_IdByte);
            // 10 业务标识 String
            this.serviceId = new String(service_IdByte);
            this.tPPid = dins.readByte();
            this.tPUdhi = dins.readByte();
            this.msgFmt = dins.readByte();
            fmtStr = this.msgFmt == 8 ? "utf-8" : "gb2312";
            byte[] src_terminal_IdByte = new byte[32];
            dins.read(src_terminal_IdByte);
            // 源终端MSISDN号码
            this.srcTerminalId = new String(src_terminal_IdByte);
            // 源终端号码类型，0：真实号码；1：伪码
            this.srcTerminalType = dins.readByte();
            // 是否为状态报告
            this.registeredDelivery = dins.readByte();
            // 0：非状态报告1：状态报告
            // 消息长度
            this.msgLength = dins.readByte();
            byte[] msg_ContentByte = new byte[msgLength];
            dins.read(msg_ContentByte);
            if (registeredDelivery == 1) {
                // 消息长度
                this.msgContent = new String(msg_ContentByte, fmtStr);
                if (msgLength == 8 + 7 + 10 + 10 + 21 + 4) {
                    ByteArrayInputStream binsC = new ByteArrayInputStream(data);
                    DataInputStream dinsC = new DataInputStream(binsC);
                    this.msgIdReport = dinsC.readLong();
                    byte[] startByte = new byte[7];
                    dinsC.read(startByte);
                    this.stat = new String(startByte, fmtStr);
                    byte[] submit_timeByte = new byte[10];
                    dinsC.read(submit_timeByte);
                    this.submitTime = new String(submit_timeByte, fmtStr);
                    byte[] done_timeByte = new byte[7];
                    dinsC.read(done_timeByte);
                    this.doneTime = new String(done_timeByte, fmtStr);
                    byte[] dest_terminal_IdByte = new byte[21];
                    dinsC.read(dest_terminal_IdByte);
                    this.destTerminalId = new String(dest_terminal_IdByte, fmtStr);
                    this.sMSCSequence = dinsC.readInt();
                    dinsC.close();
                    binsC.close();
                    // 正确
                    this.result = 0;
                } else {
                    // 消息结构错
                    this.result = 1;
                }
            } else {
                // 消息长度
                this.msgContent = new String(msg_ContentByte, fmtStr);
            }
            byte[] linkIDByte = new byte[20];
            this.linkID = new String(linkIDByte);
            // 正确
            this.result = 0;
            dins.close();
            bins.close();
        } catch (IOException e) {
            // 消息结构错
            this.result = 8;
        }
    } else {
        // 消息结构错
        this.result = 1;
        logger.info("短信网关CMPP_DELIVER,解析数据包出错，包长度不一致。长度为:" + data.length);
    }
}
public void setServiceId(String serviceId){
    this.serviceId = serviceId;
}


public void setTPUdhi(byte tpUdhi){
    tPUdhi = tpUdhi;
}


public byte getSrcTerminalType(){
    return srcTerminalType;
}


public void setLinkID(String linkID){
    this.linkID = linkID;
}


public void setResult(int result){
    this.result = result;
}


public void setStat(String stat){
    this.stat = stat;
}


public String getDestTerminalId(){
    return destTerminalId;
}


public long getMsgIdReport(){
    return msgIdReport;
}


public void setMsgContent(String msgContent){
    this.msgContent = msgContent;
}


public String getSubmitTime(){
    return submitTime;
}


public String getStat(){
    return stat;
}


public String getDestId(){
    return destId;
}


public String getSrcTerminalId(){
    return srcTerminalId;
}


public void setSrcTerminalId(String srcTerminalId){
    this.srcTerminalId = srcTerminalId;
}


public long getMsgId(){
    return msgId;
}


public byte getRegisteredDelivery(){
    return registeredDelivery;
}


public String getLinkID(){
    return linkID;
}


public byte getMsgFmt(){
    return msgFmt;
}


public byte getTPUdhi(){
    return tPUdhi;
}


public void setMsgId(long msgId){
    this.msgId = msgId;
}


public void setRegisteredDelivery(byte registeredDelivery){
    this.registeredDelivery = registeredDelivery;
}


public void setSubmitTime(String submitTime){
    this.submitTime = submitTime;
}


public byte getMsgLength(){
    return msgLength;
}


public void setMsgFmt(byte msgFmt){
    this.msgFmt = msgFmt;
}


public String getServiceId(){
    return serviceId;
}


public String getMsgContent(){
    return msgContent;
}


public void setDestTerminalId(String destTerminalId){
    this.destTerminalId = destTerminalId;
}


public void setSrcTerminalType(byte srcTerminalType){
    this.srcTerminalType = srcTerminalType;
}


public void setMsgLength(byte msgLength){
    this.msgLength = msgLength;
}


public void setDestId(String destId){
    this.destId = destId;
}


public String getDoneTime(){
    return doneTime;
}


public int getSMSCSequence(){
    return sMSCSequence;
}


public void setMsgIdReport(long msgIdReport){
    this.msgIdReport = msgIdReport;
}


public void setDoneTime(String doneTime){
    this.doneTime = doneTime;
}


public int getResult(){
    return result;
}


public void setSMSCSequence(int sMSCSequence){
    this.sMSCSequence = sMSCSequence;
}


public byte getTPPid(){
    return tPPid;
}


public void setTPPid(byte tpPid){
    tPPid = tpPid;
}


}