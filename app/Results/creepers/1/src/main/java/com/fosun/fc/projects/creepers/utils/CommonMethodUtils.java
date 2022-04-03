package com.fosun.fc.projects.creepers.utils;
 import java.awt.image.BufferedImage;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.net.ssl.SSLContext;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.poi.ss.formula.functions.T;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.nlpcn.commons.lang.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.modules.entity.BaseEntity;
import com.fosun.fc.modules.persistence.DynamicSpecifications;
import com.fosun.fc.modules.persistence.SearchFilter;
import com.fosun.fc.modules.persistence.SearchFilter.Operator;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.BaseConstant.SupperMan;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.google.common.collect.Maps;
import com.sz789.qqchaoren;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.utils.HttpConstant;
public class CommonMethodUtils {

 private  Logger logger;

 private  String ADMIN;


@SuppressWarnings("rawtypes")
public List<Map<String,String>> beanListToMap(Collection sourceList){
    List<Map<String, String>> destinationList = new ArrayList<Map<String, String>>();
    if (sourceList != null) {
        for (Iterator iterator = sourceList.iterator(); iterator.hasNext(); ) {
            Object sourceObject = iterator.next();
            Map<String, String> resultObject = beanToMap(sourceObject);
            destinationList.add(resultObject);
        }
    }
    return destinationList;
}


@SuppressWarnings({ "rawtypes", "unchecked" })
public List mapList(Collection sourceList,Object destination){
    List destinationList = new ArrayList();
    if (sourceList != null) {
        for (Iterator iterator = sourceList.iterator(); iterator.hasNext(); ) {
            Object sourceObject = iterator.next();
            Object resultObject = mapTransEntity((Map) sourceObject, destination);
            destinationList.add(resultObject);
        }
    }
    return destinationList;
}


public String matchesValue(String pattern,String source){
    if (pattern == null) {
        throw new IllegalArgumentException("pattern argument cannot be null.");
    }
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(source);
    while (m.find()) return m.group(0);
    return "";
}


public String getRandomUserAgent(){
    return BaseConstant.USER_AGENT_ARRAY[RandomUtils.nextInt(BaseConstant.USER_AGENT_ARRAY.length)];
}


public String getPhantomJSWebDriver(){
    String system = System.getProperty(BaseConstant.SYSTEM_OS_NAME);
    if (StringUtil.isNotBlank(system)) {
        if (system.toUpperCase().contains(BaseConstant.SYSTEM_TYPE_WINDOWS)) {
            return BaseConstant.SeleniumWebDriverPath.PHANTOM_JS_PATH.getValue();
        } else {
            return BaseConstant.SeleniumWebDriverPath.PHANTOM_JS_PATH_LINUX.getValue();
        }
    }
    return null;
}


@Deprecated
public CloseableHttpClient createSSLClientDefault(){
    try {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

            // 信任所有
            @Override
            public boolean isTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
                return true;
            }
        }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    } catch (KeyManagementException e) {
        e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    } catch (KeyStoreException e) {
        e.printStackTrace();
    }
    return HttpClients.createDefault();
}


