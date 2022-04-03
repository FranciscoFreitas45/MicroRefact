package org.jeecgframework.core.online.util;
 import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jeecgframework.core.online.def.CgReportConstant;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.StringUtil;
public class CgReportQueryParamUtil {


public void loadQueryParams(HttpServletRequest request,Map<String,Object> item,Map<String,Object> pageSearchFields,Map<String,Object> paramData){
    String filedName = (String) item.get(CgReportConstant.ITEM_FIELDNAME);
    String queryMode = (String) item.get(CgReportConstant.ITEM_QUERYMODE);
    String filedType = (String) item.get(CgReportConstant.ITEM_FIELDTYPE);
    if ("single".equals(queryMode)) {
        // 单条件组装方式
        // update-begin--Author:gj_shaojc  Date:20180425 for：TASK #2665 【问题确认】论坛问题
        String value = request.getParameter(filedName.toLowerCase());
        // update-end--Author:gj_shaojc  Date:20180425 for：TASK #2665 【问题确认】论坛问题
        try {
            if (StringUtil.isEmpty(value)) {
                return;
            }
            String uri = request.getQueryString();
            if (uri.contains(filedName + "=")) {
                String contiansChinesevalue = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                value = contiansChinesevalue;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        // update--begin--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
        // sql_inj_throw(value);
        // SqlInjectionUtil.filterContent(value);
        // update--end--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
        // update--begin--author:zhoujf Date:20180709 for:TASK #2759 【Online报表配置】oracle时间处理的一个bug
        // value = applyType(filedType,value);
        // update--end--author:zhoujf Date:20180709 for:TASK #2759 【Online报表配置】oracle时间处理的一个bug
        if (!StringUtil.isEmpty(value)) {
            if (value.contains("*")) {
                // 模糊查询
                value = value.replaceAll("\\*", "%");
                // params.put(filedName, CgReportConstant.OP_LIKE+value);
                pageSearchFields.put(filedName, CgReportConstant.OP_LIKE + ":" + filedName);
            } else {
                // params.put(filedName, CgReportConstant.OP_EQ+value);
                pageSearchFields.put(filedName, CgReportConstant.OP_EQ + ":" + filedName);
            }
        }
        // update--begin--author:zhoujf Date:20180709 for:TASK #2759 【Online报表配置】oracle时间处理的一个bug
        paramData.put(filedName, covertData(filedType, value, true));
    // update--end--author:zhoujf Date:20180709 for:TASK #2759 【Online报表配置】oracle时间处理的一个bug
    } else if ("group".equals(queryMode)) {
        // 范围查询组装
        // update--begin--author:zhoujf Date:20180709 for:TASK #2759 【Online报表配置】oracle时间处理的一个bug
        String begin = request.getParameter(filedName.toLowerCase() + "_begin");
        // update--end--author:zhoujf Date:20180709 for:TASK #2759 【Online报表配置】oracle时间处理的一个bug
        // update--begin--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
        // sql_inj_throw(begin);
        // SqlInjectionUtil.filterContent(begin);
        // update--end--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
        // begin= applyType(filedType,begin);
        // update--begin--author:zhoujf Date:20180709 for:TASK #2759 【Online报表配置】oracle时间处理的一个bug
        String end = request.getParameter(filedName.toLowerCase() + "_end");
        // update--end--author:zhoujf Date:20180709 for:TASK #2759 【Online报表配置】oracle时间处理的一个bug
        // update--begin--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
        // sql_inj_throw(end);
        // SqlInjectionUtil.filterContent(end);
        // update--end--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
        // end= applyType(filedType,end);
        // update--begin--author:zhoujf Date:20180709 for:TASK #2759 【Online报表配置】oracle时间处理的一个bug
        if (!StringUtil.isEmpty(begin)) {
            // String re = CgReportConstant.OP_RQ+begin;
            String re = CgReportConstant.OP_RQ + ":" + filedName + "_begin";
            pageSearchFields.put(filedName, re);
            paramData.put(filedName + "_begin", covertData(filedType, begin, true));
        }
        if (!StringUtil.isEmpty(end)) {
            // String re = CgReportConstant.OP_LQ+end;
            String re = CgReportConstant.OP_LQ + ":" + filedName + "_end";
            pageSearchFields.put(filedName, re);
            paramData.put(filedName + "_end", covertData(filedType, end, false));
        }
    // update--end--author:zhoujf Date:20180709 for:TASK #2759 【Online报表配置】oracle时间处理的一个bug
    }
}


public Object covertData(String fieldType,String value,boolean isBegin){
    Object obj = null;
    if (!StringUtil.isEmpty(value)) {
        if (CgReportConstant.TYPE_STRING.equalsIgnoreCase(fieldType)) {
            obj = value;
        } else if (CgReportConstant.TYPE_DATE.equalsIgnoreCase(fieldType)) {
            if (value.length() == 19) {
            } else if (value.length() == 10) {
                if (isBegin) {
                    value += " 00:00:00";
                } else {
                    value += " 23:59:59";
                }
            }
            SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            obj = DateUtils.str2Date(value, datetimeFormat);
        } else if (CgReportConstant.TYPE_DOUBLE.equalsIgnoreCase(fieldType)) {
            obj = value;
        } else if (CgReportConstant.TYPE_INTEGER.equalsIgnoreCase(fieldType)) {
            obj = value;
        } else {
            obj = value;
        }
    }
    return obj;
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