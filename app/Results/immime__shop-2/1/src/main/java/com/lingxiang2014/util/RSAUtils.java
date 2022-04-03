package com.lingxiang2014.util;
 import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Assert;
public class RSAUtils {

 private  Provider PROVIDER;

 private  int KEY_SIZE;

private RSAUtils() {
}
public String encrypt(PublicKey publicKey,String text){
    Assert.notNull(publicKey);
    Assert.notNull(text);
    byte[] data = encrypt(publicKey, text.getBytes());
    return data != null ? Base64.encodeBase64String(data) : null;
}


public String decrypt(PrivateKey privateKey,String text){
    Assert.notNull(privateKey);
    Assert.notNull(text);
    byte[] data = decrypt(privateKey, Base64.decodeBase64(text));
    return data != null ? new String(data) : null;
}


public KeyPair generateKeyPair(){
    try {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", PROVIDER);
        keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return null;
    }
}


}