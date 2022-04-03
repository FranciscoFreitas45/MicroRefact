package com.fosun.fc.projects.creepers.utils.code;
 import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
public class DhCode extends BaseCode{

 public  String ALGORITHM;

 private  int KEY_SIZE;

 public  String SECRET_ALGORITHM;

 private  String PUBLIC_KEY;

 private  String PRIVATE_KEY;


public SecretKey getSecretKey(String publicKey,String privateKey){
    // 初始化公钥
    byte[] pubKeyBytes = decryptBASE64(publicKey);
    KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKeyBytes);
    PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
    // 初始化私钥
    byte[] priKeyBytes = decryptBASE64(privateKey);
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKeyBytes);
    Key priKey = keyFactory.generatePrivate(pkcs8KeySpec);
    KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory.getAlgorithm());
    keyAgree.init(priKey);
    keyAgree.doPhase(pubKey, true);
    // 生成本地密钥
    SecretKey secretKey = keyAgree.generateSecret(SECRET_ALGORITHM);
    return secretKey;
}


public byte[] encrypt(byte[] data,String publicKey,String privateKey){
    // 生成本地密钥
    SecretKey secretKey = getSecretKey(publicKey, privateKey);
    // 数据加密
    Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    return cipher.doFinal(data);
}


public void main(String[] args){
    // 生成甲方密钥对儿
    Map<String, Object> aKeyMap = DhCode.initKey();
    String aPublicKey = DhCode.getPublicKey(aKeyMap);
    String aPrivateKey = DhCode.getPrivateKey(aKeyMap);
    System.err.println("甲方公钥:\n" + aPublicKey);
    System.err.println("甲方私钥:\n" + aPrivateKey);
    // 由甲方公钥产生本地密钥对儿
    Map<String, Object> bKeyMap = DhCode.initKey(aPublicKey);
    String bPublicKey = DhCode.getPublicKey(bKeyMap);
    String bPrivateKey = DhCode.getPrivateKey(bKeyMap);
    System.err.println("乙方公钥:\n" + bPublicKey);
    System.err.println("乙方私钥:\n" + bPrivateKey);
    String aInput = "abc ";
    System.err.println("原文: " + aInput);
    // 由甲方公钥，乙方私钥构建密文
    byte[] aCode = DhCode.encrypt(aInput.getBytes(), aPublicKey, bPrivateKey);
    // 由乙方公钥，甲方私钥解密
    byte[] aDecode = DhCode.decrypt(aCode, bPublicKey, aPrivateKey);
    String aOutput = (new String(aDecode));
    System.err.println("解密: " + aOutput);
    System.err.println("验证DH对于同一内容【由甲方公钥，乙方私钥构建密文--由乙方公钥，甲方私钥解密】是否一致:" + BaseCode.compareValue(aInput, aOutput));
    System.err.println(" ===============反过来加密解密================== ");
    String bInput = "def ";
    System.err.println("原文: " + bInput);
    // 由乙方公钥，甲方私钥构建密文
    byte[] bCode = DhCode.encrypt(bInput.getBytes(), bPublicKey, aPrivateKey);
    // 由甲方公钥，乙方私钥解密
    byte[] bDecode = DhCode.decrypt(bCode, aPublicKey, bPrivateKey);
    String bOutput = (new String(bDecode));
    System.err.println("解密: " + bOutput);
    System.err.println("验证DH对于同一内容【由乙方公钥，甲方私钥构建密文--由甲方公钥，乙方私钥解密】是否一致:" + BaseCode.compareValue(bInput, bOutput));
}


public String getPublicKey(Map<String,Object> keyMap){
    Key key = (Key) keyMap.get(PUBLIC_KEY);
    return encryptBASE64(key.getEncoded());
}


public byte[] decrypt(byte[] data,String publicKey,String privateKey){
    // 生成本地密钥
    SecretKey secretKey = getSecretKey(publicKey, privateKey);
    // 数据解密
    Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    return cipher.doFinal(data);
}


public String getPrivateKey(Map<String,Object> keyMap){
    Key key = (Key) keyMap.get(PRIVATE_KEY);
    return encryptBASE64(key.getEncoded());
}


public Map<String,Object> initKey(String key){
    // 解析甲方公钥
    byte[] keyBytes = decryptBASE64(key);
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
    PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
    // 由甲方公钥构建乙方密钥
    DHParameterSpec dhParamSpec = ((DHPublicKey) pubKey).getParams();
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyFactory.getAlgorithm());
    keyPairGenerator.initialize(dhParamSpec);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    // 乙方公钥
    DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
    // 乙方私钥
    DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
    Map<String, Object> keyMap = new HashMap<String, Object>(2);
    keyMap.put(PUBLIC_KEY, publicKey);
    keyMap.put(PRIVATE_KEY, privateKey);
    return keyMap;
}


}