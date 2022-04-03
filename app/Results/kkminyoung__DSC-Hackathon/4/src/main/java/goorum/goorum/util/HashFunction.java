package goorum.goorum.util;
 import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class HashFunction {


public String bytesToHex(byte[] bytes){
    StringBuilder builder = new StringBuilder();
    for (byte b : bytes) {
        builder.append(String.format("%02x", b));
    }
    return builder.toString();
}


public String sha256(String msg){
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(msg.getBytes());
    return bytesToHex(md.digest());
}


}