@SuppressWarnings({ "rawtypes", "unchecked" })
public Object mapTransEntity(Map map,Object dto){
    if (dto == null) {
        return null;
    }
    Class newClass = dto.getClass();
    Object resultObj = newClass.newInstance();
    Field[] fs = newClass.getDeclaredFields();
    String methodName = null;
    Object mapValue = null;
    String parameterType = null;
    Class[] parameterTypes = new Class[1];
    Object[] args = new Object[1];
    Iterator it = map.keySet().iterator();
    while (it.hasNext()) {
        String key = (String) it.next();
        if (key != null) {
            for (int i = 0; i < fs.length; i++) {
                if (key.equals(fs[i].getName())) {
                    methodName = "set" + key.replaceFirst(key.substring(0, 1), key.substring(0, 1).toUpperCase());
                    try {
                        parameterTypes[0] = fs[i].getType();
                        parameterType = parameterTypes[0].toString();
                        Method method = newClass.getDeclaredMethod(methodName, parameterTypes);
                        mapValue = map.get(key);
                        if (parameterTypes[0] == Integer.class || parameterTypes[0] == int.class) {
                            if (mapValue instanceof Integer) {
                                args[0] = mapValue;
                            } else {
                                args[0] = Integer.parseInt((String) mapValue);
                            }
                        } else if (parameterTypes[0] == Date.class) {
                            if (mapValue instanceof Date) {
                                args[0] = mapValue;
                            } else {
                                args[0] = new Date(DateUtils.parseDate((String) mapValue, BaseConstant.DateFormatPatternTypeArr.TYPE_ARRAY.getArr()).getTime());
                            }
                        } else if (parameterTypes[0] == java.util.Date.class) {
                            if (mapValue instanceof java.util.Date) {
                                args[0] = mapValue;
                            } else {
                                args[0] = DateUtils.parseDate((String) mapValue, BaseConstant.DateFormatPatternTypeArr.TYPE_ARRAY.getArr());
                            }
                        } else if (parameterTypes[0] == double.class || parameterTypes[0] == Double.class) {
                            if (mapValue instanceof Double) {
                                args[0] = mapValue;
                            } else {
                                args[0] = Double.parseDouble((String) mapValue);
                            }
                        } else if (parameterTypes[0] == BigDecimal.class) {
                            if (mapValue instanceof BigDecimal) {
                                args[0] = mapValue;
                            } else {
                                args[0] = new BigDecimal((String) mapValue);
                            }
                        } else if (parameterTypes[0] == String.class) {
                            if (mapValue instanceof String[]) {
                                String[] tempArray = (String[]) mapValue;
                                String result = "";
                                for (int m = 0; m < tempArray.length; m++) {
                                    result = result + tempArray[m] + ",";
                                }
                                result = result.substring(0, result.length() - 1);
                                TranslateMethodUtil.fullToHalf(result);
                                args[0] = result;
                            } else {
                                mapValue = TranslateMethodUtil.fullToHalf((String) mapValue);
                                args[0] = mapValue;
                            }
                        } else {
                            args[0] = mapValue;
                        }
                        method.invoke(resultObj, args);
                        method.invoke(dto, args);
                    } catch (SecurityException e) {
                        throw new SecurityException("[mapTransDTO]安全异常" + e);
                    } catch (NoSuchMethodException e) {
                        throw new NoSuchMethodException("[mapTransDTO]DTO中无此方法异常" + e);
                    } catch (IllegalArgumentException e) {
                        throw new Exception("DTO中" + key + "属性类型" + parameterType + "与Map中值为" + mapValue + "的类型不匹配");
                    } catch (IllegalAccessException e) {
                        throw new IllegalAccessException("[mapTransDTO]IllegalAccessException异常");
                    } catch (ParseException e) {
                        throw new ParseException("[mapTransDTO]ParseException异常", 0);
                    }
                }
            }
        }
    }
    return resultObj;
}


public String dateFormat(java.util.Date date,String pattern){
    DateFormat df = new SimpleDateFormat(pattern);
    return df.format(date);
}


public Map<SupperMan,String> imageAnalyticalBySupperManFile(String filePath){
    Map<SupperMan, String> map = new HashMap<SupperMan, String>();
    try {
        map = qqchaoren.imageAnalytical(filePath);
    } catch (Exception e) {
        System.err.println("======>>> supperMan imageAnalytical Exception！");
        e.printStackTrace();
    }
    return map;
}


public String getChromeWebDriver(){
    String system = System.getProperty(BaseConstant.SYSTEM_OS_NAME);
    if (StringUtil.isNotBlank(system)) {
        if (system.toUpperCase().contains(BaseConstant.SYSTEM_TYPE_WINDOWS)) {
            return BaseConstant.SeleniumWebDriverPath.CHROME_PATH.getValue();
        } else {
            return BaseConstant.SeleniumWebDriverPath.CHROME_PATH_LINUX.getValue();
        }
    }
    return null;
}


public Request buildDefaultRequest(Map<String,String> nameValuePairMap,String url){
    Request request = new Request();
    if (url.length() > 0) {
        request.setUrl(url);
    }
    request.setMethod(HttpConstant.Method.POST);
    NameValuePair[] valuePairs = new NameValuePair[nameValuePairMap.size()];
    Set<Entry<String, String>> entrySet = nameValuePairMap.entrySet();
    int index = 0;
    for (Entry<String, String> entry : entrySet) {
        valuePairs[index] = new BasicNameValuePair(entry.getKey(), entry.getValue());
        index++;
    }
    request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, valuePairs);
    return request;
}


