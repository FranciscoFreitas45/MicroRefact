package com.uec.imonitor.common.base;
 import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.uec.imonitor.common.datatables.DataTablesRequestEntity;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.log.ErrorCodeManager;
import com.uec.imonitor.DTO.DataTablesRequestEntity;
@Scope("prototype")
public class BaseController {

 private Log log;

 public  HttpServletRequest request;

 public  HttpServletResponse response;

 public  HttpSession session;

 private  String ERROR_MSG;

// 从application.properties中读取配置，若获取不到默认为“zh_CN”
@Value("${inews.i18n:zh_CN}")
 private  String i18n;


// 绑定请求参数到命令对象
@ModelAttribute
public void setRequestParams(HttpServletRequest request,HttpServletResponse response,HttpSession session){
    this.request = request;
    this.response = response;
    this.session = session;
}


public String getOrderType(DataTablesRequestEntity aoData){
    if (null != aoData) {
        return aoData.getsSortDir_0();
    }
    return "desc";
}


public String getOrderValue(DataTablesRequestEntity aoData){
    String orderValue = null;
    // 获取排序的列序号
    int iSortCol = aoData.getiSortCol_0();
    // String orderType = aoData.getsSortDir_0();//获取排序的对应列序号的规则:正序(asc)or倒序(desc)
    String getter = "getmDataProp_" + iSortCol;
    try {
        Method method = aoData.getClass().getMethod(getter, new Class[] {});
        Object value = method.invoke(aoData, new Object[] {});
        if (null != value) {
            orderValue = (String) value;
        }
    } catch (Exception e) {
        log.error("get the OrderType failed.");
        log.error(e);
    }
    // if(StringUtils.isEmpty(orderValue)){
    // orderValue = Constant.ORDER_VALUE_DEFAULT_DB;
    // }
    return orderValue;
}


public ModelMap getModelMap(boolean result,Object resultObj,String errorCode,String errorMsg){
    ModelMap map = new ModelMap();
    map.put("result", result);
    map.put("resultObj", resultObj);
    map.put("errorCode", errorCode);
    map.put("errorMsg", errorMsg);
    return map;
}


}