package com.kingen.util;
 import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.zip.CRC32;
import org.apache.commons.lang3.Validate;
import com.google.common.hash.Hashing;
import com.kingen.bean.ClientContact;
import com.kingen.bean.User;
import com.kingen.service.account.AccountService;
import DTO.ClientContact;
public class Digests {

 private  int DEFAULT_ITERATIONS;

 public  Charset UTF8;

 private  String SHA1;

 private  String MD5;

 private  SecureRandom random;


public byte[] sha1(InputStream input){
    return digest(input, SHA1);
}


public long crc32AsLong(String input,Charset charset){
    CRC32 crc32 = new CRC32();
    crc32.update(input.getBytes(charset));
    return crc32.getValue();
}


public String encryptPassword(String pwd,String salt){
    byte[] hashPassword = Digests.sha1(pwd.getBytes(), Encodes.decodeHex(salt), AccountService.HASH_INTERATIONS);
    return (Encodes.encodeHex(hashPassword));
}


public byte[] generateSalt(int numBytes){
    Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);
    byte[] bytes = new byte[numBytes];
    random.nextBytes(bytes);
    return bytes;
}


public int murmur32(String input,Charset charset,int seed){
    return Hashing.murmur3_32(seed).hashString(input, charset).asInt();
}


public byte[] digest(InputStream input,String algorithm){
    try {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        int bufferLength = 8 * 1024;
        byte[] buffer = new byte[bufferLength];
        int read = input.read(buffer, 0, bufferLength);
        while (read > -1) {
            messageDigest.update(buffer, 0, read);
            read = input.read(buffer, 0, bufferLength);
        }
        return messageDigest.digest();
    } catch (GeneralSecurityException e) {
        throw Exceptions.unchecked(e);
    }
}


public void entryptPassword(ClientContact user){
    byte[] salt = Digests.generateSalt(Integer.valueOf(Global.getConfig("shiro.salt_size")));
    user.setSalt(Encodes.encodeHex(salt));
    byte[] hashPassword = Digests.sha1(user.getPwd().getBytes(), salt, Integer.valueOf(Global.getConfig("shiro.hash.interations")));
    user.setPwd(Encodes.encodeHex(hashPassword));
}


public int crc32(String input,Charset charset){
    CRC32 crc32 = new CRC32();
    crc32.update(input.getBytes(charset));
    return (int) crc32.getValue();
}


public byte[] md5(InputStream input){
    return digest(input, MD5);
}


}