public Request buildDoLoginRequest(CreepersLoginParamDTO param,Request oldRequest){
    Request request = new Request();
    Map<String, String> nameValuePairMap = param.getNameValuePair();
    NameValuePair[] valuePairs = new NameValuePair[nameValuePairMap.size()];
    Set<Entry<String, String>> entrySet = nameValuePairMap.entrySet();
    int index = 0;
    for (Entry<String, String> entry : entrySet) {
        valuePairs[index] = new BasicNameValuePair(entry.getKey(), entry.getValue());
        index++;
    }
    request.getExtras().putAll(oldRequest.getExtras());
    request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, valuePairs);
    return request;
}


public Properties getApplicationProperties(){
    Properties sConfig = new Properties();
    try {
        String system = System.getProperty(BaseConstant.SYSTEM_OS_NAME);
        String path = "";
        if (StringUtil.isNotBlank(system)) {
            if (system.toUpperCase().contains(BaseConstant.SYSTEM_TYPE_WINDOWS)) {
                path = "E:/2015/workspace/fortune-projects/projects-creepers-v1.0.0/src/main/resources/application.properties";
            } else {
                path = "/opt/app/appCREEPERS/webconfig/application.properties";
            }
        }
        FileReader fileReader = new FileReader(path);
        sConfig.load(fileReader);
        fileReader.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return sConfig;
}


public java.util.Date stringToDataConverter(String source){
    return DateUtils.parseDate(source, BaseConstant.DateFormatPatternTypeArr.TYPE_ARRAY.getArr());
}


public Request buildIndexRequest(CreepersParamDTO param){
    if (StringUtils.isBlank(param.getOrderUrl().get(BaseConstant.OrderUrlKey.INDEX_URL))) {
        return null;
    }
    Request request = new Request();
    request.setMethod(HttpConstant.Method.POST);
    request.setUrl(param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL));
    Map<String, String> nameValuePairMap = param.getNameValuePair();
    NameValuePair[] valuePairs = new NameValuePair[nameValuePairMap.size()];
    Set<Entry<String, String>> entrySet = nameValuePairMap.entrySet();
    int index = 0;
    for (Entry<String, String> entry : entrySet) {
        valuePairs[index] = new BasicNameValuePair(entry.getKey(), entry.getValue());
        index++;
    }
    request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, valuePairs);
    return request;
}


public Map<String,Object> removeNullValueKey(Map<String,Object> map){
    Map<String, Object> resultMap = new HashMap<String, Object>();
    Set<Entry<String, Object>> entrySet = map.entrySet();
    for (Entry<String, Object> entry : entrySet) {
        if (entry.getValue() != null) {
            resultMap.put(entry.getKey(), entry.getValue());
        }
    }
    return resultMap;
}


