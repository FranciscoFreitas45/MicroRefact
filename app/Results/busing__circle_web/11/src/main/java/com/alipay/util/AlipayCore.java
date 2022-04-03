package com.alipay.util;
 import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.PartSource;
import com.alipay.config.AlipayConfig;
public class AlipayCore {


public String createLinkString(Map<String,String> params){
    List<String> keys = new ArrayList<String>(params.keySet());
    Collections.sort(keys);
    String prestr = "";
    for (int i = 0; i < keys.size(); i++) {
        String key = keys.get(i);
        String value = params.get(key);
        if (i == keys.size() - 1) {
            // 拼接时，不包括最后一个&字符
            prestr = prestr + key + "=" + value;
        } else {
            prestr = prestr + key + "=" + value + "&";
        }
    }
    return prestr;
}


public void logResult(String sWord){
    FileWriter writer = null;
    try {
        writer = new FileWriter(AlipayConfig.log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
        writer.write(sWord);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


public Map<String,String> paraFilter(Map<String,String> sArray){
    Map<String, String> result = new HashMap<String, String>();
    if (sArray == null || sArray.size() <= 0) {
        return result;
    }
    for (String key : sArray.keySet()) {
        String value = sArray.get(key);
        if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
            continue;
        }
        result.put(key, value);
    }
    return result;
}


public String getAbstract(String strFilePath,String file_digest_type){
    PartSource file = new FilePartSource(new File(strFilePath));
    if (file_digest_type.equals("MD5")) {
        return DigestUtils.md5Hex(file.createInputStream());
    } else if (file_digest_type.equals("SHA")) {
        return DigestUtils.sha256Hex(file.createInputStream());
    } else {
        return "";
    }
}


}