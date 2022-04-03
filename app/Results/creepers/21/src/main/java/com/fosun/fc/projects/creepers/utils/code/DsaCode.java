package com.fosun.fc.projects.creepers.utils.code;
 import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
public class DsaCode extends BaseCode{

 public  String ALGORITHM;

 private  int KEY_SIZE;

 private  String DEFAULT_SEED;

 private  String PUBLIC_KEY;

 private  String PRIVATE_KEY;


public String sign(byte[] data,String privateKey){
    // 解密由base64编码的私钥
    byte[] keyBytes = decryptBASE64(privateKey);
    // 构造PKCS8EncodedKeySpec对象
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    // KEY_ALGORITHM 指定的加密算法
    KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
    // 取私钥匙对象
    PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
    // 用私钥对信息生成数字签名
    Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
    signature.initSign(priKey);
    signature.update(data);
    return encryptBASE64(signature.sign());
}


public boolean verify(byte[] data,String publicKey,String sign){
    // 解密由base64编码的公钥
    byte[] keyBytes = decryptBASE64(publicKey);
    // 构造X509EncodedKeySpec对象
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    // ALGORITHM 指定的加密算法
    KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
    // 取公钥匙对象
    PublicKey pubKey = keyFactory.generatePublic(keySpec);
    Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
    signature.initVerify(pubKey);
    signature.update(data);
    // 验证签名是否正常
    return signature.verify(decryptBASE64(sign));
}


public void main(String[] args){
    String inputStr = "abc";
    byte[] data = inputStr.getBytes();
    // 构建密钥
    Map<String, Object> keyMap = DsaCode.initKey();
    // 获得密钥
    String publicKey = DsaCode.getPublicKey(keyMap);
    String privateKey = DsaCode.getPrivateKey(keyMap);
    System.err.println("公钥:\n" + publicKey);
    System.err.println("私钥:\n" + privateKey);
    // 产生签名
    String sign = DsaCode.sign(data, privateKey);
    System.err.println("签名:\n" + sign);
    // 验证签名
    boolean status = DsaCode.verify(data, publicKey, sign);
    System.err.println("状态:\n" + status);
    System.err.println("验证DSA对于同一内容【使用私钥对数据签名——使用公钥、签名对数据验证】是否一致:" + status);
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
    return initKey(DEFAULT_SEED);
}


}