package com.fosun.fc.projects.creepers.utils.code;
 import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
public class DesCode extends BaseCode{

 public  String ALGORITHM;


public Key toKey(byte[] key){
    DESKeySpec dks = new DESKeySpec(key);
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
    SecretKey secretKey = keyFactory.generateSecret(dks);
    // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
    // SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
    return secretKey;
}


public byte[] encrypt(byte[] data,String key){
    Key k = toKey(decryptBASE64(key));
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, k);
    return cipher.doFinal(data);
}


public void main(String[] args){
    String inputStr = "DES";
    String key = initKey();
    System.err.println("原文:\t" + inputStr);
    System.err.println("密钥:\t" + key);
    byte[] inputData = inputStr.getBytes();
    inputData = DesCode.encrypt(inputData, key);
    System.err.println("加密后:\t" + DesCode.encryptBASE64(inputData));
    byte[] outputData = DesCode.decrypt(inputData, key);
    String outputStr = new String(outputData);
    System.err.println("解密后:\t" + outputStr);
    System.err.println("验证DES对于同一内容加密解密是否一致:" + BaseCode.compareValue(inputStr, outputStr));
}


public byte[] decrypt(byte[] data,String key){
    Key k = toKey(decryptBASE64(key));
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, k);
    return cipher.doFinal(data);
}


public String initKey(String seed){
    SecureRandom secureRandom = null;
    if (seed != null) {
        secureRandom = new SecureRandom(decryptBASE64(seed));
    } else {
        secureRandom = new SecureRandom();
    }
    KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
    kg.init(secureRandom);
    SecretKey secretKey = kg.generateKey();
    return encryptBASE64(secretKey.getEncoded());
}


}