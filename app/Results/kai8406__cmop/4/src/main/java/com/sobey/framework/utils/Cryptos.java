package com.sobey.framework.utils;
 import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class Cryptos {

 private  String AES;

 private  String AES_CBC;

 private  String HMACSHA1;

 private  int DEFAULT_HMACSHA1_KEYSIZE;

 private  int DEFAULT_AES_KEYSIZE;

 private  int DEFAULT_IVSIZE;

 private  SecureRandom random;


public byte[] generateHmacSha1Key(){
    try {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(HMACSHA1);
        keyGenerator.init(DEFAULT_HMACSHA1_KEYSIZE);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    } catch (GeneralSecurityException e) {
        throw Exceptions.unchecked(e);
    }
}


public byte[] aesEncrypt(byte[] input,byte[] key,byte[] iv){
    return aes(input, key, iv, Cipher.ENCRYPT_MODE);
}


public byte[] generateAesKey(int keysize){
    try {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(keysize);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    } catch (GeneralSecurityException e) {
        throw Exceptions.unchecked(e);
    }
}


public boolean isMacValid(byte[] expected,byte[] input,byte[] key){
    byte[] actual = hmacSha1(input, key);
    return Arrays.equals(expected, actual);
}


public String aesDecrypt(byte[] input,byte[] key,byte[] iv){
    byte[] decryptResult = aes(input, key, iv, Cipher.DECRYPT_MODE);
    return new String(decryptResult);
}


public byte[] generateIV(){
    byte[] bytes = new byte[DEFAULT_IVSIZE];
    random.nextBytes(bytes);
    return bytes;
}


public byte[] hmacSha1(byte[] input,byte[] key){
    try {
        SecretKey secretKey = new SecretKeySpec(key, HMACSHA1);
        Mac mac = Mac.getInstance(HMACSHA1);
        mac.init(secretKey);
        return mac.doFinal(input);
    } catch (GeneralSecurityException e) {
        throw Exceptions.unchecked(e);
    }
}


public byte[] aes(byte[] input,byte[] key,byte[] iv,int mode){
    try {
        SecretKey secretKey = new SecretKeySpec(key, AES);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance(AES_CBC);
        cipher.init(mode, secretKey, ivSpec);
        return cipher.doFinal(input);
    } catch (GeneralSecurityException e) {
        throw Exceptions.unchecked(e);
    }
}


}