public void main(String[] args){
    // System.out.println(CommonMethodUtils.toUtf8String("深圳市裕同包装科技股份有限公司"));
    // System.out.println(CommonMethodUtils.unescape25(
    // "%25E6%25B7%25B1%25E5%259C%25B3%25E5%25B8%2582%25E5%25BF%25AB%25E6%2592%25AD%25E7%25A7%2591%25E6%258A%2580%25E6%259C%2589%25E9%2599%2590%25E5%2585%25AC%25E5%258F%25B8"));
    // System.out.println(CommonMethodUtils.matches("(?=&maent.pripid=)(.*)(?<=&)",
    // "http://gsxt.scaic.gov.cn/ztxy.do?method=qygsInfo&maent.pripid=CD6675400159&czmk=czmk8&random=1469630731260"));
    // System.out.println(CommonMethodUtils.matchesValue("(?<=&maent.pripid=)(.*)(?=&)",
    // "http://gsxt.scaic.gov.cn/ztxy.do?method=qygsInfo&maent.pripid=CD6675400159&czmk=czmk8&random=1469630731260"));
    // System.out.println(BaseConstant.SeleniumWebDriverPath.PHANTOM_JS_PATH_LINUX.getValue());
    // System.out.println(stringToDataConverter("2016/08/17 09:59:13"));
    // System.out.println(randomSleepTime());
    // System.out.println(imageAnalytical("https://www.fosunling.com/image.jsp",
    // BaseConstant.ImageAnalyticalType.URL_PATH.getValue()));
    // String str =
    // "15812345131s15812345672a15812345133s15812345674a15812345135s15812345676a";
    // Pattern p = Pattern.compile("1[358]\\d{9}");
    // Matcher m = p.matcher(str);
    // while (m.find()) {
    // System.out.println(m.group());
    // // m.find();
    // }
    // buildAllReadyRequest(new CreepersLoginParamDTO());
    // System.out.println(BaseConstant.getLoginTaskListTypeList());
    // String str = "小明是个（， | （ｓ";
    // System.out.println(TranslateMethodUtil.fullToHalf(str));
    // imageAnalyticalBySupperManHttp("d:/image/20161012153126.png");
    // String url =
    // "http://daoyou-chaxun.cnta.gov.cn/single_info/selectlogin_1.asp";
    // System.err.println(url.substring(0,url.lastIndexOf("/")));
    // System.err.println("sql.date:" + new DATE());
    // System.err.println("java.util.date:" + new
    // java.util.Date().toString());
    // for (int i = 0; i < 10; i++) {
    // System.err.println((RandomUtils.nextInt(40) + 1));
    // }
    // System.err.println("result:"+CommonMethodUtils.unescape("%2565C5%25884C%25793E".replaceAll("%25",
    // "%")));;
    // System.err.println("旅行社
    // result:"+CommonMethodUtils.toUtf8StringWith("旅行社","%25"));;
    // System.err.println("导 游
    // result:"+CommonMethodUtils.toUtf8StringWith("导 游","%25"));;
    // List<Map<String, String>> allTaskList = BaseConstant.getAllDataTaskListTypeList();
    // for (Map<String, String> map : allTaskList) {
    // System.err.println("name:" + map.get("name"));
    // System.err.println("value:" + map.get("value"));
    // System.err.println("==========================");
    // }
    // for (int i = 0; i < 50; i++) {
    // System.err.println(getRandomUserAgent());
    // }
    // System.err.println("http://www.sda.gov.cn/wbpp/generalsearch?sort=true&sortId=CTIME&record=20&columnid=CLID|BCID&relation=MUST|MUST&tableName=Region&colNum=2&qryNum=2&curPage=1&qryidstr=CLID|BCID&qryValue=cl0369|0019&record=20&mytarget=~blank&dateFormat=yyyy%C4%EAMM%D4%C2dd%C8%D5&titleLength=-1&subTitleFlag=0&classStr=|-1|-1|ListColumnClass15|LawListSub15|listnew15|listtddate15|listmore15|distance15|classtab15|classtd15|pageTdSTR15|pageTdSTR15|pageTd15|pageTdF15|pageTdE15|pageETd15|pageTdGO15|pageTdGOTB15|pageGOButton15|pageDatespan15|pagestab15|pageGOText15&curPage=1"
    // .replaceAll("\\|", "%7C"));
    // System.out.println(matches("[\u4e00-\u9fa5]+","企业名称 (中文）"));
    // System.out.println(matchesValue("[\u4e00-\u9fa5]+","企业名称 (中文）"));
    System.out.println("企业名称 (中文）".replaceAll("[^\u4e00-\u9fa5]", ""));
}


public int randomSleepTime(){
    return (RandomUtils.nextInt(1000) + 10) * 10;
}


public Element getRootElement(String xml){
    try {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new StringReader(xml));
        Element root = doc.getRootElement();
        return root;
    } catch (DocumentException e) {
        e.printStackTrace();
        return null;
    }
}


public String getImageAnalyticalValueByChaoren(Map<SupperMan,String> imageMap){
    String resultValue = "";
    if (BaseConstant.SupperManType.SUCCEED.toString().equals(imageMap.get(BaseConstant.SupperMan.SUPPER_MAN_STATUS))) {
        resultValue = imageMap.get(BaseConstant.SupperMan.SUPPER_MAN_RESULT);
    } else {
        logger.info("====>>>验证码解析错误,返回码：" + imageMap.get(BaseConstant.SupperMan.SUPPER_MAN_STATUS));
        if (imageMap.size() > 0) {
            logger.info("====>>>验证码解析错误,返回信息中有有图片ID,调用报告错误接口" + imageMap.get(BaseConstant.SupperMan.SUPPER_MAN_STATUS));
            CommonMethodUtils.imageAnalyticalErrorHttp(imageMap);
        }
    }
    return resultValue;
}


