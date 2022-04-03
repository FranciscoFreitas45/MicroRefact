package org.danyuan.application.common.utils.tool;
 import java.math.BigDecimal;
import org.danyuan.application.common.utils.string.StringUtils;
public class ConvUtils {


public long convToLong(Object value){
    long longValue = 0L;
    if (value == null) {
        return longValue;
    } else {
        try {
            longValue = Long.parseLong(value.toString().trim().replace(",", ""));
        } catch (NumberFormatException ex) {
            return longValue;
        }
    }
    return longValue;
}


public String convToMoney(Object pValue){
    return String.format("%1$,.2f", convToDecimal(pValue));
}


public int convToInt(Object value){
    int intValue = 0;
    if (value == null) {
        return intValue;
    } else {
        try {
            intValue = Integer.parseInt(value.toString().trim().replace(",", ""));
        } catch (NumberFormatException ex) {
            return intValue;
        }
    }
    return intValue;
}


public String convToString(Object obj){
    if (obj == null) {
        return "";
    }
    if (StringUtils.isEmpty(obj.toString())) {
        return "";
    }
    return obj.toString();
}


public boolean convBitToBool(byte[] bytes){
    if (bytes == null) {
        return false;
    }
    return bytes[0] == 0x01 ? true : false;
}


public double convToDouble(Object pValue){
    double dblValue = 0D;
    if (pValue == null) {
        return dblValue;
    }
    try {
        dblValue = Double.parseDouble(String.valueOf(pValue).trim().replace(",", ""));
    } catch (Exception ex) {
        return dblValue;
    }
    return dblValue;
}


public boolean convToBool(Object obj){
    if (obj == null) {
        return false;
    }
    if (obj instanceof byte[]) {
        return convBitToBool((byte[]) obj);
    }
    if ("1".equals(obj.toString())) {
        return true;
    } else if ("0".equals(obj.toString())) {
        return false;
    } else if ("true".equalsIgnoreCase(obj.toString())) {
        return true;
    } else if ("false".equalsIgnoreCase(obj.toString())) {
        return false;
    }
    return false;
}


public BigDecimal convToDecimal(Object value){
    BigDecimal dec = new BigDecimal("0");
    if (value == null) {
        return dec;
    } else if (value instanceof BigDecimal) {
        dec = (BigDecimal) value;
    } else {
        try {
            dec = new BigDecimal(String.valueOf(value).trim().replace(",", ""));
        } catch (Exception ex) {
            return dec;
        }
    }
    return dec;
}


}