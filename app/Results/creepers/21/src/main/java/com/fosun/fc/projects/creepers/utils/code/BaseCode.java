package com.fosun.fc.projects.creepers.utils.code;
 import java.math.BigInteger;
import java.security.MessageDigest;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
@SuppressWarnings("restriction")
public class BaseCode {

 public  String KEY_SHA;

 public  String KEY_MD5;

 public  String KEY_MAC;


public String encryptBASE64(byte[] key){
    return (new BASE64Encoder()).encodeBuffer(key);
}


public byte[] encryptSHA(byte[] data){
    MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
    sha.update(data);
    return sha.digest();
}


public boolean compareValue(Object obj1,Object obj2){
    if (obj1 instanceof byte[] && obj2 instanceof byte[]) {
        String str1 = new String((byte[]) obj1);
        String str2 = new String((byte[]) obj2);
        return str1.equals(str2);
    } else if (obj1 instanceof byte[] && obj2 instanceof String) {
        String str1 = new String((byte[]) obj1);
        String str2 = (String) obj2;
        return str1.equals(str2);
    } else if (obj1 instanceof String && obj2 instanceof byte[]) {
        String str1 = (String) obj1;
        String str2 = new String((byte[]) obj2);
        return str1.equals(str2);
    } else if (obj1 instanceof String && obj2 instanceof String) {
        String str1 = (String) obj1;
        String str2 = (String) obj2;
        return str1.equals(str2);
    } else {
        return false;
    }
}


public byte[] encryptHMAC(byte[] data,String key){
    SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
    Mac mac = Mac.getInstance(secretKey.getAlgorithm());
    mac.init(secretKey);
    return mac.doFinal(data);
}


public String encryptMD5To16(String digest){
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    try {
        // 获得密文
        byte[] md = encryptMD5(digest.getBytes());
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


public void main(String[] args){
    String inputStr = "654123";
    System.err.println("原文:\n" + inputStr);
    byte[] inputData = inputStr.getBytes();
    String code = BaseCode.encryptBASE64(inputData);
    System.err.println("BASE64加密后:\n" + code);
    byte[] output = BaseCode.decryptBASE64(code);
    String outputStr = new String(output);
    System.err.println("BASE64解密后:\n" + outputStr);
    // 验证BASE64加密解密一致性
    System.err.println("验证BASE64加密解密一致性:" + inputStr.equals(outputStr));
    // 验证MD5对于同一内容加密是否一致
    System.err.println("验证MD5对于同一内容加密是否一致:" + BaseCode.compareValue(BaseCode.encryptMD5(inputData), BaseCode.encryptMD5(inputData)));
    // 验证SHA对于同一内容加密是否一致
    System.err.println("验证SHA对于同一内容加密是否一致:" + BaseCode.compareValue(BaseCode.encryptSHA(inputData), BaseCode.encryptSHA(inputData)));
    String key = BaseCode.initMacKey();
    System.err.println("Mac密钥:\n" + key);
    // 验证HMAC对于同一内容，同一密钥加密是否一致
    System.err.println("验证HMAC对于同一内容，同一密钥加密是否一致:" + BaseCode.compareValue(BaseCode.encryptHMAC(inputData, key), BaseCode.encryptHMAC(inputData, key)));
    BigInteger md5 = new BigInteger(BaseCode.encryptMD5(inputData));
    System.err.println("MD5:\n" + md5.toString(16));
    System.err.println("trans1:" + new String(BaseCode.encryptMD5To16(inputData)));
    System.err.println("trans2:" + BaseCode.encryptMD5(inputStr));
    BigInteger sha = new BigInteger(BaseCode.encryptSHA(inputData));
    System.err.println("SHA:\n" + sha.toString(32));
    BigInteger mac = new BigInteger(BaseCode.encryptHMAC(inputData, inputStr));
    System.err.println("HMAC:\n" + mac.toString(16));
}


public byte[] decryptBASE64(String key){
    return (new BASE64Decoder()).decodeBuffer(key);
}


public byte[] encryptMD5(String data){
    MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
    md5.update(data.getBytes());
    return md5.digest();
}


public String initMacKey(){
    KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
    SecretKey secretKey = keyGenerator.generateKey();
    return encryptBASE64(secretKey.getEncoded());
}


}