@Override
public boolean isTrusted(java.security.cert.X509Certificate[] arg0,String arg1){
    return true;
}


public Request buildAllReadyRequest(CreepersParamDTO param,String method){
    Request request = buildAllReadyRequest(param);
    request.setMethod(method);
    return request;
}


public Map<String,String> beanToMap(Object obj,Map<String,String> map){
    if (map == null) {
        map = new HashMap<String, String>();
    }
    if (obj == null) {
        return new HashMap<String, String>();
    }
    map = BeanUtils.describe(obj);
    return map;
}


public void setByDT(BaseEntity entity){
    java.util.Date date = new java.util.Date();
    entity.setUpdatedBy(ADMIN);
    entity.setCreatedBy(ADMIN);
    entity.setUpdatedDt(date);
    entity.setCreatedDt(date);
}


public String toUtf8String(String s){
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c >= 0 && c <= 255) {
            sb.append(c);
        } else {
            byte[] b;
            try {
                b = String.valueOf(c).getBytes("utf-8");
            } catch (Exception ex) {
                System.out.println(ex);
                b = new byte[0];
            }
            for (int j = 0; j < b.length; j++) {
                int k = b[j];
                if (k < 0)
                    k += 256;
                sb.append("%" + Integer.toHexString(k).toUpperCase());
            }
        }
    }
    return sb.toString();
}


public Element getChildElementByXpath(Element root,String xpath){
    return ((List<Element>) getChildElementsByXpath(root, xpath)).get(0);
}


@Deprecated
public String imageAnalytical(String path,String type,String typeUrl){
    String apiUrl = "http://lab.ocrking.com/ok.html";
    // String apiUrl = "http://39.77.20.53:6080/ok.html"; // 备用api
    String apiKey = "0a09ebda8a9d33849hELjXXWv04pnNXwF3DrxD2UYroeApaOBctTnW";
    Map<String, String> dataMap = new HashMap<String, String>();
    // you need to modify parameters according to OcrKing Api Document
    dataMap.put("service", "OcrKingForCaptcha");
    dataMap.put("language", "eng");
    dataMap.put("charset", "7");
    // 如果不传递原始url到type或乱传一个地址到type 结果很可能就是错的
    // 如果想禁止后台做任何预处理 type可以设为 http://www.nopreprocess.com
    // 如果确实不确定验证码图片的原url type可以设为 http://www.unknown.com 此时后台只进行常用预处理
    // 此demo中type值只针对此demo中的图片，其它网站图片请不要用此值
    if (StringUtil.isBlank(typeUrl)) {
        typeUrl = "http://www.unknown.com";
    }
    dataMap.put("type", typeUrl);
    if (BaseConstant.ImageAnalyticalType.URL_PATH.getValue().equals(type)) {
        dataMap.put("url", path);
    }
    dataMap.put("apiKey", apiKey);
    Map<String, String> fileMap = new HashMap<String, String>();
    if (BaseConstant.ImageAnalyticalType.FILE_PATH.getValue().equals(type)) {
        fileMap.put("ocrfile", path);
    }
    String ret = OcrKing.postMultipart(apiUrl, dataMap, fileMap);
    return ret;
}


@SuppressWarnings("rawtypes")
public boolean isEmpty(Object source){
    if (source != null) {
        if (source instanceof List && ((List) source).size() > 0) {
            return false;
        } else if (source instanceof Map && ((Map) source).size() > 0) {
            return false;
        }
    }
    return true;
}


