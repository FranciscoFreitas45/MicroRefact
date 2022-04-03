package org.jeecgframework.web.system.sms.util.msg.util;
 import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jeecgframework.core.util.LogUtil;
public class MsgUtils {

 private  int sequenceId;


public byte[] getMsgBytes(byte[] msg,int start,int end){
    byte[] msgByte = new byte[end - start];
    int j = 0;
    for (int i = start; i < end; i++) {
        msgByte[j] = msg[i];
        j++;
    }
    return msgByte;
}


public String getTimestamp(){
    DateFormat format = new SimpleDateFormat("MMddHHmmss");
    return format.format(new Date());
}


public String encodeUCS2(String src){
    byte[] bytes;
    try {
        bytes = src.getBytes("UTF-16BE");
    } catch (UnsupportedEncodingException e) {
        bytes = new byte[0];
    }
    StringBuffer reValue = new StringBuffer();
    StringBuffer tem = new StringBuffer();
    for (int i = 0; i < bytes.length; i++) {
        tem.delete(0, tem.length());
        tem.append(Integer.toHexString(bytes[i] & 0xFF));
        if (tem.length() == 1) {
            tem.insert(0, '0');
        }
        reValue.append(tem);
    }
    return reValue.toString().toUpperCase();
}


public String readString(java.io.DataInputStream ins,int len){
    byte[] b = new byte[len];
    try {
        ins.read(b);
        String s = new String(b);
        s = s.trim();
        return s;
    } catch (IOException e) {
        return "";
    }
}


public String decodeUCS2(String src){
    byte[] bytes = new byte[src.length() / 2];
    for (int i = 0; i < src.length(); i += 2) {
        bytes[i / 2] = (byte) (Integer.parseInt(src.substring(i, i + 2), 16));
    }
    String reValue = "";
    try {
        reValue = new String(bytes, "UTF-16BE");
    } catch (UnsupportedEncodingException e) {
        reValue = "";
    }
    return reValue;
}


public byte[] getAuthenticatorSource(String spId,String secret){
    try {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] data = (spId + "\0\0\0\0\0\0\0\0\0" + secret + MsgUtils.getTimestamp()).getBytes();
        return md5.digest(data);
    } catch (NoSuchAlgorithmException e) {
        LogUtil.error("SP链接到ISMG拼接AuthenticatorSource失败：" + e.getMessage());
        return null;
    }
}


public int getSequence(){
    ++sequenceId;
    if (sequenceId > 255) {
        sequenceId = 0;
    }
    return sequenceId;
}


public void writeString(DataOutputStream dous,String s,int len){
    try {
        byte[] data = s.getBytes("gb2312");
        if (data.length > len) {
            LogUtil.error("向流中写入的字符串超长！要写" + len + " 字符串是:" + s);
        }
        int srcLen = data.length;
        dous.write(data);
        while (srcLen < len) {
            dous.write('\0');
            srcLen++;
        }
    } catch (IOException e) {
        LogUtil.error("向流中写入指定字节长度的字符串失败：" + e.getMessage());
    }
}


}