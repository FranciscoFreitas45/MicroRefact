package com.fosun.fc.projects.creepers.utils.code;
 import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
public class RsaCode extends BaseCode{

 public  String KEY_ALGORITHM;

 public  String SIGNATURE_ALGORITHM;

 private  String PUBLIC_KEY;

 private  String PRIVATE_KEY;


public byte[] decryptByPrivateKey(byte[] data,String key){
    // 对密钥解密
    byte[] keyBytes = decryptBASE64(key);
    // 取得私钥
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
    // 对数据解密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    return cipher.doFinal(data);
}


public byte[] decryptByPublicKey(byte[] data,String key){
    // 对密钥解密
    byte[] keyBytes = decryptBASE64(key);
    // 取得公钥
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key publicKey = keyFactory.generatePublic(x509KeySpec);
    // 对数据解密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, publicKey);
    return cipher.doFinal(data);
}


public byte[] encryptByPublicKey(byte[] data,String key){
    // 对公钥解密
    byte[] keyBytes = decryptBASE64(key);
    // 取得公钥
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key publicKey = keyFactory.generatePublic(x509KeySpec);
    // 对数据加密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return cipher.doFinal(data);
}


public byte[] encryptByPrivateKey(byte[] data,String key){
    // 对密钥解密
    byte[] keyBytes = decryptBASE64(key);
    // 取得私钥
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
    // 对数据加密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
    return cipher.doFinal(data);
}


public String sign(byte[] data,String privateKey){
    // 解密由base64编码的私钥
    byte[] keyBytes = decryptBASE64(privateKey);
    // 构造PKCS8EncodedKeySpec对象
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    // KEY_ALGORITHM 指定的加密算法
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    // 取私钥匙对象
    PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
    // 用私钥对信息生成数字签名
    Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
    signature.initSign(priKey);
    signature.update(data);
    return encryptBASE64(signature.sign());
}


public boolean verify(byte[] data,String publicKey,String sign){
    // 解密由base64编码的公钥
    byte[] keyBytes = decryptBASE64(publicKey);
    // 构造X509EncodedKeySpec对象
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    // KEY_ALGORITHM 指定的加密算法
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    // 取公钥匙对象
    PublicKey pubKey = keyFactory.generatePublic(keySpec);
    Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
    signature.initVerify(pubKey);
    signature.update(data);
    // 验证签名是否正常
    return signature.verify(decryptBASE64(sign));
}


public String getPublicKey(Map<String,Object> keyMap){
    Key key = (Key) keyMap.get(PUBLIC_KEY);
    return encryptBASE64(key.getEncoded());
}


public String getPrivateKey(Map<String,Object> keyMap){
    Key key = (Key) keyMap.get(PRIVATE_KEY);
    return encryptBASE64(key.getEncoded());
}


public Map<String,Object> initKey(){
    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
    keyPairGen.initialize(1024);
    KeyPair keyPair = keyPairGen.generateKeyPair();
    // 公钥
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    // 私钥
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    Map<String, Object> keyMap = new HashMap<String, Object>(2);
    keyMap.put(PUBLIC_KEY, publicKey);
    keyMap.put(PRIVATE_KEY, privateKey);
    return keyMap;
}


}