public String unescape(String s){
    StringBuffer sbuf = new StringBuffer();
    int l = s.length();
    int ch = -1;
    int b, sumb = 0;
    for (int i = 0, more = -1; i < l; i++) {
        /* Get next byte b from URL segment s */
        switch(ch = s.charAt(i)) {
            case '%':
                ch = s.charAt(++i);
                int hb = (Character.isDigit((char) ch) ? ch - '0' : 10 + Character.toLowerCase((char) ch) - 'a') & 0xF;
                ch = s.charAt(++i);
                int lb = (Character.isDigit((char) ch) ? ch - '0' : 10 + Character.toLowerCase((char) ch) - 'a') & 0xF;
                b = (hb << 4) | lb;
                break;
            case '+':
                b = ' ';
                break;
            default:
                b = ch;
        }
        /* Decode byte b as UTF-8, sumb collects incomplete chars */
        if ((b & 0xc0) == 0x80) {
            // 10xxxxxx (continuation byte)
            // Add 6 bits to sumb
            sumb = (sumb << 6) | (b & 0x3f);
            if (--more == 0)
                // Add char to sbuf
                sbuf.append((char) sumb);
        } else if ((b & 0x80) == 0x00) {
            // 0xxxxxxx (yields 7 bits)
            // Store in sbuf
            sbuf.append((char) b);
        } else if ((b & 0xe0) == 0xc0) {
            // 110xxxxx (yields 5 bits)
            sumb = b & 0x1f;
            // Expect 1 more byte
            more = 1;
        } else if ((b & 0xf0) == 0xe0) {
            // 1110xxxx (yields 4 bits)
            sumb = b & 0x0f;
            // Expect 2 more bytes
            more = 2;
        } else if ((b & 0xf8) == 0xf0) {
            // 11110xxx (yields 3 bits)
            sumb = b & 0x07;
            // Expect 3 more bytes
            more = 3;
        } else if ((b & 0xfc) == 0xf8) {
            // 111110xx (yields 2 bits)
            sumb = b & 0x03;
            // Expect 4 more bytes
            more = 4;
        } else /* if ((b & 0xfe) == 0xfc) */
        {
            // 1111110x (yields 1 bit)
            sumb = b & 0x01;
            // Expect 5 more bytes
            more = 5;
        }
    /* We don't test if the UTF-8 encoding is well-formed */
    }
    return sbuf.toString();
}


public String unescape25(String string){
    return unescape(string.replaceAll("%25", "%"));
}


public Element getChildElement(Element root,String nodeName,int orderNo){
    return (Element) root.elements(nodeName).get(orderNo);
}


public Request buildCaptchaRequest(CreepersParamDTO param){
    if (StringUtils.isBlank(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL))) {
        return null;
    }
    Request request = new Request();
    request.setMethod(HttpConstant.Method.POST);
    request.setUrl(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL));
    Map<String, String> nameValuePairMap = param.getNameValuePair();
    NameValuePair[] valuePairs = new NameValuePair[nameValuePairMap.size()];
    Set<Entry<String, String>> entrySet = nameValuePairMap.entrySet();
    int index = 0;
    for (Entry<String, String> entry : entrySet) {
        valuePairs[index] = new BasicNameValuePair(entry.getKey(), entry.getValue());
        index++;
    }
    request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, valuePairs);
    return request;
}


public Request checkNeedRecycle(int triedTimes,Request request){
    if (triedTimes > 0) {
        return request.setPriority(triedTimes).putExtra(Request.CYCLE_TRIED_TIMES, triedTimes);
    } else {
        return request;
    }
}


public boolean matches(String pattern,String source){
    if (pattern == null) {
        throw new IllegalArgumentException("pattern argument cannot be null.");
    }
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(source);
    return m.matches();
}


public String matchesChineseValue(String source){
    return source.replaceAll("[^\u4e00-\u9fa5]", "");
}


public String getNodeValue(Element element,String nodeName){
    return element.elementText(nodeName);
}


public void imageAnalyticalErrorFile(Map<SupperMan,String> map){
    qqchaoren.imageAnalyticalError(map);
}


public Request buildGetRequestCarryMap(String url){
    return buildGetRequestCarryMap(url, null);
}


public Map<SupperMan,String> imageAnalyticalBySupperManHttp(String fileName){
    Map<SupperMan, String> map = new HashMap<SupperMan, String>();
    try {
        BufferedImage image = ImageIO.read(new File(fileName));
        // 读取1.gif并传输
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        boolean flag = ImageIO.write(image, "jpg", out);
        if (flag) {
            System.out.println("图片文件读取成功！");
        }
        byte[] imageData = out.toByteArray();
        String imageString = TranslateMethodUtil.supperManByteToString(imageData);
        String responseContent = HttpClientUtil.supperManPost(imageString);
        JSONObject jsonObj = (JSONObject) JSON.parse(responseContent);
        String info = jsonObj.getString("info");
        map.put(BaseConstant.SupperMan.SUPPER_MAN_STATUS, info);
        String result = jsonObj.getString("result");
        map.put(BaseConstant.SupperMan.SUPPER_MAN_RESULT, result);
        String imgId = jsonObj.getString("imgId");
        map.put(BaseConstant.SupperMan.SUPPER_MAN_IMAGE_ID, imgId);
    } catch (IOException e) {
        System.err.println("读取图片失败");
        e.printStackTrace();
    }
    return map;
}


