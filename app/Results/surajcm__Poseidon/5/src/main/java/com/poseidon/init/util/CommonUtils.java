package com.poseidon.init.util;
 public class CommonUtils {

private CommonUtils() {
}
public String sanitizedString(String unFormatted){
    String sanitized = null;
    if (unFormatted != null) {
        sanitized = unFormatted.replaceAll("[\n\r\t]", "_");
    }
    return sanitized;
}


}