package com.empl.mgr.utils;
 import java.io.Serializable;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
public class EncryptUtil implements Serializable{

 private  long serialVersionUID;


public String encodeMD5String(String str){
    if (StringUtils.isEmpty(str))
        return null;
    return DigestUtils.md5Hex(str);
}


}