package com.fosun.fc.projects.creepers.utils;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.nlpcn.commons.lang.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
public class TranslateMethodUtil {


public String deleteFirstWord(String string){
    return string.substring(1);
}


public String checkStrIsBlank(String str){
    if (StringUtil.isBlank(str)) {
        return null;
    } else {
        return str;
    }
}


public Map<String,Object> pageMapToEqSearchMap(Map<String,Object> map){
    Set<Entry<String, Object>> entrySet = map.entrySet();
    Map<String, Object> targetMap = new HashMap<String, Object>();
    for (Entry<String, Object> entry : entrySet) {
        if (entry.getKey().contains("_")) {
            // 存在_下划线，说明已经用了对应的查询操作符
            targetMap.put(entry.getKey(), entry.getValue());
        } else {
            targetMap.put("EQ_" + entry.getKey(), entry.getValue());
        }
    }
    return targetMap;
}


public String fullToHalf(String fullStr){
    if (null == fullStr || fullStr.length() <= 0) {
        return "";
    }
    char[] charArray = fullStr.toCharArray();
    // 对全角字符转换的char数组遍历
    for (int i = 0; i < charArray.length; ++i) {
        int charIntValue = (int) charArray[i];
        // 如果符合转换关系,将对应下标之间减掉偏移量65248;如果是空格的话,直接做转换
        if (charIntValue >= 65281 && charIntValue <= 65374) {
            charArray[i] = (char) (charIntValue - 65248);
        } else if (charIntValue == 12288 || charIntValue == 160) {
            charArray[i] = (char) 32;
        }
    }
    return new String(charArray).trim();
}


public void addCommonParamMap(List<Map<String,String>> sourceList,Map<String,String> paramMap){
    for (Map<String, String> sourceMap : sourceList) {
        addCommonParamMap(sourceMap, paramMap);
    }
}


public String firstUpperOtherLower(String str){
    return firstWordToUpper(toLower(str));
}


public String toLower(String temp){
    if (checkStrIsBlank(temp) == null)
        return "";
    temp = temp.toLowerCase();
    while (temp.contains("_")) {
        int index = temp.indexOf("_");
        temp = temp.substring(0, index) + temp.substring(index + 1, index + 2).toUpperCase() + temp.substring(index + 2);
    }
    return temp;
}


public String supperManByteToString(byte[] bytes){
    StringBuffer result = new StringBuffer();
    String hex = "";
    for (int i = 0; i < bytes.length; i++) {
        // 字节高4位
        hex = String.valueOf(BaseConstant.HEXSTR.charAt((bytes[i] & 0xF0) >> 4));
        // 字节低4位
        hex += String.valueOf(BaseConstant.HEXSTR.charAt(bytes[i] & 0x0F));
        result.append(hex);
    }
    return result.toString();
}


public void main(String[] args){
    // System.out.println(deleteFileSuffix("file.txt"));
    // System.out.println(fullToHalf("13788996531 "));
    // System.out.println(fullToHalf("13788996531 ").length());
    // char[] cha = { ' ' };
    // System.out.println((int) cha[0]);
    List<String> list = new ArrayList<String>();
    list.add("GUIDE_NO");
    list.add("SEX");
    list.add("QULIFY_NO");
    list.add("GUIDE_LEVEL");
    list.add("CARD_NO");
    list.add("EDUCATION");
    list.add("CERT_NO");
    list.add("LAN");
    list.add("AREA");
    list.add("PEOPLE");
    list.add("CERT_DT");
    list.add("SCORE");
    list.add("PUBLISH_DT");
    list.add("PUBLISH_TYPE");
    list.add("AGENT_NAME");
    list.add("PHONE");
    list.add("MEMO");
    for (String string : list) {
        System.err.println(toLower(string));
    }
}


public void addCommonParamMapMedical(List<Map<String,String>> sourceList,Map<String,String> paramMap){
    List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
    for (Map<String, String> sourceMap : sourceList) {
        String keyValue = sourceMap.get(CreepersConstant.TCreepersMedicalColumn.KEY.getValue());
        sourceMap.remove(CreepersConstant.TCreepersMedicalColumn.KEY.getValue());
        List<Map<String, String>> contentList = new ArrayList<Map<String, String>>();
        contentList.add(sourceMap);
        Map<String, String> entityMap = new HashMap<String, String>();
        entityMap.put(CreepersConstant.TCreepersMedicalColumn.KEY.getValue(), keyValue);
        entityMap.putAll(paramMap);
        entityMap.put(CreepersConstant.TCreepersMedicalColumn.CONTENT.getValue(), JSONObject.toJSONString(contentList, BaseConstant.features));
        resultList.add(entityMap);
    }
    sourceList.clear();
    sourceList.addAll(resultList);
}


public String halfToFull(String halfStr){
    if (null == halfStr || halfStr.length() <= 0) {
        return "";
    }
    char[] charArray = halfStr.toCharArray();
    // 对半角字符转换的char数组遍历
    for (int i = 0; i < charArray.length; ++i) {
        int charIntValue = (int) charArray[i];
        // 如果符合转换关系,将对应下标之间增加偏移量65248;如果是空格的话,直接做转换
        if (charIntValue >= 33 && charIntValue <= 126) {
            charArray[i] = (char) (charIntValue + 65248);
        } else if (charIntValue == 32) {
            charArray[i] = (char) 12288;
        }
    }
    return new String(charArray).trim();
}


public String buildPageSearchParam(String key,String value,String prefix){
    StringBuffer result = new StringBuffer();
    if (StringUtils.isNotBlank(prefix))
        result.append(prefix);
    result.append(key).append("=").append(value);
    return result.toString();
}


public Map<String,String> buildPageSearchParamMap(String key,String value,String prefix){
    Map<String, String> map = new HashMap<String, String>();
    StringBuffer result = new StringBuffer();
    if (StringUtils.isNotBlank(prefix))
        result.append(prefix);
    result.append(key).append("=").append(value);
    map.put(BaseConstant.PAGE_SEARCH_PARAMS, result.toString());
    return map;
}


public String deleteFileSuffix(String filePath){
    if (checkStrIsBlank(filePath) == null) {
        return "";
    } else if (filePath.indexOf(".") == -1) {
        return filePath;
    } else {
        return filePath.substring(0, filePath.indexOf("."));
    }
}


public String firstWordToUpper(String str){
    if (checkStrIsBlank(str) == null) {
        return "";
    }
    return str.substring(0, 1).toUpperCase() + str.substring(1);
}


public String firstWordToLower(String str){
    if (checkStrIsBlank(str) == null) {
        return "";
    }
    return str.substring(0, 1).toLowerCase() + str.substring(1);
}


}