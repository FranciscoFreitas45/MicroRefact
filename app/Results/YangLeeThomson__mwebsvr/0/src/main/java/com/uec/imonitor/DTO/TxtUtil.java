package com.uec.imonitor.DTO;
 import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TxtUtil {

 private  Logger log;

 private  Map<String,Map<String,String>> map;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public String hashKeyForDisk(String key){
    String cacheKey;
    try {
        final MessageDigest mDigest = MessageDigest.getInstance("MD5");
        mDigest.update(key.getBytes());
        cacheKey = bytesToHexString(mDigest.digest());
    } catch (NoSuchAlgorithmException e) {
        cacheKey = String.valueOf(key.hashCode());
    }
    return cacheKey;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hashKeyForDisk"))

.queryParam("key",key)
;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


}