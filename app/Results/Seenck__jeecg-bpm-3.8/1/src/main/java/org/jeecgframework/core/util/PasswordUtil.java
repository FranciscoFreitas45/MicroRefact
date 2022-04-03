package org.jeecgframework.core.util;
 import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
public class PasswordUtil {

 public  String ALGORITHM;

 public  String Salt;

 private  int ITERATIONCOUNT;


public byte[] getStaticSalt(){
    // 产出盐
    return Salt.getBytes();
}


public String encrypt(String plaintext,String password,byte[] salt){
    Key key = getPBEKey(password);
    byte[] encipheredData = null;
    PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATIONCOUNT);
    try {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
        encipheredData = cipher.doFinal(plaintext.getBytes());
    } catch (Exception e) {
    }
    return bytesToHexString(encipheredData);
}


public byte charToByte(char c){
    return (byte) "0123456789ABCDEF".indexOf(c);
}


public byte[] getSalt(){
    // 实例化安全随机数
    SecureRandom random = new SecureRandom();
    // 产出盐
    return random.generateSeed(8);
}


public Key getPBEKey(String password){
    // 实例化使用的算法
    SecretKeyFactory keyFactory;
    SecretKey secretKey = null;
    try {
        keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        // 设置PBE密钥参数
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        // 生成密钥
        secretKey = keyFactory.generateSecret(keySpec);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return secretKey;
}


public void main(String[] args){
    int i = 10;
    for (int j = 0; j < i; j++) {
        if ((j) % 3 == 0) {
            System.out.print("<br>");
        } else {
            System.out.print(j);
        }
    }
    System.out.print(-1 % 2 == 0);
    String str = "root";
    String password = "root";
    org.jeecgframework.core.util.LogUtil.info("明文:" + str);
    org.jeecgframework.core.util.LogUtil.info("密码:" + password);
    try {
        byte[] salt = PasswordUtil.getStaticSalt();
        String ciphertext = PasswordUtil.encrypt(str, password, salt);
        org.jeecgframework.core.util.LogUtil.info("密文:" + ciphertext);
        String plaintext = PasswordUtil.decrypt(ciphertext, password, salt);
        org.jeecgframework.core.util.LogUtil.info("明文:" + plaintext);
        String result = PasswordUtil.decrypt("ea3d519525358e00", "root", salt);
        org.jeecgframework.core.util.LogUtil.info("明文:" + result);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public String decrypt(String ciphertext,String password,byte[] salt){
    Key key = getPBEKey(password);
    byte[] passDec = null;
    PBEParameterSpec parameterSpec = new PBEParameterSpec(getStaticSalt(), ITERATIONCOUNT);
    try {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
        passDec = cipher.doFinal(hexStringToBytes(ciphertext));
    } catch (Exception e) {
    // TODO: handle exception
    }
    return new String(passDec);
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


public byte[] hexStringToBytes(String hexString){
    if (hexString == null || hexString.equals("")) {
        return null;
    }
    hexString = hexString.toUpperCase();
    int length = hexString.length() / 2;
    char[] hexChars = hexString.toCharArray();
    byte[] d = new byte[length];
    for (int i = 0; i < length; i++) {
        int pos = i * 2;
        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
    }
    return d;
}


}