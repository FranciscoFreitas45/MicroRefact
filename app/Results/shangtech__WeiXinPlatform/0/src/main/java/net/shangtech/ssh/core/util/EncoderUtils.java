package net.shangtech.ssh.core.util;
 import java.security.MessageDigest;
public class EncoderUtils {

 private  char[] HEX_DIGITS;


public String encode(String algorithm,String str){
    if (str == null)
        return null;
    try {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(str.getBytes());
        return getFormattedText(messageDigest.digest());
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}


public String getFormattedText(byte[] bytes){
    int length = bytes.length;
    StringBuilder sb = new StringBuilder(length * 2);
    for (int i = 0; i < length; i++) {
        sb.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
        sb.append(HEX_DIGITS[bytes[i] & 0x0f]);
    }
    return sb.toString();
}


public String SHA1(String str){
    return encode("SHA1", str);
}


public void main(String[] args){
    System.out.println("1=" + EncoderUtils.MD5(EncoderUtils.MD5("songxh@19900212")));
    System.out.println("2=" + EncoderUtils.MD5(EncoderUtils.MD5("2")));
    System.out.println("xinyuantang=" + EncoderUtils.MD5(EncoderUtils.MD5("xinyuantang@xinyuantang")));
    System.out.println(EncoderUtils.SHA1("songxh"));
}


public String MD5(String str){
    return encode("MD5", str);
}


}