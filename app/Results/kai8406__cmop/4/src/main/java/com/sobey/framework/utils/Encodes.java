package com.sobey.framework.utils;
 import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;
public class Encodes {

 private  String DEFAULT_URL_ENCODING;

 private  char[] BASE62;


public String encodeBase64(byte[] input){
    return Base64.encodeBase64String(input);
}


public String encodeHex(byte[] input){
    return Hex.encodeHexString(input);
}


public byte[] decodeBase64(String input){
    return Base64.decodeBase64(input);
}


public String escapeHtml(String html){
    return StringEscapeUtils.escapeHtml4(html);
}


public String encodeBase62(byte[] input){
    char[] chars = new char[input.length];
    for (int i = 0; i < input.length; i++) {
        chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
    }
    return new String(chars);
}


public byte[] decodeHex(String input){
    try {
        return Hex.decodeHex(input.toCharArray());
    } catch (DecoderException e) {
        throw Exceptions.unchecked(e);
    }
}


public String encodeUrlSafeBase64(byte[] input){
    return Base64.encodeBase64URLSafeString(input);
}


public String urlDecode(String part){
    try {
        return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
    } catch (UnsupportedEncodingException e) {
        throw Exceptions.unchecked(e);
    }
}


public String escapeXml(String xml){
    return StringEscapeUtils.escapeXml(xml);
}


public String unescapeHtml(String htmlEscaped){
    return StringEscapeUtils.unescapeHtml4(htmlEscaped);
}


public String urlEncode(String part){
    try {
        return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
    } catch (UnsupportedEncodingException e) {
        throw Exceptions.unchecked(e);
    }
}


public String unescapeXml(String xmlEscaped){
    return StringEscapeUtils.unescapeXml(xmlEscaped);
}


}