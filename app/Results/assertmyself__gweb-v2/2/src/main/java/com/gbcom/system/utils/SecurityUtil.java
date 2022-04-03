package com.gbcom.system.utils;
 import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class SecurityUtil {

 private  byte[] k;

 private  byte[] v;

 private  String c;


public byte[] encrypt(byte[] bytes){
    try {
        // 第1个字节写数据开始位置
        int num = (bytes.length + 1) % 16;
        int newLength = bytes.length + 1 + 16 - num;
        byte[] newBytes = new byte[newLength];
        System.arraycopy(bytes, 0, newBytes, 17 - num, bytes.length);
        newBytes[0] = new Integer((17 - num) & 0xff).byteValue();
        Cipher cipher = Cipher.getInstance(c);
        SecretKeySpec secretkeyspec = new SecretKeySpec(k, c.substring(0, 3));
        IvParameterSpec parameterSpec = new IvParameterSpec(v);
        cipher.init(Cipher.ENCRYPT_MODE, secretkeyspec, parameterSpec);
        return cipher.doFinal(newBytes);
    } catch (Exception e) {
        // To change body of catch statement use File | Settings | File Templates.
        e.printStackTrace();
    }
    return null;
}


public byte[] decrypt(byte[] bytes){
    try {
        Cipher cipher = Cipher.getInstance(c);
        SecretKeySpec secretkeyspec = new SecretKeySpec(k, c.substring(0, 3));
        IvParameterSpec parameterSpec = new IvParameterSpec(v);
        cipher.init(Cipher.DECRYPT_MODE, secretkeyspec, parameterSpec);
        byte[] newBytes = cipher.doFinal(bytes);
        int pos = newBytes[0] & 0xff;
        byte[] res = new byte[newBytes.length - pos];
        System.arraycopy(newBytes, pos, res, 0, newBytes.length - pos);
        return res;
    } catch (Exception e) {
        // To change body of catch statement use File | Settings | File Templates.
        e.printStackTrace();
    }
    return null;
}


public String MD5(String s){
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    try {
        byte[] btInput = s.getBytes();
        // 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        // 使用指定的字节更新摘要
        mdInst.update(btInput);
        // 获得密文
        byte[] md = mdInst.digest();
        // 把密文转换成十六进制的字符串形式
        int j = md.length;
        char[] str = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


}