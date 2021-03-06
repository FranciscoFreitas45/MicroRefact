package com.weixin.util;
 import java.security.MessageDigest;
import java.util.Arrays;
public class CheckUtil {

 public  String TOKEN;


public boolean checkSignature(String signature,String timestamp,String nonce){
    String[] arr = new String[] { TOKEN, timestamp, nonce };
    Arrays.sort(arr);
    StringBuilder sb = new StringBuilder();
    for (String string : arr) {
        sb.append(string);
    }
    String str = getSha1(sb.toString());
    String temp = getSha1(str);
    return temp.equals(signature);
}


public String getSha1(String decript){
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.update(decript.getBytes());
        byte[] messageDigest = digest.digest();
        // Create Hex String
        StringBuffer hexString = new StringBuffer();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "";
}


}