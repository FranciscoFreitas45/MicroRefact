package com.fosun.fc.projects.creepers.utils.code;
 import java.security.Key;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
public class PbeCode extends BaseCode{

 public  String ALGORITHM;


public Key toKey(String password){
    PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
    SecretKey secretKey = keyFactory.generateSecret(keySpec);
    return secretKey;
}


public byte[] encrypt(byte[] data,String password,byte[] salt){
    Key key = toKey(password);
    PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
    return cipher.doFinal(data);
}


public byte[] initSalt(){
    byte[] salt = new byte[8];
    Random random = new Random();
    random.nextBytes(salt);
    return salt;
}


public void main(String[] args){
    String inputStr = "abc";
    System.err.println("原文: " + inputStr);
    byte[] input = inputStr.getBytes();
    String pwd = "efg";
    System.err.println("密码: " + pwd);
    byte[] salt = PbeCode.initSalt();
    byte[] data = PbeCode.encrypt(input, pwd, salt);
    System.err.println("加密后: " + PbeCode.encryptBASE64(data));
    byte[] output = PbeCode.decrypt(data, pwd, salt);
    String outputStr = new String(output);
    System.err.println("解密后: " + outputStr);
    System.err.println("验证PBE对于同一内容【加密解密】是否一致:" + BaseCode.compareValue(inputStr, outputStr));
}


public byte[] decrypt(byte[] data,String password,byte[] salt){
    Key key = toKey(password);
    PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
    return cipher.doFinal(data);
}


}