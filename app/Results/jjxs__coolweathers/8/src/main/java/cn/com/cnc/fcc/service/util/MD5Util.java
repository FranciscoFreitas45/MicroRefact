package cn.com.cnc.fcc.service.util;
 import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MD5Util {


public String toMd5(String plainText){
    String re_md5 = new String();
    Logger log = LoggerFactory.getLogger(MD5Util.class);
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte[] b = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        re_md5 = buf.toString();
    } catch (NoSuchAlgorithmException e) {
        log.info(e.getMessage());
    }
    return re_md5;
}


public String MD5(String data){
    java.security.MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] array = md.digest(data.getBytes("UTF-8"));
    StringBuilder sb = new StringBuilder();
    for (byte item : array) {
        sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString().toUpperCase();
}


}