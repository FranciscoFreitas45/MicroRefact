package com.gbcom.system.utils;
 import java.io.UnsupportedEncodingException;
public class TransformUtil {


public int byteArray2Int(byte[] bRefArr){
    int iOutcome = 0;
    byte bLoop;
    for (int i = 0; i < bRefArr.length; i++) {
        bLoop = bRefArr[i];
        iOutcome += (bLoop & 0xFF) << (8 * (3 - i));
    }
    return iOutcome;
}


public String getIpString(byte[] ip){
    String value = "";
    for (byte b : ip) {
        int b1 = 0xFF & b;
        value += b1 + ".";
    }
    return value.substring(0, value.lastIndexOf("."));
}


public void main(String[] args){
    byte[] src = { 0x28, 0x51, 0x32, 0x08, (byte) 0xa5, 0x2c };
    String asc = bytes2Ascii(src);
    byte[] aaa = ascii2bytes(asc);
    String hexString = bytesToHexString(src);
    System.out.println(getMacString(src));
    System.out.println(hexString);
    System.out.println(str2HexString(asc));
    System.out.println(hexStr2Str(str2HexString(asc)));
}


public byte[] int2byteArray(int num){
    byte[] result = new byte[4];
    // 取最高8位放到0下标
    result[0] = (byte) ((num >>> 24) & 0xFF);
    // 取次高8为放到1下标
    result[1] = (byte) ((num >>> 16) & 0xFF);
    // 取次低8位放到2下标
    result[2] = (byte) ((num >>> 8) & 0xFF);
    // 取最低8位放到3下标
    result[3] = (byte) ((num) & 0xFF);
    return result;
}


public String str2HexString(String str){
    char[] chars = "0123456789ABCDEF".toCharArray();
    StringBuilder sb = new StringBuilder("");
    byte[] bs = str.getBytes();
    int bit;
    for (int i = 0; i < bs.length; i++) {
        bit = (bs[i] & 0x0f0) >> 4;
        sb.append(chars[bit]);
        bit = bs[i] & 0x0f;
        sb.append(chars[bit]);
    }
    return sb.toString();
}


public String hexStr2Str(String hexStr){
    String str = "0123456789ABCDEF";
    char[] hexs = hexStr.toCharArray();
    byte[] bytes = new byte[hexStr.length() / 2];
    int n;
    for (int i = 0; i < bytes.length; i++) {
        n = str.indexOf(hexs[2 * i]) * 16;
        n += str.indexOf(hexs[2 * i + 1]);
        bytes[i] = (byte) (n & 0xff);
    }
    return new String(bytes);
}


public String bytes2Ascii(byte[] src){
    StringBuffer sb = new StringBuffer();
    for (byte ea : src) {
        sb.append((char) ea);
    }
    return sb.toString();
}


public String getMacString(byte[] mac){
    String value = "";
    for (int i = 0; i < mac.length; i++) {
        String sTemp = Integer.toHexString(0xFF & mac[i]);
        sTemp = "00".substring(sTemp.length()) + sTemp;
        value = value + sTemp + ":";
    }
    return value.substring(0, value.lastIndexOf(":")).toUpperCase();
}


public byte[] hexStringToBytes(String hexString){
    byte[] bytes = new byte[hexString.length() / 2];
    int index = 0;
    while (index * 2 < hexString.length()) {
        String hex = hexString.substring(index * 2, index * 2 + 2);
        byte b = (byte) Integer.parseInt(hex, 16);
        bytes[index] = b;
        index++;
    }
    return bytes;
}


public String bytesToHexString(byte[] src){
    StringBuilder stringBuilder = new StringBuilder("");
    if (src == null || src.length <= 0) {
        return null;
    }
    for (int i = 0; i < src.length; i++) {
        int v = src[i] & 0xFF;
        String hv = Integer.toHexString(v);
        if (hv.length() < 2) {
            stringBuilder.append(0);
        }
        stringBuilder.append(hv);
    }
    return stringBuilder.toString();
}


public long byteArray2Long(byte[] bRefArr){
    long iOutcome = 0;
    byte bLoop;
    for (int i = 0; i < bRefArr.length; i++) {
        bLoop = bRefArr[i];
        iOutcome += (bLoop & 0xFF) << (8 * (7 - i));
    }
    return iOutcome;
}


public byte[] long2byteArray(long num){
    byte[] result = new byte[8];
    // 取最高8位放到0下标
    result[0] = (byte) ((num >>> 56) & 0xFF);
    // 取次高8为放到1下标
    result[1] = (byte) ((num >>> 48) & 0xFF);
    // 取次低8位放到2下标
    result[2] = (byte) ((num >>> 40) & 0xFF);
    // 取最低8位放到3下标
    result[3] = (byte) ((num >>> 32) & 0xFF);
    // 取最高8位放到0下标
    result[4] = (byte) ((num >>> 24) & 0xFF);
    // 取次高8为放到1下标
    result[5] = (byte) ((num >>> 16) & 0xFF);
    // 取次低8位放到2下标
    result[6] = (byte) ((num >>> 8) & 0xFF);
    // 取最低8位放到3下标
    result[7] = (byte) ((num) & 0xFF);
    return result;
}


public byte[] getMacBytes(String mac){
    byte[] macBytes = new byte[6];
    String[] strArr = mac.split(":");
    for (int i = 0; i < strArr.length; i++) {
        int value = Integer.parseInt(strArr[i], 16);
        macBytes[i] = (byte) value;
    }
    return macBytes;
}


public short byteArray2Short(byte[] bRefArr){
    short iOutcome = 0;
    byte bLoop;
    for (int i = 0; i < bRefArr.length; i++) {
        bLoop = bRefArr[i];
        iOutcome += (bLoop & 0xFF) << (8 * (1 - i));
    }
    return iOutcome;
}


public byte[] getIpBytes(String ip){
    byte[] ipBytes = new byte[4];
    String[] strArr = ip.split("[.]");
    for (int i = 0; i < strArr.length; i++) {
        int value = Integer.parseInt(strArr[i]);
        ipBytes[i] = (byte) value;
    }
    return ipBytes;
}


public byte[] ascii2bytes(String src){
    char[] carry = src.toCharArray();
    byte[] bytes = new byte[carry.length];
    for (int i = 0; i < carry.length; i++) {
        bytes[i] = (byte) carry[i];
    }
    return bytes;
}


}