@SuppressWarnings("rawtypes")
public List<Map<String,Object>> entityList(Collection sourceList){
    List<Map<String, Object>> destinationList = new ArrayList<Map<String, Object>>();
    if (sourceList != null) {
        for (Iterator iterator = sourceList.iterator(); iterator.hasNext(); ) {
            Object sourceObject = iterator.next();
            Map<String, Object> resultObject = entityTransMap(sourceObject);
            destinationList.add(resultObject);
        }
    }
    return destinationList;
}


public String getConfigIni(){
    String system = System.getProperty(BaseConstant.SYSTEM_OS_NAME);
    if (StringUtil.isNotBlank(system)) {
        if (system.toUpperCase().contains(BaseConstant.SYSTEM_TYPE_WINDOWS)) {
            return BaseConstant.SeleniumWebDriverPath.CONFIG_INI_PATH.getValue();
        } else {
            return BaseConstant.SeleniumWebDriverPath.CONFIG_INI_PATH_LINUX.getValue();
        }
    }
    return null;
}


@SuppressWarnings("unchecked")
public List<Element> getChildElementsByXpath(Element root,String xpath){
    return root.selectNodes(xpath);
}


public void imageAnalyticalErrorHttp(String imageId){
    if (StringUtils.isBlank(imageId)) {
        logger.error("打码超人ImageID为空，不能调用积分返还！");
        return;
    }
    HttpClientUtil.supperManError(imageId);
}


public Specification<?> buildSpecification(Map<String,Object> searchParams,String dateFlag){
    Map<String, SearchFilter> filters = Maps.newHashMap();
    for (Entry<String, Object> entry : searchParams.entrySet()) {
        String key = entry.getKey();
        Object value = entry.getValue();
        if (StringUtils.isBlank((String) value)) {
            continue;
        }
        if (null != key && key.contains(dateFlag)) {
            value = stringToDataConverter((String) value);
        }
        String[] names = StringUtils.split(key, "_");
        if (names.length != 2) {
            throw new IllegalArgumentException(key + " is not a valid search filter name");
        }
        String filedName = names[1];
        Operator operator = Operator.valueOf(names[0]);
        SearchFilter filter = new SearchFilter(filedName, operator, value);
        filters.put(key, filter);
    }
    Specification<?> spec = DynamicSpecifications.bySearchFilter(filters.values(), T.class);
    return spec;
}


public Map<String,Object> entityTransMap(Object bean){
    return entityTransMap(bean, null);
}


public String getFirefoxWebDriver(){
    String system = System.getProperty(BaseConstant.SYSTEM_OS_NAME);
    if (StringUtil.isNotBlank(system)) {
        if (system.toUpperCase().contains(BaseConstant.SYSTEM_TYPE_WINDOWS)) {
            return BaseConstant.SeleniumWebDriverPath.FIREFOX_PATH.getValue();
        } else {
            return BaseConstant.SeleniumWebDriverPath.FIREFOX_PATH_LINUX.getValue();
        }
    }
    return null;
}


public String toUtf8StringWith(String s,String sp){
    return toUtf8String(s).replaceAll("%", sp);
}


public String getImageIdByChaoren(Map<SupperMan,String> imageMap){
    String resultValue = "";
    if (BaseConstant.SupperManType.SUCCEED.toString().equals(imageMap.get(BaseConstant.SupperMan.SUPPER_MAN_STATUS))) {
        resultValue = imageMap.get(BaseConstant.SupperMan.SUPPER_MAN_IMAGE_ID);
    } else {
        logger.info("====>>>验证码解析错误,返回码：" + imageMap.get(BaseConstant.SupperMan.SUPPER_MAN_STATUS));
        if (imageMap.size() > 0) {
            logger.info("====>>>验证码解析错误,返回信息中有有图片ID,调用报告错误接口" + imageMap.get(BaseConstant.SupperMan.SUPPER_MAN_STATUS));
            CommonMethodUtils.imageAnalyticalErrorHttp(imageMap);
        }
    }
    return resultValue;
}


}