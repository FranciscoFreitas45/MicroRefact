package com.xwtec.xwserver.util.json.processors;
 import java.math.BigDecimal;
import java.math.BigInteger;
import com.xwtec.xwserver.util.json.JSON;
import com.xwtec.xwserver.util.json.JSONFunction;
import com.xwtec.xwserver.util.json.JSONNull;
import com.xwtec.xwserver.util.json.JSONString;
public class JsonVerifier {


public boolean isValidJsonValue(Object value){
    if (JSONNull.getInstance().equals(value) || value instanceof JSON || value instanceof Boolean || value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long || value instanceof Double || value instanceof BigInteger || value instanceof BigDecimal || value instanceof JSONFunction || value instanceof JSONString || value instanceof String) {
        return true;
    }
    return false;
}


}