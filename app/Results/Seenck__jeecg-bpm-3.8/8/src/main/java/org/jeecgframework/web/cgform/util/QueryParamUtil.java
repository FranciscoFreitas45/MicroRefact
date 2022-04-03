package org.jeecgframework.web.cgform.util;
 import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jeecgframework.core.util.DBTypeUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.common.CgAutoListConstant;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class QueryParamUtil {


public String applyType(String fieldType,String value){
    if (!StringUtil.isEmpty(value)) {
        String result = "";
        if (CgAutoListConstant.TYPE_STRING.equalsIgnoreCase(fieldType)) {
            // update-begin--Author:zzl  Date:20151123 for：加入配置属性可默认进行模糊查询
            // if(ResourceUtil.fuzzySearch&&(!value.contains("*"))){
            // value="*"+value+"*";
            // }
            // update-end--Author:zzl  Date:20151123 for：加入配置属性可默认进行模糊查询
            result = "'" + value + "'";
        } else if (CgAutoListConstant.TYPE_DATE.equalsIgnoreCase(fieldType)) {
            result = getDateFunction(value, "yyyy-MM-dd");
        } else if (CgAutoListConstant.TYPE_DOUBLE.equalsIgnoreCase(fieldType)) {
            result = value;
        } else if (CgAutoListConstant.TYPE_INTEGER.equalsIgnoreCase(fieldType)) {
            result = value;
        } else {
            result = value;
        }
        return result;
    } else {
        return "";
    }
}


@SuppressWarnings("unchecked")
public void loadQueryParams(HttpServletRequest request,CgFormFieldEntity b,Map params){
    // --add-begin--Author:钟世云  Date:20150614 for：online支持树配置
    if (CgAutoListConstant.BOOL_FALSE.equalsIgnoreCase(b.getIsQuery())) {
        return;
    }
    // --add-end--Author:钟世云  Date:20150614 for：online支持树配置
    if ("single".equals(b.getQueryMode())) {
        // 单条件组装方式
        // --update-begin--Author:dangzhenghui  Date:20170502 for：Online Excel导出数据bug，中文乱码问题----
        String value = request.getParameter(b.getFieldName());
        // --update-begin--Author:scott  Date:20180223 for：Online数据导出，单查询模式，默认背景值导致导出数据为空问题----
        if (StringUtil.isEmpty(value) || "请输入关键字".equals(value)) {
            // --update-end--Author:scott  Date:20180223 for：Online数据导出，单查询模式，默认背景值导致导出数据为空问题----
            return;
        }
        // update-begin--Author:dangzhenghui  Date:20170502 for：Online Excel导出数据bug，中文乱码问题----
        sql_inj_throw(value);
        value = applyType(b.getType(), value);
        if (!StringUtil.isEmpty(value)) {
            if (value.contains("*")) {
                // 模糊查询
                value = value.replaceAll("\\*", "%");
                params.put(b.getFieldName(), CgAutoListConstant.OP_LIKE + value);
            } else {
                params.put(b.getFieldName(), CgAutoListConstant.OP_EQ + value);
            }
        }
    } else if ("group".equals(b.getQueryMode())) {
        // 范围查询组装
        String begin = request.getParameter(b.getFieldName() + "_begin");
        sql_inj_throw(begin);
        begin = applyType(b.getType(), begin);
        String end = request.getParameter(b.getFieldName() + "_end");
        sql_inj_throw(end);
        end = applyType(b.getType(), end);
        if (!StringUtil.isEmpty(begin)) {
            String re = CgAutoListConstant.OP_RQ + begin;
            if (!StringUtil.isEmpty(end)) {
                re += " AND " + b.getFieldName() + CgAutoListConstant.OP_LQ + end;
            }
            params.put(b.getFieldName(), re);
        } else if (!StringUtil.isEmpty(end)) {
            String re = CgAutoListConstant.OP_LQ + end;
            params.put(b.getFieldName(), re);
        }
    }
}


public void sql_inj_throw(String str){
    if (sql_inj(str)) {
        throw new RuntimeException("请注意,填入的参数可能存在SQL注入!");
    }
}


public String getDateFunction(String dateStr,String dateFormat){
    String dbType = getDBType();
    String dateFunction = "";
    if ("mysql".equalsIgnoreCase(dbType)) {
        // mysql日期函数
        dateFunction = "'" + dateStr + "'";
    } else if ("oracle".equalsIgnoreCase(dbType)) {
        // oracle日期函数
        dateFunction = "TO_DATE('" + dateStr + "','" + dateFormat + "')";
    } else if ("sqlserver".equalsIgnoreCase(dbType)) {
        // sqlserver日期函数
        // update-begin author:taoYan date:20170727 for:sqlserver时间格式化----
        // dateFunction = "(CONVERT(VARCHAR,'"+dateStr+"') as DATETIME)";
        dateFunction = "CONVERT(VARCHAR,'" + dateStr + "',120)";
    // 120 或者 20	yyyy-mm-dd hh:mi:ss(24h)
    // update-end author:taoYan date:20170727 for:sqlserver时间格式化----
    } else if ("postgres".equalsIgnoreCase(dbType)) {
        // postgres日期函数
        dateFunction = "'" + dateStr + "'::date ";
    } else {
        dateFunction = dateStr;
    }
    return dateFunction;
}


public boolean sql_inj(String str){
    if (StringUtil.isEmpty(str)) {
        return false;
    }
    String inj_str = "'|and|exec|insert|select|delete|update|count|chr|mid|master|truncate|char|declare|;|or|+|,";
    // String inj_str = "'|and|exec|insert|select|delete|update|count|chr|mid|master|truncate|char|declare|;|or|-|+|,";
    String[] inj_stra = inj_str.split("\\|");
    for (int i = 0; i < inj_stra.length; i++) {
        if (str.indexOf(" " + inj_stra[i] + " ") >= 0) {
            return true;
        }
    }
    return false;
}


public String getDBType(){
    return DBTypeUtil.getDBType();
}


public String datatimeFormat(String datetime){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    SimpleDateFormat dateFormatTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date d = null;
    try {
        d = dateFormat.parse(datetime);
        return dateFormatTo.format(d);
    } catch (Exception e) {
        return datetime;
    }
}


@SuppressWarnings("unchecked")
public String getJson(List<Map<String,Object>> result){
    JSONArray rows = new JSONArray();
    for (Map m : result) {
        JSONObject item = new JSONObject();
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = String.valueOf(m.get(key));
            key = key.toLowerCase();
            if (key.contains("time") || key.contains("date")) {
                value = datatimeFormat(value);
            }
            item.put(key, value);
        }
        rows.add(item);
    }
    return rows.toString();
}


}