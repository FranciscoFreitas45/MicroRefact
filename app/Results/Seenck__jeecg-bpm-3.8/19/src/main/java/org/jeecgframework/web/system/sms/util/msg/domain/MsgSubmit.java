package org.jeecgframework.web.system.sms.util.msg.domain;
 import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.jeecgframework.web.system.sms.util.msg.util.MsgUtils;
public class MsgSubmit extends MsgHead{

 private  Logger logger;

 private  long msgId;

 private  byte pkTotal;

 private  byte pkNumber;

 private  byte registeredDelivery;

 private  byte msgLevel;

 private  String serviceId;

 private  byte feeUserType;

 private  String feeTerminalId;

 private  byte feeTerminalType;

 private  byte tpPId;

 private  byte tpUdhi;

 private  byte msgFmt;

 private  String msgSrc;

 private  String feeType;

 private  String feeCode;

 private  String valIdTime;

 private  String atTime;

 private  String srcId;

 private  byte destUsrTl;

 private  String destTerminalId;

 private  byte destTerminalType;

 private  byte msgLength;

 private  byte[] msgContent;

 private  String linkID;


public byte getFeeTerminalType(){
    return feeTerminalType;
}


public void setMsgLevel(byte msgLevel){
    this.msgLevel = msgLevel;
}


public String getMsgSrc(){
    return msgSrc;
}


public void setLinkID(String linkID){
    this.linkID = linkID;
}


public byte[] toByteArry(){
    ByteArrayOutputStream bous = new ByteArrayOutputStream();
    DataOutputStream dous = new DataOutputStream(bous);
    try {
        dous.writeInt(this.getTotalLength());
        dous.writeInt(this.getCommandId());
        dous.writeInt(this.getSequenceId());
        // Msg_Id 信息标识，由SP接入的短信网关本身产生，本处填空
        dous.writeLong(this.msgId);
        // Pk_total 相同Msg_Id的信息总条数
        dous.writeByte(this.pkTotal);
        // Pk_number 相同Msg_Id的信息序号，从1开始
        dous.writeByte(this.pkNumber);
        // Registered_Delivery
        dous.writeByte(this.registeredDelivery);
        // 是否要求返回状态确认报告
        // Msg_level 信息级别
        dous.writeByte(this.msgLevel);
        // Service_Id
        MsgUtils.writeString(dous, this.serviceId, 10);
        // 业务标识，是数字、字母和符号的组合。
        // Fee_UserType 计费用户类型字段
        dous.writeByte(this.feeUserType);
        // 0：对目的终端MSISDN计费；
        // 1：对源终端MSISDN计费；
        // 2：对SP计费;
        // 3：表示本字段无效，对谁计费参见Fee_terminal_Id字段。
        // Fee_terminal_Id
        MsgUtils.writeString(dous, this.feeTerminalId, 32);
        // 被计费用户的号码
        // Fee_terminal_type
        dous.writeByte(this.feeTerminalType);
        // 被计费用户的号码类型，0：真实号码；1：伪码
        // TP_pId
        dous.writeByte(this.tpPId);
        // TP_udhi
        dous.writeByte(this.tpUdhi);
        // Msg_Fmt
        dous.writeByte(this.msgFmt);
        // Msg_src 信息内容来源(SP_Id)
        MsgUtils.writeString(dous, this.msgSrc, 6);
        // FeeType 资费类别
        MsgUtils.writeString(dous, this.feeType, 2);
        // FeeCode
        MsgUtils.writeString(dous, this.feeCode, 6);
        // 存活有效期
        MsgUtils.writeString(dous, this.valIdTime, 17);
        // 定时发送时间
        MsgUtils.writeString(dous, this.atTime, 17);
        // Src_Id spCode
        MsgUtils.writeString(dous, this.srcId, 21);
        // DestUsr_tl
        dous.writeByte(this.destUsrTl);
        // Dest_terminal_Id
        MsgUtils.writeString(dous, this.destTerminalId, 32);
        // Dest_terminal_type
        dous.writeByte(this.destTerminalType);
        // 接收短信的用户的号码类型，0：真实号码；1：伪码
        // Msg_Length
        dous.writeByte(this.msgLength);
        // 信息内容
        dous.write(this.msgContent);
        // 点播业务使用的LinkID
        MsgUtils.writeString(dous, this.linkID, 20);
        dous.close();
    } catch (IOException e) {
        logger.error("封装短信发送二进制数组失败。");
    }
    return bous.toByteArray();
}


public void setTpPId(byte tpPId){
    this.tpPId = tpPId;
}


public void setFeeTerminalId(String feeTerminalId){
    this.feeTerminalId = feeTerminalId;
}


public String getFeeTerminalId(){
    return feeTerminalId;
}


public void setFeeType(String feeType){
    this.feeType = feeType;
}


public long getMsgId(){
    return msgId;
}


public byte getRegisteredDelivery(){
    return registeredDelivery;
}


public void setMsgFmt(byte msgFmt){
    this.msgFmt = msgFmt;
}


public String getServiceId(){
    return serviceId;
}


public long getPkTotal(){
    return pkTotal;
}


public void setPkTotal(byte pkTotal){
    this.pkTotal = pkTotal;
}


public void setMsgLength(byte msgLength){
    this.msgLength = msgLength;
}


public void setFeeTerminalType(byte feeTerminalType){
    this.feeTerminalType = feeTerminalType;
}


public byte getDestTerminalType(){
    return destTerminalType;
}


public void setPkNumber(byte pkNumber){
    this.pkNumber = pkNumber;
}


public byte getMsgLevel(){
    return msgLevel;
}


public String getAtTime(){
    return atTime;
}


public String getFeeType(){
    return feeType;
}


public byte getDestUsrTl(){
    return destUsrTl;
}


public byte getTpUdhi(){
    return tpUdhi;
}


public void setDestTerminalType(byte destTerminalType){
    this.destTerminalType = destTerminalType;
}


public void setValIdTime(String valIdTime){
    this.valIdTime = valIdTime;
}


public void setServiceId(String serviceId){
    this.serviceId = serviceId;
}


public void setFeeUserType(byte feeUserType){
    this.feeUserType = feeUserType;
}


public String getDestTerminalId(){
    return destTerminalId;
}


public void setMsgContent(byte[] msgContent){
    this.msgContent = msgContent;
}


public byte getPkNumber(){
    return pkNumber;
}


public String getFeeCode(){
    return feeCode;
}


public String getSrcId(){
    return srcId;
}


public void setFeeCode(String feeCode){
    this.feeCode = feeCode;
}


public String getLinkID(){
    return linkID;
}


public byte getMsgFmt(){
    return msgFmt;
}


public void setMsgId(long msgId){
    this.msgId = msgId;
}


public void setRegisteredDelivery(byte registeredDelivery){
    this.registeredDelivery = registeredDelivery;
}


public byte getMsgLength(){
    return msgLength;
}


public byte[] getMsgContent(){
    return msgContent;
}


public void setDestTerminalId(String destTerminalId){
    this.destTerminalId = destTerminalId;
}


public void setSrcId(String srcId){
    this.srcId = srcId;
}


public byte getTpPId(){
    return tpPId;
}


public byte getFeeUserType(){
    return feeUserType;
}


public void setMsgSrc(String msgSrc){
    this.msgSrc = msgSrc;
}


public String getValIdTime(){
    return valIdTime;
}


public void setDestUsrTl(byte destUsrTl){
    this.destUsrTl = destUsrTl;
}


public void setTpUdhi(byte tpUdhi){
    this.tpUdhi = tpUdhi;
}


public void setAtTime(String atTime){
    this.atTime = atTime;
}


}