package com.sobey.framework.utils;
 import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import org.apache.commons.lang3.Validate;
public class Digests {

 private  String SHA1;

 private  String MD5;

 private  SecureRandom random;


public byte[] sha1(InputStream input){
    return digest(input, SHA1);
}


public byte[] generateSalt(int numBytes){
    Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);
    byte[] bytes = new byte[numBytes];
    random.nextBytes(bytes);
    return bytes;
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


public byte[] md5(InputStream input){
    return digest(input, MD5);
}


}