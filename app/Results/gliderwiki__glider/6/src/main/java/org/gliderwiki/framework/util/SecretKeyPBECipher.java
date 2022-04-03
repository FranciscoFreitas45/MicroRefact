package org.gliderwiki.framework.util;
 import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
public class SecretKeyPBECipher {

 private  PBEKeySpec keySpec;

 private  SecretKey key;

 private  SecretKeyFactory keyFactory;

 private  PBEParameterSpec parameterSpec;

 private  Cipher cipher;

 private  byte[] salt;

 private  int iteration;

 private  byte[] encryptedText;

 private  byte[] decryptedText;


public void initiate(String currentKey){
    keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
    keySpec = new PBEKeySpec(currentKey.toCharArray());
    key = keyFactory.generateSecret(keySpec);
    parameterSpec = new PBEParameterSpec(salt, iteration);
    cipher = Cipher.getInstance("PBEWithMD5AndDES");
}


public byte[] encrypt(String plainText){
    cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
    encryptedText = cipher.doFinal(plainText.getBytes("euc-kr"));
    return encryptedText;
}


public String stringBuffersChars(int len){
    StringBuffer buffer = new StringBuffer();
    Random random = new Random();
    String[] chars = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");
    for (int i = 0; i < len; i++) {
        buffer.append(chars[random.nextInt(chars.length)]);
    }
    System.out.println("buffer.toString() :" + buffer.toString());
    return buffer.toString();
}


public String setUserPassword(String password,String key){
    String pwd = "";
    byte[] data;
    try {
        SecretKeyPBECipher.initiate(key);
        data = SecretKeyPBECipher.encrypt(password);
        pwd = Base64Coder.encodeString(data);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return pwd;
}


public String getUserPassword(String password,String key){
    String pwd = "";
    byte[] data;
    try {
        SecretKeyPBECipher.initiate(key);
        data = Base64Coder.decode(password);
        data = SecretKeyPBECipher.decrypt(data);
        pwd = new String(data, "euc-kr");
    } catch (Exception e) {
        e.printStackTrace();
    }
    return pwd;
}


public byte[] decrypt(String decrtyptText){
    cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
    decryptedText = cipher.doFinal(decrtyptText.getBytes("euc-kr"));
    return decryptedText;
}


}