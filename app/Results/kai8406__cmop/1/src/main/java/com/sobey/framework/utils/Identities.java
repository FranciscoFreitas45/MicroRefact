package com.sobey.framework.utils;
 import java.security.SecureRandom;
import java.util.UUID;
public class Identities {

 private  SecureRandom random;


public String uuid2(){
    return UUID.randomUUID().toString().replaceAll("-", "");
}


public String randomBase62(int length){
    byte[] randomBytes = new byte[length];
    random.nextBytes(randomBytes);
    return Encodes.encodeBase62(randomBytes);
}


public String uuid(){
    return UUID.randomUUID().toString();
}


public long randomLong(){
    return Math.abs(random.nextLong());
}


}