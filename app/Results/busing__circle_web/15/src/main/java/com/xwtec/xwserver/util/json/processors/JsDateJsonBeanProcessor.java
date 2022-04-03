package com.xwtec.xwserver.util.json.processors;
 import java.util.Calendar;
import java.util.Date;
import com.xwtec.xwserver.util.json.JSONObject;
import com.xwtec.xwserver.util.json.JsonConfig;
public class JsDateJsonBeanProcessor implements JsonBeanProcessor{


public JSONObject processBean(Object bean,JsonConfig jsonConfig){
    JSONObject jsonObject = null;
    if (bean instanceof java.sql.Date) {
        bean = new Date(((java.sql.Date) bean).getTime());
    }
    if (bean instanceof Date) {
        Calendar c = Calendar.getInstance();
        c.setTime((Date) bean);
        jsonObject = new JSONObject().element("year", c.get(Calendar.YEAR)).element("month", c.get(Calendar.MONTH)).element("day", c.get(Calendar.DAY_OF_MONTH)).element("hours", c.get(Calendar.HOUR_OF_DAY)).element("minutes", c.get(Calendar.MINUTE)).element("seconds", c.get(Calendar.SECOND)).element("milliseconds", c.get(Calendar.MILLISECOND));
    } else {
        jsonObject = new JSONObject(true);
    }
    return jsonObject;
}


}