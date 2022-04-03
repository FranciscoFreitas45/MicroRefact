package com.sobey.framework.utils;
 import org.apache.commons.lang3.StringUtils;
public class StringCommonUtils {


public String replaceAndSubstringText(String str,String symbol,String replaceSymbol){
    if (StringUtils.isNotBlank(str)) {
        String tempStr = str.substring(0, str.length() - 1);
        return StringUtils.replace(tempStr, symbol, replaceSymbol);
    }
    return null;
}


}