package org.jeecgframework.web.cgform.util;
 import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SignatureUtil {

 private  Logger LOG;


public boolean checkSign(Map<String,String> paramMap,String key,String sign){
    if (key == null) {
        throw new BusinessException("key不能为空");
    }
    if (sign == null) {
        throw new BusinessException("需要验签的字符为空");
    }
    return sign.equals(sign(paramMap, key));
}


public String createSign(Map<String,String> paramMap,String key){
    StringBuffer sb = new StringBuffer();
    SortedMap<String, String> sort = new TreeMap<String, String>(paramMap);
    Set<Entry<String, String>> es = sort.entrySet();
    Iterator<Entry<String, String>> it = es.iterator();
    while (it.hasNext()) {
        @SuppressWarnings("rawtypes")
        Map.Entry entry = (Map.Entry) it.next();
        String k = (String) entry.getKey();
        String v = (String) entry.getValue();
        if (null != v && !"".equals(v) && !"null".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
            sb.append(k + "=" + v + "&");
        }
    }
    sb.append("key=" + key);
    LOG.info("HMAC source:{}", new Object[] { sb.toString() });
    String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
    LOG.info("HMAC:{}", new Object[] { sign });
    return sign;
}


public String sign(Map<String,String> paramMap,String key){
    if (key == null) {
        throw new BusinessException("key不能为空");
    }
    String sign = createSign(paramMap, key);
    return sign;
}


public void main(String[] args){
    // String url = "http://www.saphao.com:9999/P3-Web/commonxrs/toIndex.do?actId=402880ee51334a520151334c3eaf0001&openid=oR0jFt_DTsAUJebWqGeq3A1VWfRw&nickname=JEFF&subscribe=1&jwid=&sign=F5E56A64B650A98E67CCCFFF871C7133";
    // Map<String,String> t = getSignMap(url);
    // for(Map.Entry<String, String> entry:t.entrySet()){
    // System.out.println(entry.getKey()+"--->"+entry.getValue());
    // }
    String key = "26F72780372E84B6CFAED6F7B19139CC47B1912B6CAED753";
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("tableName", "jform_le_main");
    paramMap.put("id", "402813815398698b015398698b710000");
    paramMap.put("data", "{jform_le_main:[{id=\"402813815398698b015398698b710000\",name:\"ceshi111111\",sex:1,remark:\"java developer\"}],jform_le_subone:[{main_id=\"402813815398698b015398698b710000\",name:\"ceshi111111\",sex:1,remark:\"java developer\"}],jform_le_submany:[{main_id=\"402813815398698b015398698b710000\",name:\"ceshi111111\",sex:1,remark:\"java developer\"},{name:\"ceshi111111\",sex:1,remark:\"java developer\"}]}");
    paramMap.put("method", "updateFormInfo");
    System.out.println(createSign(paramMap, key));
}


public Map<String,String> getSignMap(String url){
    Map<String, String> paramMap = new HashMap<String, String>();
    url = url.substring(url.indexOf("?") + 1);
    String[] params = url.split("&");
    for (int i = 0; i < params.length; i++) {
        String param = params[i];
        if (param.indexOf("=") != -1) {
            String[] values = param.split("=");
            if (values != null && values.length == 2) {
                // update----begin---author:scott----date:20160115----for:昵称转码，签名问题处理----
                if ("nickname".equals(values[0])) {
                    paramMap.put(values[0], URLDecoder.decode(values[1]));
                } else {
                    paramMap.put(values[0], values[1]);
                }
            // update----begin---author:scott----date:20160115----for:昵称转码，签名问题处理----
            }
        }
    }
    return paramMap;
}


}