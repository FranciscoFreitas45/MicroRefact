package com.ushahidi.swiftriver.core.util;
 public class GravatarUtil {


public String gravatar(String email){
    String url = "https://secure.gravatar.com/avatar/%s?s=80&d=mm&r=g";
    String md5Hex = MD5Util.md5Hex(email.trim().toLowerCase());
    return String.format(url, md5Hex);
}


}